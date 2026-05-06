package com.example.composeapp.models

import androidx.annotation.DrawableRes

data class Movie(
    val id :Int,
    val title : String,
    val desc : String,
    val rating : Double,
    val imageUrl : String,
    val genre : String,
    val year : String,
    val duration : String,
    val director: String,
    val writer: String,
    val stars: List<String>
)

data class City(
    val id: Int,
    val name: String
)

data class Cinema(
    val id: Int,
    val name: String,
    val address: String,
    val cityId: Int,
    val imageUrl: String = "https://link-to-cinema-placeholder.com"
)