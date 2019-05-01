package pham.honestbee.carousell_news.ui.news

import io.reactivex.Observable
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import pham.honestbee.carousell_news.repository.NewsRepository
import pham.honestbee.carousell_news.vo.News

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val rule = MockitoJUnit.rule()
    private lateinit var mockWebServer: MockWebServer

    @Mock
    lateinit var newsRepository: NewsRepository
    lateinit var viewModel: MainViewModel

    @Before
    fun before() {
        mockWebServer = MockWebServer()
        val news = News("123", "Test Title", "test description",
                "https://via.placeholder.com/600/d32776",
                1532939458, 5)
        val newsList = ArrayList<News>()
        newsList.add(news)
        `when`(newsRepository.getNewsList()).thenReturn(Observable.just(newsList))
        viewModel = MainViewModel(newsRepository)
    }

    @Test
    fun init() {
        verify(newsRepository).getNewsList()
        Mockito.verifyNoMoreInteractions(newsRepository)
    }

    @Test
    fun verifyLoadingIsFalseWhenReturn() {
        verify(newsRepository, times(1)).getNewsList()
        assert(!viewModel.loading.get())
        assert(viewModel.loadSuccess.get())
        MatcherAssert.assertThat(viewModel.newsList[0].id, `is`("123"))
    }
}