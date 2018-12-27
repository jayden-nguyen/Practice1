package com.example.admin.practice1.data.response.toppage

import com.example.admin.practice1.data.response.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserItem(@SerializedName("id") val id: Int,
                    @SerializedName("userName") val userName: String,
                    @SerializedName("createTime") val createTime: Long)