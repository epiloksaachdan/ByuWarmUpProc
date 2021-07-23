package com.example.postapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postappapi.PostResponse
import com.example.postappapi.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showPost()
        setListener()

        //insert()

    }

//    private fun insert() {
//        RetrofitClient.instance.insert(
////            "achdan",
////            tvPostTitle.text.toString(),
////            tvPostBody.text.toString()
//            "achdan",
//            "title 5",
//            "body 5"
//
//        ).enqueue(object : Callback<CreatePostResponse>{
//            override fun onResponse(
//                call: Call<CreatePostResponse>,
//                response: Response<CreatePostResponse>
//            ) {
//
//            }
//
//            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
//
//                tvtitle.text = "FAIL"
//            }
//        })
//    }

    private fun showPost() {
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager =  LinearLayoutManager( this)

        RetrofitClient.instance.getPosts().enqueue(object: Callback<ArrayList<PostResponse>>{
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                response.body()?.let { list.addAll(it)}
                val adapter = PostAppAdapter(list)
                recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }

    private fun navigate (id: String){
        println(id)
        val intent = Intent(this, InsertUpdateActivity::class.java)
        println("checked")
        //intent.putExtra("id",id)
        startActivity(intent)
    }

    private fun setListener(){
        fabadd.setOnClickListener{
            navigate("0")
            println("checked")
        }
    }


}