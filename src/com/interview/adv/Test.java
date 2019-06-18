package com.interview.adv;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/06/18 15:39
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("we are you");
        String strCopy = str.toString();
        System.out.println(str);
        String rep = "%20";
        List<Integer> indexs = new ArrayList<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == ' '){
                indexs.add(i);
            }
        }
        System.out.println(str);
    }
}
