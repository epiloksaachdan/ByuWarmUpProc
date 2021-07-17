package com.example.postapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postapp.database.Post
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val postAdapter: PostAdapter by lazy {PostAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()
        loadData()
    }

    private fun setAdapter() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = postAdapter

    }

    private fun deleteData(){

    }

    private fun loadData() {

        val postList = arrayListOf<Post>()
        for (i in 1..5) {
            postList.add(Post(1,"Title $i","ini isinya"))
        }
        postAdapter.updateData(postList)
    }
}