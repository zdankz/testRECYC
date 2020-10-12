package com.example.testryc.views.main

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testryc.R
import com.example.testryc.models.entity.User

class UserViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val ivUserAvatar: ImageView = v.findViewById(R.id.ivUserAvatar)
    private val tvUserName: TextView = v.findViewById(R.id.tvUserName)
    private val rlRoot: RelativeLayout = v.findViewById(R.id.rlRoot)

    fun bind(user: User) {
        ivUserAvatar.setImageResource(R.drawable.no_avatar_male)
        tvUserName.text = user.name

        // Sự kiện khi nhấn vào
        clickEvent(user)
    }

    private fun clickEvent(user: User) {
//        val intent = Intent(rlRoot.context, MainActivity::class.java)
//        intent.putExtra("user_id", user.id)
//        rlRoot.context.startActivity(intent)

        rlRoot.setOnClickListener {
            Toast.makeText(
                rlRoot.context,
                "Đã nhấn id ${user.id} có tên ${user.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}