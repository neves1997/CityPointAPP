package api

data class ocorrencia(
    val id: Int,
    val imagem: String,
    val descricao: String,
    val latitude: Double,
    val longitude: Double,
    val utilizador_id: Int
)
