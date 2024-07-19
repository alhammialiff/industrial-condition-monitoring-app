package com.example.kotlindevcourse

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
){

    object Back: BottomBarScreen(
        route = "back",
        title =  "Back",
        icon = Icons.Default.KeyboardArrowLeft
    )

    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Forward: BottomBarScreen(
        route = "forward",
        title = "Forward",
        icon = Icons.Default.KeyboardArrowRight
    )

}