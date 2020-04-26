package com.liyafeng.kotlinproject.practice

//数据类，必须有构造参数，参数必须声明var或者val
//在 JVM 中，如果要无参的构造函数，则所有的属性必须指定默认值。

/*
*
* 初始化，可以用init,也可以直接写在参数上
* 因为kotlin初始是调用构造函数，然后是init，然后是成员变量（这个看代码顺序来执行）
*
* ========构造函数=====
* 有主次之分，次必须最终委托给主
* */
data class User(var name: String = "123", var age: Int = 1) {

    init {
        age += 1
    }

    fun foo(): Unit {
        //解构声明  https://www.kotlincn.net/docs/reference/multi-declarations.html
        //这个也是语法糖，实际调用的是 component1() component2()方法
        val (name1, age1) = User(age = 2)//可选


        val user = User("1", 3);


        //解构声明 用在for中 ,直接从列表中取出User,然后解构
        val array = arrayListOf(User(), User())
        for ((name2, age2) in array) {
            print(name2)
            print(age2)
        }

//        数据类自动声明 componentN() 函数


        //任何表达式都可出现在解构声明的右侧，只要他们有所需数量的componentN()即可

        // Map中实现了 componentN()方法 ，所以我们可以直接映射
        //operator fun <K, V> Map.Entry<K, V>.component1() = getKey()  拓展函数
//        operator fun <K, V> Map.Entry<K, V>.component2() = getValue()

        val map = mapOf("1" to 1, "2" to 2)

        for ((key, value) in map) { //他的对象是

        }


        //忽略某个变量，且不会调用他的component方法
        val (_, age3) = User(age = 2)//可选


        //在 lambda 表达式中解构
        map.mapValues { entry -> print(entry.key) }

        map.mapValues { (key, value) -> print(value) }


        //解构指定类型
        val (_, age4: Int) = User(age = 2)//可选


    }
}

