package pham.honestbee.carousell_news.ui.news

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.junit.MockitoJUnit
import pham.honestbee.carousell_news.vo.News

/**
 * Created by Pham on 28/4/2019.
 */
@RunWith(JUnit4::class)
class NewsItemViewModelTest {
    @get:Rule
    val rule = MockitoJUnit.rule()

    lateinit var viewModel: NewsItemViewModel

    @Before
    fun before() {
        val news = News("123", "Test Title", "test description",
                "https://via.placeholder.com/600/d32776",
                1532939458, 5)
        viewModel = NewsItemViewModel(news)
    }

    @Test
    fun getDate() {
        MatcherAssert.assertThat(viewModel.getUrl(), CoreMatchers.`is`("https://via.placeholder.com/600/d32776"))
    }

    @Test
    fun getTitle() {
        MatcherAssert.assertThat(viewModel.getTitle(), CoreMatchers.`is`("Test Title"))
    }

    @Test
    fun getDescription() {
        MatcherAssert.assertThat(viewModel.getDescription(), CoreMatchers.`is`("test description"))
    }
}