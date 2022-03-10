package com.epi.miniprojet

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class FullImageScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_image_screen)
        supportActionBar?.hide(); //hide the title bar
        val fullScreenImageView: ImageView =
            findViewById<View>(
                R.id.fullScreenImageView
            ) as ImageView
        val callingActivityIntent = intent
        if (callingActivityIntent != null) {
            val imageUri: Uri? = callingActivityIntent.data
            if (imageUri != null) {
                Glide.with(this)
                    .load(imageUri)
                    .into(fullScreenImageView)
            }
        }

    }
}