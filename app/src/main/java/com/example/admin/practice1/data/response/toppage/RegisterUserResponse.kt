package com.example.admin.practice1.data.response.toppage

import com.example.admin.practice1.data.response.BaseResponse
import com.google.gson.annotations.SerializedName

class RegisterUserResponse(@SerializedName("reponseData") val reponseData:UserItem?): BaseResponse()