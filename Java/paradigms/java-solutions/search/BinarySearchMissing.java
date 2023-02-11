package search;

public class BinarySearchMissing {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length-1];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(args[i + 1]);
        }
        System.out.println(recursiveBinarySearch(x, a, -1, a.length));
    }

    //Inv:  a[l] > x && a[r] <= x && l < r && a[-1] = +бесконечность && a[a.length] = -бесконечность
    //Pre:  для любого i: a[i] >= a[i+1] && x - целое число && l < r
    //Post: (Result = r: a[l] > x && a[r] <= x && l = r-1) || (Result = -r-1: x нет в массиве)
    public static int iterativeBinarySearch(int x, int[] a) {
        int l = -1;
        int r = a.length;
        // l = -1 && r = a.length && a[l] = +бесконечность && a[r] = -бесконечность && a[l] > x && a[r] <= x
        while (l < r - 1) {
            // a[l] > x && a[r] <= x && l < r-1
            int m = l + (r - l) / 2;
            // a[l] > x && a[r] <= x && l < m < r
            if (a[m] > x) {
                //a[l] > x && a[r] <= x && l > r-1 && l < m < r && a[m] > x
                l = m;
                //a[l] > x && a[r] <= x && l = m && l' < l < r
            } else {
                //a[l] > x && a[r] <= x && (r-l) > 1 && l < m < r && a[m] <= x
                r = m;
                //a[l] > x && a[r] <= x && r = m && l < r < r'
            }
        }
        //a[l] > x && a[r] <= x && l = r-1
        if (r != a.length && a[r] == x) {
            // r != a.length && a[r] = x && a[l] > x && a[r] <= x && l = r-1
            return r;
            // R = r: a[l] > x && a[r] <= x && l = r-1
        } else {
            // (r = a.length && a[r] = -бесконечность && a[l] > x && a[r] < x && l = r-1 && x в массиве нет) ||
            //            (r != a.length && a[r] != x && a[l] > x && a[r] < x && l = r-1 && x в массиве нет)
            return -r - 1;
            // R = -r-1: x в массиве нет
        }
    }
    // (Result == r: a[l] > x && a[r] <= x && l = r-1) || (Result == -r-1: x в массиве нет)


    //Inv:  a[l] > x && a[r] <= x && l < r && a[-1] = +бесконечнось && a[a.length] = -бесконечность
    //Pre:  для любого i: a[i] >= a[i+1] && l < r && a[l] > x && a[r] <= x && x - целое число && l [-1..a.length-1] && r [0..a.length]
    //Post: (Result = r: a[l] > x && a[r] <= x && l = r-1) || (Result = -r-1: x нет в массиве)
    public static int recursiveBinarySearch(int x, int[] a, int l, int r)  {
        // l = -1 && r = a.length && a[l] = +бесконечность && a[r] = -бесконечность && a[l] > x && a[r] <= x
        if (l < r - 1) {
            //a[l] > x && a[r] <= x && l < r-1
            int m = l + (r - l) / 2;
            //a[l] > x && a[r] <= x && l < r-1 && l < m < r
            if (a[m] > x) {
                //a[l] > x && a[r] <= x && l < r-1 && l < m < r && a[m] > x
                l = m;
                //a[l] > x && a[r] <= x && l == m && l' < l < r
            } else {
                //a[l] > x && a[r] <= x && l < r-1 && l < m < r && a[m] <= x
                r = m;
                //a[l] > x && a[r] <= x && l == m && l < r < r'
            }
            //a[l] > x && a[r] <= x && l' < l < r < r'
            return recursiveBinarySearch(x, a, l, r);
            //a[l] > x && a[r] <= x && l = r-1
        } else {
            //a[l] > x && a[r] <= x && l = r-1
            if (r != a.length && a[r] == x) {
                // r != a.length && a[r] = x && a[l] > x && a[r] <= x && l = r-1
                return r;
                // R = r: a[l] > x && a[r] <= x && l = r-1
            } else {
                // (r = a.length && a[r] = -бесконечность && a[l] > x && a[r] < x && l = r-1 && x в массиве нет) ||
                //            (r != a.length && a[r] != x && a[l] > x && a[r] < x && l = r-1 && x в массиве нет)
                return -r - 1;
                // R = -r-1: x в массиве нет
            }
        }
    }
    // (Result == r: a[l] > x && a[r] <= x && l = r-1) || (Result == -r-1: x в массиве нет)
}
