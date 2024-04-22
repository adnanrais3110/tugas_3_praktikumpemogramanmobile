package com.application.handphone_app.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.application.handphone_app.R
import com.application.handphone_app.data.database.Handphone
import com.application.handphone_app.data.viewmodel.HandphoneViewModel
import com.application.handphone_app.navigation.Screen
import com.application.handphone_app.views.utils.CustomUpdateButton
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandphoneDetailScreen(id: Int, handphoneViewModel: HandphoneViewModel, navController: NavController) {
    val context = LocalContext.current
    var hpNameState: String? by remember { mutableStateOf(null) }
    var brandState: String? by remember { mutableStateOf(null) }
    var hpClassState: String? by remember { mutableStateOf(null) }
    var priceState: String? by remember { mutableStateOf(null) }
    var isHpClassDropDownExpanded by remember { mutableStateOf(false) }
    var isPriceDropDownExpanded by remember { mutableStateOf(false) }
    val hpClassList = listOf("Entry Level ", "Mid Range", "High End", "Flagship")
    val priceList = listOf(
        "Rp. 1.800,000 ",
        "Rp. 2.100,000",
        "Rp. 3.150,000",
        "Rp. 4.200,000",
        "Rp. 10.500,000",
        "Rp. 15.500,000",
        "Rp. 20.000,000",
    )

    LaunchedEffect(Unit) {
        handphoneViewModel.getHp(id)
    }
    handphoneViewModel.getHp(id)

    val hp = handphoneViewModel.getHandphone.observeAsState().value
    hp ?: return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Details Handphone",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 60.dp),
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                OutlinedTextField(
                    value = hpNameState
                        ?: hp.hpName,  // display database text if no modified text available
                    onValueChange = { hpNameState = it },
                    label = { Text(stringResource(id = R.string.handphone_name)) },
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = brandState
                        ?: hp.brand,
                    onValueChange = { brandState = it },
                    label = { Text(stringResource(id = R.string.brand)) },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box {
                    OutlinedTextField(
                        value = hpClassState ?: hp.hpClass,
                        onValueChange = { hpClassState = it },
                        placeholder = { androidx.compose.material.Text(text = hp.hpClass) },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isHpClassDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowCircleDown, "") }
                    )

                    DropdownMenu(
                        expanded = isHpClassDropDownExpanded,
                        onDismissRequest = { isHpClassDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        hpClassList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                hpClassState = selectedItem
                                isHpClassDropDownExpanded = false
                            }) {
                                androidx.compose.material.Text(selectedItem)
                            }
                            if (index != hpClassList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {
                    OutlinedTextField(
                        value = priceState ?: hp.price,
                        onValueChange = { priceState = it },
                        placeholder = { androidx.compose.material.Text(text = hp.price) },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isPriceDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowCircleDown, "") }
                    )

                    DropdownMenu(
                        expanded = isPriceDropDownExpanded,
                        onDismissRequest = { isPriceDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        priceList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                priceState = selectedItem
                                isPriceDropDownExpanded = false
                            }) {
                                androidx.compose.material.Text(selectedItem)
                            }
                            if (index != priceList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                CustomUpdateButton(stringResource(id = R.string.update_handphone))
                {
                    // Create the Handphone object
                    val handphone = Handphone(
                        hpName = hpNameState ?: hp.hpName,
                        brand = brandState ?: hp.brand,
                        hpClass = hpClassState ?: hp.hpClass,
                        price = priceState ?: hp.price
                    )

                    // Update the Handphone in the database
                    handphoneViewModel.updateHp(
                        id,
                        handphone.hpName,
                        handphone.brand,
                        handphone.hpClass,
                        handphone.price
                    )
                    Toast.makeText(context, "Handphone updated successfully", Toast.LENGTH_SHORT)
                        .show()

                }
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    val openDialog = remember { mutableStateOf(false) }

                    Button(onClick = { openDialog.value = true }) {
                        Text(text = "Delete Handphone")
                    }

                    if (openDialog.value) {
                        AlertDialog(
                            onDismissRequest = { openDialog.value = false },
                            title = {
                                Text(text = "Deleting Handphone")
                            },
                            text = {
                                Text(text = "Do you really want to Delete this Handphone ?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        hp?.let { id ->
                                            handphoneViewModel.deleteHp(id)
                                        }
                                        openDialog.value = false
                                        Toast.makeText(context, "Handphone Deleted successfully", Toast.LENGTH_SHORT)
                                            .show()
                                        navController.navigate(Screen.AllHandphonesScreen.route)
                                    },
                                ) {
                                    Text(text = "CONFIRM")

                                }
                            },
                            dismissButton = {
                                Button(onClick = { openDialog.value = false }
                                ) {
                                    Text(text = "CANCEL")
                                }
                            }
                        )
                    }
                }

            }

        }

    }
}



