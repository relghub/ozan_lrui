package com.example.loginregisterui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>("")
    private val _filteredRecipes = MutableLiveData<List<RecipeModel>>()

    // Get the current list of recipes
    val filteredRecipes: LiveData<List<RecipeModel>> = _filteredRecipes

    // Get the current search query
    val searchQuery: LiveData<String> = _searchQuery

    init {
        val allRecipes = repository.getAllRecipes()
        _filteredRecipes.value = allRecipes
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun filterRecipes(query: String) {
        if (query.isEmpty()) {
            val allRecipes = repository.getAllRecipes()
            _filteredRecipes.value = allRecipes
            return
        }

        if (query.length < 3) {
            _filteredRecipes.value?.let { all ->
                _filteredRecipes.value = all
            }
            return
        }

        // Filter recipes based on the query
        val filtered = _filteredRecipes.value?.filter { recipe ->
            recipe.mealName.lowercase().contains(query.lowercase()) ||
                    recipe.mealDescription.lowercase().contains(query.lowercase())
        } ?: emptyList()

        _filteredRecipes.value = filtered
    }
}