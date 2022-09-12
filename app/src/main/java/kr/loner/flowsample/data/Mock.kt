package kr.loner.flowsample.data

import kr.loner.flowsample.data.model.Blog
import kr.loner.flowsample.data.model.BlogType

object Mock {
    val sampleBlogList: List<Blog> = listOf(
        Blog(id= 0L , type = BlogType.Daum),
        Blog(id= 1L , content = null, type = BlogType.Daum),
        Blog(id= 2L , type = BlogType.Naver),
        Blog(id= 3L , type = BlogType.Daum),
        Blog(id= 4L , type = BlogType.Naver),
        Blog(id= 5L , type = BlogType.Daum),
        Blog(id= 6L , type = BlogType.Naver),
        Blog(id= 7L , type = BlogType.Daum),
        Blog(id= 8L , type = BlogType.Naver),
        Blog(id= 9L , type = BlogType.Daum),
        Blog(id= 10L , type = BlogType.Naver),
        Blog(id= 11L , type = BlogType.Daum),
        Blog(id= 12L , type = BlogType.Naver),
        Blog(id= 13L , type = BlogType.Daum),
        Blog(id= 14L , type = BlogType.Naver)
    )
}