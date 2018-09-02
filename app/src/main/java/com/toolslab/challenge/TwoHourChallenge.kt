package com.toolslab.challenge

import com.facebook.drawee.backends.pipeline.Fresco
import com.toolslab.challenge.base_repository.di.DaggerRepositoryComponent
import com.toolslab.challenge.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

class TwoHourChallenge : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val repositoryComponent = DaggerRepositoryComponent.builder().build()
        return DaggerAppComponent
                .builder()
                .create(this)
                .repositoryComponent(repositoryComponent)
                .build()
    }

}
