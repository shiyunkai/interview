package com.interview.sort;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/22 15:35
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        SortAlgorithm.printArray(SortAlgorithm.bubbleSortAsc(new int[]{2, 1, -3, 4, 2, 3, 5, 6, 7, 101, 87}));
        SortAlgorithm.printArray(SortAlgorithm.bubbleSortDesc(new int[]{2, 1, -3, 4, 2, 3, 5, 6, 7, 101, 87}));
        SortAlgorithm.printArray(SortAlgorithm.selectSort(new int[]{2, 1, -3, 4, 2, 3, 5, 6, 7, 101, 87}));
        SortAlgorithm.printArray(SortAlgorithm.insertSort(new int[]{2, 1, -3, 4, 2, 3, 5, 6, 7, 101, 87}));
        SortAlgorithm.printArray(SortAlgorithm.insertSort2(new int[]{8, 2, 5, 9, 7, 10, 1, 15, 12, 3}));
        System.out.println("shell sort:");
        SortAlgorithm.printArray(SortAlgorithm.shellSort(new int[]{2, 1, -3, 4, 2, 3, 5, 6, 7, 101, 87}));
        SortAlgorithm.printArray(SortAlgorithm.shellSort(new int[]{8, 2, -3, -2, 1500, 0, 5, 4, 9, 300, 7, 7, 10, 1, 15, -50, 12, 101, 200, 3}));

    }
}
