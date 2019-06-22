package com.interview.adv;

import java.util.ArrayList;
import java.util.List;

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

        //double power = solution.Power(-2, -2);
        //double pow = Math.pow(-2, -2);
        //long ll = 10;
        //System.out.println(String.format("%f, %f, %d",power,pow,10));

        //List<String> a = null;
        //test(a);
        //System.out.println(a.size());

        //StringBuffer a = new StringBuffer("A");
        //StringBuffer b = new StringBuffer("B");
        //int i = 5;
        //operator(a,b,7);
        //System.out.println(a+", "+b+", "+i);
        /*
        int x = 0;
        int y = 10;
        do {
            y--;
            ++x;
        } while (x < 6);
        System.out.println(String.format("x=%d, y=%d", x, y));
        */
        ((NULL)null).haha();

        int[] arr = bubbleSort(new int[]{-5, 29, 7, 10, 5, 16});
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }


    }

    public static void test(List<String> a) {
        a = new ArrayList<String>();
        a.add("abc");
    }


    /**
     * 规律总结：
     * Java参数，不管是原始类型还是引用类型，传递的都是副本
     * 如果参数类型是原始类型，那么传过来的就是这个参数的一个副本，也就是这个原始参数的值，这个跟之前所谈的传值是一样的。如果在函数中改变了副本的值不会改变原始的值.
     * 如果参数类型是引用类型，那么传过来的就是这个引用参数的副本，这个副本存放的是参数的地址。如果在函数中没有改变这个副本的地址，而是通过地址改变改变了地址指向的值，那么在函数内的改变会影响到传入的参数。
     * 如果在函数中改变了副本的地址，如当执行如a＝其他对象，a＝new等赋值操作时，实际上是将a指向新的位置，那么函数外的原值不改变。
     */
    public static void operator(StringBuffer x, StringBuffer y, int j) {
        x.append(y);
        y = x;
        y.append("C");
        j = 7;
    }

    public static int[] bubbleSort(int[] arr){
        int len = arr.length - 1;
        for(int i = len; i>=0;i++){
            for(int j=0;j<len; j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}

class NULL {
    public static void haha(){
        System.out.println("haha");
    }
}
