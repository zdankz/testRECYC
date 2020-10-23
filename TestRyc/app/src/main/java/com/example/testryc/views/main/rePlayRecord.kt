package com.example.testryc.views.main

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testryc.R
import kotlinx.android.synthetic.main.activity_re_play_record.*

class rePlayRecord : AppCompatActivity() {
    lateinit var mp : MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_play_record)
        var intent = intent
        var tendf: String? = intent.getStringExtra("duongdan")
        tesst.text = tendf.toString()

        var mr = MediaPlayer()
        mp.setDataSource(tendf)
        mp.prepare()
        mp.start()


    }
}
