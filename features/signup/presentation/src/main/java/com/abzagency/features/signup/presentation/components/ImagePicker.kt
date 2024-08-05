package com.abzagency.features.signup.presentation.components

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.abzagency.core.common.file.FileUtils
import com.abzagency.core.common.file.FileUtils.createTempPhotoFile
import com.abzagency.core.common.file.FileUtils.getFileFromUri
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImagePicker(
    onResult: (photo: File?) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val tempPhotoFile = createTempPhotoFile(context, ".jpeg")
    val tempPhotoUri = FileProvider.getUriForFile(
        context,
        context.packageName + FileUtils.PROVIDER_NAME,
        tempPhotoFile
    )
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) {
            onResult(tempPhotoFile)
            onDismiss()
        }

    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            val imageFile = uri?.let { getFileFromUri(context, it) }
            onResult(imageFile)

            onDismiss()
        }
    )

    val cameraRequestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            cameraLauncher.launch(tempPhotoUri)
        }
    }

    ImagePickerBottomSheet(
        onDismiss = onDismiss,
        onCameraClick = {
            if (!cameraPermissionState.status.isGranted) {
                cameraRequestPermissionLauncher.launch(Manifest.permission.CAMERA)
            } else {
                cameraLauncher.launch(tempPhotoUri)
            }
        },
        onGalleryClick = {
            pickerLauncher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
    )
}