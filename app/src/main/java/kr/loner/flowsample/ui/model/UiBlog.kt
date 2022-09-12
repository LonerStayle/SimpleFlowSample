package kr.loner.flowsample.ui.model

import kr.loner.flowsample.data.model.Blog

data class UiBlog(
    val blog: Blog,
    val isSelected: Boolean = false
)
