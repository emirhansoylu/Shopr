package dev.duckbuddyy.shopr.carts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.duckbuddyy.shopr.domain.ShoprRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartsViewModel @Inject constructor(
    private val shoprRepository: ShoprRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            println("deneme1")
            shoprRepository.getCart().onSuccess { cart ->
                cart.products.forEach {
                    println(it)
                }
            }
            println("deneme1")
        }
    }

}