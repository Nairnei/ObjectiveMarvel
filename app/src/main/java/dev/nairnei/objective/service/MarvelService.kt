package dev.nairnei.objective.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.Timestamp


class MarvelService {
    private val _api: MarvelApi

    val service: MarvelApi get() = _api


    companion object {
        var baseUrl: String = "https://gateway.marvel.com:443"
        val ts = java.sql.Timestamp(System.currentTimeMillis()).time.toString()
        val privateKey = "16014b04855cffd84bcde0917a6017aaf951f751"
        val publicKey = "734bce0e532df256214e32040740fdbe"

        val limite = 4
        private var INSTANCE: MarvelService? = null

        /**
         * Method that returns the instance
         * @return
         */
        fun getInstance(): MarvelService? {
            if (INSTANCE == null) {
                INSTANCE = MarvelService()
            }
            return INSTANCE
        }

        fun hash(): String {
            val input = "$ts$privateKey$publicKey"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        _api = retrofit.create(MarvelApi::class.java)
    }



}