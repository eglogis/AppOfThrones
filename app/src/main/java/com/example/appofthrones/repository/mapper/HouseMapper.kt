package com.example.appofthrones.repository.mapper

import com.example.appofthrones.entity.HouseEntity
import org.json.JSONObject

object HouseMapper {

    fun houseMapper(jsonHouse: JSONObject): HouseEntity {
        return HouseEntity(
            jsonHouse.getString("name"),
            jsonHouse.getString("region"),
            jsonHouse.getString("words"),
            jsonHouse.getString("img")
        )
    }
}