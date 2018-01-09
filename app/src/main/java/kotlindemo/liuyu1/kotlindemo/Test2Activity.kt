package kotlindemo.liuyu1.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.textView

/**
 * Created by liuyu1 on 2017/7/18.
 */
class Test2Activity : AppCompatActivity() {
    val range: IntRange = 0..1024 // [0, 1024]
    val range_exclusive: IntRange = 0 until 1024 // [0, 1024) = [0, 1023]
    //基本数据类型IntArray，其他是Array<类型> = arrayOf
    val arrayOfInt: IntArray = intArrayOf(1, 3, 5, 7)
    val arrayOfChar: CharArray = charArrayOf('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd')
    val arrayOfString: Array<String> = arrayOf("我", "是", "码农")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            textView(intent.extras["Key"]?.toString()?: "Hello") {

            }.lparams(matchParent, matchParent){
                gravity = Gravity.CENTER
            }
        }

        //kotlin默认不能空，变量类型后面跟?号定义，表明这是一个可空类型
        //val name: String = getName() ?: return  //如果是null就return
        //println(name.length)
        val value: String? = "HelloWorld"
        println(value!!.length)//强制认定不为null，无视它是否为null（最好不要用，危险npe）

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

        for (int in arrayOfInt) {
            println(int)
        }
        println(arrayOfChar.joinToString(""))   //变成string
        println(arrayOfInt.slice(1..2))  //3，5

    }


    fun getName(): String? {
        return null
    }
}

