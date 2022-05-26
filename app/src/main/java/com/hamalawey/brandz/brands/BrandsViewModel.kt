package com.hamalawey.brandz.brands

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamalawey.domain.entities.brand.BrandResponse
import com.hamalawey.domain.usecase.GetBrandItemsFromRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val getBrandItemsFromRemote: GetBrandItemsFromRemote
) : ViewModel() {
    private val _brand: MutableStateFlow<BrandResponse?> = MutableStateFlow(null)
    val brand: StateFlow<BrandResponse?> = _brand

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getBrandData(page: Int, perPage: Int) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val brands = getBrandItemsFromRemote.invoke(page, perPage)
                _brand.value = brands
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                Log.d("Error", e.message.toString())
            }
        }
    }
}