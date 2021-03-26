package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import entities.Notaent

@Dao
interface Notadao {

    @Query("Select * from notas_table order by data ASC")
    fun getAlphabetizedNotas(): LiveData<List<Notaent>>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notaent: Notaent)

    @Query("DELETE FROM notas_table WHERE id == :id")
    suspend fun delete(id: Int)


    @Query("UPDATE notas_table SET tituto = :titulo , texto = :texto, data = :data WHERE id == :id")
    suspend fun update(id: Int, titulo: String, texto: String, data: String)






}