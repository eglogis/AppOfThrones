package com.example.appofthrones.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.appofthrones.entity.CharacterEntity
import com.example.appofthrones.entity.HouseEntity
import com.example.appofthrones.repository.mapper.CharacterMapper
import java.security.AccessControlContext
import kotlin.random.Random

object CharacterRepository {

    private const val URL_CHARACTER = "https://5f27d26af5d27e001612e628.mockapi.io/characters"

    private var characters: MutableList<CharacterEntity> = mutableListOf()

    fun requestCharacters(
        context: Context,
        success: ((MutableList<CharacterEntity>) -> Unit),
        error: (() -> Unit)
    ) {

        if (characters.isEmpty()) {
            val request = JsonArrayRequest(Request.Method.GET, URL_CHARACTER, null,
                { response ->
                    characters = CharacterMapper.charactersMapper(response)
                    success.invoke(characters)
                },
                {
                    error.invoke()
                })

            Volley.newRequestQueue(context).add(request)
        } else {
            success.invoke(characters)
        }
    }

    fun findCharacter(index: String?): CharacterEntity? {
        return characters.find {
            it.id == index
        }
    }
}