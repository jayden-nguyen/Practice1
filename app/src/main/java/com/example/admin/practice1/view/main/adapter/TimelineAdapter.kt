package com.example.admin.practice1.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.practice1.R
import com.example.admin.practice1.data.response.PostItem
import com.example.admin.practice1.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.android.synthetic.main.layout_post_bottom.view.*
import kotlinx.android.synthetic.main.layout_post_header.view.*

class TimelineAdapter: RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {
    private var mDataList = ArrayList<PostItem>()
    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TimelineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        mContext = parent.context
        return TimelineViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mDataList.size
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        holder.mUserName.text = mDataList[position].userName
        holder.mContent.text = mDataList[position].content
        holder.mCommentNumber.text = "Watch All " +mDataList[position].commentNumber.toString() + " comments"
        holder.mLikeNumber.text = mDataList[position].likeNumber.toString()+ " likes"
        loadImageFromUrl(mContext!!, mDataList[position].imageUrl, holder.mImgPost)
    }

    inner class TimelineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mUserName = itemView.tvUserName
        val mImgPost = itemView.imgPost
        val mCommentNumber = itemView.tvCommentNumber
        val mLikeNumber = itemView.tvLikeNumber
        val mContent = itemView.tvContent
    }

    fun setDataList(list: ArrayList<PostItem>) {
        mDataList = list
    }

    fun addDataList(list: ArrayList<PostItem>) {
        mDataList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        mDataList.clear()
        notifyDataSetChanged()
    }

}