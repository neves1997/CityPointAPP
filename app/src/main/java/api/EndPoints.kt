package api


import retrofit2.http.*
import retrofit2.Call

interface EndPoints {
    @GET("api/listarUti/")
    fun getUsers(): Call<List<Utilizador>>

    @GET("api/uti/{id}")
    fun getUserById(@Path("id") id: Int): Call<Utilizador>

    @FormUrlEncoded
    @POST("api/login")
    fun postLgn(@Field("name") first: String, @Field("pass") second: String): Call<List<OutputLogin>>

    @GET("api/listarRep")
    fun getProblemas(): Call<List<ocorrencia>>
}




