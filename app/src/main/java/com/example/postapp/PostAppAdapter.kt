package com.example.postapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postappapi.PostResponse
import kotlinx.android.synthetic.main.list_post_item.view.*

class PostAppAdapter(private val list: ArrayList<PostResponse>): RecyclerView.Adapter<PostAppAdapter.PostViewHolder>() {
    inner class  PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(postResponse: PostResponse) {
            itemView.apply {
                tvPostTitle.text = postResponse.title
                tvPostBody.text = postResponse.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_post_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


}