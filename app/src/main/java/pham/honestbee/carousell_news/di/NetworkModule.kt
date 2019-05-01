package pham.honestbee.carousell_news.di

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import pham.honestbee.carousell_news.BuildConfig
import pham.honestbee.carousell_news.api.NewsService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    @Singleton
    @Provides
    fun provideService(okHttpClient: OkHttpClient): NewsService {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build()
                .create(NewsService::class.java)
    }
}