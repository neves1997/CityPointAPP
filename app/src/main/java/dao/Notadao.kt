package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dataclasse.Notasdc
import entities.Notaent

@Dao
interface Notadao {

    @Query("Select * from notas_table order by data ASC")
    fun getAlphabetizedNotas(): LiveData<List<Notaent>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notaent: Notaent)

    @Query("DELETE FROM notas_table")
    suspend fun deleteAll()
}