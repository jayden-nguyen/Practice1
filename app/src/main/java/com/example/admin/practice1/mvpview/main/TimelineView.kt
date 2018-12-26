package com.example.admin.practice1.mvpview.main

import com.example.admin.practice1.base.BaseView
import com.example.admin.practice1.data.response.PostItem

interface TimelineView: BaseView {
    fun renderTimelineList(listTimeline: ArrayList<PostItem>?)
}