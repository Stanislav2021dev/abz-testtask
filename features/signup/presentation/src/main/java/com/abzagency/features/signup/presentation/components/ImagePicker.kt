package com.abzagency.features.signup.presentation.components

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.abzagency.features.signup.presentation.utils.FileUtils
import com.abzagency.features.signup.presentation.utils.FileUtils.createTempPhotoFile
import com.abzagency.features.signup.presentation.utils.FileUtils.getFileFromUri
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@RequiresApi(Build.VERSION_CODES.KITKAT)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImagePicker(
    onResult: (photo: File?) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val tempPhotoFile = createTempPhotoFile(context, ".jpeg")
    val tempPhotoUri =  FileProvider.getUriForFile(
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
            Log.d("TESTTT", "ImagePicker: ${tempPhotoFile.path} ")
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