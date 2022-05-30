package com.hamalawey.brandz

import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.view.View
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.hamalawey.brandz.utils.getConfig


class DotView(context: Context) : View(context) {
    private var isDotSelected: Boolean = false
    private var mColor: Long = 0

    init {
        mColor = getConfig(context).app_color
        setDotSelected(isDotSelected)
        setCorners(30, mColor.toInt())
    }

    fun setDotSelected(isSelected: Boolean) {
        isDotSelected = isSelected
        if (isSelected) {
            setColor(mColor.toInt())
        } else {
            setColor(R.color.black)
        }
    }

    private fun setColor(color: Int) {
        setBackgroundColor(color)
        setCorners(24, color)
        invalidate()
    }

    private fun setCorners(corners: Int, color: Int) {
        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, corners.toFloat())
            .build()

        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
        background = shapeDrawable
        background.setTint(color)

        invalidate()
        requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val config: Configuration = resources.configuration
        if (config.layoutDirection === LAYOUT_DIRECTION_RTL) {
            //in Right To Left layout
            canvas?.rotate(90f)
        } else{
            canvas?.rotate(-90f)
        }
    }
}