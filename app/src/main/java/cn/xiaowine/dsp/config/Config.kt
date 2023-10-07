package cn.xiaowine.dsp.config

import android.content.Context
import cn.xiaowine.dsp.DSP
import cn.xiaowine.dsp.delegate.Delegate.serial
import cn.xiaowine.dsp.delegate.Delegate.serialLazy


class Config{
    var name: String by serial("xiaowine")
    var age: Int by serialLazy(18)
}
