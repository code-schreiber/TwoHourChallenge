package com.toolslab.challenge.view.main

import dagger.Module
import dagger.Provides

@Module
class FeedModule {

    @Provides
    fun providePresenter(feedPresenter: FeedPresenter): FeedContract.Presenter = feedPresenter

}
