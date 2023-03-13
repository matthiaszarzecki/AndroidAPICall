package com.matthiaszarzecki.androidapicall02

import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
  /// Calling https://jsonplaceholder.typicode.com/posts - returns directly a list of Posts
  @GET("posts")
  fun getPosts(): Call<List<Post>>
}