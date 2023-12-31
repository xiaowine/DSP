package cn.xiaowine.dsp.demo.hook.app

import android.app.Application
import android.util.Log
import cn.xiaowine.dsp.demo.BuildConfig
import cn.xiaowine.dsp.DSP
import cn.xiaowine.dsp.demo.config.Config
import cn.xiaowine.dsp.data.MODE
import cn.xiaowine.dsp.demo.hook.BaseHook
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import kotlin.random.Random

object SystemUi : BaseHook() {
    override fun init() {
        Application::class.java.methodFinder().filterByName("attach").first().createHook {
            after {
                DSP.init(it.thisObject as Application, BuildConfig.APPLICATION_ID, MODE.HOOK,true)
                val config = Config()
                config.name = "小明${System.currentTimeMillis()}"
                config.age = Random.nextInt(100)
                Log.i("aaaaaaaaaaaaa", "name:${config.name} age:${config.age}")

            }
        }
    }
}