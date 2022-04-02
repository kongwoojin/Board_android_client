package com.kongjak.board_client

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kongjak.board_client.connections.RetrofitBuilder
import com.kongjak.board_client.data.Article
import com.kongjak.board_client.databinding.ActivityArticleBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 1)

        getApi(id)
    }

    private fun getApi(id: Int) {
        RetrofitBuilder.api.getArticle(id).enqueue(object : Callback<Article> {
            override fun onResponse(
                call: Call<Article>,
                response: Response<Article>
            ) {
                val data = response.body()
                binding.titleTextView.text = data?.title
                binding.textTextView.text = data?.text
                binding.usernameTextView.text = data?.username

                Log.d("response", data.toString())
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }
}