package com.upb.examen2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ClimaDatoDao {

    @Query("SELECT * FROM clima_dato ORDER BY id DESC")
    fun observarTodos(): Flow<List<ClimaDatoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(item: ClimaDatoEntity)

    @Query("DELETE FROM clima_dato WHERE id = :id")
    suspend fun eliminarPorId(id: Int)

    @Query("DELETE FROM clima_dato")
    suspend fun eliminarTodos()
}