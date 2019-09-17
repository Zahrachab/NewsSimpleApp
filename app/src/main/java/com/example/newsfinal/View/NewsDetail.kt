package com.example.newsfinal.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.newsfinal.Model.News
import com.example.newsfinal.R

import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import com.like.LikeButton
import com.like.OnLikeListener
import com.example.newsfinal.Room.*
import kotlinx.android.synthetic.main.activity_news_detail.*


class NewsDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        bindData()
        var intentThatStartedThisActivity = getIntent()


        val db =NewsDB.getInstance(this)

            val dao = db?.articleDao()

            if (article?.id?.let { dao?.getNewsById(it) } != null ) {


                likeButton.isLiked=true
            }


            var art = News()
            var act=this
            art.id= article?.id
            art.title = article!!.title
            art.description =   article!!.description
            art.image = article!!.image
            art.author = article!!.author
            art.categorie = article!!.categorie
            art.date = article!!.date
            art.url= article!!.url




            likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton) {

                    dao?.saveNews(art)

                    AppTools.showToast(act, "Article archivé")

                    dao?.getNews()?.forEach()
                    {
                        Log.i("Fetch Records", "Id:  : ${it.id}")
                        Log.i("Fetch Records", "Name:  : ${it.author}")

                    }

                }

                override fun unLiked(likeButton: LikeButton) {
                    dao?.deleteNews(art)
                    AppTools.showToast(act, "Article supprimié")
                    dao?.getNews()?.forEach()
                    {
                        Log.i("Fetch Records", "Id:  : ${it.id}")
                        Log.i("Fetch Records", "Name:  : ${it.author}")

                    }

                }
            })


            //fetch Records





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

}

