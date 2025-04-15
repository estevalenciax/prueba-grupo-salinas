package com.ev.pruebagruposalidas.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ev.pruebagruposalidas.list.data.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    suspend fun getPokemonList(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemonList(list: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon WHERE name LIKE :name")
    suspend fun getPokemonByName(name: String): PokemonEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon WHERE name LIKE '%' || :name || '%'")
    suspend fun searchPokemonByName(name: String): List<PokemonEntity>

}