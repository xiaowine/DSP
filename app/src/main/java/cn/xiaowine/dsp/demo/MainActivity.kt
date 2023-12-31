package cn.xiaowine.dsp.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import cn.xiaowine.dsp.DSP
import cn.xiaowine.dsp.demo.config.Config
import cn.xiaowine.dsp.data.MODE
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DSP.init(application, BuildConfig.APPLICATION_ID, MODE.HOOK)
        val config = Config()
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