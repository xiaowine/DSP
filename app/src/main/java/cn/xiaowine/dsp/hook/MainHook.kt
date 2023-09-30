package cn.xiaowine.dsp.hook


import android.util.Log
import cn.xiaowine.dsp.hook.app.SystemUi
import com.github.kyuubiran.ezxhelper.EzXHelper
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MainHook : IXposedHookLoadPackage, IXposedHookZygoteInit {


    private val systemUiPackage = "com.android.systemui"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {


        EzXHelper.initHandleLoadPackage(lpparam)
        when (lpparam.packageName) {
            systemUiPackage -> {
                initHooks(SystemUi)
            }
        }

    }


    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        EzXHelper.initZygote(startupParam)
    }

    private fun initHooks(vararg hook: BaseHook) {
        hook.forEach {
            runCatching {
                if (it.isInit) return@forEach
                it.init()
                it.isInit = true
                Log.i("Xposed", "Inited hook: ${it.javaClass.simpleName}")
            }.exceptionOrNull()?.let {
                Log.i("Xposed", "Failed init hook: ${it.javaClass.simpleName}")
            }
        }
    }
}