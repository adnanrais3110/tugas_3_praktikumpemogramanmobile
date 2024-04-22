package com.application.handphone_app.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("main_screen")
    object AddHandphoneScreen : Screen("add_a_handphone")
    object HandphoneDetailsScreen : Screen("handphone_details")
    object AllHandphonesScreen : Screen("all_handphones")
}
