package com.example.numverifyapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numverifyapp.Models.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumVerifyViewModel(private val repository: NumVerifyRepository): ViewModel() {

    private val _countries = MutableLiveData<Map<String, Country>?>()
    var countries: LiveData<Map<String, Country>?> = _countries

    private val _number = MutableLiveData<Number?>()
    var number: LiveData<Number?> = _number


    fun searchCountryList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCountryCodeList()
            _countries.postValue(response)
        }
    }

    fun validateNumber(number: String, countryCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.validateNumber(number, countryCode)
            _number.postValue(response)
    }
}
}

