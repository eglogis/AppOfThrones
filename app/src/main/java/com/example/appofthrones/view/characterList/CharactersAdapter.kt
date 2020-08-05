package com.example.appofthrones.view.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appofthrones.R
import com.example.appofthrones.entity.CharacterEntity
import com.example.appofthrones.view.utils.ResourcesCharacterUtil
import kotlinx.android.synthetic.main.character_item.view.*

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private val items = mutableListOf<CharacterEntity>()
    private val itemClickListener: ((CharacterEntity, Int) -> Unit)?

    constructor() : super() {
        itemClickListener = null
    }

    constructor(itemClickListener: ((CharacterEntity, Int) -> Unit)) : super() {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.character = item
    }

    fun setCharacter(characterList: MutableList<CharacterEntity>) {
        items.clear()
        items.addAll(characterList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var character: CharacterEntity? = null
            set(value) {
                value?.let {
                    setDataCharacter(itemView, value)
                    field = value
                }
            }

        init {
            itemView.setOnClickListener {
                character?.let {
                    itemClickListener?.invoke(character as CharacterEntity, adapterPosition)
                }
            }
        }
    }

    private fun setDataCharacter(view: View, character: CharacterEntity) {
        view.name_character.text = character.name
        view.title_character.text = character.title
        val overlayColor = ResourcesCharacterUtil.getOverlayColor(character.house.name)
        view.viewOverlay.background =
            overlayColor?.let { it1 ->
                ContextCompat.getDrawable(view.context, it1)
            }

        Glide.with(view.context)
            .load(character.img)
            .into(view.imageCharacter)
    }
}