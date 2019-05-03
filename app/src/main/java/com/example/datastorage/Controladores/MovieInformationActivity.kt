package com.example.datastorage.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import android.widget.Button
import android.widget.RatingBar
import com.example.datastorage.R
import com.example.datastorage.Modelos.Movie
import com.google.gson.Gson
import android.view.View
import android.graphics.BitmapFactory
import android.widget.Toast
import com.example.datastorage.Modelos.User
import com.example.datastorage.Servicios.FavoriteMovieDBServices
import com.example.datastorage.Servicios.PermissionService
import com.like.LikeButton
import com.like.OnLikeListener







class MovieInformationActivity : AppCompatActivity() {

    private lateinit var movie : Movie
    private lateinit var user : User
    private lateinit var userData : String
    private var movieIsFavorite : Boolean = false

    private lateinit var favMovieBDService : FavoriteMovieDBServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_information)

        favMovieBDService = FavoriteMovieDBServices(this)

        val data = this.intent.getStringExtra("movie")
        movie = Gson().fromJson(data, Movie::class.javaObjectType)

        userData = this.intent.getStringExtra("user")
        user = Gson().fromJson(userData, User::class.javaObjectType)

        findViewById<TextView>(R.id.nameProfileInfo).text = movie.name
        findViewById<TextView>(R.id.duracion).text = movie.duration.toString()
        findViewById<TextView>(R.id.year).text = movie.year.toString()
        findViewById<TextView>(R.id.sinopsis).text = movie.synopsis
        findViewById<TextView>(R.id.director).text = movie.director
        findViewById<RatingBar>(R.id.ratingBar).rating = movie.score!!.toFloat()
        var clicked : Int = 1;

        val favoriteButton = findViewById<Button>(R.id.button)
        var alreadyExists: Boolean?
        alreadyExists = favMovieBDService.checkFavoriteMovie(user.idUser,movie.idMovie)
        movieIsFavorite = alreadyExists
        if (alreadyExists){
            favoriteButton.text="Eliminar de Favoritos"
        }
        else {
            favoriteButton.text="Agregar a Favoritos"
        }
        val img = movie.image
        if (img != null) {
            findViewById<ImageView>(R.id.imageProfileInfo).setImageBitmap(
                BitmapFactory.decodeByteArray(img, 0, img.size)
            )
        }
    }

    fun markFavorite(view : View) {
        val favoriteButton = findViewById<Button>(R.id.button)
        if (!movieIsFavorite) {
            favMovieBDService.markAsFavorite(user, movie)
            favoriteButton.text = "Eliminar de Favoritos"
        } else {
            favMovieBDService.unmarkAsFavorite(user, movie)
            favoriteButton.text = "Agregar a Favoritos"
        }
    }

    fun goBack(view : View) {
        val intent = Intent(this, MoviesListActivity::class.java)
        intent.putExtra("user", userData)
        startActivity(intent)
    }


}
