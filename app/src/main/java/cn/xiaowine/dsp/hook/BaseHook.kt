package cn.xiaowine.dsp.hook

abstract class BaseHook {
    var isInit: Boolean = false
    abstract fun init()
}