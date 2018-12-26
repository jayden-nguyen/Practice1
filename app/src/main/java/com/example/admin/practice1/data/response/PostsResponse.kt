package com.example.admin.practice1.data.response

import com.google.gson.annotations.SerializedName

data class PostsResponse(@SerializedName("data") val data: ArrayList<PostItem>?): BaseResponse()