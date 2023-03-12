package com.matthiaszarzecki.androidapicall02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

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
            .create(ApiInterface::class.java)

        val retrofitData = retroFitBuilder.getData()

        retrofitData.enqueue(
            object : Callback<List<MyDataItem>?> {
                override fun onResponse(
                    call: Call<List<MyDataItem>?>,
                    response: Response<List<MyDataItem>?>
                ) {
                    val responseBody = response.body()!!

                    val myStringBuilder = StringBuilder()
                    for (myData in responseBody) {
                        myStringBuilder.append(myData.id)
                        myStringBuilder.append("\n")
                    }

                    val textView: TextView = findViewById<TextView>(R.id.displayText)
                    textView.text = myStringBuilder
                }

                override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {

                }
            }
        )
    }
}