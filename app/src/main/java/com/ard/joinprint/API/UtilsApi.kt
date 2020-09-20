package com.ard.joinprint.API

import com.ard.joinprint.API.RetrofitClient.getClient

object UtilsApi {
    const val BASE_URL_API = "https://dev3-api.development.tastelabgroup.com/api/v1/"

    // Declare Interface BaseApiService
    @JvmStatic
    val aPIService: BaseApiService
        get() = getClient(BASE_URL_API)!!.create(BaseApiService::class.java)
}