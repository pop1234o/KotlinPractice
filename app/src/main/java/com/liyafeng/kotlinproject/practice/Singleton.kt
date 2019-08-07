package com.liyafeng.kotlinproject.practice

//一个单例，实际上就是饿汉式的单例，在加载的时候创建一个示例，赋值给INSTANCE的静态final 变量
//一般工具类可以这么写，直接通过类名访问
object Singleton {
    var name: String? = null
    fun getValue() = 5
}