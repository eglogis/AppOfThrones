package com.example.appofthrones.view.utils

import android.os.Build
import com.example.appofthrones.R

object ResourcesCharacterUtil {

    private val defaultValor =
        arrayOf(R.color.starkOverlay, R.color.starkBase, R.drawable.ic_stark)

    private val colors = mapOf(
        Pair(
            "stark", arrayOf(
                R.color.starkOverlay, R.color.starkBase,
                R.drawable.ic_stark
            )
        ),
        Pair(
            "lannister", arrayOf(
                R.color.lannisterOVerlay, R.color.lannisterBase,
                R.drawable.ic_lannister
            )
        ),
        Pair(
            "baratheon", arrayOf(
                R.color.baratheonOVerlay, R.color.baratheonBase,
                R.drawable.ic_baratheon
            )
        ),
        Pair(
            "tyrell", arrayOf(
                R.color.tyrellOVerlay, R.color.tyrellBase,
                R.drawable.ic_tyrell
            )
        ),
        Pair(
            "arryn", arrayOf(
                R.color.arrynOVerlay, R.color.arrynBase,
                R.drawable.ic_arryn
            )
        ),
        Pair(
            "targaryen", arrayOf(
                R.color.targaryenOVerlay, R.color.targaryenBase,
                R.drawable.ic_targaryen
            )
        ),
        Pair(
            "martell", arrayOf(
                R.color.martellOVerlay, R.color.martellBase,
                R.drawable.ic_martell
            )
        ),
        Pair(
            "greyjoy", arrayOf(
                R.color.greyjoyOVerlay, R.color.greyjoyBase,
                R.drawable.ic_greyjoy
            )
        ),
        Pair(
            "frey", arrayOf(
                R.color.freyOVerlay, R.color.freyBase,
                R.drawable.ic_frey
            )
        ),
        Pair(
            "tully", arrayOf(
                R.color.turryOVerlay, R.color.tullyBase,
                R.drawable.ic_tully
            )
        )
    )

    fun getOverlayColor(houseId: String): Int? {
        var palette: Array<Int> = arrayOf()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            palette = colors.getOrDefault(houseId, defaultValor)
        }
        return palette[0]
    }

    fun getBaseColor(houseId: String): Int {
        var palette: Array<Int> = arrayOf()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            palette = colors.getOrDefault(houseId, defaultValor)
        }
        return palette[1]
    }

    fun getIcon(houseId: String): Int {
        var palette: Array<Int> = arrayOf()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            palette = colors.getOrDefault(houseId, defaultValor)
        }
        return palette[2]
    }
}