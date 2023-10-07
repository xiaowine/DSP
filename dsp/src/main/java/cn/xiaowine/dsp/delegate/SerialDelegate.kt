package cn.xiaowine.dsp.delegate

import android.content.SharedPreferences
import android.util.Log
import cn.xiaowine.dsp.DSP.opt
import cn.xiaowine.dsp.DSP.isXSPf
import cn.xiaowine.dsp.DSP.save
import cn.xiaowine.dsp.DSP.sharedPreferences
import de.robv.android.xposed.XSharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SerialDelegate<T>(private val default: T?) : ReadWriteProperty<Any, T> {
    private val spEditor: SharedPreferences.Editor
        get() = sharedPreferences.edit()

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val key = property.name
        if (isXSPf) {
            return (sharedPreferences as XSharedPreferences).opt(key, default)
        }
        return sharedPreferences.opt(key, default)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (isXSPf) return
        val key = property.name
        spEditor.apply {
            save(key to value as Any)
            commit()
        }
    }

}