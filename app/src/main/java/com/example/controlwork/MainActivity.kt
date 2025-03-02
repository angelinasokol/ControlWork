package com.example.controlwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.controlwork.ui.theme.ControlWorkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            }
        }

@Composable
fun PrintOrderScreen() {
    var pageCount by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf(10f) }
    var pricePerPage = 30
    val totalPrice = calculateTotalPrice(pageCount.toIntOrNull(), pricePerPage, discount)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Заказ на печать",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = pageCount,
                    onValueChange = { pageCount = it.filter { char -> char.isDigit() } },
                    label = { Text("Количество страниц") },
                    modifier = Modifier.fillMaxWidth()
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Скидка: ${discount.toInt()}%", fontSize = 16.sp)
                    Slider(
                        value = discount,
                        onValueChange = { discount = it },
                        valueRange = 0f..100f,
                        steps = 9,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    text = "Итоговая стоимость: ${if (totalPrice >= 0) "$totalPrice руб." else "Введите данные"}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    )
}



fun calculateTotalPrice(pageCount: Int?, pricePerPage: Int, discount: Float) : Int {
    return if (pageCount != null && pageCount > 0) {
        val basePrice = pageCount * pricePerPage
        (basePrice * (1 - discount / 100)).toInt()
    } else {
        -1
    }
}