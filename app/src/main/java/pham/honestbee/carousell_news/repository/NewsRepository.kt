package pham.honestbee.carousell_news.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pham.honestbee.carousell_news.api.NewsService
import pham.honestbee.carousell_news.vo.News
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(val newsService: NewsService) {
    fun getNewsList(): Observable<List<News>> {
        return newsService.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}