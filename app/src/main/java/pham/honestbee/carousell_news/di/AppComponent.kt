package pham.honestbee.carousell_news.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pham.honestbee.carousell_news.NewsApplication
import pham.honestbee.carousell_news.repository.NewsRepository
import javax.inject.Singleton

/**
 *[AndroidSupportInjectionModule]
 *is the module from Dagger.Android that helps with the generation
 *and location of sub-components.
 * */
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ActivityBindingModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<NewsApplication> {

    val newsRepository: NewsRepository

    // Syntactic sugar to do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}