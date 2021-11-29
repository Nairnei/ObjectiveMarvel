package dev.nairnei.objective.service

import dev.nairnei.objective.model.CharactersModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    fun characters(
        @Query("offset") page: Int? = 0,
        @Query("name") name: String? = null,
        @Query("ts") ts: String = MarvelService.ts,
        @Query("apikey") apiKey: String = MarvelService.publicKey,
        @Query("hash") hash: String = MarvelService.hash(),
        @Query("limit") limit: Int = MarvelService.limite
    ): Call<CharactersModel>

}