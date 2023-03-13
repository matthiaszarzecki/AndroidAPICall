package com.matthiaszarzecki.androidapicall02

data class Post (
    val body: String,
    var id: Int,
    var title: String,
    val userId: Int
)

data class PostResponse (
    val posts: List<Post>
)