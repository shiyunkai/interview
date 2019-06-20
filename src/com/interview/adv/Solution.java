package com.interview.adv;

import java.util.Stack;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/18 15:39
 * @Description:
 */
public class Solution {

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public int FibonacciRecursion(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return FibonacciRecursion(n - 1) + FibonacciRecursion(n - 2);
    }

    public int FibonacciLoop(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int a = 1, b = 1, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }


    public class MyQueue {
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            int top = stack2.pop();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return top;
        }
    }


    /**
     * @param pre      前序遍历
     * @param startPre 前序遍历开始索引
     * @param endPre   前序遍历结束索引
     * @param in       中序遍历
     * @param startIn  中序遍历开始索引
     * @param endIn    中序遍历结束索引
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn) {
            return null;
        }

        TreeNode rootNode = new TreeNode(pre[startPre]);
        // 找到根元素在中序遍历中的索引
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                // 找到了，构建二叉树
                rootNode.left = reConstructBinaryTree(pre, startPre + 1, i - startIn + startPre, in, startIn, i - 1);
                rootNode.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        }
        return rootNode;
    }

    // 计算整数的二进制中1的个数，负数用补码表示
    public int NumberOf1(int n) {
        int count = 0;
        if (n >= 0) {
            while (n >> 1 != 0 || n % 2 == 1) {
                if (n % 2 == 1) {
                    count++;
                }
                n = n >> 1;
            }
        } else {
            // 计算补码
            for (int i = 0; i < 32; i++) {
                int t = (n & 0x80000000 >>> i) >>> (31 - i);
                if (t % 2 == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public int NumberOf2(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public int NumberOf3(int n) {

        int count = 0;
        String nStr = Integer.toBinaryString(n);
        for (int i = 0; i < nStr.length(); i++) {
            if (nStr.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    // 求base的 exponent次方
    public double Power(double base, int exponent) {
        if (base <= 0 || base == 1) {
            throw new RuntimeException("基数必须大于0，且不等于1");
        }
        if (exponent == 0) {
            return 1d;
        }
        double result = base;
        int j = (exponent < 0) ? -exponent : exponent;
        for (int i = 1; i < j; i++) {
            result *= base;
        }

        return exponent > 0 ? result : 1 / result;
    }


}
