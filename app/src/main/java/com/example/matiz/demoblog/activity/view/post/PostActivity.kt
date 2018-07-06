package com.example.matiz.demoblog.activity.view.post

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.matiz.demoblog.R
import com.example.matiz.demoblog.activity.util.BaseActivity
import com.example.matiz.demoblog.backend.model.Comment
import com.example.matiz.demoblog.backend.model.Post
import com.example.matiz.demoblog.backend.model.User

import kotlinx.android.synthetic.main.activity_post.*
import org.koin.android.ext.android.inject

class PostActivity : BaseActivity(), PostContract.View {

    override val presenter: PostContract.Presenter by inject{ mapOf(Params.POST_VIEW to this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        comments.layoutManager = LinearLayoutManager(this)
        comments.isNestedScrollingEnabled = false
        var bundle :Bundle ?= intent.extras
        bundle?.containsKey(EXTRAS_NAMES.POST).let{
            var post = this.intent.extras[EXTRAS_NAMES.POST]
            if(post is Post){
                presenter.displayComments(post.id)
                presenter.displayUser(post.userId)
                post_title.text = post.title
                post_content.text = post.body
                logPost(post)
            }
        }
    }
    override fun displayCommentsProgress() {
        comments_pb.visibility=View.VISIBLE
    }

    override fun displayCommentsSuccess() {
        comments_pb.visibility=View.GONE
    }

    override fun displayUserProgress() {
        content_pb.visibility=View.VISIBLE
    }

    override fun displayUserSuccess() {
        content_pb.visibility=View.GONE
    }

    override fun onGetUserSuccess(user: User) {
        author.text=user.name
        author.setOnClickListener({showEmailWindow(user.email)})
    }

    override fun onGetUserFailed(error: Throwable) {
        Toast.makeText(this, "cannot get the user data", Toast.LENGTH_LONG).show()
        author.text="undefined"
        content_pb.visibility=View.GONE
    }

    override fun onGetCommentsSuccess(comments: List<Comment>) {
        if(comments.size==0){
            Toast.makeText(this, "there is no comments to this post", Toast.LENGTH_LONG).show()
        }else{
            this.comments.adapter = CommentsAdapter(comments)
        }
        comments_pb.visibility=View.GONE
    }

    override fun onGetCommentsFailed(error: Throwable) {
        Toast.makeText(this, "cannot get the comments", Toast.LENGTH_LONG).show()
    }

    private fun showEmailWindow(email: String) {
        val emailIntent = Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf(email));
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    private fun logPost(post: Post) {
        var bundle = Bundle()
        bundle.putInt("post_id", post.id)
        bundle.putInt("post_user_id", post.userId)
        firebaseAnalytics.logEvent("post_opened", bundle);
    }


    object EXTRAS_NAMES{
        val POST="POST"
    }

}
