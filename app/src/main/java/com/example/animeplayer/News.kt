package com.example.animeplayer

import android.util.Log
import org.jsoup.Jsoup

class News(val title: String,
           val image: String,
           val urL: String) {

    companion object {

        fun getNews(page: Int): List<News> {
            try {
                val rows = Jsoup
                    .connect("https://animejoy.ru/page/$page/")
                    .get().getElementsByClass("block story shortstory")
                val result = ArrayList<News>()

                for (row in rows) {
                    val categories = row.children()
                    val image = categories[1].child(2).child(3).attr("src")
                    val title = categories[0].text()
                    val urL = categories[0].child(0).children().attr("href")

                    val n = News(title, "https://animejoy.ru$image", urL)
                    result.add(n)
                }

                return result

            } catch (e: Throwable) {
                Log.d("lol", "Error", e)
                return emptyList()
            }
        }
    }
}