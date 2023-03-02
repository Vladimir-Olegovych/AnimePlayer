package com.example.animeplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.animeplayer.databinding.ActivityJojo5Binding
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

var num1 = ""

class Jojo5Activity : AppCompatActivity() {

    private lateinit var doc: Document
    private lateinit var secThread: Thread
    private lateinit var runnable: Runnable

    private lateinit var binding: ActivityJojo5Binding
    private lateinit var urL: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJojo5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.extras?.getString("image")
        val title = intent.extras?.getString("title")
        urL = intent.extras?.getString("urL").toString()

        Picasso.get().load(image).into(binding.imageAnime)
        binding.textAnime. text = title
        init()
    }
    fun onClick(view: View){
        if (binding.buttonSee == view){
            val intent = Intent(this, PlayerActivity::class.java)

            if (binding.textSeria.text.toString() == "1") {
                num1 =
                    "https://noda2.cdnjoy.site/Tomo-chan-wa-Onnanoko/01-1080.mp4"
                startActivity(intent)
            }

            if (binding.textSeria.text.toString() == "2") {
                num1 =
                    "https://www.secvideo1.online/get_file/15/ab4a8d134148885ecebcbd735b12bcb5d7f4314b38/500000/500485/500485_720p.mp4"
                startActivity(intent)
            }

            if (binding.textSeria.text.toString() == "3") {
                num1 =
                    "https://www.secvideo1.online/get_file/15/ff165d5e6409f343efb3761281b528dba905d74f4f/500000/500486/500486_720p.mp4"
                startActivity(intent)
            }
        }
    }
    fun onClickText(view: View){
        if (binding.textView3 == view){
            val intent = Intent(this, WebView::class.java)
            intent.putExtra("urL", urL)
            startActivity(intent)
        }
    }
    private fun init() {

            runnable = Runnable {
                run() {
                    getWeb()
                }
            }
            secThread = Thread(runnable)
            secThread.start()
        }

    private fun getWeb() {
        val urL = intent.extras?.getString("urL")


        try {
            doc = Jsoup.connect(urL).get()
            val description: Elements = doc.getElementsByClass("pcdescrf")
            binding.textDescriptionAnime.text = description.get(0).children().text()
        } catch (e: IOException) {
            e.printStackTrace()
        } }
}