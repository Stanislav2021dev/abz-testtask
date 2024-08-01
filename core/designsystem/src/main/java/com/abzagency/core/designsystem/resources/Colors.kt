package com.abzagency.core.designsystem.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.abzagency.core.designsystem.R

object Colors

@Composable
@ReadOnlyComposable
fun Colors.backgroundPrimary(): Color = colorResource(id = R.color.background_primary)

@Composable
@ReadOnlyComposable
fun Colors.backgroundSecondary(): Color = colorResource(id = R.color.background_secondary)

@Composable
@ReadOnlyComposable
fun Colors.primary(): Color = colorResource(id = R.color.primary)

@Composable
@ReadOnlyComposable
fun Colors.secondary(): Color = colorResource(id = R.color.secondary)

@Composable
@ReadOnlyComposable
fun Colors.textPrimary(): Color = colorResource(id = R.color.black).copy(alpha = 0.87f)

@Composable
@ReadOnlyComposable
fun Colors.textSecondary(): Color = colorResource(id = R.color.black).copy(alpha = 0.6f)
