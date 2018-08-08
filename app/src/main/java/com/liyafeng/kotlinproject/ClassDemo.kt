package com.liyafeng.kotlinproject

import android.content.Context
import android.view.View

//如果不声明，主构造函数中的参数是val类型的
// constructor 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面
// private修饰 构造函数可见性

//在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值
class ClassDemo private constructor(name: String, var count: Int) {

    var s = "this is $name"

    init {
        print(name.length)
        count = 5
//        print(s2)//这个时候s2还没有初始化
    }

    var s2 = "this is second$count";

    //如果有多个 初始化块 和属性 则他们都按照书写顺序来执行
    init {

        print(s2)

    }


    //次构造函数 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数

    constructor(num: Double = 0.0) : this("", 1) {
        print(num)

    }

    //通过别的次构造函数间接委托
    constructor(n: String) : this(1.0) {
        print(n)
    }
}


//在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类：
//这是一个空类
//注意：Any 并不是 java.lang.Object；尤其是，它除了 equals()、hashCode() 与 toString() 外没有任何成员
class Example


//open声明这个类可以被继承，默认情况下，在 Kotlin 中所有的类都是 final
open class BaseClass {
    open fun a1() = 1
    fun a2() = 1 //如果不写open ，这个函数默认是final的
}

class SubClass : BaseClass() {
    //实现空的构造函数

    //标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final
    override fun a1() = 3 //不写override会报错

//    override fun a2() = 1

}

//如果类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型，或委托给另一个构造函数做到这一点
class MyView : View {

    constructor(context: Context) : super(context)
}

//声明主构造函数，直接调用super
class MyView1(context: Context) : View(context) {

}

