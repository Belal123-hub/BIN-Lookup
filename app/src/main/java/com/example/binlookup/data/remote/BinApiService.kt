package com.example.binlookup.data.remote

import com.example.binlookup.data.model.BinResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface BinApiService {
    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinResponse
}
