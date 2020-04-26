package com.liyafeng.kotlinproject.practice

class StaticMethod {

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
        val name = ""

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
        fun doSome() = 3


    }

    /*
    *     *
    * ======static方法 静态方法的几种写法==========
    * //@JvmStatic  这个注解只能用在 object xx{} 或者 companion onject中
    *
    * object A{
    *  @JvmStatic
    *  fun get(){}
    * }
    * //半生对象是静态的，但里面的方法不是
    *
    * class A privite constructor(context Context){
    *
    *       companion object{
    *          fun get(){
    *          ....
    *          }
    *       }
    *
    * }
    *
    * //如果想生成一个静态方法
    *
    *  class A privite constructor(context Context){
    *
    *       companion object{
    *          @JvmStatic //这里会给class A生成一个同名静态方法，里面调用的事（静态）伴生对象的实例方法
    *          fun get(){
    *          ....
    *          }
    *       }
    *
    * }
    *
    * ===========静态变量===========
    * class A{
    *   companion object {
    *      val MIN_CLICK_DELAY_TIME = 800
    *  }
    * }
    *
    * 这样在A中
    * private static final int MIN_CLICK_DELAY_TIME = 800;
    *
    * 伴生对象中
    *
    *  public static final class Companion {
    *      public final int getMIN_CLICK_DELAY_TIME() {
    *          return A.MIN_CLICK_DELAY_TIME;
    *       }
    *   }
    *
    *
    * ---------val-------------
    * 如果是 类内的
    * val MIN_CLICK_DELAY_TIME = 800
    * 生成final非静态变量
    * private final int MIN_CLICK_DELAY_TIME = 800;
    *
    * val用public修饰无效，依然生成private的
    *
    *
    * 如果是包内的val
    * val ARG_1 = "1"
    *  const val ARG_2 = "2"
    *
    *  private val ARG_3 = "3"
    *  private const val ARG_4 = "4"
    *
    * 会生成
    * public final class Akt {
    *   @NotNull
    *   private static final String ARG_1 = "1";//val 私有静态 ,但是用getter方法
    *   @NotNull
    *   public static final String getARG_1() {
    *   return ARG_1;
    *   }
    *
    *   @NotNull
    *   public static final String ARG_2 = "2"; //const 共有静态
    *
    *   private static final String ARG_3 = "3"; // 带private都是私有静态
    *   private static final String ARG_4 = "4"; //
    *
    *
    * }
    *
    * 如果是object内的val
    *
    * object AppUtil {
    *       val mainHandler = Handler(Looper.getMainLooper())
    * }
    *
    * 那么
    *
    * public final class AppUtil {
    * @NotNull
    * private static final Handler mainHandler; //私有静态变量
    * public static final AppUtil INSTANCE;
    *
    * @NotNull
    * public final Handler getMainHandler() { //通过共有方法访问
    * return mainHandler;
    * }
    *
    * private AppUtil() {
    * }
    *
    * static {
    *      AppUtil var0 = new AppUtil();
    *      INSTANCE = var0;
    *      mainHandler = new Handler(Looper.getMainLooper());
    *      }
    * }
    *
    *
    *
    *
    * */

}
