package com.example.loginregisterui

import androidx.annotation.DrawableRes

data class RecipeModel(
    val mealName: String,
    val mealDescription: String,
    @DrawableRes val mealImage: Int
)