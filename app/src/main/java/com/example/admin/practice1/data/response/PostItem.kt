package com.example.admin.practice1.data.response

import com.google.gson.annotations.SerializedName

data class PostItem(@SerializedName("userId") val userId: Int,
                    @SerializedName("id") val id: Int,
                    @SerializedName("userName") val userName: String,
                    @SerializedName("title") val title: String,
                    @SerializedName("content") val content: String,
                    @SerializedName("imageUrl") val imageUrl: String,
                    @SerializedName("commentNumber") val commentNumber: Int,
                    @SerializedName("likeNumber") val likeNumber: Int)
