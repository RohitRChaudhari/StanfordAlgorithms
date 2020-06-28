package stanford.algo.course.one;

public class Karatsuba {
    String str1 = "3141592653589793238462643383279502884197169399375105820974944592";
    String str2 = "2718281828459045235360287471352662497757247093699959574966967627";

    String mult(String a, String b) {

        return "";
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        final Karatsuba karatsuba = new Karatsuba();
        System.out.println(karatsuba.addString(karatsuba.str1, karatsuba.str2));
    }

    String brute(String a, String b) {
        final char[] chars = str1.toCharArray();
        final char[] chars1 = str2.toCharArray();

        return "";
    }

    String addString(String a, String b) {
        final int[] x = stringToIntArray(a);
        final int[] y = stringToIntArray(b);
        return add(x, y).toString();
    }

    private int[] stringToIntArray(final String b) {
        final char[] d = b.toCharArray();
        int[] y = new int[d.length];
        for (int j = 0; j < d.length; j++) {
            y[j] = d[j] - '0';
        }
        return y;
    }

    int[] add(int[] a, int[] b) {
        int bs = b.length, as = a.length, cSize = Math.max(as, bs) + 1, i = as - 1, j = bs - 1, k = cSize - 1, carry = 0;
        int[] c = new int[cSize];
        while (true) {
            if (i >= 0 && j >= 0) {
                int t = b[j--] + a[i--] + carry;
                c[k--] = t / 10;
                carry = t % 10;
            }
            else if (j >= 0) {
                int t = b[j--] + carry;
                c[k--] = t / 10;
                carry = t % 10;
            }
            else if (i >= 0) {
                int t = a[i--] + carry;
                c[k--] = t / 10;
                carry = t % 10;
            }
            else if (carry == 1) {
                c[k--] = carry;
                break;
            }
        }
        return c;
    }

}
