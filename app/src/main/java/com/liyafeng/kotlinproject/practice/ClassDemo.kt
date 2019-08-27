package com.liyafeng.kotlinproject.practice

import android.content.Context
import android.view.View
import com.liyafeng.kotlinproject.practice.demo.Base
import java.util.*


/**
 * ==============可见性修饰符===========
 * 类、对象、接口、构造函数、方法、属性和它们的 setter 都可以有 可见性修饰符
 *
 *
 *
 *
 * 在 Kotlin 中有这四个可见性修饰符：
 * private、 文件内可见
 * protected、
 * internal 模块内可见
 * public。
 * 如果没有显式指定修饰符的话，默认可见性是 public。
 *
 * -----internal----
 * 一个模块是编译在一起的一套 Kotlin 文件：

一个 IntelliJ IDEA 模块；
一个 Maven 项目；
一个 Gradle 源集（例外是 test 源集可以访问 main 的 internal 声明）；
一次 <kotlinc> Ant 任务执行所编译的一套文件。
 *
 * -------构造函数的修饰符-----
 * 构造函数的修饰符
 * class C private constructor(a: Int) { …… }
 *
 * 默认情况下，所有构造函数都是 public，这实际上等于类可见的地方它就可见
 *
 * ===========顶层声明===============
 * 就是直接在文件内声明
 * package foo
 *  fun baz() { ... }
 *  class Bar { ... }
 *  函数、属性和类、对象和接口可以在顶层声明
 *
 * protected 不适用于顶层声明。因为他只有在有继承的地方出现
 *
 *
 *
 *
 *
 *
 * */


//============类的定义==============
//如果不声明，主构造函数中的参数是val类型的
// constructor 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面
// private修饰 构造函数可见性

//在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值
//如果加上var 或者val这个值就变成成员变量，否则只能在init里用
//init代码就是构造参数的代码

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
    //有主构造函数，那么次构造函数必须委托给主构造，不能直接委托给父类
    constructor(num: Double = 0.0) : this("", 1) {
        print(num)


    }

    //通过别的次构造函数间接委托
    constructor(n: String) : this(1.0) {
        print(n)
    }

    fun print() {
        print(count)//能访问count，但是不能访问name
    }
}


//在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类：
//这是一个空类
//注意：Any 并不是 java.lang.Object；尤其是，它除了 equals()、hashCode() 与 toString() 外没有任何成员
class Example


//open声明这个类可以被继承，默认情况下，在 Kotlin 中所有的类都是 final
//open和java中final对应，kotlin默认都是final的类和方法，如果要被继承或者重写，需要open关键字
open class BaseClass {
    open fun a1() = 1
    fun a2() = 1 //如果不写open ，这个函数默认是final的

    private fun a3() = 2

    protected open fun a4() = 2
}

class SubClass : BaseClass() {
    //实现空的构造函数

    //标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final
    //因为是覆盖 所以必须要override
    override fun a1() = 3 //不写override会报错

    //报错，因为父类是final类型的
//    override fun a2() = 1

    //私有的不会报错
    fun a3() = 4

    override fun a4() = 5


}

//如果类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型，或委托给另一个构造函数做到这一点
class MyView : View {

    constructor(context: Context) : super(context)
}

//声明主构造函数，直接调用super
class MyView1(context: Context) : View(context) {

}


//
//

// region  属性，getter setter自定义   field关键字-幕后字段  const关键字-常量 lateinit关键字


/**
 * lateinit不能用于原始类型
 *
 *
 * */
fun foo0(): Unit {

}
// endregion


class FieldClass {

}


//接口  (属性，)
interface MyInterface {
    var a: Int;

    fun foo()
}

interface MyInterface1 {
    var a: Int;

    fun foo()
}

class Class1 : MyInterface, MyInterface1 {


    override var a: Int
        get() = 4
        set(value) {}


    //解决覆盖冲突,好像不行。。。
    override fun foo() {
//        super<MyInterface>.foo()
//        super<MyInterface1>.foo()
    }


}


