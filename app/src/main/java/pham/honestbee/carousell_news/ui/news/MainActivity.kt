package pham.honestbee.carousell_news.ui.news

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import pham.honestbee.carousell_news.R
import pham.honestbee.carousell_news.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var binding: ActivityMainBinding? = null
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModelAndBind()
    }

    private fun initViewModelAndBind() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        binding?.viewModel = viewModel
        binding?.rvImage?.layoutManager = LinearLayoutManager(binding?.rvImage?.context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.sort_popular -> {
                viewModel.apply {
                    isPopularSort.value = true
                    sortNews()
                }
                true
            }
            R.id.sort_recent -> {
                viewModel.apply {
                    isPopularSort.value = false
                    sortNews()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}