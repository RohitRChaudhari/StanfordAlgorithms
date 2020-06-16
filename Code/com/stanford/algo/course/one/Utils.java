package stanford.algo.course.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            if (a[i - 1] < a[i]) return false;
        }
        return true;
    }

}
