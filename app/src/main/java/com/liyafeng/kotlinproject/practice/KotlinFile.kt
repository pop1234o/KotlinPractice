package com.liyafeng.kotlinproject.practice
//file 中可以定义多个class
// 而class 中只能定义一个class

class KotlinFile{
    fun fook(): Unit {

    }
}
class Class11{

    fun foo1(): Unit {

        //let 操作，如果一个对象是可null的，那么用这个操作可以少写 ?.，参考Fragment的oncreate
        var kk :KotlinFile?=null
        kk?.let {
            it.fook()
            it.fook()
            it.fook()
        }

        //apply操作 ，可以返回这个类，在大括号中做其他操作 这样就可以少写对象引用进行初始化了，参考Fragment的newInstance
       var k1 =  KotlinFile().apply {
            fook()
        }



    }
}






