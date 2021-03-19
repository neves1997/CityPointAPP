package db

import androidx.lifecycle.LiveData
import dao.Notadao
import entities.Notaent

class NotasRepository(private val notadao: Notadao) {

    val allNotas: LiveData<List<Notaent>> = notadao.getAlphabetizedNotas()

    suspend fun insert(notaent: Notaent) {
        notadao.insert(notaent)
    }

}