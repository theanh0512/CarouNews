package pham.honestbee.carousell_news.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pham.honestbee.carousell_news.ui.news.MainActivity

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}