package juan.example.kotlinchallenge.Model.API_Response

import juan.example.kotlinchallenge.Model.Entidades.CompañiaDeProduccion
import juan.example.kotlinchallenge.Model.Entidades.Genero
import juan.example.kotlinchallenge.Model.Entidades.PaisDeProduccion

data class ResponsePelicula(val genres: ArrayList<Genero>,
                            val hompage: String,
                            val id: Int,
                            val original_language: String,
                            val original_title: String,
                            val overview: String,
                            val popularity: Float,
                            val poster_path: String,
                            val production_companies: ArrayList<CompañiaDeProduccion>,
                            val production_countries: ArrayList<PaisDeProduccion>,
                            val release_date: String,
                            val runtime: Int,
                            val status: String,
                            val title: String,
                            val vote_average: Float)