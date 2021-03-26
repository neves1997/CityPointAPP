package db

import androidx.lifecycle.LiveData
import dao.Notadao
import entities.Notaent

class NotasRepository(private val notadao: Notadao) {

    val allNotas: LiveData<List<Notaent>> = notadao.getAlphabetizedNotas()

    suspend fun insert(notaent: Notaent) {
        notadao.insert(notaent)
    }

    suspend fun delete(id: Int) {
       notadao.delete(id)
    }

    suspend fun update(id: Int, titulo: String, texto:String, data: String) {
        notadao.update(id, titulo, texto, data)
    }
}