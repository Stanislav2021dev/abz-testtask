package com.abzagency.core.common.file

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

object FileUtils {
    fun createTempPhotoFile(context: Context, fileExtension: String): File {
        val pickDir = File(context.cacheDir, CAMERA_PICKER_CACHE_FOLDER)
        if (!pickDir.exists()) {
            pickDir.mkdir()
        }

        return File.createTempFile(TEMP_FILE_PREFIX, fileExtension, pickDir)
    }

    fun getFileFromUri(context: Context, uri: Uri): File? {
        return try {
            val pickDir = File(context.cacheDir, CAMERA_PICKER_CACHE_FOLDER)
            val tempFile = File.createTempFile(TEMP_FILE_PREFIX, IMAGE_SUFFIX, pickDir)
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(tempFile)

            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private const val TEMP_FILE_PREFIX = "temp_"
    private const val CAMERA_PICKER_CACHE_FOLDER = "image_pick"
    const val PROVIDER_NAME = ".provider"
    private const val IMAGE_SUFFIX = ".jpeg"
}