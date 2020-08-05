package com.example.appofthrones.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appofthrones.R
import com.example.appofthrones.view.characterDetails.DetailsFragment

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("CHARACTER_ID")

        if (savedInstanceState == null && id != null) {
            this.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,
                    DetailsFragment.newInstance(
                        id
                    )
                )
                .commit()
        }
    }
}