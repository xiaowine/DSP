package cn.xiaowine.dsp.demo.hook

abstract class BaseHook {
    var isInit: Boolean = false
    abstract fun init()
}