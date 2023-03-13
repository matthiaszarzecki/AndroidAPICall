package com.matthiaszarzecki.androidapicall02

import retrofit2.Call
import retrofit2.http.GET

interface API {
  @GET("posts")
  fun getPosts(): Call<List<Post>>
}