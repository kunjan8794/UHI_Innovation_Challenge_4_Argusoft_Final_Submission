package com.uhi.data.local.pref

interface Preference {

    fun setLogin()

    fun isLogin(): Boolean

    fun setAppLanguage(lanCode: String)

    fun getAppLanguage(): String

    fun clear()
}
