package com.example.appofthrones.view.characterDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.*
import com.bumptech.glide.Glide
import com.example.appofthrones.R
import com.example.appofthrones.entity.CharacterEntity
import com.example.appofthrones.entity.HouseEntity
import com.example.appofthrones.repository.CharacterRepository
import com.example.appofthrones.view.utils.HouseDialog
import com.example.appofthrones.view.utils.ResourcesCharacterUtil
import kotlinx.android.synthetic.main.data_character.*
import kotlinx.android.synthetic.main.header_character.*

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance(id: String): DetailsFragment {
            val instance =
                DetailsFragment()
            val args = Bundle()
            args.putString("CHARACTER_ID", id)
            instance.arguments = args
            return instance
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("CHARACTER_ID")
        val character = CharacterRepository.findCharacter(id)
        setDetails(character)

        button.setOnClickListener {
            character?.let {
                showDialog(character.house)
            }
        }
    }

    private fun setDetails(character: CharacterEntity?) {
        character?.let {
            with(character) {
                nameCharacter.text = name
                nameWork.text = title
                bornLabel.text = born
                actorLabel.text = actor
                quoteLabel.text = quote
                parentName.text = "$father & $mother"
                spouseLabel.text = spouse
                context?.let { it1 ->
                    Glide.with(it1)
                        .load(character.img)
                        .into(imageCharacter)
                }
            }
        }
        setDetailsResources(character)
    }

    private fun showDialog(house: HouseEntity) {
        val dialog =
            HouseDialog.newInstance(house)
        dialog.show(childFragmentManager, "houseDialog")
    }

    private fun setDetailsResources(character: CharacterEntity?) {
        character?.let {
            with(character) {
                button.backgroundTintList = context?.let { it2 ->
                    getColorStateList(it2, ResourcesCharacterUtil.getBaseColor(house.name))
                }
                viewOverlay.background =
                    ResourcesCharacterUtil.getOverlayColor(house.name)?.let { it1 ->
                        context?.let { it2 ->
                            getDrawable(it2, it1)
                        }
                    }
                button.setImageDrawable(context?.let {
                    getDrawable(it, ResourcesCharacterUtil.getIcon(house.name))
                })
            }
        }
    }
}