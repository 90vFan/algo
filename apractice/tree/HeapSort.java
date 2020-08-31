import java.util.Arrays;
/**
 * 堆排序
 * 堆元素从数组下标0开始
 */
public class HeapSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        // 建堆
        buildHeap(arr);
        System.out.println("buildHeap: " + Arrays.toString(arr));
        // 排序
        int k = arr.length - 1;
        while (k > 0) {
            swap(arr, 0, k);
            heapify(arr, --k, 0);
            System.out.println("heapify:  " + Arrays.toString(arr));
        }
    }

    private static void buildHeap(int[] arr) {
        // (arr.length - 1)/2 为最后一个叶子节点的父节点
        // 从最后一个非叶子节点开始，依次堆化直到根节点
        for (int i = (arr.length -1) / 2; i >= 0; i--) {
            heapify(arr, arr.length - 1, i);
        }
    }

    /**
     * 堆化
     */
    private static void heapify(int[] arr, int n, int i) {
        while (true) {
            int maxPos = i;
            // 节点arr[i]与左节点arr[i*2 + 1]比较, 获得最大值位置
            if (i * 2 + 1 <= n && arr[i] < arr[i*2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 最大值与右子节点[i*2+2]比较，获取最大值位置
            if (i * 2 +2 <= n && arr[i] < arr[i*2 + 2]) {
                maxPos = i * 2 + 2;
            }
            // 最大值是当前位置结束循环
            if (maxPos == i) {
                break;
            }
            // 与子节点交换位置
            swap(arr, i, maxPos);
            // 以交换后子节点位置接着往下查找
            i = maxPos;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 2, 1, 5, 7, 6, 8};
        sort(arr);
        System.out.println("HeapSort: " + Arrays.toString(arr));
    }

}
