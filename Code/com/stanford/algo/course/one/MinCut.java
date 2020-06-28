package stanford.algo.course.one;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/*
 *  Karger's algorithm to compute the minimum cut of a connected graph.
 *  Takes as input an undirected connected graph and computes a cut
 *  with the fewest number of crossing edges. We need to repeat the
 *  algorithm n(n-1)*(ln n) / 2 times to guarantee success.
 *  The probability of not finding the minimum cut is 1/n.
 *
 *
 *  Method that calculates the minimum cut by randomly choosing an edge,
 *  contracting the vertices making that edge, removing all the self loops
 *  until there are two vertices left in the graph.
 *  This is an efficient approach invented by MIT professor David Karger in 1993.
 */
public class MinCut {
    private static final Random rand = new Random();
    private static final int TWO = 2;        // Need to loop over the graph until there are only 2 vertices remain.


    // constructs n - 1 graphs and performs the min cut algorithm
    public static void main(String[] arg) throws IOException {
        MinCut mc = new MinCut();
        HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        ArrayList<Integer> vertices = new ArrayList<>();
        Utils.constructGraph(adjList, vertices);
        int absoluteMinimum = adjList.size() * adjList.size();
        for (int i = 0; i < adjList.size(); i++) {
            absoluteMinimum = Math.min(absoluteMinimum, mc.minCut(adjList, vertices));
            adjList.clear();
            vertices.clear();
            Utils.constructGraph(adjList, vertices);
        }
        System.out.println("\n MINIMAL CUT FOUND IS \n" + absoluteMinimum);
    }

    public int minCut(HashMap<Integer, ArrayList<Integer>> adjList,
                      ArrayList<Integer> vertices) {

        int label = getUniqueLabel(adjList.size());
        while (vertices.size() > TWO) {
            MC MC = new MC(adjList, vertices).getRandomEdge();
            mergeVertices(adjList, MC);
            removeB(adjList, vertices, MC.B);
            updateBothVertexWithNewLabel(adjList, vertices, label, MC.A, MC.B);
            updateALabel(adjList, vertices, label, MC.A);
            removeSelfLoops(adjList, label);
            label = getUniqueLabel(label);
        }
        return adjList.get(vertices.get(0)).size();
    }

    private int getUniqueLabel(int label) {
        // Increase the label by 1 so its uniqueness is preserved
        return ++label;
    }

    private void mergeVertices(final HashMap<Integer, ArrayList<Integer>> adjList, final MC MC) {
        adjList.get(MC.A).addAll(adjList.get(MC.B));
    }

    private void removeB(final HashMap<Integer, ArrayList<Integer>> adjList, final ArrayList<Integer> vertices, final int B) {
        vertices.remove(vertices.indexOf(B));
        adjList.remove(B);
    }

    private void updateBothVertexWithNewLabel(final HashMap<Integer, ArrayList<Integer>> adjList, final ArrayList<Integer> vertices, final int label, final int A, final int B) {
        // Give a new label to vertex1 and change all the labels of
        // of vertex1 or vertex2 to the new label
        for (Integer vertex : vertices) {
            ArrayList<Integer> values = adjList.get(vertex);
            for (int j = 0; j < values.size(); j++) {
                if (values.get(j) == A || values.get(j) == B)
                    values.set(j, label);
            }
        }
    }

    private void updateALabel(final HashMap<Integer, ArrayList<Integer>> adjList, final ArrayList<Integer> vertices, final int label, final int A) {
        // Change the label of the key node vertex1 in the adjList
        ArrayList<Integer> values = adjList.get(A);
        adjList.remove(A);
        adjList.put(label, values);
        // Change the label of vertex1 in the vertices ArrayList
        vertices.set(vertices.indexOf(A), label);
    }

    /**
     * @param adjList
     * @param label   Remove self loops by looping over the element of the new label
     *                and by deleting all edges outgoing to its own label.
     */
    private void removeSelfLoops(final HashMap<Integer, ArrayList<Integer>> adjList, final int label) {
        adjList.get(label).removeIf(vertex -> vertex == label);
    }

    public int getNoOfEdges(HashMap<Integer, ArrayList<Integer>> adjList, ArrayList<Integer> vertices) {
        return vertices.stream().mapToInt(vertex -> adjList.get(vertex).size()).sum();
    }

    public int[][] getEdges(int noOfEdges, HashMap<Integer, ArrayList<Integer>> adjList, ArrayList<Integer> vertices) {
        int k = 0;
        int[][] edges = new int[noOfEdges][2];
        for (Integer src : vertices) {
            for (Integer dest : adjList.get(src)) {
                edges[k][0] = src;
                edges[k++][1] = dest;
            }
        }
        return edges;
    }

    /*
     * The MC class is used to store the proposed space of solution woth two random nodes
     * chosen from Graph named as A and B as in the lecture
     */
    private class MC {
        private final HashMap<Integer, ArrayList<Integer>> adjList;
        private final ArrayList<Integer> vertices;
        public int A;
        public int B;

        public MC(final HashMap<Integer, ArrayList<Integer>> adjList, final ArrayList<Integer> vertices) {
            this.adjList = adjList;
            this.vertices = vertices;
        }

        public MC getRandomEdge() {
            int noOfEdges = getNoOfEdges(adjList, vertices);
            int[][] edges = getEdges(noOfEdges, adjList, vertices);
            int no = rand.nextInt(edges.length);
            A = edges[no][0];
            B = edges[no][1];
            return this;
        }
    }
}