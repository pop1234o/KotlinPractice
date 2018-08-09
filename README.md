# KotlinPractice
一个Kotlin语言的讲解和实践仓库
https://www.kotlincn.net（中文官网，学习必看）

### Android Studio(AS)创建Kotlin工程
参考 https://www.kotlincn.net/docs/tutorials/kotlin-android.html
1.首先我们要正常创建一个Android Project
2.然后AS在3.0以上自动带kotlin的插件，所以我们AS最好在3.0以上
3.在AS中Tools-> kotlin -> configure kotlin in project 
4.会弹出框，选择Android with Gradle ,然后AS就自动帮我们修改工程的build.gradle 和
模块的build.gradle两个文件。重新build的一下，我们就能使用kotlin来开发Android了
5.当然我们可以添加kotlin的依赖
``` 工程的build.gradle
classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.30"
//这个版本号要和AS自带的kotlin plugin的版本一致，否则会出现编译错误
//这里很坑，我们可以在AS的Settings中的Plugin中看到kotlin插件的版本

```


### 基础概念
表达式：一个表达式是有返回值的，他可以在赋值语句的右边
比如，var a = 1+2+3  
1+2+3  就叫一个表达式

//if当做表达式
var max = if(a>b) a else b

//if当做条件控制语句，因为并没有返回值
if(a>b)
 print(a)
else
 print(b)













