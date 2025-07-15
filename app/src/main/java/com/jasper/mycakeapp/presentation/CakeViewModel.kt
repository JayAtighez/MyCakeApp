package com.jasper.mycakeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasper.mycakeapp.data.Cake
import com.jasper.mycakeapp.usecase.GetCakesUseCase
import com.jasper.mycakeapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CakeViewModel @Inject constructor(private val getCakesUseCase: GetCakesUseCase) :
    ViewModel() {

    private val _cakeList = MutableStateFlow<Resource<List<Cake>>>(Resource.Idle())
    val cakeList: StateFlow<Resource<List<Cake>>> = _cakeList.asStateFlow()

    init {
        getAllCakes()
    }

    fun getAllCakes() {
        viewModelScope.launch {
            getCakesUseCase().collect { cakeItems ->
                when (cakeItems) {
                    is Resource.Success -> _cakeList.value = cakeItems
                    is Resource.Error -> {
                        _cakeList.value = Resource.Error(cakeItems.message ?: "An error occurred")
                    }

                    else -> null
                }
            }
        }
    }
}