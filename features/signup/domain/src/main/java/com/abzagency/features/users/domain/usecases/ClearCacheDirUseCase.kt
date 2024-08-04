package com.abzagency.features.users.domain.usecases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ClearCacheDirUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke() {
        val cacheDir = context.cacheDir
        val tempFiles = cacheDir.listFiles { _, name -> name.contains(TEMP_FILE_PREFIX) }

        if (tempFiles != null) {
            for (file in tempFiles) {
                file.delete()
            }
        }
    }

    companion object {
        private const val TEMP_FILE_PREFIX = "temp_"
    }
}