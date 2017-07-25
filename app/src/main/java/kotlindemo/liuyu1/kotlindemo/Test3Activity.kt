package kotlindemo.liuyu1.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by liuyu1 on 2017/7/19.
 */
class Test3Activity : AppCompatActivity() {
    val FINAL_HELLO_WORLD: String = "HelloWorld"   //常亮
    var helloWorld: String = FINAL_HELLO_WORLD    //变量
    val FINAL_HELLO_CHINA = "HelloChina"   //类型可以不写，自动推导
    val args: Array<String> = arrayOf("1", "2")

    private val USERNAME = "kotlin"
    private val PASSWORD = "jetbrains"
    private val ADMIN_USER = "admin"
    private val ADMIN_PASSWD = "admin"
    private val DEBUG = 1
    private val USER = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkArgs(args)
        val arg1 = args[0].toInt()
        val arg2 = args[1].toInt()
        println("$arg1 + $arg2 = ${sum(arg1, arg2)}")
        println(int2Long(3))

        println(sum(1, 3))
        //和上面等价
        println(sum.invoke(1, 3))

        args.forEach ForEach@ {
            //会执行方法外面的
            if (it == "q") return@ForEach
            println(it)
        }
        args.forEach { println(it) }

        println(sum)   //Function2<java.lang.Integer, java.lang.Integer, java.lang.Integer>
        println(int2Long)   //Function1<java.lang.Integer, java.lang.Long>,最多22个参数
//        println(::printUsage is () -> Unit)

        //-name <Name>
        if ("-name" in args) {
            println(args[args.indexOf("-name") + 1])
        }

        if (Book() on Desk()) { // dsl,可以定制自己的表达式

        }

        //if不仅是是条件语句，也是表达式，可以接收参数，返回最后一行
        val mode = if (args.isNotEmpty() && args[0] == "1") {
            DEBUG
        } else {
            USER
        }

        println("请输入用户名：")
        val username = readLine()
        println("请输入密码：")
        val passwd = readLine()

        if (mode == DEBUG && username == ADMIN_USER && passwd == ADMIN_PASSWD) {
            println("管理员登录成功")
        } else if (username == USERNAME && passwd == PASSWORD) {
            println("登录成功")
        } else {
            println("登录失败")
        }

        for (arg in args) {
            println(arg)
        }

        for ((index, value) in args.withIndex()) {
            println("$index -> $value")
        }
        //和上面是一样的
        for (indexedValue in args.withIndex()) {
            println("${indexedValue.index} -> ${indexedValue.value}")
        }

        val list = MyIntList()
        list.add(1)
        list.add(2)
        list.add(3)
        for (i in list) {
            println(i)
        }

        val result = try{//捕获异常可以这样写，也可以像java那样写
            args[0].toInt() / args[1].toInt()
        }catch (e: Exception){
            e.printStackTrace()
            0
        }
        println(result)

        val x = 5
        when(x){
            is Int -> println("Hello $x")
            in 1..100 -> println("$x is in 1..100")
            !in 1..100 -> println("$x is not in 1..100")
            args[0].toInt() -> println("x == args[0]")
        }

        val mode1 = when{  //和if else一个意思
            args.isNotEmpty() && args[0] == "1" -> 1
            else -> 0
        }

//        val list = arrayListOf(1,3,4,5)
        val array = intArrayOf(1,3,4,5)
        hello(3.0, *array)//*展开

    }

    //默认参数，不传为默认，vararg可变参数，智能是数组不能是list
    fun hello(double: Double, vararg ints: Int, string: String = "Hello"){
        println(double)
        ints.forEach(::println)
        println(string)
    }

    fun checkArgs(args: Array<String>) {
        if (args.size != 2) {
            printUsage()
            System.exit(-1)
        }
    }

    fun printUsage() {
        println("请传入两个整型参数，例如 1 2") // (Any?) -> Unit
    } // ()->Unit

    val sum = { arg1: Int, arg2: Int ->
        println("$arg1 + $arg2 = ${arg1 + arg2}")
        arg1 + arg2
    }
// (Int, Int) -> Int

    val printlnHello = {
        println("Hello")
    }
// ()-> Unit

    val int2Long = fun(x: Int): Long {  //匿名函数，用变量接收，可用lambda表示
        return x.toLong()
    }
// (Int) -> Long

    class X

    class A{
        var b = 0
        lateinit var c: String   //var延迟初始化用lateinit
        lateinit var d: X
        val e: X by lazy {   //val用lazy代理
            println("init X")
            X()
        }

        var cc: String? = null //初始化成null不好
    }


    class Complex(var real: Double, var imaginary: Double) {
        operator fun plus(other: Complex): Complex {
            return Complex(real + other.real, imaginary + other.imaginary)
        }

        operator fun plus(other: Int): Complex {
            return Complex(real + other, imaginary)
        }

        operator fun plus(other: Any): Int {
            return real.toInt()
        }

        operator fun invoke(): Double {
            return Math.hypot(real, imaginary)
        }

        override fun toString(): String {
            return "$real + ${imaginary}i"
        }
    }

    class Book {
        //中缀表达式：只有一个参数，且用infix修饰
        infix fun on(any: Any): Boolean {
            return false
        }
    }

    class Desk

    //iterator原理
    class MyIterator(val iterator: Iterator<Int>) {
        operator fun next(): Int {
            return iterator.next()
        }

        operator fun hasNext(): Boolean {
            return iterator.hasNext()
        }
    }

    class MyIntList {
        private val list = ArrayList<Int>()

        fun add(int: Int) {
            list.add(int)
        }

        fun remove(int: Int) {
            list.remove(int)
        }

        operator fun iterator(): MyIterator {
            return MyIterator(list.iterator())
        }
    }
}




