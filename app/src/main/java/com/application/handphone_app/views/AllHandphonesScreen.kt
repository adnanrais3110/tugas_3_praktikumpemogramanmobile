package com.application.handphone_app.views

import HandleBackPress
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.handphone_app.data.database.Handphone
import com.application.handphone_app.data.viewmodel.HandphoneViewModel
import com.application.handphone_app.navigation.Screen
import com.application.handphone_app.views.utils.TitleText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.application.handphone_app.R
import com.application.handphone_app.ui.theme.BlueBlack


@Composable
fun AllHandphonesScreen(navController: NavController, handphoneViewModel: HandphoneViewModel) {
    val handphones: List<Handphone> by handphoneViewModel.allHandphones.observeAsState(initial = listOf())
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(Unit) {
        handphoneViewModel.getHandphones()
    }

    LaunchedEffect(Unit) {
        handphoneViewModel.getHandphones()
    }

    Box(
        modifier = Modifier
            .background(color = BlueBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(color = BlueBlack)
        ) {
            if (handphones.isEmpty()) {
                TitleText(
                    text = stringResource(id = R.string.all_handphones_title),
                    modifier = Modifier,
                )
                Column(
                    modifier = Modifier.offset(y = -(30).dp)
                ) {
                    EmptyContent()
                }
            } else {
                TitleText(
                    text = stringResource(id = R.string.all_handphones_title),
                    modifier = Modifier,
                )
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .background(color = BlueBlack),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(handphones) { hp ->
                        OutlinedCard(
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clickable {
                                    navController.navigate(route = Screen.HandphoneDetailsScreen.route + "/" + hp.id)
                                },
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(10.dp)
                            ) {
                                AsyncImage(
                                    model = "https://source.unsplash.com/random/430x400/?iphone+15",
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .wrapContentSize()
                                        .clip(shape = RoundedCornerShape(10.dp))
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = hp.hpName,
                                        modifier = Modifier
                                            .padding(16.dp, 12.dp, 0.dp, 0.dp),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = "Brand - ${hp.brand}",
                                        modifier = Modifier
                                            .padding(16.dp, 8.dp, 10.dp, 0.dp),
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.End
                                    )
                                    Text(
                                        text = "Category - ${hp.hpClass}",
                                        modifier = Modifier
                                            .padding(16.dp, 3.dp, 0.dp, 5.dp),
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "${hp.price}",
                                        modifier = Modifier.padding(16.dp, 36.dp, 0.dp, 0.dp),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    if (backDispatcher != null) {
        HandleBackPress(backDispatcher) {
            navController.navigate(Screen.HomeScreen.route)
        }
    }
}

@Composable
fun EmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = BlueBlack),
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(
                R.drawable.phone_android
            ), contentDescription = stringResource(
                R.string.no_handphone
            ),
            tint = Color.LightGray
        )
        androidx.compose.material.Text(
            text = stringResource(
                R.string.text_empty_content
            ),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = Color.LightGray,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
