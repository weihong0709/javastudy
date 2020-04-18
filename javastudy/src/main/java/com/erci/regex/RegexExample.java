package com.erci.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void simpleRegexExample(){
        /**
         * 匹配字符串字面值
         */
        String regex = "Ship";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("Ship111");
        /**
         *matches是将整个输入串与模式匹配，find是查找输入串中与模式匹配的子串
         *(1)matches：整个匹配，只有整个字符序列完全匹配成功，才返回True，否则返回False,匹配成功时会移动下次匹配的位置到末尾
         *   这也是先调用matches方法，如果匹配成功，再调用find方法会导致匹配不到的原因，两个方法不要混用
         *(2)find：部分匹配，从当前位置开始匹配，找到一个匹配的子串，将移动下次匹配的位置
         *(3)find(int start）：重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。
         *(4)lookingAt:部分匹配，总是从第一个字符进行匹配,匹配成功了不再继续匹配，匹配失败了,也不继续匹配
         *(5)reset():要从头开始匹配，可以调用reset方法重置匹配位置
         *
         * 每次执行匹配操作后start()，end()，group()三个方法的值都会改变,改变成匹配到的子字符串的信息
         * 只有当匹配操作成功，才可以使用start()，end()，group()三个方法，否则会抛出java.lang.IllegalStateException，
         * 也就是当matches()，lookingAt()，find()其中任意一个方法返回 true 时，才可以使用。
         *
         */
        System.out.println("method matches:"+matcher.matches());
        System.out.println("method find:"+matcher.find());
        System.out.println("method lookingAt:"+matcher.lookingAt());

        matcher = pattern.matcher("Ship");
        System.out.println("--method find:"+matcher.find());
        System.out.println("--start:"+matcher.start());
        System.out.println("--method matches:"+matcher.matches());
        System.out.println("--start:"+matcher.start());
        System.out.println("--method find:"+matcher.find());
        System.out.println("--method lookingAt:"+matcher.lookingAt());
    }


}
