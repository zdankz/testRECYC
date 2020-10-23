package com.example.testryc.views.main

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

import com.example.testryc.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_secon.*
import java.util.jar.Manifest

class Secon : AppCompatActivity() {
    //private val video : LinearLayout = findViewById(R.id.video)
    lateinit var mr : MediaRecorder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_secon)
        val intent = intent
        val url:String? = intent.getStringExtra("URL")
        val title:String? = intent.getStringExtra("name")
        val tentep : String = title.toString()
        val intenplay : Intent = Intent(this,rePlayRecord::class.java)

        val youtube :YouTubePlayerView = findViewById((R.id.youtube_player_view))
        lifecycle.addObserver(youtube)


        var path = Environment.getExternalStorageDirectory().toString()+"/Sounds/$tentep.3gp"

        mr = MediaRecorder()
        btnStart.isEnabled = false
        btnStop.isEnabled = false
        btnPlay.isEnabled = false

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 111
            )
            btnStart.isEnabled = true

        //bat dau ghi am
        btnStart.setOnClickListener {
            mr.setAudioSource(MediaRecorder.AudioSource.MIC)
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mr.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
            mr.setOutputFile(path)
            mr.prepare()
            mr.start()
            btnStart.isEnabled = false
            btnStop.isEnabled = true

        }

        //dung thu am
        btnStop.setOnClickListener {
            mr.stop()
            var duongdan = path.toString()
            btnStart.isEnabled = true
            btnPlay.isEnabled = true
            duongdantest.text = duongdan.toString()
        }

        //mghe lai doan ghi am
        btnPlay.setOnClickListener {
//            var mp = MediaPlayer()
//            mp.setDataSource(path)
//            mp.prepare()
//            mp.start()
            var duongdan = path.toString()
            intenplay.putExtra("duongdan",duongdan.toString())
            startActivity(intenplay)
            //intenplay.putExtra("duongdan",path)
        }





        youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videourl = url.toString()
                youTubePlayer.loadVideo(videourl,0f)
                youtube_player_view.enterFullScreen()
                youtube_player_view.exitFullScreen()
                youtube_player_view.isFullScreen()
                youtube_player_view.toggleFullScreen()


            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            btnStart.isEnabled = true
    }

    override fun onDestroy() {
        super.onDestroy()
        detroy()
    }
    fun detroy(){
        youtube_player_view.release()
    }
}
