package kotlindemo.liuyu1.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by liuyu1 on 2017/7/18.
 */
class Test2Activity : AppCompatActivity() {
    val range: IntRange = 0..1024 // [0, 1024]
    val range_exclusive: IntRange = 0 until 1024 // [0, 1024) = [0, 1023]


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val name: String = getName() ?: return  //如果是null就return
        //println(name.length)
        val value: String? = "HelloWorld"
        println(value!!.length)//强制认定不为null

        println(range.contains(50))  //和下面是等价的
        println(50 in range)
        for (i in range_exclusive) {
            print("$i, ")
        }

        val arg1: Int = 0
        val arg2: Int = 1
        println("" + arg1 + " + " + arg2 + " = " + (arg1 + arg2))    //和下面是等价的
        println("$arg1 + $arg2 = ${arg1 + arg2}")    //字符串模板
        val sayHello: String = "Hello \"Trump\""

        println(sayHello)
        val salary: Int = 1000
        //$salary
        println("\$salary")

//        val parent: Parent = Parent()
//        val child: Child? = parent as? Child    //加上？如果转换异常，返回null，不抛异常
//        println(child)
    }


    fun getName(): String? {
        return null
    }
}

