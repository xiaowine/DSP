package cn.xiaowine.dsp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import cn.xiaowine.dsp.annotation.SerializeConfig
import cn.xiaowine.dsp.data.MODE
import cn.xiaowine.dsp.delegate.SerialDelegate
import cn.xiaowine.dsp.delegate.SerialLazyDelegate
import de.robv.android.xposed.XSharedPreferences
import kotlin.properties.ReadWriteProperty

abstract class DSP(private val context: Context?, private val packageName: String, val isXSPf: Boolean = false) {
    val sharedPreferences: SharedPreferences by lazy { getSPf()!! }
    private var key: String = ""


    @Suppress("DEPRECATION")
    @SuppressLint("WrongConstant", "WorldReadableFiles")
    private fun getSPf(): SharedPreferences? {
        key = this::class.java.getAnnotation(SerializeConfig::class.java)?.key ?: error("key is empty")
        val mode = this::class.java.getAnnotation(SerializeConfig::class.java)?.mode ?: MODE.APP
        if (isXSPf) {
            val pref = XSharedPreferences(packageName, key)
            pref.file.canRead()
            Log.d("XSP", "getSPf: ${pref.file.canRead()}")
            return if (pref.file.canRead()) pref else null
        }
        return if (mode == MODE.HOOK) {
            context!!.createDeviceProtectedStorageContext().getSharedPreferences(key, Context.MODE_WORLD_READABLE)
        } else {
            context!!.getSharedPreferences(key, Context.MODE_PRIVATE)
        }
    }

    inline fun <reified T> serial(default: T? = null): ReadWriteProperty<Any, T> = SerialDelegate(default, sharedPreferences, isXSPf)


    inline fun <reified T> serialLazy(default: T? = null): ReadWriteProperty<Any, T> = SerialLazyDelegate(default, sharedPreferences, isXSPf)


    companion object {
        fun SharedPreferences.Editor.put(key: String, value: Any) {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
            }
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> SharedPreferences.opt(key: String, defValue: T?): T {
            return when (defValue) {
                is String -> getString(key, defValue.toString()) as T
                is Long -> getLong(key, defValue.toLong()) as T
                is Int -> getInt(key, defValue) as T
                is Boolean -> getBoolean(key, defValue) as T
                is Float -> getFloat(key, defValue) as T
                else -> defValue ?: throw IllegalArgumentException("defValue is null")
            }
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> XSharedPreferences.opt(key: String, defValue: T?): T {
            return when (defValue) {
                is String -> getString(key, defValue.toString()) as T
                is Long -> getLong(key, defValue.toLong()) as T
                is Int -> getInt(key, defValue) as T
                is Boolean -> getBoolean(key, defValue) as T
                is Float -> getFloat(key, defValue) as T
                else -> defValue ?: throw IllegalArgumentException("defValue is null")
            }
        }
    }
}