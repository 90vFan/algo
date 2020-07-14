import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 从个位开始，对数组arr按"指数"进行排序
        for (int base = 1; max / base > 0; base *= 10) {
            countingSort(arr, base);
        }
    }

    // 计数排序-对数组按照"某个位数"进行排序
    public static void countingSort(int[] arr, int base) {
        if (arr.length <= 1) {
            return;
        }

        // 计算每个元素的个数
        int[] countArr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int value = (arr[i] / base) % 10;
            countArr[value]++;
        }
        System.out.println("Counter: " + Arrays.toString(countArr));

        // 计算排序后的位置
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }
        System.out.println("Accumulater: " + Arrays.toString(countArr));

        // 临时数组, 存储排序之后的结果
        int[] tempArr = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = (countArr[i] / base) % 10;
            int idx = value - 1;
            tempArr[idx] = arr[i];
            countArr[value]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = countArr[i];
        }
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 52, 43, 32, 56, 93, 29, 19};
        System.out.println("Origin: " + Arrays.toString(arr1));
        radixSort(arr1);
        System.out.println("Sorted: " + Arrays.toString(arr1));
    }
}
