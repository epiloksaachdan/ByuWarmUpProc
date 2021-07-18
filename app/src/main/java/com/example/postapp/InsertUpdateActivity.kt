package com.example.postapp

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postapp.database.AppDatabase
import com.example.postapp.database.Post
import kotlinx.android.synthetic.main.activity_insert_update.*

class InsertUpdateActivity : AppCompatActivity() {

    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_update)

        id = intent.getLongExtra("ID",0)

        if(id == 0L){
            //insert
            tvInsertUpdateTitle.text="Insert Post"
        }
        else {
            //update
            tvInsertUpdateTitle.text="Update Post"
            //load data

            val post = AppDatabase.getInstance(this).postDao().getById(id)
            ettitle.setText(post.title)
            etbody.setText(post.body)
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
                if (id == 0L){
                    insert(title,body)
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

    private fun insert(title: String,body: String){
        val post = Post(0,title,body)
        val id = AppDatabase.getInstance(this).postDao().insert(post)

        if (id > 0 ){
            finish()
            Toast.makeText(this,"Insert Success", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
        }
    }
    private fun update(id: Long,title: String,body: String){
        val post = Post(id,title,body)
        val row = AppDatabase.getInstance(this).postDao().update(post)

        if (row > 0 ){
            finish()
            Toast.makeText( this,"Update Success", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
        }
    }
}