package kotlindemo.liuyu1.kotlindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import  kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lambda简化
        bt_jump.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                toast("Click")
            }
        })
        bt_jump.setOnClickListener({ view -> toast("Click") })
        //如果左边的参数没有使用到，我们甚至可以省略左边的参数：
        bt_jump.setOnClickListener({ toast("Click") })
        //如果这个函数的最后一个参数是一个函数，我们可以把这个函数移动到圆括号外面：
        bt_jump.setOnClickListener() { toast("Click") }
        //如果这个函数只有一个参数，我们可以省略这个圆括号：
        bt_jump.setOnClickListener { toast("Click") }

        bt_jump.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, Test2Activity::class.java)
            startActivity(intent)
        })
        //需要anko
        bt_jump.onClick { view ->
            startActivity<Test2Activity>("Key" to "From MainActivity")
        }

    }
}
