package pham.honestbee.carousell_news.utils

import java.util.*
import java.util.concurrent.TimeUnit

object TimeAgo {

    val times: MutableMap<String, Long> = LinkedHashMap()

    init {
        times["year"] = TimeUnit.DAYS.toMillis(365)
        times["month"] = TimeUnit.DAYS.toMillis(30)
        times["week"] = TimeUnit.DAYS.toMillis(7)
        times["day"] = TimeUnit.DAYS.toMillis(1)
        times["hour"] = TimeUnit.HOURS.toMillis(1)
        times["minute"] = TimeUnit.MINUTES.toMillis(1)
        times["second"] = TimeUnit.SECONDS.toMillis(1)
    }

    @JvmOverloads
    fun toRelative(duration: Long, maxLevel: Int = times.size): String {
        var tempDuration = duration
        val res = StringBuilder()
        var level = 0
        for ((key, value) in times) {
            val timeDelta = tempDuration / value
            if (timeDelta > 0) {
                res.append(timeDelta)
                        .append(" ")
                        .append(key)
                        .append(if (timeDelta > 1) "s" else "")
                        .append(", ")
                tempDuration -= value * timeDelta
                level++
            }
            if (level == maxLevel) {
                break
            }
        }
        return if ("" == res.toString()) {
            "0 seconds ago"
        } else {
            res.setLength(res.length - 2)
            res.append(" ago")
            res.toString()
        }
    }

    fun toRelative(start: Date, end: Date): String {
        assert(start.after(end))
        return toRelative(end.time - start.time)
    }

    fun toRelative(start: Date, end: Date, level: Int): String {
        assert(start.after(end))
        return toRelative(end.time - start.time, level)
    }
}