package kotlindemo.liuyu1.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.println.kotlin.chapter4.annotations.PoKo
import kotlin.reflect.KProperty

/**
 * Created by liuyu1 on 2017/7/19.
 */
class Test4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val person: Person = MaNong(23)
        person.work()
        println(person.age)

        val person2: Person = Doctor(24)
        person2.work()
        println(person2.age)

        val latitude = Latitude.ofDouble(3.0)
        val latitude2 = Latitude.ofLatitude(latitude)
        println(Latitude.TAG)

        val driver = CarDriver()
        val writer = PPTWriter()
        val seniorManager = SeniorManager(driver, writer)
        seniorManager.drive()
        seniorManager.write()

        println(D(3).x())
        println(D(-10).x())
        println(D(-110).x())
        println(D(-10000).x())

        val delegates = Delegates()
        println(delegates.hello)
        println(delegates.hello2)
        println(delegates.hello3)
        delegates.hello3 = "value of hello3"
        println(delegates.hello3)

        println("abc" * 16)
        "abc".b = 5
        println("abc".b)

        val overloads = Overloads()
        overloads.a(3)

        val integerList = ArrayList<Int>()
        integerList.add(13)
        integerList.add(2)
        integerList.add(5)
        //java这俩个方法是一个都是remove，所以有时候会混淆，不知道remove对象还是index
        integerList.removeAt(1)
        integerList.remove(5)

        val china = Country(0, "中国")
        println(china)
        println(china.component1())
        println(china.component2())
        val (id, name) = china
        println(id)
        println(name)
        val componentX = ComponentX()
        val (a, b, c, d) = componentX
        println("$a $b$c$d")

        val inner = Outter().Inner()
        val view = View()
        //匿名内部类。和java不同的是，java不能继承别的东西了
        view.onClickListener = object : Outter(), OnClickListener{
            override fun onClick() {

            }
        }
    }

    abstract class Person(open val age: Int) {
        //只有open的成员和类才能被继承,借口和抽象类默认是open的
        abstract fun work()
    }

    class MaNong(age: Int) : Person(age) {

        override val age: Int
            get() = 0

        override fun work() {
            println("我是码农，我在写代码")
        }
    }

    class Doctor(age: Int) : Person(age) {
        override fun work() {
            println("我是医生，我在给病人看病")
        }
    }


    class Latitude private constructor(val value: Double) {
        companion object {
            //加上这个注解Java可以直接像静态那样调用，否则得Latitude.companion.ofDouble(3.0)
            @JvmStatic
            fun ofDouble(double: Double): Latitude {
                return Latitude(double)
            }

            fun ofLatitude(latitude: Latitude): Latitude {
                return Latitude(latitude.value)
            }

            @JvmField
            val TAG: String = "Latitude"
        }
    }

    class Manager : Driver, Writer {
        override fun write() {

        }

        override fun drive() {

        }
    }

    //接口代理，可以不是必须实现接口或抽象类的方法
    class SeniorManager(val driver: Driver, val writer: Writer) : Driver by driver, Writer by writer

    class CarDriver : Driver {
        override fun drive() {
            println("开车呢")
        }
    }

    class PPTWriter : Writer {
        override fun write() {
            println("做PPT呢")
        }

    }

    interface Driver {
        fun drive()
    }

    interface Writer {
        fun write()
    }

    abstract class A {
        open fun x(): Int = 5
    }

    interface B {
        fun x(): Int = 1
    }

    interface C {
        fun x(): Int = 0
    }

    class D(var y: Int = 0) : A(), B, C {

        override fun x(): Int {
            println("call x(): Int in D")
            if (y > 0) {
                return y
            } else if (y < -200) {
                return super<C>.x()
                //接口方法有冲突时可以这样写，如果冲突的方法返回类型不一样就能不行了，最好不要那样干
            } else if (y < -100) {
                return super<B>.x()
            } else {
                return super<A>.x()
            }
        }
    }

    class Delegates {
        val hello by lazy {
            //属性代理，提供getValue
            "HelloWorld"
        }

        val hello2 by X()

        var hello3 by X()
    }

    class X {
        //可以自定义代理，需要实现相应的getValue、setValue
        private var value: String? = null

        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            println("getValue: $thisRef -> ${property.name}")
            return value ?: ""
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("setValue, $thisRef -> ${property.name} = $value")
            this.value = value
        }
    }

    //扩展方法，不用运算符operator的话，用"abc".times(16)这样来调用,jva可以类名.times("abc", 16)调用
    operator fun String.times(int: Int): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until int) {
            stringBuilder.append(this)
        }
        return stringBuilder.toString()
    }

    val String.a: String
        get() = "abc"

    var String.b: Int
        set(value) {

        }
        get() = 5

    class Overloads{
        @JvmOverloads//默认参数是0，可以不传，但java调用也不想传的时候加这个注解
        fun a(int: Int = 0): Int{
            return int
        }
    }

    @PoKo//data数据类默认是final、并且没有无参构造函数的，可以通过插件解决这个问题
    data class Country(val id: Int, val name: String)

    class ComponentX{//数据类的内部实现，有几个参数就有几个 component几
        operator fun component1(): String{
            return "您好，我是"
        }

        operator fun component2(): Int{
            return 1
        }

        operator fun component3(): Int{
            return 1
        }

        operator fun component4(): Int{
            return 0
        }
    }

    open class Outter{
        val a: Int = 0
        inner class Inner{
            val a: Int = 5

            fun hello(){
                println(this@Outter.a)//和外部类成员重复加这个注解
            }
        }
    }

    interface OnClickListener{
        fun onClick()
    }

    class View{
        var onClickListener: OnClickListener? = null
    }

}