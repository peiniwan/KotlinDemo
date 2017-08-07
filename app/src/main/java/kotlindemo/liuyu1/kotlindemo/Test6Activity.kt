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
 *
 *
 * java和kotlin互调
 *
 *
 */
class Test6Activity : AppCompatActivity() {
    val args: Array<String> = arrayOf("1", "2")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //除了java那样创建集合，还可以这样创建
        val list = listOf("Hello", "World")
        val map = mapOf("key" to "value", "2" to "whatever")

        /**
         * java读一个文件
         */
//        BufferedReader bufferedReader = null;
//        try {
//            bufferedReader = new BufferedReader(new FileReader(new File("build.gradle")));
//            String line;
//            while((line = bufferedReader.readLine()) != null){
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                bufferedReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        val file = File("build.gradle")
        /**
         * 第一种方法
         */
        val bufferedReader = BufferedReader(FileReader(file))
        var line: String

        while(true){
            line = bufferedReader.readLine()?:break
            println(line)
        }
        bufferedReader.close()
        /**
         * 第二种方法
         */
        BufferedReader(FileReader(file)).use {
            var line: String
            while(true){
                line = it.readLine()?:break
                println(line)
            }
        }
        /**
         * 第三种方法
         */
        File("build.gradle").readLines().forEach(::println)


    }



}