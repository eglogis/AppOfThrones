package com.example.appofthrones.view.utils

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.appofthrones.R
import com.example.appofthrones.entity.HouseEntity
import kotlinx.android.synthetic.main.dialog_house.view.*
import java.io.Serializable

class HouseDialog : DialogFragment() {

    companion object {
        fun newInstance(house: HouseEntity): HouseDialog {
            val argument = Bundle()
            argument.putSerializable("HOUSE_KEY", house as Serializable)
            val dialog = HouseDialog()
            dialog.arguments = argument
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(
            R.layout.dialog_house,
            null, false
        )

        val house = arguments?.getSerializable("HOUSE_KEY") as HouseEntity
        setDialogData(house, view)

        return AlertDialog.Builder(context)
            .setView(view)
            .setPositiveButton(R.string.text_accept) { _, _ -> dismiss() }
            .create()
    }

    private fun setDialogData(house: HouseEntity, view: View) {
        view.nameHouse.text = house.name
        view.nameRegion.text = house.region
        view.nameWords.text = house.words
        view.dialogContainer.background = context?.let {
            getDrawable(it, ResourcesCharacterUtil.getBaseColor(house.name))
        }

        context?.let {
            Glide.with(it)
                .load(house.img)
                .into(view.imgHouse)
        }
    }
}