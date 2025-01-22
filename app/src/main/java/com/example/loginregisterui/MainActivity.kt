package com.example.loginregisterui

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the repository and ViewModel
        val repository = RecipeRepository(this)
        val factory = RecipeViewModelFactory(repository)
        recipeViewModel = ViewModelProvider(this, factory)[RecipeViewModel::class.java]

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerview_recipes)
        adapter = RecipeAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up SearchView
        searchView = findViewById(R.id.recipe_search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    recipeViewModel.setSearchQuery(it)
                    recipeViewModel.filterRecipes(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    recipeViewModel.setSearchQuery(it)
                    recipeViewModel.filterRecipes(it)
                }
                return true
            }
        })

        // Observe filtered recipes
        recipeViewModel.filteredRecipes.observe(this) { recipes ->
            if (recipes != null) {
                adapter.submitList(recipes)
            }
        }
    }
}