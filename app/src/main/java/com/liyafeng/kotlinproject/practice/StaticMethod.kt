package com.liyafeng.kotlinproject.practice

class StaticMethod{

    //这样写法相当于java类中的静态变量
//    companion object {
//        const val name=""
//    }

    //这种写法效果同上 ，也是类中的静态变量
//    companion object {
//        @JvmField
//        val name=""
//    }

    //这种写法是伴生对象里有非静态变量
    companion object {
        val name=""

        /*
        *  StaticMethod.doSome();
        * StaticMethod.Companion.doSome()
        * 两种调用方法
        * 会在类中生成同名静态方法，里面调用 Companion.doSome()
        *
        * 静态对象写法 就只能是 object（对象和伴生对象）中写@JvmStatic了
        *
        * */
        @JvmStatic
        fun doSome()=3


    }

}
