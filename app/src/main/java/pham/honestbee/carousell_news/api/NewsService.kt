package pham.honestbee.carousell_news.api

import io.reactivex.Observable
import pham.honestbee.carousell_news.vo.News
import retrofit2.http.GET

interface NewsService {
    @GET("carousell_news.json")
    fun getNews(): Observable<List<News>>
}