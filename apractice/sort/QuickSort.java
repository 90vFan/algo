import java.util.Arrays;

// package sorts;

public class QuickSort {
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    /**
     * 递归函数
     */
    public static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r);  // 获得区分点
        quickSortInternally(a, p, q-1);
        quickSortInternally(a, q+1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j = p; j < r; ++j) {
            if (a[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[r] = tmp;

        System.out.println("i=" + i + "     " + Arrays.toString(a));
        return i;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 52, 43, 32, 56, 93, 29, 19};
        System.out.println("Origin: " + Arrays.toString(arr1));
        quickSort(arr1, arr1.length);
        System.out.println("Sorted: " + Arrays.toString(arr1));
    }
}
