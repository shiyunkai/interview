package com.interview.adv;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/18 11:27
 * @Description:
 */
public class ObjectReference {

    public static void main(String[] args) {
        String abc = new String("abc");
        SoftReference<String> softRef = new SoftReference<>(abc);
        WeakReference<String> weakRef = new WeakReference<>(abc);
        abc = null;
        softRef.clear();

        String property = System.getProperty("java.class.path");
        System.out.println(property);
    }
}
