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

        args.forEach ForEach@ {  //会执行方法外面的
            if (it == "q") return@ForEach
            println(it)
        }
        args.forEach {  println(it) }

        println(sum)   //Function2<java.lang.Integer, java.lang.Integer, java.lang.Integer>
        println(int2Long)   //Function1<java.lang.Integer, java.lang.Long>,最多22个参数
//        println(::printUsage is () -> Unit)

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
}




