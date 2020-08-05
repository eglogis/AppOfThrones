package com.example.appofthrones.view.characterList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appofthrones.R
import com.example.appofthrones.entity.CharacterEntity
import com.example.appofthrones.repository.CharacterRepository
import kotlinx.android.synthetic.main.fragment_character.*
import java.lang.IllegalArgumentException

class CharacterFragment : Fragment() {

    interface OnItemClick {
        fun onItemClick(character: CharacterEntity)
    }

    private lateinit var itemClick: OnItemClick

    private val list: RecyclerView by lazy {
        recycler_character!!.layoutManager = LinearLayoutManager(context)
        recycler_character!!.adapter = adapter
        recycler_character!!
    }

    private val adapter: CharactersAdapter by lazy {
        val characterAdapter =
            CharactersAdapter { character, position ->
                itemClick.onItemClick(character)
            }
        characterAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestCharacter()
        retryButton.setOnClickListener {
            requestCharacter()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemClick) {
            itemClick = context
        } else {
            throw IllegalArgumentException(
                "La actividad conectada no implementa la interface onItemClick"
            )
        }
    }

    private fun requestCharacter() {
        showLoading()
        context?.let {
            CharacterRepository.requestCharacters(it,
                { characterList ->
                    view?.let {
                        setRecycler(characterList)
                        showList()
                    }
                },
                {
                    view?.let {
                        hideList()
                    }
                })
        }
    }

    private fun showList() {
        loading.visibility = View.INVISIBLE
        recycler_character.visibility = View.VISIBLE
        errorContainer.visibility = View.INVISIBLE
    }

    private fun hideList() {
        loading.visibility = View.INVISIBLE
        errorContainer.visibility = View.VISIBLE
        recycler_character.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
        errorContainer.visibility = View.INVISIBLE
        recycler_character.visibility = View.INVISIBLE
    }

    private fun setRecycler(characters: MutableList<CharacterEntity>) {
        adapter.setCharacter(characters)
        list.adapter = adapter
    }
}