package cn.xiaowine.dsp.config

import android.app.Application
import cn.xiaowine.dsp.DSP
import cn.xiaowine.dsp.annotation.SerializeConfig
import cn.xiaowine.dsp.data.MODE

@SerializeConfig("config", MODE.HOOK)
class Config(application: Application, packageName: String, isXSPf: Boolean = false) : DSP(application, packageName, isXSPf) {
    var name: String by serial("xiaowine")
    var age: Int by serialLazy(18)
}
