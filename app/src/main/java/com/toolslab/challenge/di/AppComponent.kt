package com.toolslab.challenge.di

import android.app.Application
import com.toolslab.challenge.TwoHourChallenge
import com.toolslab.challenge.base_repository.di.RepositoryComponent
import com.toolslab.challenge.view.main.FeedModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivitiesBindingModule::class,
            FeedModule::class
        ],
        dependencies = [
            RepositoryComponent::class
        ]
)
interface AppComponent : AndroidInjector<TwoHourChallenge> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun repositoryComponent(repositoryComponent: RepositoryComponent): Builder

        fun build(): AppComponent
    }
}
