package cn.xiaowine.dsp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import cn.xiaowine.dsp.config.Config
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val config = Config(application, application.packageName, false)
        findViewById<TextView>(R.id.textView).text = config.name
        findViewById<TextView>(R.id.textView2).text = config.age.toString()
        findViewById<Button>(R.id.button).setOnClickListener {
            config.name = "小明${System.currentTimeMillis()}"
            findViewById<TextView>(R.id.textView).text = config.name
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            config.age = Random.nextInt(100)
            findViewById<TextView>(R.id.textView2).text = config.age.toString()
        }
    }
}