package com.toolslab.challenge.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}
