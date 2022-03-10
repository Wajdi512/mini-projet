package com.epi.miniprojet.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epi.miniprojet.api.ApiInterface
import com.epi.miniprojet.models.User
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class UserViewModel : ViewModel() {
    private var usersList: MutableLiveData<List<User>>? = null
    var errorMessage = MutableLiveData<String>()

    fun getUsers(): MutableLiveData<List<User>>? {
        if (usersList == null) {
            usersList = MutableLiveData<List<User>>()
            loadUsers()
        }
        return usersList
    }

    private fun loadUsers() {
        val retIn =
            ApiInterface.RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        retIn.getImages(clientId = "Y04er8NmAuQjzf6yjMg8-k3ILmdoZ-4gkB8wC0zQ9OQ", count = "20")
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.code() == 200) {
                        val users = mutableListOf<User>()
                        var imageString = response.body()?.string()
                        if (imageString != null && !imageString.isEmpty()) {
                            var json = JsonParser()
                            var jsonArray = json.parse(imageString).asJsonArray
                            for (image in jsonArray) {
                                val id =
                                    image.asJsonObject.get("id").asString
                                val userName =
                                    image.asJsonObject.get("user").asJsonObject.get("name").asString
                                val imageUrl =
                                    image.asJsonObject.get("urls").asJsonObject.get("small").asString
                                val createdAt = image.asJsonObject.get("created_at").asString
                                val updatedAt = image.asJsonObject.get("updated_at").asString
                                val formatter = SimpleDateFormat("yyyy-MM-dd")
                                val user = User(
                                    id = id,
                                    userName = userName,
                                    photo = imageUrl,
                                    createdAt = formatter.parse(createdAt),
                                    updatedAt = formatter.parse(updatedAt)
                                )
                                users.add(user)
                                usersList?.postValue(users)
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }


}