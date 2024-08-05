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
            val fileName = getFileName(context, uri)
            val tempFile = File.createTempFile(fileName, null, context.cacheDir)
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

    private fun getFileName(context: Context, uri: Uri): String {
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val displayNameIndex = cursor.getColumnIndexOrThrow("_display_name")
                if (displayNameIndex != -1) {
                    return cursor.getString(displayNameIndex)
                }
            }
        }
        return uri.lastPathSegment ?: "unknown_file"
    }

    private const val TEMP_FILE_PREFIX = "temp_"
    private const val CAMERA_PICKER_CACHE_FOLDER = "image_pick"
    const val PROVIDER_NAME = ".provider"
}