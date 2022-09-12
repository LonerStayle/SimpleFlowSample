package kr.loner.flowsample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kr.loner.flowsample.data.BlogRepository

class MainViewModel() : ViewModel() {

    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    val uiEvent: SharedFlow<MainUiEvent> = _uiEvent

    fun openPage(mainUiEvent: MainUiEvent) {
        viewModelScope.launch{
            _uiEvent.emit(mainUiEvent)
        }
    }

    sealed class MainUiEvent {
        object OpenFirstHomeWork : MainUiEvent()
        object OpenSecondHomeWork : MainUiEvent()
    }
}