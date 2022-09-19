package juan.example.kotlinchallenge.Api

import juan.example.kotlinchallenge.Model.API_Response.ResponsePelicula
import juan.example.kotlinchallenge.Model.API_Response.ResponsePeliculasPopulares
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMovies {
    @GET("movie/popular?api_key=0c7caaeb549be15e8cb2ea02f5892843")
    fun getPeliculasPopulares(): Call<ResponsePeliculasPopulares>

    @GET("movie/{id}?api_key=0c7caaeb549be15e8cb2ea02f5892843")
    fun getPelicula(@Path("id") id: Int): Call<ResponsePelicula>
}