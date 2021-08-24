package com.example.stargazer.retrofit

import com.example.stargazer.database.entity.RoomRepo
import com.example.stargazer.retrofit.Util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposInterface {

    @GET("users/{username}/repos")
    suspend fun getListRepos(@Path("username") username: String): List<RoomRepo>
    @GET("orgs/{username}/repos")
    suspend fun getListReposOrg(@Path("username") username: String): List<RoomRepo>

    @GET("users/{username}/repos")
    fun getReposDb(@Path("username") username: String): Call<RoomRepo>
    @GET("orgs/{username}/repos")
    fun getReposOrgDb(@Path("username") username: String): Call<RoomRepo>
}

object RepoServise {
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttp)
    private val retrofit = builder.build()

    val reposInstance: ReposInterface = retrofit.create(ReposInterface::class.java)
}