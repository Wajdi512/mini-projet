package com.epi.miniprojet

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.epi.miniprojet.models.User
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

class UserDetailScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profil_details)
        supportActionBar?.hide(); //hide the title bar
        val user = intent.getSerializableExtra("userDetail") as? User
        val image = findViewById<View>(R.id.verticalImage) as ImageView
        Glide.with(this).load(user?.photo).into(image)
        val logo = findViewById<View>(R.id.logo) as CircleImageView
        Glide.with(this).load(user?.photo).into(logo)
        val userNameTxt = findViewById<View>(R.id.verticalUserName) as TextView
        userNameTxt.text = user?.userName
        val createdAt = findViewById<View>(R.id.createdAt) as TextView
        val s = "dd/MM/yyyy"
        createdAt.text = SimpleDateFormat(s, Locale.getDefault()).format(user?.createdAt)
        val updatedAt = findViewById<View>(R.id.updatedAt) as TextView
        updatedAt.text = SimpleDateFormat(s, Locale.getDefault()).format(user?.updatedAt)
    }
}