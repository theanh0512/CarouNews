package pham.honestbee.carousell_news.ui.news

import android.arch.lifecycle.ViewModel
import android.databinding.*
import android.util.Log
import android.util.MutableBoolean
import io.reactivex.disposables.CompositeDisposable
import pham.honestbee.carousell_news.repository.NewsRepository
import pham.honestbee.carousell_news.vo.News
import javax.inject.Inject

class MainViewModel @Inject constructor(val newsRepository: NewsRepository) : ViewModel(), Observable {
    val loading = ObservableBoolean(false)
    val loadSuccess = ObservableBoolean(false)
    val newsList = ObservableArrayList<News>()
    val isPopularSort = MutableBoolean(false)
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    private val compositeDisposable = CompositeDisposable()
    private val imageAdapter = NewsAdapter(object : NewsAdapter.ItemClickListener {
        override fun onImageClick(News: News?) {}
    })

    init {
        loadNews()
    }

    // we use these here because the databinding lib is still having a timing bug
    // where calling notifyChanged() or notifyPropertyChanged(int) results in no action taking place
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    @Bindable
    fun getAdapter(): NewsAdapter {
        return this.imageAdapter
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loadNews() {
        loading.set(true)
        compositeDisposable.add(newsRepository.getNewsList()
                .subscribe({ response ->
                    loading.set(false)
                    loadSuccess.set(true)
                    newsList.clear()
                    newsList.addAll(response)
                    sortNews()
                }, { throwable ->
                    throwable.printStackTrace()
                    loading.set(false)
                    loadSuccess.set(false)
                })
                { Log.d("News", "Completed") })
    }

    fun sortNews() {
        if (isPopularSort.value) {
            newsList.sortBy { it.rank }
        } else {
            newsList.sortByDescending { it.timeCreated }
        }
        reloadData()
    }

    private fun reloadData() {
        val temp = ArrayList<News>()
        temp.addAll(newsList)
        newsList.clear()
        newsList.addAll(temp)
    }
}