/**
private 意味着只在这个类内部（包含其所有成员）可见；
protected—— 和 private一样 + 在子类中可见。
internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员；
public —— 能见到类声明的任何客户端都可见其 public 成员。
 * */


//扩展函数,好像只能在同一文件中使用。。。
fun String.haha() {
    //this代表当前String对象
    this.replace("1", "2")
    "22".haha()
}


open class C

class D : C()

fun C.foo() = "c"

fun D.foo() = "d"

fun printFoo(c: C) {
    println(c.foo())

}

//printFoo(D())  这个打印c，因为拓展方法只针对当前类型，和继承没关系

//成员函数与一个扩展函数同名，总是取成员函数


fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}

//扩展属性

var String.size: Int
    get() = length - 1
    set(value) {
        print(value)
    }

//val Foo.bar = 1 // 错误：扩展属性不能有   初始化器  =1就是初始化器


//数据类 就是实体类


//解构声明  将一个有componentN()函数的对象拆解

//密封类  sealed关键字

//region 泛型  out ,in 关键字 星投影

/**
 * 星投影
 * */
fun foo1(list: ArrayList<*>): Unit {
    //代表这个list不能写入，读取出来的类型是 Any?  Any是所有kotlin对象的父类
    val get: Any? = list[1]

}


/**
 * https://www.runoob.com/kotlin/kotlin-generics.html (Kotlin 泛型)
 * http://www.kotlincn.net/docs/reference/generics.html (泛型)
 *
 * Kotlin中的MutableList<out T>和Java中的MutableList<? extends T>是一个意思。
 * Kotlin中的MutableList<in T>和Java中的MutableList<? super T>是一个意思。
 * MutableList<*>的投影为MutableList<out Any?>。
 * Kotlin中MyType<*>对应Java中的MyType<?>。
 *
 * */

//定义泛型
class Box<T>(value: T) {


    fun fun1() {
        //参数确定，可以写也可以不写
        val box1 = Box<Int>(2)
        val box = Box(1)
    }
}

interface BaseView {
    fun funFromBaseView(): Unit {

    }

}

abstract class BaseActivity<T : BasePresenter<BaseView>> : BaseView {

    var mPresenter: T? = null
    fun onCreate(): Unit {
        mPresenter = createPresenter()
        mPresenter?.attach(this)
    }

    fun onDestory(): Unit {
        mPresenter?.detach()
    }

    fun createPresenter(): T? = null
}

class BasePresenter<T : BaseView> {

    var view: T? = null

    fun attach(view: T): Unit {
        this.view = view
    }

    fun detach() {
        view = null
    }

}

//endregion


// 如果同一类型参数需要多个上界，我们需要一个单独的 where-子句：  where关键字
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}


// 嵌套类与内部类  嵌套类相当于静态内部类， 内部类要用 inner关键字


class Outer {

    class Inner {

        fun foo(): Unit {
            val inner = Inner()

        }
    }
}

//内部类可以访问外部类
class Outer1 {

    var a = 1

    inner class Inner1 {

        fun foo(): Unit {
            print(a)
            //使用this@label来消除歧义
            print(this@Outer1.a)

        }
    }

}

//匿名内部类
fun foo1(ctx: Context): Unit {
    View(ctx).setOnClickListener { v ->
        when (v.id) {
            1 -> print(1)
            2 -> print(2)
        }
    }

    //当名称没用的时候可以命名为_
    View(ctx).setOnClickListener { _ ->
        print(2)
    }

    //匿名内部类,不允许有名称
    View(ctx).setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
        }

    })

    View(ctx).setOnClickListener(View.OnClickListener { v: View? -> v?.id })

    //直接写类名{} ,而且不写参数类型，因为只有一个
    View(ctx).setOnClickListener(View.OnClickListener { view -> view.id })

    //直接类名省略
    View(ctx).setOnClickListener({ view -> view.id })

}


//枚举类  enum关键字
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}


