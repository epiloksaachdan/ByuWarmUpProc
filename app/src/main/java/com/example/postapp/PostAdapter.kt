package com.example.postapp

import  android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.database.Post
import kotlinx.android.synthetic.main.list_post_item.view.*

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val postList = arrayListOf<Post>()

    inner class PostViewHolder(private val v: View) : RecyclerView.ViewHolder(v){
        fun bind(post : Post){
            v.apply {
                tvPostTitle.text = post.title
                PostBody.text = post.body
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
         val view = PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_post_item, parent, false))
        return view
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    fun updateData(newList: List<Post>) {
        postList.clear()
        postList.addAll(newList)
        notifyDataSetChanged()
    }
}

