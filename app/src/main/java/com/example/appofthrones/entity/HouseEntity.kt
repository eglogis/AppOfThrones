package com.example.appofthrones.entity

import java.io.Serializable

data class HouseEntity(
    var name: String,
    var region: String,
    var words: String,
    var img: String
) : Serializable