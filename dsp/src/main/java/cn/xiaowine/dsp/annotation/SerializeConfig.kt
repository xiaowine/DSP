package cn.xiaowine.dsp.annotation

import cn.xiaowine.dsp.data.MODE


@Target(AnnotationTarget.CLASS)
annotation class SerializeConfig(
    val key: String,
    val mode: MODE = MODE.APP,
)