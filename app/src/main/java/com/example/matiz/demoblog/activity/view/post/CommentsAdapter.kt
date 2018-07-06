package com.example.matiz.demoblog.activity.view.post

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matiz.demoblog.R
import com.example.matiz.demoblog.backend.model.Comment
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.comment_list_el.view.*
import kotlinx.android.synthetic.main.post_list_el.view.*

class CommentsAdapter(val comments : List<Comment>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comment_list_el, parent, false))

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(comments.get(position))

}

class ViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    fun bind(comment: Comment){
        view.email.text = comment.email
        view.comment.text = comment.body
    }
}