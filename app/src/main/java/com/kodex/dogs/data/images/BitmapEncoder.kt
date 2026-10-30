package com.kodex.dogs.data.images

 import android.net.Uri
import android.util.Base64
import android.util.Log
 import javax.inject.Inject

class BitmapEncoder @Inject constructor(
    private  val bitmapResizer: BitmapResizer,
    private val bitmapCompressor: BitmapCompressor,
    private val imageLoader: ImageLoader,
    //private val settingsData: UserSettingsData
) {
    fun uriToByteArray(uri: Uri): ByteArray{
        val bitMap = imageLoader.loadImage(uri)
        var maxDimension = INITIAL_MAX_DIMENSION
        var quality = INITIAL_QUALITY

        while (true) {
            val resizedBitmap = bitmapResizer.resize(bitMap, maxDimension)
            val bytes = bitmapCompressor.compress(resizedBitmap, quality)
            val encodedSize = Base64.encodeToString(bytes, Base64.NO_WRAP).length
            if (encodedSize <= MAX_BASE64_SIZE) return bytes

            if (quality > MIN_QUALITY) {
                quality = (quality - QUALITY_STEP).coerceAtLeast(MIN_QUALITY)
            } else if (maxDimension > MIN_MAX_DIMENSION) {
                maxDimension = (maxDimension * RESIZE_STEP).toInt()
                    .coerceAtLeast(MIN_MAX_DIMENSION)
                quality = INITIAL_QUALITY
            } else {
                throw IllegalStateException(
                    "Не удалось сжать изображение до допустимого размера для публикации"
                )
            }
        }
    }
    fun imageToBase64(uri: Uri?): String{
        if (uri == null) return ""
        val bytes = uriToByteArray(uri)
        val base64Image = Base64.encodeToString(bytes, Base64.NO_WRAP)
        Log.d("MyLog2", "base64Image size toByteArray: , ${base64Image.toByteArray(Charsets.UTF_8).size}")
        Log.d("MyLog1", "base64Image size: , ${bytes.size}")
        return base64Image
    }

    private companion object {
        const val INITIAL_MAX_DIMENSION = 1600
        const val MIN_MAX_DIMENSION = 640
        const val INITIAL_QUALITY = 88
        const val MIN_QUALITY = 64
        const val QUALITY_STEP = 8
        const val RESIZE_STEP = 0.85
        const val MAX_BASE64_SIZE = 700 * 1024
    }
}