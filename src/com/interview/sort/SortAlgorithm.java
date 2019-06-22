package com.interview.sort;

import sun.security.util.Length;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/22 15:35
 * @Description:
 */
public class SortAlgorithm {

    /**
     * 冒泡排序-升序方式
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSortAsc(int[] arr) {
        int len = arr.length - 1;
        for (int i = len; i >= 0; i--) {
            boolean isSort = true;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
        return arr;

    }

    /**
     * 冒泡排序-降序方式
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSortDesc(int[] arr) {
        int len = arr.length - 1;
        for (int i = len; i >= 0; i--) {
            boolean isSort = true;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
        return arr;

    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static int[] selectSort(int[] arr) {
        int len = arr.length;
        for (int i = len - 1; i > 0; i--) {
            // 最大元素的下标
            int max = i;
            // 找到未排序序列中最大元素的下标
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[max]) {
                    max = j;
                }
            }
            if (max != i) {
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
            }
        }
        return arr;
    }

    /**
     *  插入排序
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int[] insertSort2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int value = arr[i];
            int j = 0;//插入的位置
            for (j = i-1; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j+1] = arr[j];//移动数据
                } else {
                    break;
                }
            }
            arr[j+1] = value; //插入数据
        }
        return arr;
    }

    /**
     * 打印排序好的数组
     *
     * @param arr
     */
    public static void printArray(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}
