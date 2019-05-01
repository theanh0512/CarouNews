package pham.honestbee.carousell_news

import android.databinding.DataBindingUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pham.honestbee.carousell_news.binding.AppDataBindingComponent
import pham.honestbee.carousell_news.di.DaggerAppComponent

class NewsApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
