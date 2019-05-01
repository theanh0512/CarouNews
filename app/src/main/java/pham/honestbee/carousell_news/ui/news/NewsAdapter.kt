package pham.honestbee.carousell_news.ui.news

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import pham.honestbee.carousell_news.R
import pham.honestbee.carousell_news.databinding.ItemNewsBinding
import pham.honestbee.carousell_news.vo.News

class NewsAdapter constructor(val listener: ItemClickListener) : RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {
    private val data: MutableList<News>

    init {
        this.data = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_news,
                FrameLayout(parent.context), false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataModel = data[position]
        holder.setViewModel(NewsItemViewModel(dataModel))
        holder.binding?.itemView?.setOnClickListener {
            listener.onImageClick(data[position])
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onViewAttachedToWindow(holder: DataViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: DataViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

    fun updateData(data: List<News>?) {
        this.data.clear()
        this.data.addAll(data as ArrayList)
        notifyDataSetChanged()
    }

    // in case the view cannot be recycled due to transient state
    override fun onFailedToRecycleView(holder: DataViewHolder): Boolean {
        holder.itemView.clearAnimation()
        return super.onFailedToRecycleView(holder)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemNewsBinding? = null

        init {
            bind()
        }

        fun bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView)
            }
        }

        fun unbind() {
            if (binding != null) {
                binding!!.unbind() // Don't forget to unbind
            }
        }

        fun setViewModel(viewModel: NewsItemViewModel) {
            if (binding != null) {
                binding!!.viewModel = viewModel
            }
        }
    }

    interface ItemClickListener {
        fun onImageClick(News: News?)
    }

    companion object {
        private val TAG = "DataAdapter"
    }
}