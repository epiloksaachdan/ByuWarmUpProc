package com.example.postapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postapp.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val postAdapter: PostAdapter by lazy { PostAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
        setAdapter()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun navigate (id: Long){
        val intent = Intent(this, InsertUpdateActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }

    private fun setListener(){
        fabadd.setOnClickListener{
            navigate(0)
        }
    }

    private fun setAdapter(){
        postAdapter.setOnClickListener {
            navigate(it.id)
        }
        postAdapter.setOnDeleteListener {
            AlertDialog.Builder(this)
                .setTitle("delete")
                .setMessage("sure ?")
                .setPositiveButton("Yes"){
                    dialog,_ ->
                        AppDatabase.getInstance(this).postDao().delete(it)
                        Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
                        loadData()
                        dialog.dismiss()
                }
                .setNegativeButton("No"){
                    dialog,_ -> dialog.dismiss()
                }
                .show()
        }
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = postAdapter
    }

    private fun loadData(){
        //Load from Database Room
        val postList = AppDatabase.getInstance(this).postDao().get()
        postAdapter.updateData(postList)


//        val postList = arrayListOf<Post>()
//        for(i in 1..10){
//            postList.add(Post(1,"Title $i","Body $i"))
//        }
//        postAdapter.updateData(postList)

    }
}
