package com.uhi.utils.localization

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import com.uhi.data.local.pref.PreferenceManager.Companion.APP_LANGUAGE
import java.util.*

object LocaleHelper {


    fun onAttach(context: Context): Context {
        val locale = load(context)
        return setLocale(context, locale)
    }

    fun setLocale(context: Context, locale: Locale): Context {
        persist(context, locale)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, locale)
        } else updateResourcesLegacy(context, locale)

    }

    fun isRTL(locale: Locale): Boolean {
        return Locales.RTL.contains(locale.language)
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    private fun persist(context: Context, locale: Locale?) {
        if (locale == null) return
        getPreferences(context)
            .edit()
            .putString(APP_LANGUAGE, locale.language)
            .apply()
    }

    private fun load(context: Context): Locale {
        val preferences = getPreferences(context)
        val language = preferences.getString(APP_LANGUAGE, Locale.getDefault().language)
        return Locale(language)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    @SuppressWarnings("deprecation")
    private fun updateResourcesLegacy(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

}