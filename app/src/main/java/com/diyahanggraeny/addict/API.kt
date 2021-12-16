package com.diyahanggraeny.addict

import com.diyahanggraeny.addict.Models.APIResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface API {
    @GET("entries/en/{word}")
    fun getPosts(
        @Path(value="word", encoded = false) key: String,
    ): Call<List<APIResponseItem>>

    @GET("word?number=1&swear=0")
    fun getRandom():Call<ArrayList<String>>
}