package com.example.testryc.views.main

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testryc.R
import com.example.testryc.models.entity.User
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.RelativeLayout
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
     //val rlRoot: RelativeLayout = findViewById(R.id.rlRoot)
    private var REQUEST_CODE_SPEED_INPUT = 100
    val users = ArrayList<User>()
    lateinit var adapter: UserAdapter
    //var urlyoutube: String = "https://byyswag.000webhostapp.com/?keyword="

    val loi : String = "Nhap bai hat"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        micc.setOnClickListener {
            //clearListVideo()
            //speaktotext()
        }
        btnSearch.setOnClickListener {
            var key_serch: String = search.text.toString()

            //var key_serch2 : String = key_serch.trim()

            if(key_serch.isNullOrBlank() == true)
            {

                Toast.makeText(applicationContext,loi, Toast.LENGTH_LONG).show()
            }else {
                var key_serch2:String = key_serch.replace(' ','+')
                var urlyoutube: String = "https://byyswag.000webhostapp.com/?keyword="
                urlyoutube = urlyoutube.plus(key_serch2)
                clearListVideo()
                Getdata().execute(urlyoutube)
                initAdapter()
                initRecyclerView()
            }
        }
    }
    private fun speaktotext() {
        if(!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "K nghe gi luon ...", Toast.LENGTH_SHORT).show()
        }else
        {
            var i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Noi gi di :3")
            startActivityForResult(i,REQUEST_CODE_SPEED_INPUT)
        }
    }

    //Hàm xóa ds trong mảng Vd
    fun clearListVideo(){
        users.clear()

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SPEED_INPUT && resultCode == Activity.RESULT_OK){
            var urlyoutube: String = "https://byyswag.000webhostapp.com/?keyword="
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            search.setText("")
            search.setText(result?.get(0).toString())
            var key_input : String = search.text.toString()
            urlyoutube = urlyoutube.plus(key_input)
            Getdata().execute(urlyoutube)
            initAdapter()
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        rvAmUser.layoutManager = LinearLayoutManager(this)
        rvAmUser.setHasFixedSize(true)
        rvAmUser.adapter = adapter
    }

    private fun initAdapter() {
        adapter = UserAdapter(users)
    }

//    private fun initData() {
//        // Generate sample data
//
//        //val names = arrayListOf("Nguyễn Văn Y", "NGuyễn Văn A", "Nguyễn Văn B")
//      //  for (i in 1..100) {
//            // Add data to repository collection
//            //users.add(User("",i, names.random(),"jkjk"))
//            //users.add(User("",i,"aaa","sasa"))
//      //  }
//        // Notify to adapter that data was updated
//       // adapter.notifyDataSetChanged()
//    }

    inner class Getdata : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg p0: String?): String {
            return getContentURL(p0[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var idd: String=""
            var namee: String = ""
            var url_s: String = ""
            var jsonArray: JSONArray = JSONArray(result)
            for (video in 0..jsonArray.length() - 1) {
                var objectVD: JSONObject = jsonArray.getJSONObject(video)
                idd= objectVD.getString("ID")
                namee = objectVD.getString("song")
                url_s = objectVD.getString("songkey")
                users.add(User("",idd,namee,url_s))
                //listview.Name.text = name
                //mangbaihat.add(id+"\n"+name+"")
            }
            adapter.notifyDataSetChanged()
        }
    }
    private fun getContentURL(url: String?) : String{
        var content: StringBuilder = StringBuilder();
        val url: URL = URL(url)
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        val inputStreamReader = InputStreamReader(urlConnection.inputStream)
        val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

        var line: String = ""
        try {
            do {
                line = bufferedReader.readLine()
                if(line != null){
                    content.append(line)
                }
            }while (line != null)
            bufferedReader.close()
        }catch (e: Exception){
            Log.d("AAA", e.toString())
        }
        return content.toString()
    }
}
