package pham.honestbee.carousell_news.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import pham.honestbee.carousell_news.ui.news.NewsAdapter
import pham.honestbee.carousell_news.vo.News

class RecyclerViewDataBinding {
    /**
     * Binds the data to the [android.support.v7.widget.RecyclerView.Adapter], sets the
     * recycler view on the adapter, and performs some other recycler view initialization.
     *
     * @param recyclerView passed in automatically with the data binding
     * @param adapter      must be explicitly passed in
     * @param data         must be explicitly passed in
     */
    @BindingAdapter("app:adapter", "app:data")
    fun bind(recyclerView: RecyclerView?, adapter: NewsAdapter?, data: List<News>?) {
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(recyclerView?.context, LinearLayoutManager.VERTICAL, false)
        adapter?.updateData(data)
        val animation = TranslateAnimation(Animation.ABSOLUTE, //from xType
                0f,
                Animation.ABSOLUTE, //to xType
                0f,
                Animation.ABSOLUTE, //from yType
                200f,
                Animation.ABSOLUTE, //to yType
                0f)
        animation.duration = 700
        recyclerView?.animation = animation
        animation.start()
    }
}