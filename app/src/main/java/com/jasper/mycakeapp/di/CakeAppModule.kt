package com.jasper.mycakeapp.di

import com.jasper.mycakeapp.data.api.CakeApi
import com.jasper.mycakeapp.data.repository.CakeRepository
import com.jasper.mycakeapp.data.repository.CakeRepositoryImpl
import com.jasper.mycakeapp.util.CAKE_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CakeAppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CAKE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCakeApiInstance(retrofit: Retrofit): CakeApi {
        return retrofit.create(CakeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCakeRepository(cakeApi: CakeApi): CakeRepository {
        return CakeRepositoryImpl(cakeApi)
    }

}