package com.example.liuyu1.weatherapps

/**
 * Created by liuyu1 on 2017/6/5.
 */
//Kotlin 的构造函数可以写在类头中，跟在类名后面
class Person( var name:String){

    private var description: String? = null

    //在主构造函数中不能有任何代码实现，如果有额外的代码需要在构造方法中执行，你需要放到init代码块中执行
    init {
        name = "Zhang Tao"
    }

    internal fun sayHello() {
        println("hello $name")
    }

    fun printName(){
        println(name)
    }

    //这里我们让次级构造函数调用了主构造函数，完成 name 的赋值。
    // 由于次级构造函数不能直接将参数转换为字段，所以需要手动声明一个 description 字段，并为 description 字段赋值。
    constructor(name: String, description: String) : this(name) {
        this.description = description
    }

}