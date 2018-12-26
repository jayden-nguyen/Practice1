package com.example.admin.practice1.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.practice1.R
import com.example.admin.practice1.data.response.PostItem
import com.example.admin.practice1.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_card_image.view.*

class ProfileAdapter(): RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    private val mDataList = ArrayList<PostItem>()
    private var mContext: Context? = null
    private lateinit var mView: View
    private var mType = 0
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ProfileViewHolder {
        if (mType == 1) {
            mView = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_image, parent, false)
        } else if (mType == 2) {
            mView = LayoutInflater.from(parent.context).inflate(R.layout.item_card_image, parent, false)
        }
        mContext = parent.context
        return ProfileViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(p0: ProfileViewHolder, p1: Int) {
        loadImageFromUrl(mContext!!,mDataList[p1].imageUrl, p0.mImgItem)
    }

    inner class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mImgItem = itemView.imgItem
    }

    fun setType(type: Int) {
        mType = type
        notifyDataSetChanged()
    }

    fun addDataList(list: ArrayList<PostItem>?) {
        if (list != null) {
            mDataList.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun clearData() {
        mDataList.clear()
        notifyDataSetChanged()
    }
}