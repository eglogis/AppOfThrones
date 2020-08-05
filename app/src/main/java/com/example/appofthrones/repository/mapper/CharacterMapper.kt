package com.example.appofthrones.repository.mapper

import com.example.appofthrones.entity.CharacterEntity
import org.json.JSONArray
import org.json.JSONObject

object CharacterMapper {

    private fun characterMapper(jsonCharacter: JSONObject): CharacterEntity {
        return CharacterEntity(
            jsonCharacter.getString("id"),
            jsonCharacter.getString("name"),
            jsonCharacter.getString("born"),
            jsonCharacter.getString("title"),
            jsonCharacter.getString("actor"),
            jsonCharacter.getString("quote"),
            jsonCharacter.getString("father"),
            jsonCharacter.getString("mother"),
            jsonCharacter.getString("spouse"),
            jsonCharacter.getString("img"),
            HouseMapper.houseMapper(
                jsonCharacter.getJSONObject("house")
            )
        )
    }

    fun charactersMapper(jsonListCharacter: JSONArray): MutableList<CharacterEntity> {
        val charactersArray = mutableListOf<CharacterEntity>()
        for (index in 0 until jsonListCharacter.length()) {
            charactersArray.add(characterMapper(jsonListCharacter.getJSONObject(index)))
        }
        return charactersArray
    }
}