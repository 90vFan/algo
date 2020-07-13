import java.util.Arrays;

// package sorts;

public class QuickSort {
    public static void quickSort(int[] arr, int n) {
        quickSortInternally(arr, 0, n - 1);
    }

    /**
     * 递归函数
     */
    public static void quickSortInternally(int[] arr, int leftmark, int rightmark) {
        if (p >= rightmark) return;

        int pivot = partition(arr, leftmark, rightmark);  // 获得区分点
        quickSortInternally(arr, leftmark, pivot-1);      // left half
        quickSortInternally(arr, pivot+1, rightmark);     // right half
    }

    private static int partition(int[] arr, int leftmark, int rightmark) {
        int pivot = arr[r];                            // pivot
        int i = leftmark;                              // i leftmark
        for (int j = leftmark; j < rightmark; ++j) {   // j 遍历 index
            if (arr[j] < pivot) {
                if (i == j) {
                    ++i;                               // 当前值小于 pivot, leftmart 右移1个位置
                } else {
                    int tmp = arr[i];                  // 当前值不小于 pivot, 交换
                    arr[i++] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        int tmp = arr[i];                              // 把基准值放到正确的位置
        arr[i] = arr[r];
        arr[r] = tmp;

        System.out.println("i=" + i + "     " + Arrays.toString(arr));
        return i;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 52, 43, 32, 56, 93, 29, 19};
        System.out.println("Origin: " + Arrays.toString(arr1));
        quickSort(arr1, arr1.length);
        System.out.println("Sorted: " + Arrays.toString(arr1));
    }
}
