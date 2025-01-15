package com.example.loginregisterui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val recipeModels = ArrayList<RecipeModel>()

    val recipeImages = arrayOf(R.drawable.karaage, R.drawable.udon, R.drawable.ramen,
        R.drawable.takoyaki, R.drawable.tempura, R.drawable.yakitori)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview_recipes)
        recyclerView.adapter = RecipeAdapter(setupRecipeModels())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupRecipeModels(): List<RecipeModel> {
        val recipeNames: Array<String> =
            resources.getStringArray(R.array.recipes_list)
        val recipeDescriptions: Array<String> =
            resources.getStringArray(R.array.recipes_descriptions_list)

        for (i in recipeNames.indices) {
            recipeModels.add(RecipeModel(recipeNames[i], recipeDescriptions[i], recipeImages[i]))
        }
        return recipeModels
    }
}