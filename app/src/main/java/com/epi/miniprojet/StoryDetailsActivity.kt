package com.epi.miniprojet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epi.miniprojet.adapters.UserAdapter
import com.epi.miniprojet.adapters.UserDetailAdapter
import com.epi.miniprojet.models.User
import com.epi.miniprojet.viewmodels.UserViewModel
import de.hdodenhof.circleimageview.CircleImageView


class StoryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_content)
        supportActionBar?.hide(); //hide the title bar
        val user = intent.getSerializableExtra("userDetail") as? User
        val image = findViewById<View>(R.id.storyImageView) as ImageView
        Glide.with(this).load(user?.photo).into(image)
        val circleImageView = findViewById<View>(R.id.storyCircleImageView) as CircleImageView
        Glide.with(this).load(user?.photo).into(circleImageView)
    }


}