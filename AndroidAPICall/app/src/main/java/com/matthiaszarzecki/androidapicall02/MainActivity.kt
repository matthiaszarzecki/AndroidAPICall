package com.matthiaszarzecki.androidapicall02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    getMyData()
  }

  private fun getMyData() {
    val retroFitBuilder = Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(BASE_URL)
      .build()
      .create(API::class.java)

    val retrofitData = retroFitBuilder.getPosts()

    retrofitData.enqueue(
      object: Callback<List<Post>?> {
        override fun onResponse(
          call: Call<List<Post>?>,
          response: Response<List<Post>?>
        ) {
          val responseBody = response.body()!!

          Log.d(TAG, "Starting Call")

          val myStringBuilder = StringBuilder()
          for (post in responseBody) {
            myStringBuilder.append(post.title)
            myStringBuilder.append("\n")

            Log.d(TAG, "Adding Post Data to Display")
          }

          val textView: TextView = findViewById(R.id.displayText)
          textView.text = myStringBuilder
        }

        override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
          Log.d(TAG, "Call Failed!")
        }
      }
    )
  }
}