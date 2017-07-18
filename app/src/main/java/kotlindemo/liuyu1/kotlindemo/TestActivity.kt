package kotlindemo.liuyu1.kotlindemo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.liuyu1.weatherapps.Person
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.util.*

/**
 * Created by liuyu1 on 2017/7/17.
 */
class TestActivity : AppCompatActivity() {
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recylerTest();
//        test();
    }


    fun recylerTest() {
////        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
//        val forecastList: RecyclerView = find(R.id.forecast_list)
//        forecastList.layoutManager = LinearLayoutManager(this)
////        forecastList.adapter = ForecastListAdapter(items)
//        //假如想使用Future来工作，async返回一个Java Future。而且如果你需要一个返回结果的Future， 可以使用asyncResult
//        async() {
//            val result = RequestForecastCommand("94043").execute()
//            uiThread {
//                forecastList.adapter = ForecastListAdapter(result)
//            }
//        }
        val f1 = Forecast(Date(), 27.5f, "Shiny day")
        val f2 = f1.copy(temperature = 30f)

    }

    fun funTest() {
        toast("hello")
        longToast("hello")
        toast("hello long", Toast.LENGTH_LONG)
        toast(message = "Hello", length = Toast.LENGTH_SHORT)
    }

    fun test() {
        Person("Zhang Tao").printName()
        var quantity = 5
        val price: Double = 20.3
        val name: String = "大米"

    }

    fun inTest(x: Int, y: Int, array: IntArray, names: List<String>, text: String) {
        //如果存在于区间(1,Y-1)，则打印OK
        if (x in 1..y - 1)
            print("OK")
        //如果x不存在于array中，则输出Out
        if (x !in 0..array.lastIndex)
            print("Out")
        //打印1到5
        for (x in 1..5)
            print(x)
        //遍历集合(类似于Java中的for(String name : names))
        for (name in names)
            println(name)
        //如果names集合中包含text对象则打印yes
        if (text in names)
            print("yes")

        names?.let {
            //当data不为空的时候，执行语句块
        }
        names ?: let {
            //相反的，以下代码当data为空时才会执行
        }
    }

    fun whenTest(obj: Any) {
        when (obj) {
            1 -> print("第一项")
            "hello" -> print("这个是字符串hello")
            is Long -> print("这是一个Long类型数据")
            !is String -> print("这不是String类型的数据")
            else -> print("else类似于Java中的default")
        }
    }

    fun instanceofTest(obj: Any): Int? {
        if (obj is String) {
            // 判断一个对象是否为一个类的实例，可以使用is关键字
            // 做过类型判断以后，obj会被系统自动转换为String类型
            return obj.length
        }
        // 代码块外部的obj仍然是Any类型的引用
        return null
    }

    open class Animal(name: String)

    class Person(name: String, suName: String) : Animal(name)

    fun add(x: Int, y: Int): Int {
        return x + y
    }

    // 如果是返回Int类型，连返回类型都可以不写
    fun getIntValue(value: Int) = value

    fun add_two(x: Int, y: Int): Int = x + y

    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    //扩展函数,我们就可以在每一个Activity中直接使用toast()函数了。
    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

//    fun niceToast(message: String,
//                  tag: String = javaClass<MainActivity>().getSimpleName(),
//                  length: Int = Toast.LENGTH_SHORT) {
//        Toast.makeText(this, "[$className] $message", length).show()
//    }

    data class Forecast(val date: Date, val temperature: Float, val details: String)


}