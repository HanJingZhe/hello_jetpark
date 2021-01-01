package com.qtgm.hello_coroutine

import com.qtgm.hello_coroutine.entity.Repo
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApi {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>


    @GET("users/{user}/repos")
    fun rxListRepos(@Path("user") user: String): Single<List<Repo>>

    @GET("users/{user}/repos")
    suspend fun coroutineListRepos(@Path("user") user: String): List<Repo>

}