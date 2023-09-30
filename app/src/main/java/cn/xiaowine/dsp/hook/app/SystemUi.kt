package cn.xiaowine.dsp.hook.app

import android.app.Application
import android.util.Log
import cn.xiaowine.dsp.BuildConfig
import cn.xiaowine.dsp.config.Config
import cn.xiaowine.dsp.hook.BaseHook
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import kotlin.random.Random

object SystemUi : BaseHook() {
    override fun init() {
        Application::class.java.methodFinder().filterByName("attach").first().createHook {
            after {

                val config = Config(it.thisObject as Application, BuildConfig.APPLICATION_ID, true)
                config.name = "小明${System.currentTimeMillis()}"
                config.age = Random.nextInt(100)
                Log.i("aaaaaaaaaaaaa", "name:${config.name} age:${config.age}")

            }
        }
    }
}