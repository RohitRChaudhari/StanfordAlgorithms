package stanford.algo.course.one;

import java.util.Arrays;

import static stanford.algo.course.one.Utils.getArrayFromFile;

public class InversionCount {

    public static void main(String[] args) {
        final InversionCount inversionCount = new InversionCount();
        int[] t = new int[]{1, 3, 5, 2, 4, 6};
        final long count = inversionCount.mergeSortCount(t, 0, t.length - 1);
        System.out.println(count);
        final int[] array = getArrayFromFile("resources/IntegerArray.txt");
        final long ans = inversionCount.mergeSortCount(array, 0, array.length - 1);
        System.out.println("ans = " + ans);
    }

    long mergeSortCount(int[] a, int l, int r) {
        long count = 0;
        if (l < r) {
            int m = (l + r) / 2;
            count += mergeSortCount(a, l, m);
            count += mergeSortCount(a, m + 1, r);
            count += mergeCount(a, l, m, r);
        }
        return count;
    }

    private long mergeCount(final int[] a, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(a, l, m + 1);// 1 3 5
        int[] right = Arrays.copyOfRange(a, m + 1, r + 1);// 2 4 6
        long count = 0;
        int i = 0, j = 0, k = l;
        //if j@r < i@l :: count  += |l|-i;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                a[k++] = left[i++];
            }
            else {
                a[k++] = right[j++];
                count += (m + 1 - l) - i;
            }
        }
        while (i == left.length && j < right.length) { a[k++] = right[j++]; }
        while (i < left.length && j == right.length) { a[k++] = left[i++]; }

        return count;
    }

}
