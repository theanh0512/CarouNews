package pham.honestbee.carousell_news.ui.news

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import pham.honestbee.carousell_news.TestSchedulerRule
import pham.honestbee.carousell_news.api.NewsService
import pham.honestbee.carousell_news.repository.NewsRepository
import pham.honestbee.carousell_news.vo.News
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class NewsRepositoryTest {
    private lateinit var repository: NewsRepository

    private val service = Mockito.mock(NewsService::class.java)

    @get:Rule
    val rule = TestSchedulerRule()

    @Before
    fun before() {
        repository = NewsRepository(service)
    }

    @Test
    fun testGetNewsList() {
        val image = News("123", "Test Title", "test description",
                "https://via.placeholder.com/600/d32776",
                1532939458, 5)
        val newsList = ArrayList<News>()
        newsList.add(image)
        `when`(service.getNews()).thenReturn(Observable.just(newsList))
        val testObserver:TestObserver<List<News>> = repository.getNewsList().test()
        //repository.getNewsList().subscribe(testObserver)
        rule.testScheduler.advanceTimeBy(2, TimeUnit.SECONDS)
        testObserver
                .assertNoErrors()
                .assertValue{ l:List<News> -> l.size == 1}
    }
}