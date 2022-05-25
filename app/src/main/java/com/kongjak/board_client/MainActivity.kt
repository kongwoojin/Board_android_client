package com.kongjak.board_client

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kongjak.board_client.adapter.BoardAdapter
import com.kongjak.board_client.connections.RetrofitBuilder
import com.kongjak.board_client.data.Board
import com.kongjak.board_client.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayList: ArrayList<Board>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        getApi()

        arrayList = ArrayList()

        val boardAdapter = BoardAdapter(arrayList)
        boardAdapter.itemClick = object : BoardAdapter.ItemClick {
            override fun onClick(view: View, position: Int, id: Int) {
                val intent = Intent(baseContext, ArticleActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        }

        recyclerView = binding.includedLayout.recyclerView
        recyclerView.apply {
            adapter = boardAdapter
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getApi() {
        RetrofitBuilder.api.getBoard().enqueue(object : Callback<ArrayList<Board>> {
            override fun onResponse(
                call: Call<ArrayList<Board>>,
                response: Response<ArrayList<Board>>
            ) {
                val list = response.body()
                arrayList.addAll(list!!)
                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<Board>>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }
}