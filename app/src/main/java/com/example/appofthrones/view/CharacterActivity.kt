package com.example.appofthrones.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appofthrones.R
import com.example.appofthrones.entity.CharacterEntity
import com.example.appofthrones.view.characterDetails.DetailsFragment
import com.example.appofthrones.view.characterList.CharacterFragment
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity(),
    CharacterFragment.OnItemClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        if (savedInstanceState == null) {
            this.supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.container,
                    CharacterFragment()
                )
                .commit()
        }
    }

    override fun onItemClick(character: CharacterEntity) {
        if (isDetailsViewAvailable()) {
            showFragmentDetails(character.id)
        } else {
            navigateToDetail(character.id)
        }
    }

    private fun showFragmentDetails(idCharacter: String) {
        this.supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.detailsContainer,
                DetailsFragment.newInstance(
                    idCharacter
                )
            )
            .commit()
    }

    private fun isDetailsViewAvailable() = detailsContainer != null

    private fun navigateToDetail(itemId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("CHARACTER_ID", itemId)
        startActivity(intent)
    }
}