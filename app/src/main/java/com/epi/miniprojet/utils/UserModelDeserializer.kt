package com.epi.miniprojet.utils

import com.epi.miniprojet.models.User
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class UserModelDeserializer : JsonDeserializer<User> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): User {
        val userObject = json!!.asJsonObject["user"]
        val userName = userObject.asJsonObject["name"]
        val photoObject = json!!.asJsonObject["urls"];
        val photoUrl = photoObject.asJsonObject["small"]
        val g = GsonBuilder().setDateFormat("E MMM dd hh:mm:ss Z yyyy").create()
        val entity: User = g.fromJson(json, User::class.java)
        entity.userName = userName.asString
        entity.photo = photoUrl.asString
        return entity
    }
}