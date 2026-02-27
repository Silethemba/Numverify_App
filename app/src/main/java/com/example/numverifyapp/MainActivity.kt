package com.example.numverifyapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.numverifyapp.Models.Country
import dagger.hilt.android.HiltAndroidApp

class MyApplication : Application()
class MainActivity : ComponentActivity() {
    //work in progress to use Hilt for dependencies
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
            ?.map { (diallingCode, country) ->
                country.copy(dialling_code = diallingCode)
            }
            ?.sortedBy { it.country_name }
            ?: emptyList()


        LaunchedEffect(Unit) {
            viewModel.searchCountryList()
        }
        CountryList(countryList)
    }

    @Composable
    fun CountryList(countries: List<Country>) {
        LazyColumn(modifier = Modifier.padding(top = 30.dp)) {
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
                .padding(8.dp)
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
    fun VerifyNumber() {
        val telephone by viewModel.validateNumber.observeAsState()
        var phone by remember { mutableStateOf("") }
        var diallingCode by remember { mutableStateOf("") }

        Column() {
            Row(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .padding(16.dp)
            )
            {
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Enter Number") },
                    modifier = Modifier
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))

                OutlinedTextField(
                    value = diallingCode,
                    onValueChange = { diallingCode = it },
                    label = { Text("Country Code") },
                    modifier = Modifier
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    viewModel.validateNumber(phone, diallingCode)

                }) {
                    Text(text = "Verify")
                }
            }
            Text(
                "Phone Number valid :${telephone?.valid}",
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                "Country Code :${telephone?.country_code}",
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                "Country Name :${telephone?.country_name}",
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                "Scrollable List to find country code",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp, start = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                SimpleCountryList(viewModel)
            }
        }
    }
}









