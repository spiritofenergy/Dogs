package com.kodex.dogs.data.images

import android.graphics.Bitmap
import android.os.Build
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitmapCompressor @Inject constructor() {
    fun compress(bitmap: Bitmap,
                 quality: Int = 88,
                 imageFormat: Bitmap.CompressFormat = defaultImageFormat()): ByteArray{
        return ByteArrayOutputStream().use { stream ->
            check(bitmap.compress(imageFormat, quality, stream)) {
                "Не удалось сжать изображение"
            }
            stream.toByteArray()
        }
    }
    private fun defaultImageFormat(): Bitmap.CompressFormat{
       return if (Build.VERSION.SDK_INT >= 30){
            Bitmap.CompressFormat.WEBP_LOSSY
        }else {
            Bitmap.CompressFormat.WEBP
        }
    }
}