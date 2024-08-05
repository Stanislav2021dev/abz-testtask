# ABZ Agency TestTask README

## Overview

This is an Android application developed with modern Android and Kotlin technologies.
The project contains Jetpack Compose for UI, Hilt for dependency injection, Retrofit for networking,
and several other libraries for efficient and effective development.

## Features

- **Jetpack Compose:** Modern UI toolkit for building native UI.
- **Kotlin Coroutines:** Asynchronous programming.
- **Hilt:** Dependency injection.
- **Retrofit & OkHttp:** Networking.
- **Paging:** Efficient data pagination.
- **Compose navigation** Navigation between screens
## Project Structure

The project follows a modular architecture to separate concerns and improve maintainability.
The modules are organized as follows:

### Modules

- **`app`:** Main application module, entry point of the app.
- **`core`:** Contains core functionalities shared across features.
    - **`core:network`:** Network-related functionalities and configurations.
    - **`core:designsystem`:** Common UI components and styles.
    - **`core:common`:** Shared utilities and helpers.
- **`features`:** Feature-specific modules.
    - **`features:splash`:** Splash screen feature.
        - **`features:splash:presentation`:** UI layer for the splash screen.
    - **`features:signup`:** Signup feature.
        - **`features:signup:presentation`:** UI layer for signup.
        - **`features:signup:domain`:** Business logic for signup.
        - **`features:signup:data`:** Data handling and repositories for signup.
        - **`features:signup:models`:** Data models for signup.
            - **`features:signup:models:presentation`:** Models used in the presentation layer.
            - **`features:signup:models:domain`:** Domain models.
            - **`features:signup:models:remote`:** Remote data models and network DTOs.
    - **`features:users`:** User management feature.
        - **`features:users:presentation`:** UI layer for user-related screens.
        - **`features:users:domain`:** Business logic for user management.
        - **`features:users:data`:** Data handling and repositories for users.
        - **`features:users:models`:** Data models for users.
            - **`features:users:models:remote`:** Remote data models and network DTOs.
            - **`features:users:models:domain`:** Domain models.
            - **`features:users:models:presentation`:** Models used in the presentation layer.

## Libraries and Dependencies

### Core

- **Android Core (`androidx.core:core-ktx`)**: Version `1.13.1`
    - Provides Kotlin extensions for commonly used Android APIs.
- **AppCompat (`androidx.appcompat:appcompat`)**: Version `1.7.0`
    - Backward-compatible support for older Android versions.

### Kotlin

- **Kotlin Core (`org.jetbrains.kotlin:kotlin-stdlib`)**: Version `2.0.0`
    - Core Kotlin libraries.
- **Kotlinx Coroutines (`org.jetbrains.kotlinx:kotlinx-coroutines-core`)**: Version `1.8.1`
    - Supports async programming with coroutines.
- **Kotlin Serialization (`org.jetbrains.kotlinx:kotlinx-serialization-json`)**: Version `1.6.3`
    - JSON serialization for Kotlin.

### Dependency Injection

- **Hilt (`com.google.dagger:hilt-android`)**: Version `2.50`
    - Dependency injection framework.
- **Hilt AndroidX Integration (`androidx.hilt:hilt-navigation-compose`)**: Version `1.2.0`
    - Integration for AndroidX components.

### Networking

- **Retrofit (`com.squareup.retrofit2:retrofit`)**: Version `2.11.0`
    - Type-safe HTTP client for Android and Java.
- **OkHttp (`com.squareup.okhttp3:okhttp`)**: Version `4.12.0`
    - HTTP client for making requests.
- **Kotlin Retrofit Serialization (`com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter`)**: Version `1.0.0`
    - Converter for Kotlin serialization with Retrofit.

### Jetpack Compose

- **Compose UI (`androidx.compose.ui:ui`)**: Version `1.6.8`
    - UI components for building interfaces.
- **Compose Material 3 (`androidx.compose.material3:material3`)**: Version `1.2.1`
    - Material Design components.
- **Compose Navigation (`androidx.navigation:navigation-compose`)**: Version `2.7.7`
    - Navigation component integration with Compose.
- **Compose Compiler (`androidx.compose.compiler:compiler`)**: Version `1.5.14`
    - Compiler for Compose.
- **Compose Runtime (`androidx.compose.runtime:runtime`)**: Version `2.8.4`
    - Runtime components for Compose.
- **Compose Lifecycle Runtime (`androidx.lifecycle:lifecycle-runtime-compose`)**: Version `2.8.2`
    - Lifecycle-aware components for Compose.

### Additional Libraries

- **Accompanist (`com.google.accompanist:accompanist`)**: Version `0.34.0`
    - Utility library for Jetpack Compose.
- **Coil (`io.coil-kt:coil-compose`)**: Version `2.6.0`
    - Image loading library for Android.

### Paging

- **Paging (`androidx.paging:paging-runtime`)**: Version `3.3.1`
    - Pagination library for efficient data handling.
- **Paging Compose (`androidx.paging:paging-compose`)**: Version `3.3.1`
    - Compose integration for Paging.

## Project Setup

**Clone the repository:**
   git clone https://github.com/Stanislav2021dev/abz-testtask.git