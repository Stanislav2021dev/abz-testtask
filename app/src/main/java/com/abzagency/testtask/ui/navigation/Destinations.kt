package com.abzagency.testtask.ui.navigation


enum class RootDestination(val route: String) {
    ROOT("root"),
    SPLASH("splash"),
    HOME("home")
}

enum class HomeDestination(val route: String) {
    SIGN_UP("${RootDestination.HOME}_sign_up"),
    USERS("${RootDestination.HOME}_users")
}

enum class SignUpDestination(val route: String) {
    SIGN_UP("${HomeDestination.SIGN_UP.route}_sign_up")
}

enum class UsersDestination(val route: String) {
    USERS("${HomeDestination.USERS.route}_users")
}