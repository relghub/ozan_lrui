package com.example.loginregisterui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter: ListAdapter<RecipeModel, RecipeAdapter.ItemViewHolder>(RecipeDiffUtil){

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.recipe_name)
        val desc: TextView = view.findViewById(R.id.recipe_description)
        val image: ImageView = view.findViewById(R.id.meal_image)
        private val like: ImageView = view.findViewById(R.id.like_button)
        private val share: ImageView = view.findViewById(R.id.share_button)

        init {
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Item " + name.text + " has been clicked", Toast.LENGTH_SHORT).show()
            }
            like.setOnClickListener {
                Toast.makeText(like.context, "Item " + name.text + " has been liked", Toast.LENGTH_SHORT).show()
            }
            share.setOnClickListener {
                Toast.makeText(share.context, "Item " + name.text + " has been shared", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_recyclerview_row, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.name.text = recipe.mealName
        holder.desc.text = recipe.mealDescription
        holder.image.setImageResource(recipe.mealImage)
    }
}