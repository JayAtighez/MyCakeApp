package com.jasper.mycakeapp.data.repository

import com.jasper.mycakeapp.data.Cake
import kotlinx.coroutines.flow.Flow

interface CakeRepository {

    suspend fun getCakeList() : Flow<List<Cake>>

}