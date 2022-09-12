package kr.loner.flowsample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.loner.flowsample.data.BlogRepository
import kr.loner.flowsample.data.model.Blog

class SecondHomeWorkViewModel(private val blogRepository: BlogRepository) : ViewModel() {
    val _blogList = MutableStateFlow<List<Blog>>(emptyList())
    val blogList: StateFlow<List<Blog>> = _blogList
    init{
        _blogList.update {
            val getBlogList =  blogRepository.getBlogList
            getBlogList
        }

    }
    private val _showMsg = MutableSharedFlow<ShowMsg>()
    val showMsg: SharedFlow<ShowMsg> = _showMsg

    fun showToast(event: ShowMsg) {
        viewModelScope.launch {
            _showMsg.emit(event)
        }
    }

    class ShowMsg(val msg: String)
}