package com.toolslab.challenge.di

import com.toolslab.challenge.view.main.FeedActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector
    abstract fun feedActivity(): FeedActivity

}
