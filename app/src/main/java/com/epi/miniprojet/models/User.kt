package com.epi.miniprojet.models

import java.io.Serializable
import java.util.*

data class User(
    var id: String,
    var userName: String,
    var photo: String,
    val createdAt: Date,
    val updatedAt: Date,
) :Serializable