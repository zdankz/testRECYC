package com.example.testryc.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testryc.R
import com.example.testryc.models.entity.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val users = ArrayList<User>()
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initAdapter()
        initRecyclerView()
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

    }
}