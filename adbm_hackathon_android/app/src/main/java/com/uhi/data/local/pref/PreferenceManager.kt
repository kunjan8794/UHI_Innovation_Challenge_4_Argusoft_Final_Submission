package com.uhi.data.local.pref

import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*

class PreferenceManager(private val sharedPreferences: SharedPreferences) : Preference {

    companion object {
        private const val IS_LOGIN = "pref_is_login"
        const val APP_LANGUAGE = "APP_LANGUAGE"
        const val EN = "en"
        const val HN = "hi"
        const val GU = "gu"
    }

    override fun setLogin() {
        sharedPreferences.edit().putBoolean(IS_LOGIN, true).apply()
    }

    override fun isLogin(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGIN, false)
    }

    override fun clear() {
//        sharedPreferences.c/()
    }

    override fun setAppLanguage(lanCode: String) {
        sharedPreferences.edit().putString(APP_LANGUAGE, lanCode).apply()
    }

    override fun getAppLanguage(): String {
        return sharedPreferences.getString(APP_LANGUAGE, EN) ?: EN
    }

}
