package com.example.postapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postappapi.PostResponse
import com.example.postappapi.RetrofitClient
import kotlinx.android.synthetic.main.activity_insert_update.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class InsertUpdateActivity : AppCompatActivity() {

    private var id = "0"
    private val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_update)

        //id = intent.getStringExtra("id").toString()

        if(id == "0"){
            //insert
            tvInsertUpdateTitle.text="Insert Post"
        }
        else {
            //update
            tvInsertUpdateTitle.text="Update Post"
            //load data

            RetrofitClient.instance.getById(id).enqueue(object: Callback<ArrayList<PostResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<PostResponse>>,
                    response: Response<ArrayList<PostResponse>>
                ) {

                }

                override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

                }

            })

        }

        setListener()
    }

    private fun setListener(){
        fabdone.setOnClickListener{
            val message = validate()
            if (message == "") {
                //insert/update
                var title = ettitle.text.toString()
                var body = etbody.text.toString()
                if (id == "0"){
                    insert("achdan", title, body)
                }
                else {
                    update(id,title,body)
                }
            }
            else{
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validate(): String{
        val title = ettitle.text.toString().trim()
        val body = etbody.text.toString().trim()

        return when {
            title.isEmpty() -> "Please input title"
            body.isEmpty() -> "Please input body "
            else -> ""
        }
    }

    private fun insert(user: String,title: String,body: String){
        //val postResponse = PostAppAdapter(id)
        val post = CreatePostResponse("achdan",title,body)
        val id = RetrofitClient.instance.insert(post).enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful){
                    val id = response.body()?.id
                    val user = response.body()?.user
                    val title = response.body()?.title
                    val body = response.body()?.body
                    println("RESULT ID $id")
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        if (id.equals("")){
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
        }
        else{
            finish()
            Toast.makeText(this,"Insert Success", Toast.LENGTH_SHORT).show()
        }
    }
    private fun update(user: String,title: String,body: String){
        //val postResponse = PostAppAdapter(id)
        val post = CreatePostResponse("achdan",title,body)
        val id = RetrofitClient.instance.update(post).enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if (response.isSuccessful){
                    val id = response.body()?.id
                    val user = response.body()?.user
                    val title = response.body()?.title
                    val body = response.body()?.body
                    println("RESULT ID $id")
                }
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

        if (id.equals("")){
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
        }
        else{
            finish()
            Toast.makeText(this,"Update Success", Toast.LENGTH_SHORT).show()
        }
    }


////    private fun update(id: Long,title: String,body: String){
//        val post = Post(id,title,body)
//        val row = AppDatabase.getInstance(this).postDao().update(post)
//
//        if (row > 0 ){
//            finish()
//            Toast.makeText( this,"Update Success", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
//        }
//    }
}