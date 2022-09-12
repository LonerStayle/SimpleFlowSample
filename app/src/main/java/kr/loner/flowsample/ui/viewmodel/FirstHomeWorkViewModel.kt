package kr.loner.flowsample.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.loner.flowsample.data.BlogRepository
import kr.loner.flowsample.data.model.BlogType
import kr.loner.flowsample.ui.model.UiBlog
import kr.loner.flowsample.ui.model.UiBlogGroup
import kr.loner.flowsample.ui.toUiBlogGroup

class FirstHomeWorkViewModel(private val blogRepository: BlogRepository) : ViewModel() {

    private val _uiBlogState = MutableStateFlow(FirstHomeWorkUiBlogState.initData)
    val firstHomeWorkUiBlogState: StateFlow<FirstHomeWorkUiBlogState> = _uiBlogState

    val completeOnlyNaverSelect: StateFlow<Boolean> = firstHomeWorkUiBlogState.map {
        val resultList = it.uiBlogGroup.value.filter { uiBlog ->
            uiBlog.isSelected && uiBlog.blog.type == BlogType.Naver
        }
        resultList.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    val completeOnlyDaumSelect: StateFlow<Boolean> = firstHomeWorkUiBlogState.map {
        val resultList = it.uiBlogGroup.value.filter { uiBlog ->
            uiBlog.isSelected && uiBlog.blog.type == BlogType.Daum
        }
        resultList.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    val completeFourCountSelect: StateFlow<Boolean> = firstHomeWorkUiBlogState.map {
        it.uiBlogGroup.value.filter { it.isSelected }.size >= 4
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    val completeComplexSelect: StateFlow<Boolean> = firstHomeWorkUiBlogState.map { uiBlog ->
        val selectUiBlogs = uiBlog.uiBlogGroup.value.filter { it.isSelected }
        selectUiBlogs.find { it.blog.type == BlogType.Naver } != null
                && selectUiBlogs.find { it.blog.type == BlogType.Daum } != null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    private val _uiEvent = MutableSharedFlow<FirstHomeWorkUiEvent>()
    val uiEvent: SharedFlow<FirstHomeWorkUiEvent> = _uiEvent

    init {
        _uiBlogState.update {
            FirstHomeWorkUiBlogState(blogRepository.getBlogList.toUiBlogGroup()) {
                selectBlog(it)
            }
        }
    }

    private fun selectBlog(uiBlog: UiBlog) {
        _uiBlogState.update { uiState ->
            uiState.copy(uiBlogGroup = UiBlogGroup(uiState.uiBlogGroup.value.map { thisUiBlog ->
                if (uiBlog.blog.id == thisUiBlog.blog.id) {
                    uiBlog
                } else {
                    thisUiBlog
                }
            }))
        }
    }

    fun uiEvent(event: FirstHomeWorkUiEvent, msg: String = "") {
        viewModelScope.launch {
            val executeEvent = when (event) {
                is FirstHomeWorkUiEvent.CompleteOnlyNaverSelectMsg -> {
                    FirstHomeWorkUiEvent.ShowMsg(
                        if (completeOnlyNaverSelect.value) "활성화" else "비활성화"
                    )
                }
                is FirstHomeWorkUiEvent.CompleteOnlyDaumSelectMsg -> {
                    FirstHomeWorkUiEvent.ShowMsg(
                        if (completeOnlyDaumSelect.value) "활성화" else "비활성화"
                    )
                }
                is FirstHomeWorkUiEvent.CompleteFourCountSelectMsg -> {
                    FirstHomeWorkUiEvent.ShowMsg(
                        if (completeFourCountSelect.value) "활성화" else "비활성화"
                    )
                }
                is FirstHomeWorkUiEvent.CompleteComplexSelectMsg -> {
                    FirstHomeWorkUiEvent.ShowMsg(
                        if (completeComplexSelect.value) "활성화" else "비활성화"
                    )
                }
                is FirstHomeWorkUiEvent.ShowMsg -> {
                    FirstHomeWorkUiEvent.ShowMsg(msg)
                }
            }
            _uiEvent.emit(executeEvent)
        }
    }
}

data class FirstHomeWorkUiBlogState(
    val uiBlogGroup: UiBlogGroup,
    val selectBlog: ((UiBlog) -> Unit) = {}
) {
    companion object {
        val initData = FirstHomeWorkUiBlogState(UiBlogGroup(emptyList()))
    }
}

sealed class FirstHomeWorkUiEvent {
    object CompleteOnlyNaverSelectMsg : FirstHomeWorkUiEvent()
    object CompleteOnlyDaumSelectMsg : FirstHomeWorkUiEvent()
    object CompleteFourCountSelectMsg : FirstHomeWorkUiEvent()
    object CompleteComplexSelectMsg : FirstHomeWorkUiEvent()
    class ShowMsg(val msg: String) : FirstHomeWorkUiEvent()
}