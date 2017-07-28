package kotlindemo.liuyu1.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.println.kotlin.chapter4.annotations.PoKo
import java.io.BufferedReader
import java.io.FileReader
import kotlin.reflect.KProperty

/**
 * Created by liuyu1 on 2017/7/19.
 */
class Test5Activity : AppCompatActivity() {
    val args: Array<String> = arrayOf("1", "2")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //高阶函数：传入或返回函数的函数
        args.forEach(::println) //函数引用
        args.filter(String::isNotEmpty)  //包级函数
        //需要类的实例才能这样用::，它有函数的引用，上面println，isNotEmpty的是包级函数所以不用
        val pdfPrinter = PdfPrinter()
        args.forEach(pdfPrinter::println)
        //因为没有参数，所以可以直接用
        val helloWorld = Hello::world

        //常见的高阶函数
        BufferedReader(FileReader("hello.txt")).readText();//和下面一样
        BufferedReader(FileReader("hello.txt")).use {
            var line: String?
            while (true){
                line = it.readLine()?: break
                println(line)
            }
        }

        val list= listOf<Int>(1,2,3,5,10,8,2)
        val newList=ArrayList<Int>();
        list.forEach{
            val newElement=it*2+3
            newList.add(newElement)
        }
        //和上面一样，上面麻烦，map可以对集合进行操作，返回一个修改过得集合
        //flatmap，对集合的集合进行操作，省去了俩次遍历的麻烦，跟rx java一样
        val newList2= list.map { it*2+3 }
        val newList3= list.map { Int::toDouble }
        newList3.forEach (::println)
        newList3.map (::println)  //和上面一样，但是又重新add了一个集合



    }

    class PdfPrinter{
        fun println(any: Any){
            kotlin.io.println(any)  //重名了可以用包名调用
        }
    }

    class Hello{
        fun world(){
            println("Hello World.")
        }
    }

    data class Person(val name: String, val age: Int){
        fun work(){
            println("$name is working!!!")
        }
    }
    fun findPerson(): Person?{
        return null
    }

}