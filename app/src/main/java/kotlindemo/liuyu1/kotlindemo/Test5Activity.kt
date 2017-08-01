package kotlindemo.liuyu1.kotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.println.kotlin.chapter4.annotations.PoKo
import java.io.BufferedReader
import java.io.File
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
        //需要类的实例才能这样用::，它有函数的引用，上面println，isNotEmpty的是包级函数所以可以直接写
        val pdfPrinter = PdfPrinter()
        args.forEach(pdfPrinter::println)
        //因为没有参数，所以可以直接用
        val helloWorld = Hello::world

        //常见的高阶函数
        val list = listOf<Int>(1, 2, 3, 5, 10, 8, 2)
        val newList = ArrayList<Int>();
        list.forEach {
            val newElement = it * 2 + 3
            newList.add(newElement)
        }
        //和上面一样，上面麻烦，map可以对集合进行操作，返回一个修改过得集合
        //flatmap，对集合的集合进行操作，省去了俩次遍历的麻烦，跟rx java一样
        val newList2 = list.map { it * 2 + 3 }
        val newList3 = list.map { Int::toDouble }
        newList3.forEach(::println)
        newList3.map(::println)  //和上面输出一样，但是又重新add了一个集合，不好

        list.takeWhile { it <= 3 }.forEach(::println)  //小于的去掉
        list.forEach {
            if (it % 2 == 0) {
                println(it)
            }
        }
        list.filter { it.isEvent() }.forEach(::println)//过滤

        val br = BufferedReader(FileReader("hello.txt"));
        with(br) {
            //不用不停的调用br.它的方法了
            var line: String?
            while (true) {
                //it表示当前对象BufferedReader，所以可以用它的方法
                line = readLine() ?: break
                println(line)
            }
            close()
        }
        //use不用close了
        BufferedReader(FileReader("hello.txt")).use {
            var line: String?
            while (true) {
                //it表示当前对象BufferedReader，所以可以用它的方法
                line = it.readLine() ?: break
                println(line)
            }
        }
        //和上面一样，更简洁
        BufferedReader(FileReader("hello.txt")).readText();

        val person = findPerson();
        //person是可null的，所以需要？
        println(person?.age)
        println(person?.name)
        //上面太麻烦，findPerson加了？,所以后面不需要了
        findPerson()?.let { person ->
            person.work()
            println(person.age)
        }
        //还可以更简洁，person也不用写
        findPerson()?.apply {
            work()
            println(age)
        }

        val add5 = add(5)
        println(add5(2))//返回5+2 7

        //统计每个字符出现的次数
        val map = HashMap<Char, Int>()
        File("build.gradle").readText().toCharArray().filterNot(Char::isWhitespace).forEach {
            val count = map[it]
            if (count == null) map[it] = 1
            else map[it] = count + 1
        }
        map.forEach(::println)
        //groupBy分组，分成一个个list,跟上面一样
        File("build.gradle").readText().toCharArray().filterNot(Char::isWhitespace)//不统计空白字符
                .groupBy { it }.map {
            it.key to it.value.size
        }.forEach(::println)


    }

    fun Int.isEvent() = this % 2 == 0

    class PdfPrinter {
        fun println(any: Any) {
            kotlin.io.println(any)  //重名了可以用包名调用
        }
    }

    class Hello {
        fun world() {
            println("Hello World.")
        }
    }

    data class Person(val name: String, val age: Int) {
        fun work() {
            println("$name is working!!!")
        }
    }

    fun findPerson(): Person? {
        return null
    }

    val string = "HelloWorld"

    //闭包：返回函数
    fun makeFun(): () -> Unit {
        var count = 0
        return fun() {
            println(++count)
        }
    }

    //fun add(x: Int) = fun(y: Int) = x + y

    fun add(x: Int): (Int) -> Int {

        data class Person(val name: String, val age: Int)

        return fun(y: Int): Int {
            return x + y
        }
    }

    fun fibonacci(): Iterable<Long> {
        var first = 0L
        var second = 1L
        return Iterable {
            object : LongIterator() {
                override fun nextLong(): Long {
                    val result = second
                    second += first
                    first = second - first
                    return result
                }

                override fun hasNext() = true

            }

        }
    }


}