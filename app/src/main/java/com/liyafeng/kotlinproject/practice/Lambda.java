package com.liyafeng.kotlinproject.practice;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambda {

    /**
     * lambda是java8c出来的，其实就是简化函数定义，函数也可以作为参数传递
     *    http://www.runoob.com/java/java8-lambda-expressions.html
     *    http://www.importnew.com/16436.html (很详细的例子)
     *
     *
     *  lambda 表达式
     *      * https://juejin.im/entry/58a382da61ff4b0058ab4542
     *      * lambda 表达式其实就是函数的简化
     * ==========为什么要有lambda=====
     * 简化函数定义
     *
     * ============语法风格=======
     * (parameters) -> expression   表达式
     * 或
     * (parameters) ->{ statements; }   语句块
     *
     * (params) -> expression //只有一个表达式，默认是返回值
     * (params) -> statement
     * (params) -> { statements }
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public void a9_2() {
        /*
         * (params) -> expression //只有一个表达式，默认是返回值
         *  (params) -> statement
         *  (params) -> { statements }
         */
        //这种情况是 接口或者类只有一个方法，那么就不用写类名了
        new Thread(() -> System.out.println("")).start();

        //一个参数不用写圆括号
        new View(null).setOnClickListener(v -> {
            Object tag = v.getTag();
            System.out.println(tag);
        });
        //方法体只有一个表达式，不用return,不用花括号
        Collections.sort(new ArrayList<Integer>(), (o1, o2) -> o1 - o2);

        //有一个以上的表达式，需要花括号和return
        Collections.sort(new ArrayList<Integer>(), (o1, o2) -> {
            int i = o1 - o2;
            return i;
        });

        //数组遍历
        ArrayList<String> strings = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //foreach简化处理
            strings.forEach(s -> System.out.println(s));
            //自动引用参数，这个参数自动传入里面了
            strings.forEach(TextUtils::isEmpty);
        }

        //断言
        ArrayList<String> list = new ArrayList<>();
        filter(list, str -> str.length() > 0);
        filter(list, str -> str.startsWith("j"));


    }

    /**
     * lambda表达式
     */
    private void filter(ArrayList<String> list, Predicate<String> condition) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //过滤打印
            list.forEach(s -> {
                if (condition.test(s)) {
                    System.out.println(s);
                }
            });

            //流式处理,过滤操作，filter后是一个新的列表
            list.stream().filter(condition).forEach(System.out::print);

            //重新定义一个Predicate
            list.stream().filter(s -> s.length() > 1).forEach(System.out::print);

            //两个Predicate合并
            Predicate<String> stringPredicate = s -> s.length() > 1;
            list.stream().filter(stringPredicate.and(condition)).forEach(String::toCharArray);

            //map操作，使得数据流都经过Function处理
            list.stream().map(s -> s = s + "1").forEach(System.out::print);

            //reduce操作，减少/合并操作 ,将所有的string都相加并返回
            //每个元素都合并，合并后和其余的元素操作
            String s3 = list.stream().map(s -> s = s + "1").reduce((s1, s2) -> s1 + s2).get();
            System.out.println(s3);

            //返回过滤后的集合
            List<String> collect = list.stream().filter(s -> s.length() > 2).collect(Collectors.toList());
            collect.forEach(System.out::print);

            //Collectors 是将流中的元素经过处理移动到另一个对象中，
            String s1 = list.stream().map(s -> s + "1").collect(Collectors.joining(","));


            //distinct 去重 ，去除流中重复的数据
            List<String> strings = list.stream().distinct().collect(Collectors.toList());

            //::是只有一个参数，返回值，所以默认能识别
            //将string流转为int流
            IntSummaryStatistics statistics = list.stream().mapToInt(Integer::parseInt).summaryStatistics();
            double average = statistics.getAverage();
            int max = statistics.getMax();


        }

    }

}
