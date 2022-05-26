package com.hamalawey.brandz.utils

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hamalawey.domain.entities.ConfigModel
import java.io.IOException


fun getConfig(context: Context): ConfigModel {
    lateinit var jsonString: String
    try {
        jsonString = context.assets.open("Developers.json")
            .bufferedReader()
            .use { it.readText() }
    } catch (ioException: IOException) {

    }
    val configModel = object : TypeToken<ConfigModel>() {}.type
    return Gson().fromJson(jsonString, configModel)
}

fun overrideFonts(context: Context, v: View, font: String) {
    val finalFont = font.replace('-','_') + ".ttf"
    try {
        if (v is ViewGroup) {
            for (i in 0 until v.childCount) {
                val child = v.getChildAt(i)
                overrideFonts(context, child, font)
            }
        } else if (v is TextView) {
            v.typeface = Typeface.createFromAsset(context.assets, finalFont)
        }
    } catch (e: Exception) {
    }
}