@file:Suppress("DEPRECATION")

package cn.xiaowine.dsp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import cn.xiaowine.dsp.data.MODE
import de.robv.android.xposed.XSharedPreferences

@SuppressLint("StaticFieldLeak")
object DSP {
    private lateinit var context: Context

    lateinit var sharedPreferences: SharedPreferences
    val isXSPf: Boolean
        get() {
            return try {
                XSharedPreferences::class.java.name
                true
            } catch (_: NoClassDefFoundError) {
                false
            }
        }


    @SuppressLint("WorldReadableFiles")
    fun init(context: Context, packageName: String, mode: MODE = MODE.APP) {
        this.context = context
        if (isXSPf) {
            val pref = XSharedPreferences(packageName, packageName)
            sharedPreferences = if (pref.file.canRead()) pref else error("XSharedPreferences is null")
            return
        }
        sharedPreferences = if (mode == MODE.HOOK) {
            DSP.context.createDeviceProtectedStorageContext().getSharedPreferences(packageName, Context.MODE_WORLD_READABLE)
        } else {
            DSP.context.getSharedPreferences(packageName, Context.MODE_PRIVATE)
        }
    }


    @SuppressLint("ApplySharedPref")
    fun save(pairs: Pair<String, Any>) {
        sharedPreferences.edit().apply {
            put(pairs.first, pairs.second)
            commit()
        }
    }

    fun saveLazy(pairs: Pair<String, Any>) {
        sharedPreferences.edit().apply {
            put(pairs.first, pairs.second)
            apply()
        }
    }

    private fun SharedPreferences.Editor.put(key: String, value: Any) {
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