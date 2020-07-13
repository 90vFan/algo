import java.util.Arrays;

/**
 * 桶排序算法
 */
public class BucketSort {
    public static int[][] bucketSort(int[] arr, int bucketSize) {
        if (arr.length < 2) {
            return null;
        }

        // get min/max value
        int minValue = arr[0];
        int maxValue = arr[1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            } else if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // 桶数量
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount];

        // 将数组中值分配到各个桶里, 按大小分桶
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minValue) / bucketSize;
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }

        // 对每个桶进行排序，这里使用了快速排序
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (indexArr[i] == 0) {
                continue;
            }
            quickSort(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }

        return buckets;
    }

    /**
     * 数组扩容
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        for (int j = 0; j < tempArr.length; j++) {
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }

    /**
     * 快速排序递归函数
     */
    private static void quickSort(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(arr, p, r);
        quickSort(arr, p, q - 1);
        quickSort(arr, q + 1, r);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int leftIndex = start;
        int rightIndex = end - 1;

        boolean done = false;
        while (!done) {
            while (leftIndex <= rightIndex && arr[leftIndex] <= pivot)
                leftIndex++;
            while (leftIndex <= rightIndex && arr[rightIndex] >= pivot)
                rightIndex--;

            if (rightIndex < leftIndex) {
                done = true;
            } else {
                swap(arr, leftIndex, rightIndex);
            }
        }

        swap(arr, leftIndex, end);

        return leftIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{54,26,93,17,77,31,44,55,20,44};
        System.out.println("Origin: " + Arrays.toString(arr1));
        // quickSort(arr1, 0, arr1.length-1);
        int[][] buckets = bucketSort(arr1, 3);
        System.out.println("Sorted: " + Arrays.toString(arr1));
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("Buckets: " + Arrays.toString(buckets[i]));
        }
    }
}
