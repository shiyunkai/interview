package com.interview.adv;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/19 17:36
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        //solution.reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});
        //Solution.MyQueue myQueue = solution.new MyQueue();
        //myQueue.push(1);
        //myQueue.push(2);
        //myQueue.pop();
        //myQueue.push(3);
        //myQueue.push(4);
        //myQueue.pop();
        //long start = System.currentTimeMillis();
        //int result = solution.FibonacciRecursion(100);
        //long time = (System.currentTimeMillis()-start)/1000;
        //System.out.println(String.format("result:%d FibonacciRecursion:%d秒",result,time));
        //start = System.currentTimeMillis();
        //result = solution.FibonacciLoop(100);
        //time = (System.currentTimeMillis()-start)/1000;
        //System.out.println(String.format("result:%d FibonacciLoop:%d秒",result,time));

        //int count1 = solution.NumberOf1(-1);
        //System.out.println("二进制中1的个数为："+count1);
        //int count2 = solution.NumberOf2(-1);
        //System.out.println("二进制中1的个数为："+count2);
        //int count3 = solution.NumberOf3(7);
        //System.out.println("二进制中1的个数为："+count3);

        double power = solution.Power(-2, -2);
        double pow = Math.pow(-2, -2);
        long ll = 10;
        System.out.println(String.format("%f, %f, %d",power,pow,10));
    }
}
