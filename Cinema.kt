package com.example.movieui.core.theme

data class Cinema(
val name: String,
val operatingHours: String
)

val cinema: List<Cinema> = listOf(
    Cinema("CIPUTRA WORLD XXI", "Jam operasi F&B 11:00 - 20:40"),
    Cinema("DELTA XXI", "Jam operasi F&B 11:30 - 21:30"),
    Cinema("GALAXY XXI", "Jam operasi F&B 11:30 - 21:00"),
    Cinema("GRAND CITY XXI", "Jam operasi F&B 11:30 - 21:15"),
    Cinema("LENMARC XXI", "Jam operasi F&B 11:30 - 21:50"),
    Cinema("PAKUWON CITY XXI", "Jam operasi F&B 11:00 - 22:00"),
    Cinema("PAKUWON MALL XXI", "Jam operasi F&B 11:30 - 21:30"),
    Cinema("PTC XXI", "Jam operasi F&B 09:30 - 21:30"),
    Cinema("ROYAL XXI", "Jam operasi F&B 11:30 - 21:30"),
    Cinema("TRANS ICON MALL XXI", "Jam operasi F&B 11:30 - 21:00"),
    Cinema("TRANSMART NGAGEL XXI", "Jam operasi F&B 10:00 - 20:45")
)

