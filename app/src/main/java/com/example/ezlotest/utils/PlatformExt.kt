package com.example.ezlotest.utils

import com.example.ezlotest.R

fun String.getImageFromPlatform(): Int {
    return when (this) {
        "Sercomm G450" -> R.drawable.vera_plus_big
        "Sercomm G550" -> R.drawable.vera_secure_big
        "MiCasaVerde VeraLite" -> R.drawable.vera_edge_big
        "Sercomm NA900" -> R.drawable.vera_edge_big
        "Sercomm NA301" -> R.drawable.vera_edge_big
        "Sercomm NA930" -> R.drawable.vera_edge_big
        else -> R.drawable.vera_edge_big
    }
}

fun String.getModelFromPlatform(): String {
    return when (this) {
        "Sercomm G450" -> "Vera Plus"
        "Sercomm G550" -> "Vera Secure"
        "MiCasaVerde VeraLite" -> "Vera Edge"
        "Sercomm NA900" -> "Vera Edge"
        "Sercomm NA301" -> "Vera Edge"
        "Sercomm NA930" -> "Vera Edge"
        else -> "Vera Edge"
    }
}