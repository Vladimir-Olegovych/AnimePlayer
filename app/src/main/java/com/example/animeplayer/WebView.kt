package com.example.animeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.example.animeplayer.databinding.ActivityWebViewBinding

class WebView : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ws: WebSettings = binding.webView.settings
        ws.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        val urL = intent.extras?.getString("urL")
        binding.webView.loadUrl(urL.toString())
    }
}