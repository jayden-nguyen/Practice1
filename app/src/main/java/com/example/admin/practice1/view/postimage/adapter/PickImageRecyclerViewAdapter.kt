package com.example.admin.practice1.view.postimage.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.practice1.R
import com.example.admin.practice1.util.loadImageFromUrl
import kotlinx.android.synthetic.main.item_grid_image.view.*

class PickImageRecyclerViewAdapter: RecyclerView.Adapter<PickImageRecyclerViewAdapter.PickImageViewHolder>(){
    private val mPhotoPathList = ArrayList<String>()
    private var mListener: OnItemClick? = null
    private var mContext: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PickImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_image, parent, false)
        mContext = parent.context
        return PickImageViewHolder(view)
    }

    override fun getItemCount(): Int {
       return mPhotoPathList.size
    }

    override fun onBindViewHolder(holder: PickImageViewHolder, position: Int) {
       loadImageFromUrl(mContext!!,mPhotoPathList[position], holder.itemView.imgItem)
        holder.itemView.setOnClickListener {
            mListener?.onItemClick(mPhotoPathList[position])
        }
    }

    fun addPhotopathList(list: ArrayList<String>) {
        mPhotoPathList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData(){
        mPhotoPathList.clear()
        notifyDataSetChanged()
    }

    inner class PickImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    fun setItemClickListener(listener: OnItemClick) {
        mListener = listener
    }

    interface OnItemClick {
        fun onItemClick(photoPath: String)
    }
}