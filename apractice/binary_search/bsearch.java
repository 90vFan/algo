
public class bsearch {
    public static int search(int[] a, int value)
    {
        int low = 0;
        int high = a.length - 1;

        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid -1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid-1] != value)) {
                    return mid;
                } else {
                    high = mid -1;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] a = {2, 3, 4, 5, 6, 6, 6, 7, 8, 9};
        int idx = search(a, 6);
        System.out.println("idx: " + idx);
    }
}
