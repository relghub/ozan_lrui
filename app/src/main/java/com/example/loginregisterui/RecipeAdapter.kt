package com.example.loginregisterui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipes: List<RecipeModel>
): RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.recipe_name)
        val desc: TextView = view.findViewById(R.id.recipe_description)
        val image: ImageView = view.findViewById(R.id.meal_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_recyclerview_row, parent, false)
        return ItemViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val recipe: RecipeModel = recipes[position]

        holder.name.text = recipe.mealName
        holder.desc.text = recipe.mealDescription
        holder.image.setImageResource(recipe.mealImage)
    }
}