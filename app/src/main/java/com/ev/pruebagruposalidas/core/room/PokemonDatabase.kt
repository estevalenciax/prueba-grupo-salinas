package com.ev.pruebagruposalidas.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ev.pruebagruposalidas.data.PokemonDao
import com.ev.pruebagruposalidas.list.data.entity.PokemonEntity

@Database(entities = arrayOf(PokemonEntity::class), version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getDao(): PokemonDao
}