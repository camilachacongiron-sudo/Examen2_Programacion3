package com.upb.examen2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RazaDatoDao {

    @Query("SELECT * FROM raza_dato ORDER BY raza ASC")
    fun observarTodos(): Flow<List<RazaDatoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodos(items: List<RazaDatoEntity>)

    @Query("DELETE FROM raza_dato WHERE id = :id")
    suspend fun eliminarPorId(id: Int)

    @Query("DELETE FROM raza_dato")
    suspend fun eliminarTodos()
}