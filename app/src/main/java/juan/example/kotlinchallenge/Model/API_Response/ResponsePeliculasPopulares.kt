package juan.example.kotlinchallenge.Model.API_Response

import juan.example.kotlinchallenge.Model.Entidades.PeliculaPopular

data class ResponsePeliculasPopulares(val results: ArrayList<PeliculaPopular>)