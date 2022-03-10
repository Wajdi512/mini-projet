package com.epi.miniprojet.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epi.miniprojet.R
import com.epi.miniprojet.StoryDetailsActivity
import com.epi.miniprojet.UserDetailScreenActivity
import com.epi.miniprojet.models.User
import de.hdodenhof.circleimageview.CircleImageView
import java.io.Serializable

open class UserAdapter(private val users: List<User>, private val context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_story_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var element = users[position]
        holder.userName.text = element.userName
        Glide.with(context).load(element.photo).into(holder.imageView!!)
        holder.imageView.setOnClickListener {
            holder.imageView.borderColor = Color.GRAY
            val storyDetailScreenIntent = Intent(context, StoryDetailsActivity::class.java)
            storyDetailScreenIntent.putExtra("userDetail", element as Serializable)
            context.startActivity(storyDetailScreenIntent)
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById(R.id.userName) as TextView
        val imageView = itemView.findViewById(R.id.imageView) as CircleImageView
    }
}

