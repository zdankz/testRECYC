package com.example.testryc.views.main

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

class MainActivity : AppCompatActivity() {
    val users = ArrayList<User>()
    lateinit var adapter: UserAdapter
    var urlyoutube: String = "https://byyswag.000webhostapp.com/?keyword="
    val loi : String = "Nhap bai hat"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
                btnSearch.setOnClickListener {

            var key_serch: String = search.text.toString()

            if(key_serch.isNullOrBlank() == true)
            {
                Toast.makeText(applicationContext,loi, Toast.LENGTH_LONG).show()
            }else {
                urlyoutube = urlyoutube.trim()
                urlyoutube = urlyoutube.plus(key_serch)
                Getdata().execute(urlyoutube)
                initAdapter()
                initRecyclerView()

                //Getdata().execute(urlyoutube)
                //adapterVD = ArrayAdapter(this,android.R.layout.simple_list_item_1,mangbaihat)
                //list_video.adapter = adapterVD
            }
        }


        //initData()

    }

    private fun initRecyclerView() {
        rvAmUser.layoutManager = LinearLayoutManager(this)
        rvAmUser.setHasFixedSize(true)
        rvAmUser.adapter = adapter
    }

    private fun initAdapter() {
        adapter = UserAdapter(users)
    }

    private fun initData() {
        // Generate sample data

        //val names = arrayListOf("Nguyễn Văn Y", "NGuyễn Văn A", "Nguyễn Văn B")
      //  for (i in 1..100) {
            // Add data to repository collection
            //users.add(User("",i, names.random(),"jkjk"))
            //users.add(User("",i,"aaa","sasa"))
      //  }
        // Notify to adapter that data was updated
       // adapter.notifyDataSetChanged()
    }

    inner class Getdata : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg p0: String?): String {
            return getContentURL(p0[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            //Toast.makeText(applicationContext,result,Toast.LENGTH_LONG).show()
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
