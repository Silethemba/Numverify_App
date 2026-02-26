package com.example.numverifyapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numverifyapp.Models.Country
import com.example.numverifyapp.Models.Telephone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NumVerifyViewModel(private val repository: NumVerifyRepository): ViewModel() {

    private val _countries = MutableLiveData<Map<String, Country>?>()
    var countries: LiveData<Map<String, Country>?> = _countries

    private val _validateNumber = MutableLiveData<Telephone>()
    var validateNumber: LiveData<Telephone> = _validateNumber


    fun searchCountryList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCountryCodeList()
            _countries.postValue(response)
        }
    }

    fun validateNumber(number: String, countryCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val validateNumber = repository.validateNumber(number, countryCode)
            _validateNumber.postValue(validateNumber)
    }
}
}

