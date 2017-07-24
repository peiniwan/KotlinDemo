package kotlindemo.liuyu1.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by liuyu1 on 2017/7/19.
 */
class Test4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val person: Person = MaNong(23)
        person.work()
        println(person.age)

        val person2 : Person = Doctor(24)
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
    }

    abstract class Person(open val age: Int){//只有open的成员和类才能被继承,借口和抽象类默认是open的
        abstract fun work()
    }

    class MaNong(age: Int): Person(age){

        override val age: Int
            get() = 0

        override fun work() {
            println("我是码农，我在写代码")
        }
    }

    class Doctor(age: Int): Person(age){
        override fun work() {
            println("我是医生，我在给病人看病")
        }
    }


    class Latitude private constructor(val value: Double){
        companion object{
            //加上这个注解Java可以直接像静态那样调用，否则得Latitude.companion.ofDouble(3.0)
            @JvmStatic
            fun ofDouble(double: Double): Latitude{
                return Latitude(double)
            }

            fun ofLatitude(latitude: Latitude): Latitude{
                return Latitude(latitude.value)
            }

            @JvmField
            val TAG: String = "Latitude"
        }
    }

    class Manager: Driver, Writer {
        override fun write() {

        }

        override fun drive() {

        }
    }

    //接口代理，可以不是必须实现接口或抽象类的方法
    class SeniorManager(val driver: Driver, val writer: Writer): Driver by driver, Writer by writer

    class CarDriver: Driver {
        override fun drive() {
            println("开车呢")
        }
    }

    class PPTWriter: Writer {
        override fun write() {
            println("做PPT呢")
        }

    }

    interface Driver{
        fun drive()
    }

    interface Writer{
        fun write()
    }

    abstract class A{
        open fun x(): Int = 5
    }

    interface B{
        fun x(): Int = 1
    }

    interface C{
        fun x(): Int = 0
    }

    class D(var y: Int = 0): A(), B, C{

        override fun x(): Int {
            println("call x(): Int in D")
            if(y > 0){
                return y
            }else if(y < -200){
                return super<C>.x()
                //接口方法有冲突时可以这样写，如果冲突的方法返回类型不一样就能不行了，最好不要那样干
            }else if(y < -100){
                return super<B>.x()
            }else{
                return super<A>.x()
            }
        }
    }
}