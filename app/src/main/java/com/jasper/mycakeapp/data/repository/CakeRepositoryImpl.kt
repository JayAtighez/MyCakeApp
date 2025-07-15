package com.jasper.mycakeapp.data.repository

import com.jasper.mycakeapp.data.Cake
import com.jasper.mycakeapp.data.api.CakeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CakeRepositoryImpl @Inject constructor(private val api: CakeApi) : CakeRepository {
    override suspend fun getCakeList(): Flow<List<Cake>> = flow {

        val apiResponse = api.getAllCakes()
        if (apiResponse.isSuccessful && apiResponse.body() != null) {
            val successResponse = apiResponse.body()?.cakeList
            emit(successResponse!!)
        }
    }
}