package com.example.loginregisterui

import android.content.Context

class RecipeRepository(private val context: Context) {

    fun getAllRecipes(): List<RecipeModel> {
        val recipeNames = context.resources.getStringArray(R.array.recipes_list)
        val recipeDescriptions = context.resources.getStringArray(R.array.recipes_descriptions_list)

        val recipeImages = intArrayOf(
            R.drawable.karaage,
            R.drawable.udon,
            R.drawable.ramen,
            R.drawable.takoyaki,
            R.drawable.tempura,
            R.drawable.yakitori
        )

        return recipeNames.mapIndexed { index, name ->
            RecipeModel(
                id = index,
                mealName = name,
                mealDescription = recipeDescriptions[index],
                mealImage = recipeImages[index]
            )
        }
    }
}