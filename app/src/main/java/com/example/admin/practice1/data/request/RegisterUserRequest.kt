package com.example.admin.practice1.data.request

import com.google.gson.annotations.SerializedName

data class RegisterUserRequest(@SerializedName("userName") val userName: String,
                               @SerializedName("password") val password: String,
                               @SerializedName("gender") val gender: String,
                               @SerializedName("dob") val dob:String,
                               @SerializedName("philosophy") val philosophy: String)