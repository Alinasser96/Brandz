package com.hamalawey.brandz

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.hamalawey.brandz.utils.getConfig


class SallaTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    private var mCorners: Int
    private var mColor: Long
    private var mFont: String

    init {
        mFont = getConfig(context).font_family.replace('-', '_') + ".ttf"
        mColor = getConfig(context).app_color

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SallaTextView,
            0, 0
        ).apply {
            try {
                mFont = getString(R.styleable.SallaTextView_fontFamily)
                    ?: getConfig(context).font_family.replace('-', '_') + ".ttf"
                mColor = getString(R.styleable.SallaTextView_mcolor)?.toLong()
                    ?: getConfig(context).app_color
                mCorners = getInteger(R.styleable.SallaTextView_corners, 12)
            } finally {
                recycle()
            }
        }
        setCorners(mCorners)
        setFont(mFont)
    }

    private fun setFont(fontFamily: String) {
        val font = Typeface.createFromAsset(context.assets, fontFamily)
        typeface = font
        mFont = fontFamily
        invalidate()
        requestLayout()
    }

    private fun setCorners(corners: Int) {
        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, corners.toFloat())
            .build()

        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
        background = shapeDrawable
        background.setTint(mColor.toInt())

        mCorners = corners
        invalidate()
        requestLayout()
    }
}