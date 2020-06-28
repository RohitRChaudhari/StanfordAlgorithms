package stanford.algo.course.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {
    public static int[] getArrayFromFile(String path) {
        try {
            return Files.lines(Paths.get(path)).mapToInt(Integer::parseInt).toArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNonDecreasing(int[] a) {
        return IntStream.range(1, a.length).parallel().noneMatch(i -> a[i - 1] > a[i]);
    }

    public static boolean isNonIncreasing(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] < a[i])
                return false;
        }
        return true;
    }

    public static void constructGraph(HashMap<Integer, ArrayList<Integer>> adjList,
                                      ArrayList<Integer> vertices) throws IOException {
        final File file = new File(String.valueOf(Paths.get("Code/resources/kargerMinCut.txt")));
        BufferedReader input = new BufferedReader(new FileReader(file));
        try {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                final ArrayList<Integer> edges = getIntegerArrayList(line.split("\t"));
                int key = edges.remove(0);
                vertices.add(key);
                edges.removeIf(value -> value == key);
                adjList.put(key, edges);
            }
        }
        finally {
            input.close();
        }
    }

    static ArrayList<Integer> getIntegerArrayList(String[] S) {
        return Arrays.stream(S).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
    }
}
