package com.dor.compose.playground.composedestinationslibrary.screens.navigationwithargs

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dor.compose.playground.composedestinationslibrary.models.User
import com.dor.compose.playground.composedestinationslibrary.screens.navArgs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class NavigationWithArgsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _user = MutableStateFlow(User("", ""))
    val user: StateFlow<User> = _user

    init {
        viewModelScope.launch {
            val user: User? = savedStateHandle.navArgs()
            user?.let {
                delay(4000)
                _user.value = it
            }
        }
    }
}