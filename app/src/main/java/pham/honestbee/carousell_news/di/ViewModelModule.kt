package pham.honestbee.carousell_news.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pham.honestbee.carousell_news.ui.news.MainViewModel
import pham.honestbee.carousell_news.viewmodel.NewsViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: NewsViewModelFactory): ViewModelProvider.Factory
}
