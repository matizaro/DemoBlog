package com.example.matiz.demoblog.activity.view.postsList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matiz.demoblog.R
import com.example.matiz.demoblog.backend.model.Post
import kotlinx.android.synthetic.main.post_list_el.view.*

class PostsListAdapter(val posts : List<Post>, val onPostClick : (post: Post) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_list_el, parent, false),onPostClick)

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(posts.get(position))

}

class ViewHolder(val view : View, val onPostClick : (post: Post) -> Unit) : RecyclerView.ViewHolder(view){
    fun bind(post: Post){
        view.post_title.text = post.title
        view.content.text = post.body
        view.setOnClickListener({onPostClick.invoke(post)})
    }
}