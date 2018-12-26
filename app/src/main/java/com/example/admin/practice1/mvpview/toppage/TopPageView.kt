package com.example.admin.practice1.mvpview.toppage

import com.example.admin.practice1.base.BaseView
import com.example.admin.practice1.data.response.PostItem
import com.example.admin.practice1.data.response.PostsResponse

interface TopPageView: BaseView {
    fun renderTestPost(data: PostsResponse)
}