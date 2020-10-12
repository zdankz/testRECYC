package com.example.testryc.views.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testryc.R
import com.example.testryc.models.entity.User

class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val ivUserAvatar: ImageView = v.findViewById(R.id.ivUserAvatar)
    private val tvUserName: TextView = v.findViewById(R.id.tvUserName)
    
    fun bind(user: User) {
        ivUserAvatar.setImageResource(R.drawable.no_avatar_male)
        tvUserName.text = user.name
    }
}