package joandersongoncalves.example.menumeu.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import joandersongoncalves.example.menumeu.data.RecipeRepository
import joandersongoncalves.example.menumeu.data.database.RecipeDatabase
import joandersongoncalves.example.menumeu.data.model.HomeRecipeSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecipeRepository
    var homeRecipeSets = MutableLiveData<List<HomeRecipeSet>>()

    init {
        val recipeDao = RecipeDatabase.getDatabase(application, viewModelScope).recipeDao()
        repository = RecipeRepository(recipeDao)
        homeRecipeSets.value = listOf()
    }

    fun updateRecipesToShow() = viewModelScope.launch(Dispatchers.IO) {
        homeRecipeSets.postValue(repository.getRecipesToShow())
    }

}