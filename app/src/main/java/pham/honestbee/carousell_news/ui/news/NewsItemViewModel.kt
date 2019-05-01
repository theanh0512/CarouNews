package pham.honestbee.carousell_news.ui.news

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import pham.honestbee.carousell_news.utils.TimeAgo
import pham.honestbee.carousell_news.vo.News

class NewsItemViewModel(val news: News) : BaseObservable() {
    @Bindable
    fun getUrl(): String? {
        return if (!TextUtils.isEmpty(news.bannerUrl)) news.bannerUrl else ""
    }

    @Bindable
    fun getTitle(): String? {
        return if (!TextUtils.isEmpty(news.title)) news.title else ""
    }

    @Bindable
    fun getDescription(): String? {
        return if (!TextUtils.isEmpty(news.description)) news.description else ""
    }

    @Bindable
    fun getDate(): String? {
        return TimeAgo.toRelative(System.currentTimeMillis() - news.timeCreated * 1000, 2)
    }
}