package com.example.gdirectionspoc.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdirectionspoc.network.ApiResponse
import com.example.gdirectionspoc.pojo.GDirectionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GDirectionViewModel @Inject constructor(
    private val gDirectionRepositoryImpl: GDirectionRepositoryImpl
) : ViewModel() {

    val gDirectionMutableLiveData: MutableLiveData<ApiResponse<GDirectionResponse?>> = MutableLiveData()

    fun getGDirection(origin: String, destination: String, wayPoint: String){
        viewModelScope.launch {
            gDirectionRepositoryImpl.getGDirections(origin, destination, wayPoint).collect {
                gDirectionMutableLiveData.postValue(it)
            }
        }
    }
}