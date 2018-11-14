package com.liyafeng.kotlinproject.practice

import java.util.*

//一个泛型类
class BasePresenter_Generic<T : Objects>(internal var view: T)
