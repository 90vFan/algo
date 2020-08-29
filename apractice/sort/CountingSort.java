import java.util.Arrays;
/**
 * 计数排序
 */
public class CountingSort {
    public static void countingsort(int[] arr, int n) {
        if (n <= 1) return;


        int max = arr[0];
        for (int i = 1; i < n; ++i) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // 申请一个新的计数数组，小标大小[0, max]
        int[] countArr = new int[max + 1];

        // 计算每个元素的个数，放入 c
        for (int i = 0; i < n; ++i) {
            int value = arr[i];
            countArr[value]++;              // 计数器
        }
        System.out.println("Counter: " + Arrays.toString(countArr));

        // 依次累加，value从小到大[1,max], 计数数组保存原数组值对应的排序后的index
        for (int v = 1; v < max + 1; ++v) {
            countArr[v] = countArr[v - 1] + countArr[v];
        }
        System.out.println("Accumulater: " + Arrays.toString(countArr));

        int[] tempArr = new int[n];
        // 从右往左遍历原数组
        for (int i = n - 1; i >= 0; --i) {
            int value = arr[i];               // 取原数组arr对应index的值value
            int index = countArr[value] - 1;  // 从计算数组得到value对应的index
            tempArr[index] = value;           // 保存当前value到tempArr[index]
            countArr[value]--;                // 计算数组中value对应的计数减1
        }

        // 拷贝会原数组
        for (int i = 0; i < n; i++) {
            arr[i] = tempArr[i];
        }

    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{3, 2, 52, 43, 32, 56, 93, 29, 19};
        System.out.println("Origin: " + Arrays.toString(arr1));
        countingsort(arr1, arr1.length);
        System.out.println("Sorted: " + Arrays.toString(arr1));
    }
}
