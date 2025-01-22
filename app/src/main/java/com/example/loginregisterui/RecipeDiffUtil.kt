package com.example.loginregisterui

import androidx.recyclerview.widget.DiffUtil

object RecipeDiffUtil : DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem.mealName == newItem.mealName &&
                oldItem.mealDescription == newItem.mealDescription &&
                oldItem.mealImage == newItem.mealImage
    }
}