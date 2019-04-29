package com.example.datastorage.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.widget.RatingBar
import com.example.datastorage.R
import com.example.datastorage.Modelos.Movie
import com.google.gson.Gson
import android.view.View
import android.graphics.BitmapFactory

class MovieInformationActivity : AppCompatActivity() {

    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_information)

        val data = this.intent.getStringExtra("movie")
        movie = Gson().fromJson(data, Movie::class.javaObjectType)

        findViewById<TextView>(R.id.nameProfileInfo).text = movie.name
        findViewById<TextView>(R.id.duracion).text = movie.duration.toString()
        findViewById<TextView>(R.id.year).text = movie.year.toString()
        findViewById<TextView>(R.id.sinopsis).text = movie.synopsis
        findViewById<TextView>(R.id.director).text = movie.director
        findViewById<RatingBar>(R.id.ratingBar).rating = movie.score!!.toFloat()
        val img = movie.image
        if (img != null) {
            findViewById<ImageView>(R.id.imageProfileInfo).setImageBitmap(
                BitmapFactory.decodeByteArray(img, 0, img.size)
            )
        }


    }

    fun goBack(view : View) {
        val intent = Intent(this, MoviesListActivity::class.java)
        startActivity(intent)
    }
}