//对象  object关键字 ,
// 1 可以用来声明单例，最外层直接是object ，
// 2 静态内部类，在class内写object ，也是单例
// 3 可以当匿名内部类，直接object:父类名或者接口
// 4 可以做伴生对象(就是静态内部类,这个编译出来是这个类创建一个实例，
// 那么伴生对象就会创建一个实例，而且是static的，
// class中没有静态方法，那么使用伴生对象是静态的，然后再调用里面的方法
// 就好像是调用静态方法一样，但其实调用的是静态伴生对象的非静态方法
// )，

fun foo2(ctx: Context): Unit {
    //匿名对象
    val value: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
        }
    }

    //自动类型检测
    View(ctx).setOnClickListener(value)

    //直接没有父类的对象
    val o1 = object {
        var a = 1
    }

    print(o1.a)

}

//这称为对象声明，不能再函数中声明，只能再其他对象声明内 或者 非内部类中
//对象声明 其实就是一个单例类，我们在java中通过Single.Instance.xxx来访问
object Single : View.OnClickListener {
    override fun onClick(v: View?) {
    }

    var a = 1
    fun foo() {
        print("")
    }
}


fun foo3(): Unit {
    //单例
    Single.foo()
    Single.onClick(null)

}


//伴生对象  companion关键字

class Class2 {
    companion object Obj {
        fun foo(): Unit {

        }
    }

}

fun foo4() {
    //如果没有 companion ，则要调用 Class.Obj.foo()
    Class2.foo()
}

class Class3 {
    companion object Obj {
        fun foo(): Unit {

        }
    }

}

//对象表达式和对象声明之间的语义差异
// 表达式是创建匿名对象，声明是在类或者顶层声明的
//一个当时就创建，一个是第一次调用的时候创建，他们不是静态的
// 要静态需要 @JvmStatic


//region  委托属性 by关键字 ，调用委托属性就是调用指定的那个类？
/**
 *  https://www.kotlincn.net/docs/reference/delegated-properties.html
 * */
//endregion


//函数
// lambda 表达式参数

fun foo5(a: () -> Int): Unit {

    val invoke = a.invoke()
}

fun foo6(): Unit {
    foo5 { 1 }
}

//可变参数 vararg关键字

fun foo7(vararg a: String): Unit {
    foo7("1", "2")

}

//如果我们已经有一个数组并希望将其内容传给该函数，我们使用伸展（spread）操作符（在数组前面加 *）
fun foo8(): Unit {
    foo7(a = *arrayOf("", ""))
}


//请注意，在调用 Java 函数时不能使用命名参数语法，因为 Java 字节码并不总是保留函数参数的名称。


//中缀表示法  infix关键字 中缀函数

//它们必须是成员函数或扩展函数；
//它们必须只有一个参数；
//其参数不得接受可变数量的参数且不能有默认值。

class Class4 {
    infix fun foo9(a: Int): Int {
        return 1 + a
    }
}

fun foo10(): Unit {
    val class4 = Class4()
    val i = class4 foo9 3 //中缀函数 ，左边是定义函数的类型，右边是参数，中间是函数名

}
//中缀函数调用的优先级低于算术操作符、类型转换以及 rangeTo 操作符
//中缀函数调用的优先级高于布尔操作符 && 与 ||、is- 与 in- 检测以及其他一些操作符
//https://www.kotlincn.net/docs/reference/grammar.html#precedence

// 局部函数，成员函数，泛型函数，内联函数，拓展函数 ，高阶函数和 Lambda 表达式
// 尾递归函数 tailrec关键字 ,优化递归


//在递归调用后有更多代码时，不能使用尾递归，并且不能用在 try/catch/finally 块中。目前尾部递归只在 JVM 后端中支持
tailrec fun findFixPoint(x: Double = 1.0): Double = if (x == Math.cos(x)) x else findFixPoint(Math.cos(x))


//高阶函数和lambda表达式会带来内存开销，我们用内联函数解决，inline关键字


//协程  在 Kotlin 1.1+ 中协程是实验性的


//抽象类，抽象方法，抽象成员变量
abstract class Father {
    protected abstract val layoutId: Int

    abstract fun getLayout(): Int
}


class Son : Father() {
    override fun getLayout(): Int {
        return 1
    }

    override val layoutId: Int
        get() = 1

}












