package com.hamalawey.brandz.itemDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamalawey.domain.entities.brand.BrandResponse
import com.hamalawey.domain.entities.brand.Item
import com.hamalawey.domain.entities.item.ItemDetailsResponse
import com.hamalawey.domain.usecase.GetBrandItemsFromRemote
import com.hamalawey.domain.usecase.GetItemDetailsFromRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel@Inject constructor(
    private val getItemDetailsFromRemote: GetItemDetailsFromRemote
) : ViewModel() {
    private val _details: MutableStateFlow<ItemDetailsResponse?> = MutableStateFlow(null)
    val details: StateFlow<ItemDetailsResponse?> = _details

    fun getItemDetails(itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val brands = getItemDetailsFromRemote.invoke(itemId)
                _details.value = brands
            } catch (e: Exception){
                Log.d("Error",e.message.toString())
            }
        }
    }
}