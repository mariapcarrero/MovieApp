package com.example.datastorage.Servicios

import com.example.datastorage.Modelos.MoviexUser
import com.example.datastorage.Modelos.Movie

interface IFavoriteMovieServices {
    // fun verifyUser(user: Movie) : Boolean
    fun saveFavoriteMovie(movie: MoviexUser)
    fun consultFavoriteMovies(id: Int) : List<Movie>?
    //fun existsMovie(movie : MoviexUser) : Boolean
}


