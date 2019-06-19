package com.interview.adv;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/18 15:39
 * @Description:
 */
public class Solution {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if(pre.length == 0 || in.length==0){
            return null;
        }
        // 根元素
        int rootVal = pre[0];
        // 找到中序遍历中的根结点位置
        int rootIndex = findInRootIndex(in, rootVal);
        // 分别找到左右子数的前序、中序遍历
        TreeNode rootNode = new TreeNode(rootVal);
        rootNode.left = reConstructBinaryTree(findPreOrder(rootIndex, pre, 0), findInOrder(rootIndex, in, 0));
        rootNode.right = reConstructBinaryTree(findPreOrder(rootIndex, pre, 1), findInOrder(rootIndex, in, 1));
        return rootNode;
    }

    public int findInRootIndex(int[] in, int rootVal) {
        for (int i = 0; i < in.length; i++) {
            if (in[i] == rootVal) {
                return i;
            }
        }
        return -1;
    }

    /*
     * type:0 查找的是左子数的中序遍历，否则为右子数的中序遍历
     */
    public int[] findInOrder(int rootIndex, int[] in, int type) {
        int[] childIn;
        if (type == 0) {
            //child left
            childIn = new int[rootIndex];
            for (int i = 0; i < rootIndex; i++) {
                childIn[i] = in[i];
            }
        } else {
            //child right
            childIn = new int[in.length - rootIndex - 1];
            for (int i = rootIndex + 1; i < in.length; i++) {
                childIn[i] = in[i];
            }
        }
        return childIn;

    }

    /*
     * type:0 查找的是左子数的前序遍历，否则为右子数的前序遍历
     */
    public int[] findPreOrder(int rootIndex, int[] pre, int type) {
        int[] childPre;
        if (type == 0) {
            childPre = new int[rootIndex];
            for (int i = 1; i <= rootIndex; i++) {
                childPre[i-1] = pre[i];
            }
        } else {
            childPre = new int[pre.length - rootIndex - 1];
            for (int i = rootIndex + 1; i <= pre.length; i++) {
                childPre[i-rootIndex-1] = pre[i];
            }
        }
        return childPre;
    }
}



