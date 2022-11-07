package com.sulistyo.github.user

import android.app.Application
import com.sulistyo.github.user.core.di.databaseModule
import com.sulistyo.github.user.core.di.networkModule
import com.sulistyo.github.user.core.di.repositoryModule
import com.sulistyo.github.user.di.usecaseModule
import com.sulistyo.github.user.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                listOf(
                    databaseModule, networkModule, repositoryModule, usecaseModule, viewModelModule
                )
            )
        }
    }

}