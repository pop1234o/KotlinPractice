package com.liyafeng.kotlinproject.practice.demo;

/**
 * @author pop
 *
 * 泛型
 * 因为我们用这个类，但是里面的（参数）类型可以是不确定的，这样写有利于提高代码复用性
 *
 * class Car<T> {
 *     T t;
 *
 *     public void set(T t){
 *         this.t = t;
 *     }
 *     public T get(){
 *         return t;
 *     }
 * }
 *
 * 代码复用
 *
 * 泛型
 * 定义 是在类定义的时候的时候定义的
 * 使用 是在 继承，或者声明变量的时候定义的
 *
 * 通配符就是在使用的时候 用的
 * ======================
 *
 *
 */
public class Base {
}


interface BaseView {
}

/*
* //在java中 定义   BaseActivity<T extends BasePresenter<BaseView>>  implements BaseView
*
//那么
* class RealActivity extends BaseActivity<RealPresenter>
* 就会报错
* 因为  class RealPresenter extends BasePresenter<RealView>
* 说T应该继承 BasePresenter<BaseView>
* 而不是  RealPresenter
*
* 因为 T extends BasePresenter<BaseView> 就限定好了，T只能是   BasePresenter<BaseView>的子类
* BaseView是固定的
*
* ============java中的写法==========
* abstract class BaseActivity<T extends BasePresenter> implements BaseView
* 这样定义BaseActivity，BasePresenter上没有指定参数，那么 mPresenter.attach（）
* 就有个警告，unchecked call
*
* 这样直接转kotlin ，变成BasePresenter<*> 那么mPresenter.attach就会报错
* require Nothing
*
*
* */


abstract class BaseActivity<T extends BasePresenter<? extends BaseView>> implements BaseView {

    private T mPresenter = null;

    public void onCreate() {
        mPresenter = createPresenter();

        mPresenter.attach(this);

    }

    public void onDestory() {
        mPresenter.detach();
    }

    public T createPresenter() {
        return null;
    }
}

class BasePresenter<T extends BaseView> {

    T view = null;

//    public void attach(T view) {
//        this.view = view;
//    }

    public void detach() {
        view = null;
    }

    public void attach(BaseView baseView) {
        this.view = (T) baseView;
    }
}


class RealActivity extends BaseActivity<RealPresenter> {

}


class RealPresenter extends BasePresenter<RealView> {

}

class RealView implements BaseView {

}






/*
*
*
internal abstract class BaseActivity<V : BaseView, T : BasePresenter<V>> : BaseView {

    private var mPresenter: T? = null

    fun onCreate() {
        mPresenter = createPresenter()

        mPresenter!!.attach(this as V)

    }

    fun onDestory() {
        mPresenter!!.detach()
    }

    fun createPresenter(): T? {
        return null
    }
}

internal open class BasePresenter<T : BaseView> {

    var view: T? = null

    fun attach(view: T) {
        this.view = view
    }

    fun detach() {
        view = null
    }

}


internal class RealActivity : BaseActivity<RealView, RealPresenter>()


internal class RealPresenter : BasePresenter<RealView>()

internal class RealView : BaseView
*
* */




/*
*  这样也是可以的
*  out BaseView 就是 ? extends BaseView
* internal abstract class BaseActivity<T : BasePresenter<out BaseView>> : BaseView {

    private var mPresenter: T? = null

    fun onCreate() {
        mPresenter = createPresenter()

        mPresenter!!.attach(this)

    }

    fun onDestory() {
        mPresenter!!.detach()
    }

    fun createPresenter(): T? {
        return null
    }
}

internal open class BasePresenter<T : BaseView> {

    var view: T? = null

    //    public void attach(T view) {
    //        this.view = view;
    //    }

    fun detach() {
        view = null
    }

    fun attach(baseView: BaseView) {
        this.view = baseView as T
    }
}


internal class RealActivity : BaseActivity<RealPresenter>()


internal class RealPresenter : BasePresenter<RealView>()

internal class RealView : BaseView
*
* */





