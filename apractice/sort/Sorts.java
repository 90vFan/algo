import java.util.Arrays;

public class Sorts {
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n - 1; i++) {
            boolean flag = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (a[j+1] < a[j]) {
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                    flag = true; // 有数据交换
                }
            }
            if (!flag) return;  // 没有数据交换，提前退出
        }
    }

    /**
     * 冒泡排序改进：在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     *  对于边界外的元素在下次循环中无需比较.
     */
    public static void bubbleSort2(int[] a, int n) {
        if (n <= 1) return;

        int lastExchange = 0;
        int sortBorder = n - 1;
        for (int i = 0; i < n - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j + 1] = temp;
                    flag = true;
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if (!flag) return;
        }
    }

    /**
     * 插入排序
     */
    public static void selectionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }

    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n - 1; ++i) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            a[j+1] = value;
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{3, 4, 2, 1, 5, 7, 6, 4, 8};
        bubbleSort(array1, array1.length);
        System.out.println("bubbleSort: " + Arrays.toString(array1));

        int[] array2 = new int[] { 3, 4, 2, 1, 5, 7, 6, 4, 8 };
        bubbleSort2(array2, array2.length);
        System.out.println("bubbleSort2: " + Arrays.toString(array2));

        int[] array3 = new int[] { 3, 4, 2, 1, 5, 7, 6, 4, 8 };
        selectionSort(array3, array3.length);
        System.out.println("selectionSort: " + Arrays.toString(array3));

        int[] array4 = new int[]{3, 4, 2, 1, 5, 7, 6, 4, 8};
        insertionSort(array4, array4.length);
        System.out.println("insertionSort: " + Arrays.toString(array4));
    }
}