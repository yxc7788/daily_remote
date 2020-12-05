package algorithm;

import java.util.Arrays;

/**
 * @author yangxc27652
 * @date 2020/12/5
 * @description 多种排序算法的实现
 * https://www.cnblogs.com/ll409546297/p/10956960.html   https://www.cnblogs.com/guoyaohua/p/8600214.html
 */
public class MutiSort {

    /**
     * 冒泡排序
     */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //外层循环，遍历次数
            for (int j = 0; j < arr.length - i - 1; j++) {
                //内层循环，升序（如果前一个值比后一个值大，则交换）
                //内层循环一次，获取一个最大值
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 无论什么数据进去都是O(n2)的时间复杂度
     * @param arr
     */
    public void selectionSort (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //默认第一个是最小的。
            int min = arr[i];
            //记录最小的下标
            int index = i;
            //通过与后面的数据进行比较得出，最小值和下标
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            //然后将最小值与本次循环的，开始值交换
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;
            //说明：将i前面的数据看成一个排好的队列，i后面的看成一个无序队列。每次只需要找无需的最小值，做替换
        }
    }

    /**
     * 插入排序
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 希尔排序
     */
    public static int[] shellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }


    /**
     * 归并排序
     * 归并排序的性能不受输入数据的影响，但表现比选择排序好的多，因为始终都是O(n log n）的时间复杂度。代价是需要额外的内存空间。
     */

    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }
    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            }
            else if (j >= right.length) {
                result[index] = left[i++];
            }
            else if (left[i] > right[j]) {
                result[index] = right[j++];
            }
            else {
                result[index] = left[i++];
            }
        }
        return result;
    }


    /**
     * 快速排序
     */
    public static void quickSort(int[] arr, int low, int high) {
        // 如果指针在同一位置(只有一个数据时)，退出
        if (high - low < 1) {
            return;
        }
        // 标记，从高指针开始，还是低指针（默认高指针）
        boolean flag = true;
        // 记录指针的其实位置
        int start = low;
        int end = high;
        // 默认中间值为低指针的第一个值
        int midValue = arr[low];
        while (true) {
            // 高指针移动
            if (flag) {
                // 如果列表右方的数据大于中间值，则向左移动
                if (arr[high] > midValue) {
                    high--;
                } else if (arr[high] < midValue) {
                    // 如果小于，则覆盖最开始的低指针值，并且移动低指针，标志位改成从低指针开始移动
                    arr[low] = arr[high];
                    low++;
                    flag = false;
                }
            } else {
                // 如果低指针数据小于中间值，则低指针向右移动
                if (arr[low] < midValue) {
                    low++;
                } else if (arr[low] > midValue) {
                    // 如果低指针的值大于中间值，则覆盖高指针停留时的数据，并向左移动高指针。切换为高指针移动
                    arr[high] = arr[low];
                    high--;
                    flag = true;
                }
            }
            // 当两个指针的位置相同时，则找到了中间值的位置，并退出循环
            if (low == high) {
                arr[low] = midValue;
                break;
            }
        }
        // 然后出现有，中间值左边的小于中间值。右边的大于中间值。
        // 然后在对左右两边的列表在进行快速排序
        quickSort(arr, start, low -1);
        quickSort(arr, low + 1, end);
    }

}
