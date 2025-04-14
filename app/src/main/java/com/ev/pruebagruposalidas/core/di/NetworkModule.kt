package com.ev.pruebagruposalidas.core.di

import com.ev.pruebagruposalidas.core.network.retrofit.PokemonApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providePokemonApiClient(retrofit: Retrofit): PokemonApiClient {
        return retrofit.create(PokemonApiClient::class.java)

    }
}