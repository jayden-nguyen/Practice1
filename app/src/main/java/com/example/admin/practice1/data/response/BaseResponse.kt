package com.example.admin.practice1.data.response

import com.google.gson.annotations.SerializedName

abstract class BaseResponse(@SerializedName("status") val status: Int = -1,
                            @SerializedName("message") val message: String = ""){

}