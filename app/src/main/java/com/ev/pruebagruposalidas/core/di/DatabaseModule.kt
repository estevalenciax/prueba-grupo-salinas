package com.ev.pruebagruposalidas.core.di

import android.content.Context
import androidx.room.Room
import com.ev.pruebagruposalidas.core.room.PokemonDatabase
import com.ev.pruebagruposalidas.data.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDao(database : PokemonDatabase): PokemonDao = database.getDao()

    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext appContext: Context): PokemonDatabase {
        return Room.databaseBuilder(appContext, PokemonDatabase::class.java, "pokemon_database").build()
    }
}