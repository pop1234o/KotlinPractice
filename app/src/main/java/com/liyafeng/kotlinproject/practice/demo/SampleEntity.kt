package com.liyafeng.kotlinproject.practice.demo


/**
 */
class SampleEntity {

    //默认是private的，然后生成 getter 和setter来访问
    var type = 0
    var title: String? = null
    var defaultUnit: DefaultUnitBean? = null
    var isSelect = false

    //这样生成的一个final getIcon方法，并没有icon变量
    val icon: Int
        get() = when (type) {
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 3
            else -> 4
        }

    //生成一个 private的final变量，和一个public的get方法，并不能修改值
    val const=0

    class DefaultUnitBean {
        var id: Long = 0
        var title: String? = null

    }
}