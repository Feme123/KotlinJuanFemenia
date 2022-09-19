package juan.example.kotlinchallenge.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import juan.example.kotlinchallenge.DAO.DBOpenHelper
import juan.example.kotlinchallenge.Model.Entidades.CompañiaDeProduccion
import juan.example.kotlinchallenge.Model.Entidades.Genero
import juan.example.kotlinchallenge.Model.Entidades.PaisDeProduccion
import juan.example.kotlinchallenge.Model.Entidades.Pelicula

class PeliculaViewModel : ViewModel() {

    fun agregarPelicula(id: Int, titulo: String, poster: String, fechaEstreno: String, idiomaOriginal: String, duracion: Int, votacion: Float, estado: String, sinopsis: String, generos: String, compañiasProduccion: String, paisesProduccion: String, context: Context): Boolean
    {
        val db = DBOpenHelper(context,null)
        val pelicula = Pelicula(id,titulo,poster,fechaEstreno,idiomaOriginal,duracion,votacion,estado,sinopsis,generos,compañiasProduccion,paisesProduccion)
        return db.agregarPelicula(pelicula)
    }

    fun obtenerPelicula(id: Int, context: Context): Pelicula?
    {
        val db = DBOpenHelper(context,null)
        return db.obtenerPelicula(id)
    }

    fun cargarGeneros(generos: ArrayList<Genero>) : String
    {
        var genero = ""
        if (generos.size == 1)
        {
            genero += generos[0].name
        }
        else
        {
            for (i in 0 until generos.size)
            {
                genero += if (i > 0 && i < generos.size-1) {
                    generos[i].name + " - "
                } else {
                    if (i == 0) {
                        generos[i].name + " - "
                    } else {
                        generos[i].name
                    }
                }
            }
        }
        return genero
    }

    fun cargarCompañiasProduccion(compañias: ArrayList<CompañiaDeProduccion>) : String
    {
        var compañia: String = ""
        if (compañias.size == 1)
        {
            compañia += compañias[0].name
        }
        else
        {
            for (i in 0 until compañias.size)
            {
                compañia += if (i > 0 && i < compañias.size-1) {
                    compañias[i].name + " - "
                } else {
                    if (i == 0) {
                        compañias[i].name + " - "
                    } else {
                        compañias[i].name
                    }
                }
            }
        }
        return compañia
    }

    fun cargarPaisesProduccion(paises: ArrayList<PaisDeProduccion>) : String
    {
        var pais = ""
        if (paises.size == 1)
        {
            pais += paises[0].name
        }
        else
        {
            for (i in 0 until paises.size)
            {
                pais += if (i > 0 && i < paises.size-1) {
                    paises[i].name + " - "
                } else {
                    if (i == 0) {
                        paises[i].name + " - "
                    } else {
                        paises[i].name
                    }
                }
            }
        }
        return pais
    }
}