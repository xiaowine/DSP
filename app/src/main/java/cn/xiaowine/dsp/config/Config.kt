package cn.xiaowine.dsp.config

import android.content.Context
import cn.xiaowine.dsp.DSP
import cn.xiaowine.dsp.annotation.SerializeConfig
import cn.xiaowine.dsp.data.MODE

@SerializeConfig("config", MODE.HOOK)
class Config(context: Context, packageName: String, isXSPf: Boolean = false) : DSP(context, packageName, isXSPf) {
    var name: String by serial("xiaowine")
    var age: Int by serialLazy(18)
}
