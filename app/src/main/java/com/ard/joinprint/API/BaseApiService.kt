package com.ard.joinprint.API

import com.ard.joinprint.Model.Response.ListRawMaterialResponse
import com.ard.joinprint.Model.Response.ListStoreResponse
import com.ard.joinprint.Model.Response.MaterialDetailResponse
import com.ard.joinprint.Model.Response.TokenResponse
import com.ard.joinprint.Model.Store
import retrofit2.Call
import retrofit2.http.*

interface BaseApiService {
    //USER
    @FormUrlEncoded
    @POST("oauth/token")
    fun tokenRequest(
        @Field("grant_type") grantType: String?,
        @Field("client_id") clientId: String?,
        @Field("client_secret") clientSecret: String?,
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<TokenResponse?>?

    @GET("stores")
    fun listStoreRequest(@Header("Authorization") authToken: String?): Call<ListStoreResponse?>?

    @GET("stores/{storeId}")
    fun showStoreRequest(
        @Header("Authorization") authToken: String?,
        @Path("storeId") storeId: String?
    ): Call<Store?>?

    @GET("raw-materials")
    fun listRawMaterial(
        @Header("Authorization") authToken: String?,
        @Query("storeId") storeId: String?
    ): Call<ListRawMaterialResponse?>?

    @GET("raw-materials/{rawMaterialId}")
    fun showRawMaterial(
        @Header("Authorization") authToken: String?,
        @Path("rawMaterialId") rawMaterialId: String?
    ): Call<MaterialDetailResponse?>
}