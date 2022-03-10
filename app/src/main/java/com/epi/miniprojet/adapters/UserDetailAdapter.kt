package com.epi.miniprojet.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epi.miniprojet.FullImageScreenActivity
import com.epi.miniprojet.R
import com.epi.miniprojet.UserDetailScreenActivity
import com.epi.miniprojet.models.User
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

open class UserDetailAdapter(private val users: List<User>, private val context: Context) :
    RecyclerView.Adapter<UserDetailAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_home_details, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val element = users[position]
        holder.userName.text = element.userName
        val dateFormat = "dd/MM/yyyy"
        holder.createdAt.text = "Created At: ".plus(
            SimpleDateFormat(dateFormat, Locale.getDefault()).format(element.createdAt)
        )
        holder.updatedAt.text = "Updated At: ".plus(
            SimpleDateFormat(dateFormat, Locale.getDefault()).format(element.updatedAt)
        )
        Glide.with(context).load(element.photo).into(holder.imageView)
        holder.imageView.setOnClickListener {
            Toast.makeText(context, "You clicked on an image.", Toast.LENGTH_SHORT).show()
            val fullScreenIntent = Intent(context, FullImageScreenActivity::class.java)
            fullScreenIntent.data =
                Uri.parse(element.photo)
            context.startActivity(fullScreenIntent)

        }
        holder.userName.setOnClickListener {
            val detailScreenIntent = Intent(context, UserDetailScreenActivity::class.java)
            detailScreenIntent.putExtra("userDetail", element as Serializable)
            context.startActivity(detailScreenIntent)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById(R.id.verticalUserName) as TextView
        val imageView = itemView.findViewById(R.id.verticalImage) as ImageView
        val createdAt = itemView.findViewById(R.id.createdAt) as TextView
        val updatedAt = itemView.findViewById(R.id.updatedAt) as TextView

    }
}
