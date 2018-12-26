package com.example.admin.practice1.data.response.pickimage

import com.example.admin.practice1.data.response.BaseResponse
import com.google.gson.annotations.SerializedName
import java.nio.file.Path

data class PhotoInfoResponse(@SerializedName("photoPaths") val photoPaths: ArrayList<String>): BaseResponse()