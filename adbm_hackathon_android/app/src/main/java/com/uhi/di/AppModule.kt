package com.uhi.di

import android.content.Context
import com.uhi.Application
import com.uhi.data.local.database.Database
import com.uhi.data.local.database.DatabaseManager
import com.uhi.data.local.database.RoomDatabase
import com.uhi.data.local.pref.EncPref
import com.uhi.data.local.pref.Preference
import com.uhi.data.local.pref.PreferenceManager
import com.uhi.data.remote.Api
import com.uhi.data.remote.ApiManager
import com.uhi.utils.common.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppEncSharedPref(@ApplicationContext context: Context): EncPref {
        return EncPref.Builder()
            .serPrefName(context.packageName)
            .setContext(context)
            .build()
    }

    /*   @Singleton
       @Provides
       fun provideAppRoomDatabase(@ApplicationContext context: Context): RoomDatabase {
           return Room.databaseBuilder(
               context,
               RoomDatabase::class.java,
               "app.db"
           ).build()
       }*/

    @Singleton
    @Provides
    fun provideAppPreference(encPref: EncPref): Preference {
        return PreferenceManager(encPref)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(roomDatabase: RoomDatabase): Database {
        return DatabaseManager(roomDatabase)
    }

    @Singleton
    @Provides
    fun provideNetworkHelper(application: android.app.Application): NetworkHelper {
        return NetworkHelper(application)
    }

    @Singleton
    @Provides
    fun provideAppApi(networkHelper: NetworkHelper, preference: Preference): Api {
        return ApiManager(networkHelper, preference)
    }
}