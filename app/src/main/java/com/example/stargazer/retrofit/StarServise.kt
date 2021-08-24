package com.example.stargazer.retrofit

import com.example.stargazer.database.entity.RoomStar
import com.example.stargazer.retrofit.Util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface StarInterface {
    @Headers("Accept: application/vnd.github.v3.star+json")

    @GET("repos/{username}/{repoName}/stargazers")
    suspend fun getListStar(
        @Path("username") username: String,
        @Path("repoName") repoName: String
    ): List<RoomStar>

    @GET("repos/{username}/{repoName}/stargazers")
    fun getStarDb(
        @Path("username") username: String,
        @Path("repoName") repoName: String
    ): Call<RoomStar>
}

object StarServise {
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttp)
    private val retrofit = builder.build()

    val starInstance: StarInterface = retrofit.create(StarInterface::class.java)
}