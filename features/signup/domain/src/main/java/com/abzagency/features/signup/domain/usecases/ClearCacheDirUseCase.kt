package com.abzagency.features.signup.domain.usecases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class ClearCacheDirUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke() {
        val pickDir = File(context.cacheDir, CAMERA_PICKER_CACHE_FOLDER)
        if (pickDir.exists() && pickDir.isDirectory) {
            val files = pickDir.listFiles()
            files?.forEach { it.delete() }
        }
    }

    companion object {
        private const val CAMERA_PICKER_CACHE_FOLDER = "image_pick"
    }
}