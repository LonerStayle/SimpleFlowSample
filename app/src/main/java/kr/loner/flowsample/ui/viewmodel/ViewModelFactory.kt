package kr.loner.flowsample.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.loner.flowsample.data.BlogRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val blogRepository: BlogRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FirstHomeWorkViewModel::class.java) -> {
                FirstHomeWorkViewModel(blogRepository) as T
            }
            modelClass.isAssignableFrom(SecondHomeWorkViewModel::class.java) -> {
                SecondHomeWorkViewModel(blogRepository) as T
            }
            else -> throw IllegalArgumentException()
        }
    }
}