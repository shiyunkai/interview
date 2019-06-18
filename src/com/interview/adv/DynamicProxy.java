package com.interview.adv;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/18 09:09
 * @Description: 动态代理
 */
public class DynamicProxy {

    public static void main(String[] args) {
        // 1. 写一个ArrayList的动态代理
        final List<String> list = new ArrayList<>();
        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(),
                list.getClass().getInterfaces(),
                (proxy,method,objects)->method.invoke(list,objects));

        proxyInstance.add("你好！");
        System.out.println(list);

    }
}
