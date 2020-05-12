package com.liyafeng.kotlinproject.practice.demo


/**
 */
class SampleEntity {

    //默认是private的，然后生成 getter 和setter来访问
    var type = 0
    var title: String? = null
    var defaultUnit: DefaultUnitBean? = null
    var isSelect = false

    val icon: Int
        get() = when (type) {
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 3
            else -> 4
        }

    class DefaultUnitBean {
        var id: Long = 0
        var title: String? = null

    }
}