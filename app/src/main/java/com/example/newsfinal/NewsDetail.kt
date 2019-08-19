package com.example.newsfinal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.AsyncTask
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_news_detail.view.*


class NewsDetail : AppCompatActivity() {

    private var db: AppDatabase? = null
    private var newsDao: NewsDao? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        bindData()
        var intentThatStartedThisActivity = getIntent()

        likeButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                sauvegarder()
            }

            override fun unLiked(likeButton: LikeButton) {

            }
        })
    }

    fun bindData() {
        categorie_news.text = article!!.categorie
        title_article.text = article!!.title
        date.text = article!!.date
        categorie_article.text = article!!.categorie
        auteur.text = article!!.author
        txt_descreption.text = article!!.description


    }
    companion object {
        var article : News? = null
    }

    fun sauvegarder() {
        db = AppDatabase.getDatabase(context = this)
        with(newsDao){
            this?.insert(article!!)
        }
    }
}

