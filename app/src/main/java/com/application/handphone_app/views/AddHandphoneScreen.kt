package com.application.handphone_app.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.application.handphone_app.R
import com.application.handphone_app.data.database.Handphone
import com.application.handphone_app.data.viewmodel.HandphoneViewModel
import com.application.handphone_app.ui.theme.BlueBlack
import com.application.handphone_app.views.utils.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHandphoneScreen(handphoneViewModel: HandphoneViewModel) {
    val context = LocalContext.current
    var hpName by rememberSaveable { mutableStateOf("") }
    var brand by rememberSaveable { mutableStateOf("") }
    var hpClass by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
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


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Add Your Donation",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = BlueBlack,
                modifier = Modifier
                    .padding(top = 60.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = hpName,
                label = { Text(stringResource(id = R.string.handphone_name)) },
                onValueChange = {
                    hpName = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = brand,
                label = { Text(stringResource(id = R.string.brand)) },
                onValueChange = {
                    brand = it
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    OutlinedTextField(
                        value = hpClass,
                        onValueChange = { hpClass = it },
                        placeholder = { androidx.compose.material.Text(text = "Choose The Class") },
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
                            .fillMaxWidth(0.8f),
                    ) {
                        hpClassList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                hpClass = selectedItem
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
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {
                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        placeholder = { androidx.compose.material.Text(text = "Choose The Price") },
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
                            .fillMaxWidth(0.8f),
                    ) {
                        priceList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                price = selectedItem
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
            Spacer(modifier = Modifier.height(30.dp))
            CustomButton(stringResource(id = R.string.add_handphone)) {
                // Create the Handphone object
                if (hpName == "" || brand == "" || hpClass == "" || price == "") {
                    Toast.makeText(context, "Handphone Added Failed", Toast.LENGTH_SHORT).show()
                    Log.d("data db", "Data Gagal")
                } else {
                    val handphone = Handphone(hpName, brand, hpClass, price)
                    Log.d("data db", "Data Berhasil $handphone")

                    // Update the Handphone to the database
                    handphoneViewModel.addHp(handphone)
                    // Clear text fields
                    hpName = ""
                    brand = ""
                    hpClass = ""
                    price = ""
                    Toast.makeText(context, "Handphone added successfully", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }
}
