package com.example.postapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postappapi.RetrofitClient
import kotlinx.android.synthetic.main.activity_insert_update.*

class InsertUpdateActivity : AppCompatActivity() {

    private var id = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_update)

        id = intent.getLongExtra("ID",0).toString()

        if(id == "0"){
            //insert
            tvInsertUpdateTitle.text="Insert Post"
        }
        else {
            //update
            tvInsertUpdateTitle.text="Update Post"
            //load data

//            val post = AppDatabase.getInstance(this).postDao().getById(id)
//            ettitle.setText(post.title)
//            etbody.setText(post.body)
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
                    insert(title,body)
                }
                else {
                    //update(id,title,body)
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

    private fun insert(title: String,body: String){
        //val postResponse = PostAppAdapter(id)
        val postapp = CreatePostResponse(id,"achdan",title,body)
        val id = RetrofitClient.instance.insert(postapp)

        if (id != "" ){
            finish()
            Toast.makeText(this,"Insert Success", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
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