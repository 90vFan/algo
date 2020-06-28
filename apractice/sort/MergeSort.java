import java.util.Arrays;


public class MergeSort {
    public static void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n-1);
    }

    private static void mergeSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = p + (r - p) / 2;
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q+1, r);

        // merge a[p...q] and a[q+1...r] as a[p...r]
        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;

        // temp arr: a[p...r]
        int[] tmp = new int[r-p+1];
        while (i<=q && j<=r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断哪个子数组有剩余数据
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余数据拷贝到 temp arr
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将 tmp 中的数组拷贝会 a[p...r]
        for (i = 0; i <= r-p; ++i) {
            a[p+i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 52, 43, 32, 56, 93, 29, 19};
        System.out.println("Origin: " + Arrays.toString(arr1));
        mergeSort(arr1, arr1.length);
        System.out.println("Sorted: " + Arrays.toString(arr1));
    }
}
