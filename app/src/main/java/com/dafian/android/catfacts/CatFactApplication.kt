package com.dafian.android.catfacts

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CatFactApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CatFactApplication)
            modules(
                appModule,
                cacheModule,
                dataModule,
                domainModule,
                presentationModule,
                remoteModule,
                useCaseModule,
                mobileUiModule
            )
        }
    }
}