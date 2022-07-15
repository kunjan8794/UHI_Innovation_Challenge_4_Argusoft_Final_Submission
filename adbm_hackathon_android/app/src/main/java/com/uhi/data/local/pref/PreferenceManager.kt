package com.uhi.data.local.pref

import androidx.core.content.edit
import java.util.*

class PreferenceManager(private val sharedPreferences: EncPref) : Preference {

    companion object {
        private const val IS_LOGIN = "pref_is_login"
        const val APP_LANGUAGE = "APP_LANGUAGE"
        const val EN = "en"
        const val HN = "hn"
        const val GU = "gu"
    }

    override fun setLogin() {
        sharedPreferences.putBoolean(IS_LOGIN, true)
    }

    override fun isLogin(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGIN, false)
    }

    override fun clear() {
        sharedPreferences.clear()
    }

    override fun setAppLanguage(lanCode: String) {
        sharedPreferences. putString(APP_LANGUAGE, lanCode)
    }

    override fun getAppLanguage(): String {
        return sharedPreferences.getString(APP_LANGUAGE, EN)
    }

}
