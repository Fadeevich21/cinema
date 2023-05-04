package com.example.cinema

import android.app.Application
import com.example.cinema.di.appModule
import com.example.cinema.di.daoModule
import com.example.cinema.di.databaseModule
import com.example.cinema.di.mapperModule
import com.example.cinema.di.repositoryModule
import com.example.cinema.di.useCaseModule
import com.example.cinema.domain.model.User
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    companion object {
        var user: User? = null
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    repositoryModule,
                    useCaseModule,
                    databaseModule,
                    daoModule,
                    mapperModule
                )
            )
        }
    }
}