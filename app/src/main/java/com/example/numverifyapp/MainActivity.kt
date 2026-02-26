package com.example.numverifyapp

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.numverifyapp.Models.Country

class MainActivity : ComponentActivity() {
    private val repository = NumVerifyRepository()
    val viewModel = NumVerifyViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //SimpleCountryList(viewModel)
            VerifyNumber()
        }
    }
    @Composable
    fun SimpleCountryList(viewModel: NumVerifyViewModel) {
        val countryMap by viewModel.countries.observeAsState()
        // Convert Map -> List
        val countryList = countryMap
            ?.map { (diallingCode, country,) ->
                country.copy(dialling_code = diallingCode)
            }
            ?.sortedBy { it.country_name}
            ?: emptyList()

       LaunchedEffect(Unit) {
            viewModel.searchCountryList()
       }
        CountryList(countryList)
    }
    @Composable
    fun CountryList(countries: List<Country>) {
        LazyColumn {
            items(countries) { country ->
                CountryItem(country)
            }
        }
    }
    @Composable
    fun CountryItem(country: Country) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = country.country_name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = country.dialling_code,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
    @Composable
    fun VerifyNumber(){
        var phone by remember { mutableStateOf("") }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
            ){
            TextField(
                value = phone, // The current value to display
                onValueChange = { text ->
                    phone = text
                },
                label = Text("Enter Phone Number") // The label displayed above the text field
            )
        }
    }
}




