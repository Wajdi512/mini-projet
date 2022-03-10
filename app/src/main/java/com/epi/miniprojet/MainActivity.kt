package com.epi.miniprojet

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.epi.miniprojet.adapters.UserAdapter
import com.epi.miniprojet.adapters.UserDetailAdapter
import com.epi.miniprojet.models.User
import com.epi.miniprojet.viewmodels.UserViewModel


class MainActivity : AppCompatActivity() {
    lateinit var usersList: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide(); //hide the title bar
        loadImages()
        val searchTxt = findViewById<EditText>(R.id.editTextTextPersonName);
        searchTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s != null && s.toString().trim().isNotEmpty()) {
                    prepareHomeImages(usersList.filter { user ->
                        user.userName.lowercase().contains(s.toString().lowercase())
                    })
                }
            }
        })
    }


    private fun loadImages() {
        var model: UserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        model.getUsers()?.observe(
            this
        ) { t ->
            usersList = t as List<User>
            prepareHomeImages(t)
            val usersView = findViewById<RecyclerView>(R.id.usersList)
            val usersAdapter = UserAdapter(t, this@MainActivity)
            usersView.adapter = usersAdapter
            val layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            usersView.layoutManager = layoutManager

        }
    }

    private fun prepareHomeImages(t: List<User?>?) {
        val verticalUsersView = findViewById<RecyclerView>(R.id.verticalUsersList)
        val verticalUsersAdapter = UserDetailAdapter(t as List<User>, this@MainActivity)
        verticalUsersView.adapter = verticalUsersAdapter
        verticalUsersView.layoutManager = GridLayoutManager(this@MainActivity, 2)
    }


}