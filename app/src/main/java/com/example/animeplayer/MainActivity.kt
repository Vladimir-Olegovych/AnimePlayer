package com.example.animeplayer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter(this, Handler(Looper.getMainLooper()))

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter

        adapter.refresh()
    }

    fun itemOnClickList(news: News){
        val intent = Intent(this, Jojo5Activity::class.java)
        intent.putExtra("image", news.image)
        intent.putExtra("urL", news.urL)
        intent.putExtra("title", news.title)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        adapter.shutdown()
    }
}