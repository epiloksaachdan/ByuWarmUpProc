package com.example.postapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postappapi.PostResponse
import com.example.postappapi.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<PostResponse>()

    private val postAdapter: PostAppAdapter by lazy { PostAppAdapter(list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()
        showPost()
        setListener()

        //insert()
    }

    override fun onResume() {
        super.onResume()
        showPost()

    }

    private fun setAdapter(){
        postAdapter.setOnClickListener {
//            val intent = Intent(this, InsertUpdateActivity::class.java)
//            intent.putExtra("id", it.id)
//            startActivity(intent)
            navigate(it.id)
        }
        postAdapter.setOnDeleteListener {
            AlertDialog.Builder(this)
                .setTitle("delete")
                .setMessage("sure ?")
                .setPositiveButton("Yes"){
                        dialog,_ ->
//                    AppDatabase.getInstance(this).postDao().delete(it)
                    RetrofitClient.instance.delete(it).enqueue(object : Callback<Void>{
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            //Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
                            showPost()
                            dialog.dismiss()
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })

                }
                .setNegativeButton("No"){
                        dialog,_ -> dialog.dismiss()
                }
                .show()
        }


    }

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
        postAdapter.updateData(list)

    }

    private fun navigate (id: String?){
        val intent = Intent(this, InsertUpdateActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    private fun setListener(){
        fabadd.setOnClickListener{
            navigate("0")
        }
    }
}