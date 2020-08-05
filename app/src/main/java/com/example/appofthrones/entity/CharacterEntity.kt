package com.example.appofthrones.entity

import java.util.*

data class CharacterEntity(
    var id: String = UUID.randomUUID().toString(),
    var name: String,
    var born: String,
    var title: String,
    var actor: String,
    var quote: String,
    var father: String,
    var mother: String,
    var spouse: String,
    var img: String,
    var house: HouseEntity
)