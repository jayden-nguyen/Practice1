package com.example.admin.practice1.customview.main

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareFrameLayout : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super (context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super (context, attributeSet, defStyleAttr)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attributeSet, defStyleAttr, defStyleRes)

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        layoutParams.height = width
        invalidate()
    }
}