package com.example.matiz.demoblog.activity.view.postsList

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.matiz.demoblog.R
import com.example.matiz.demoblog.activity.util.BaseActivity
import com.example.matiz.demoblog.activity.view.post.PostActivity
import com.example.matiz.demoblog.backend.model.Post
import com.example.matiz.demoblog.activity.view.postsList.Params.POSTS_VIEW
import org.koin.android.ext.android.inject
import kotlinx.android.synthetic.main.activity_posts_list.*

class PostsListActivity : BaseActivity(), PostsListContract.View{
    override val presenter: PostsListContract.Presenter by inject{ mapOf(POSTS_VIEW to this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_list)
        posts_list.layoutManager = LinearLayoutManager(this)
        presenter.displayPosts()
    }
    override fun displayProgress() = setLoaderVisibility(View.VISIBLE)

    override fun displaySuccess() = setLoaderVisibility(View.GONE)

    override fun onGetPostsSuccess(posts: List<Post>) {
        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down);
        posts_list.setLayoutAnimation(controller);
        posts_list.adapter = PostsListAdapter(posts, this::postClick)
        posts_list.scheduleLayoutAnimation();
    }

    override fun onGetPostsFailed(error: Throwable){
        Toast.makeText(this, "failed to load posts", Toast.LENGTH_LONG).show()
        setLoaderVisibility(View.GONE)
    }

    private fun postClick(post : Post){
        var intent = Intent(this, PostActivity::class.java)
        intent.putExtra(PostActivity.EXTRAS_NAMES.POST, post)
        startActivity(intent)
    }
    private fun setLoaderVisibility(visibility : Int){
        TransitionManager.beginDelayedTransition(main_container);
        list_loader.visibility = visibility
    }

}
