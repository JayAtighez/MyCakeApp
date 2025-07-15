package com.jasper.mycakeapp.data.repository

import com.jasper.mycakeapp.data.Cake
import com.jasper.mycakeapp.data.api.CakeApi
import com.jasper.mycakeapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CakeRepositoryImpl @Inject constructor(private val api: CakeApi) : CakeRepository {
    override suspend fun getCakeList(): Flow<Resource<List<Cake>>> = flow {
        emit(Resource.Loading())

        val apiResponse = api.getAllCakes()
        if (apiResponse.isSuccessful && apiResponse.body() != null) {
            emit(Resource.Success(apiResponse.body()?.cakeList!!))
        } else {
            emit(Resource.Error(apiResponse.errorBody()?.string()))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "Unknown network error"))
    }.flowOn(Dispatchers.IO)
}