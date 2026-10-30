package com.kodex.dogs.data.images

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.toBitmap(): Bitmap?{
        return try {
            val base64Image = Base64.decode(this)
            BitmapFactory.decodeByteArray(
                base64Image,0,
                base64Image.size
            )
        }catch (e: IllegalArgumentException){
            Log.d("MyLog2", "toBitmap size: , ${this.toByteArray(Charsets.UTF_8).size}")
            null
        }
    }
