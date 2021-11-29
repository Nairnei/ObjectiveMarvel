package dev.nairnei.objective.repository

import dev.nairnei.objective.model.CharactersModel
import dev.nairnei.objective.service.MarvelService
import retrofit2.Call

class MarvelRepository {


    fun listCharacters(page: Int? = 0, name: String? = null): Call<CharactersModel> {

        return MarvelService().service.characters(page, name)
    }

}
