package kr.loner.flowsample.data.model

data class Blog(
    val id:Long,
    val title: String = "블로그 이름 입니다",
    val content: String? = "ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㅇㅁㄴㅇㅁㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㅇㅁㄴㅇㅁㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㅇㅁㄴㅇㅁㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㅇㅁㄴㅇㅁ",
    val type:BlogType
)

enum class BlogType{
    Naver,
    Daum
}