package com.jasper.mycakeapp.data.api

import com.jasper.mycakeapp.data.CakeList
import retrofit2.Response
import retrofit2.http.GET

interface CakeApi {

    @GET("missingcharacter/0b9d63e289cdb6149e3931911ac2f7ec/raw/cake.json")
    suspend fun getAllCakes() : Response<CakeList>

}