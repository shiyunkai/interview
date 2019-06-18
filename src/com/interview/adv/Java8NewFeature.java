package com.interview.adv;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/18 13:36
 * @Description:
 */
public class Java8NewFeature {

    public static void main(String[] args) {
        // foreach
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and time API");
        features.forEach(n-> System.out.println(n));
        System.out.println("------------方法引用--------------");

        // 方法引用
        features.forEach(System.out::println);
        System.out.println("--------使用 lambda 表达式和函数式接口 Predicate-----------");

        // 使用 lambda 表达式和函数式接口 Predicate
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J:");
        filter(languages,(str)->false);
    }

    public static void filter(List<String> names, Predicate condition){
        for(String name: names) {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }
}
