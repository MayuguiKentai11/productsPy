package com.git.productsPy.network

import com.git.productsPy.communication.ApiResponse
import retrofit2.Call
import retrofit2.http.GET


interface ProductApiService {
    @GET("/products/")
    fun getProducts() : Call<ApiResponse>

}