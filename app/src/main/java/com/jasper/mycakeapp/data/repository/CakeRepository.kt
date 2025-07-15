package com.jasper.mycakeapp.data.repository

import com.jasper.mycakeapp.data.Cake
import com.jasper.mycakeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CakeRepository {

    suspend fun getCakeList() : Flow<Resource<List<Cake>>>

}