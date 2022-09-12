package kr.loner.flowsample.ui

import kr.loner.flowsample.data.model.Blog
import kr.loner.flowsample.ui.model.UiBlog
import kr.loner.flowsample.ui.model.UiBlogGroup

fun Blog.toUiModel() = UiBlog(blog = this)
fun List<Blog>.toUiBlogGroup() = UiBlogGroup(map(Blog::toUiModel))
