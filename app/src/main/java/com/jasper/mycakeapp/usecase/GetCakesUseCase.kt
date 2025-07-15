package com.jasper.mycakeapp.usecase

import com.jasper.mycakeapp.data.Cake
import com.jasper.mycakeapp.data.repository.CakeRepository
import com.jasper.mycakeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCakesUseCase @Inject constructor(private val repository: CakeRepository) {

    suspend operator fun invoke(): Flow<Resource<List<Cake>>> {
        return repository.getCakeList()
    }
}