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
    }

@Composable
fun PrintOrderScreen() {
    var pageCount by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf(10f) }
    var pricePerPage = 30
    val totalPrice = calculateTotalPrice(pageCount.toIntOrNull(), pricePerPage, discount)
}

fun calculateTotalPrice(pageCount: Int?, pricePerPage: Int, discount: Float) : Int {
    return if (pageCount != null && pageCount > 0) {
        val basePrice = pageCount * pricePerPage
        (basePrice * (1 - discount / 100)).toInt()
    } else {
        -1
    }
}