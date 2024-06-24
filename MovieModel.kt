package com.example.movieui.module.home.model

import com.example.movieui.R

data class MovieModel(
    val id:String,
    val title: String,
    val assetImage: Int,
    val type: String,
    val duration: String,
    val rating: String,
    val synopsis: String,
    val isPlaying: Boolean
)


val nowPlayingMovie = listOf(
    MovieModel(
        id= "1",
        title = "Godzilla The New Empire",
        assetImage = R.drawable.godzilla,
        type = "Action",
        duration = "1h 27m",
        rating = "9.0/10",
        synopsis = "Godzilla mengisahkan tentang kebangkitan Godzilla yang kembali untuk melawan ancaman baru terhadap dunia. " +
                "Dalam konflik epik ini, monster raksasa ini harus menghadapi musuh yang lebih kuat dari sebelumnya sambil berusaha melindungi umat manusia dari kehancuran total.",
        isPlaying = true
    ),
    MovieModel(
        id= "2",
        title = "Pemandi Jenazah",
        assetImage = R.drawable.pemandijenazah,
        type = "Horror",
        duration = "1h 59m",
        rating = "8.0/10",
        synopsis = "Pemandi jenazah yang mengisahkan tentang seseorang yang memandikan jenazah neneknya" +
                " yang tanpa sengaja terlibat dalam serangkaian kejadian misterius yang mengungkap rahasia kelam di balik setiap mayat yang ia hadapi.",
        isPlaying = true
    ),
    MovieModel(
        id= "3",
        title = "Avatar The Way of Water",
        assetImage = R.drawable.avatar,
        type = "Action",
        duration = "2h 29m",
        rating = "9.5/10",
        synopsis = "Avatar yang mengisahkan tentang peperangan dengan kaum laut dan kaum darat di dunia Pandora, " +
                "di mana Jake Sully dan Neytiri berusaha menjaga keseimbangan alam semesta sambil mengeksplorasi lautan yang mempesona, mengungkapkan rahasia dan keajaiban baru dari planet tersebut.",
        isPlaying = true
    )
)

val upcoming = listOf(
    MovieModel(
        id= "4",
        title = "Sonic The Hedgehog",
        assetImage = R.drawable.sonic,
        type = "Animation",
        duration = "1h 46m",
        rating = "8.7/10",
        synopsis = "Sonic the hedgehog yang mengisahkan tentang cincin yang dapat berpindah tempat",
        isPlaying = false
    ),
    MovieModel(
        id= "5",
        title = "Agak Laen",
        assetImage = R.drawable.agaklaen,
        type = "Comedy",
        duration = "2h 11m",
        rating = "9.9/10",
        synopsis = "Film buatan anak indonesia yang berasal dari podcast agak laen",
        isPlaying = false
    ),
    MovieModel(
        id= "6",
        title = "Black Panther Wakanda Forever",
        assetImage = R.drawable.blackpanther,
        type = "Action",
        duration = "1h 46m",
        rating = "9.4/10",
        synopsis = "Black Panther yang menceritakan kisah wakanda",
        isPlaying = false
    )
)

