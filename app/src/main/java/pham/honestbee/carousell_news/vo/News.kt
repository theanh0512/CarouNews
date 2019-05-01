package pham.honestbee.carousell_news.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by Pham on 28/4/2019.
 */
data class News(var id: String, var title: String, var description: String,
                @SerializedName("banner_url")
                var bannerUrl: String,
                @SerializedName("time_created")
                var timeCreated: Long,
                var rank: Int)