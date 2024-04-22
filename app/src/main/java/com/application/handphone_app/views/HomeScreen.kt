package com.application.handphone_app.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.handphone_app.navigation.Screen
import com.application.handphone_app.ui.theme.BlueBlack
import com.application.handphone_app.views.utils.CustomButtonWhite


@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().background(color = BlueBlack),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Phonepedia",
                fontFamily = FontFamily.Monospace,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(100.dp))
            CustomButtonWhite("List all Handphones") { navController.navigate(route = Screen.AllHandphonesScreen.route) }
            Spacer(modifier = Modifier.height(20.dp))
            CustomButtonWhite("Add a Handphone") { navController.navigate(Screen.AddHandphoneScreen.route) }
        }
    }
}