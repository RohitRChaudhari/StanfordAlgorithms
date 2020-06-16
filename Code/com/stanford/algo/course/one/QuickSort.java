package stanford.algo.course.one;

import java.util.Arrays;

import static stanford.algo.course.one.Utils.getArrayFromFile;
import static stanford.algo.course.one.Utils.isNonDecreasing;

public class QuickSort {
    static long count = 0;
    private static QuickSort quickSort = new QuickSort();

    /*1 162085
    2 164123
    3 138382
    */
    public static void main(String[] args) {
        testStanford();
        getCount();
    }

    private static void getCount() {
        System.out.println("count = " + count);
    }

    private static void testStanford() {
        final int[] a = getArrayFromFile("resources/QuickSort.txt");
        test(a);
    }

    private static void test(final int[] a) {
        quickSort.sort(a, 0, a.length - 1);
        System.out.println("isNonDecreasing(a) = " + isNonDecreasing(a));
    }


    private static void test2() {
        int[] a = new int[]{-74, 48, -20, 2, 10, -84, -5, -9, 11, -24, -91, 2, -71, 64, 63, 80, 28, -30, -58, -11, -44, -87, -22, 54, -74,
                    -10, -55, -28, -46, 29, 10, 50, -72, 34, 26, 25, 8, 51, 13, 30, 35, -8, 50, 65, -6, 16, -2, 21, -78, 35, -13, 14, 23, -3, 26, -90, 86, 25, -56, 91, -13, 92, -25, 37, 57, -20, -69, 98, 95, 45, 47, 29, 86, -28, 73, -44, -46, 65, -84, -96, -24, -12, 72, -68, 93, 57, 92, 52, -45, -2, 85, -63, 56, 55, 12, -85, 77, -39};

        test(a);
        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));
    }

    private static void test1() {
        int[] a = new int[]{9, 8, 45, 5, 1, 7};
        test(a);
        System.out.println("Arrays.toString(a) = " + Arrays.toString(a));
    }

    void sort(int[] a, int l, int r) {
        if (l >= r) return;
        int p = partition(a, l, r);
        sort(a, l, p - 1);
        sort(a, p + 1, r);
    }


    /**
     * @param target
     * @param low
     * @param high
     * @return returns true if target lies if low<=target<=high
     */
    public boolean getMedian(int target, int low, int high) {
        return (target <= low && target >= high) || (target >= low && target <= high);
    }

    private int partition(final int[] a, final int l, final int r) {
        if (l == r) return l;
        int m = l + (r - l) / 2;
        if (getMedian(a[m], a[l], a[r]))
            swap(a, l, m);
        else if (getMedian(a[r], a[l], a[m]))
            swap(a, r, l);
        return partitionLeftPivot(a, l, r);
    }




    /*private int partitionRightPivot(final int[] a, final int left, final int right) {
        if (left == right) return left;
        int pivot = a[right];
        int positionOfPivotInSortedArray = left;
        for (int curr = left; curr < right; curr++) {
            if (a[curr] < pivot) {
                swap(a, positionOfPivotInSortedArray, curr);
                positionOfPivotInSortedArray++;
            }
        }
        swap(a, right, positionOfPivotInSortedArray);
        count += (right - left);
        return positionOfPivotInSortedArray;
    }*/

    private int partitionRightPivot(final int[] a, final int left, final int right) {
        if (left == right) return left;
        swap(a, left, right);
        return partitionLeftPivot(a, left, right);
    }

    void swap(int[] a, int atIndex, int withIndex) {
        int temp = a[atIndex];
        a[atIndex] = a[withIndex];
        a[withIndex] = temp;
    }

    private int partitionLeftPivot(final int[] array, final int left, final int right) {
        if (left == right) return left;
        int pivot = array[left];
        int positionOfPivotInSortedArray = left + 1;
        for (int curr = left + 1; curr <= right; curr++) {
            if (array[curr] < pivot) {
                swap(array, positionOfPivotInSortedArray, curr);
                positionOfPivotInSortedArray++;
            }
        }
        swap(array, left, positionOfPivotInSortedArray - 1);
        count += (right - left);
        return positionOfPivotInSortedArray - 1;
    }

}
