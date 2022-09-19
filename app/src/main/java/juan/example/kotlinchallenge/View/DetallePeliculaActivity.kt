package juan.example.kotlinchallenge.View

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import juan.example.kotlinchallenge.Api.Implementation.ApiMoviesImp
import juan.example.kotlinchallenge.Model.API_Response.ResponsePelicula
import juan.example.kotlinchallenge.Model.Entidades.Pelicula
import juan.example.kotlinchallenge.Model.Entidades.PeliculaPopular
import juan.example.kotlinchallenge.R
import juan.example.kotlinchallenge.ViewModel.PeliculaViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallePeliculaActivity : AppCompatActivity() {

    private lateinit var imgPortada: ImageView
    private lateinit var txtTitulo: TextView
    private lateinit var txtFechaLanzamiento: TextView
    private lateinit var txtIdiomaOriginal: TextView
    private lateinit var txtDuracion: TextView
    private lateinit var txtVotacion: TextView
    private lateinit var txtEstado: TextView
    private lateinit var txtSinopsis: TextView
    private lateinit var txtGenero: TextView
    private lateinit var txtCompañiasProduccion: TextView
    private lateinit var txtPaisesProduccion: TextView
    private lateinit var pelicula: PeliculaPopular
    private lateinit var peliculaVM: PeliculaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)
        inicializarComponentes()

        peliculaVM = PeliculaViewModel()

        pelicula = intent.getSerializableExtra("pelicula") as PeliculaPopular

        if (peliculaVM.obtenerPelicula(pelicula.id,this) != null)
        {
            val peliculaDBLocal = peliculaVM.obtenerPelicula(pelicula.id,this)
            cargarDetallePeliculaDBLocal(peliculaDBLocal!!)
        }
        else
        {
            val api = ApiMoviesImp()
            api.getPelicula(pelicula.id).enqueue(object : Callback<ResponsePelicula>{
                override fun onResponse(call: Call<ResponsePelicula>, response: Response<ResponsePelicula>) {
                    if (response.body() != null)
                    {
                        cargarDetallePeliculaAPI(response.body()!!)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"No hay información de la pelicula seleccionada",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePelicula>, t: Throwable) {
                    Toast.makeText(applicationContext,"Problemas de conexión o error en la consulta a la API", Toast.LENGTH_LONG).show()
                    Log.e("apirest",t.message.toString())
                }

            })
        }
    }

    private fun inicializarComponentes()
    {
        imgPortada = findViewById(R.id.imgPortada)
        txtTitulo = findViewById(R.id.txtTitulo)
        txtFechaLanzamiento = findViewById(R.id.txtFechaLanzamiento)
        txtIdiomaOriginal = findViewById(R.id.txtIdiomaOriginal)
        txtDuracion = findViewById(R.id.txtDuracion)
        txtVotacion = findViewById(R.id.txtVotacion)
        txtEstado = findViewById(R.id.txtEstado)
        txtSinopsis = findViewById(R.id.txtSinopsis)
        txtGenero = findViewById(R.id.txtGenero)
        txtCompañiasProduccion = findViewById(R.id.txtCompañiasProduccion)
        txtPaisesProduccion = findViewById(R.id.txtPaisesProduccion)
    }

    private fun cargarDetallePeliculaAPI(pelicula: ResponsePelicula)
    {
        val poster_path: String = "https://image.tmdb.org/t/p/original"+pelicula.poster_path
        Picasso.get()
            .load(poster_path)
            .into(imgPortada)

        txtTitulo.text = pelicula.original_title
        txtFechaLanzamiento.text = pelicula.release_date
        txtIdiomaOriginal.text = pelicula.original_language
        txtDuracion.text = pelicula.runtime.toString()
        txtVotacion.text = pelicula.vote_average.toString()
        txtEstado.text = pelicula.status
        txtSinopsis.text = pelicula.overview
        txtGenero.text = peliculaVM.cargarGeneros(pelicula.genres)
        txtCompañiasProduccion.text = peliculaVM.cargarCompañiasProduccion(pelicula.production_companies)
        txtPaisesProduccion.text = peliculaVM.cargarPaisesProduccion(pelicula.production_countries)

        peliculaVM.agregarPelicula(pelicula.id,
            pelicula.original_title,
            pelicula.poster_path,
            pelicula.release_date,
            pelicula.original_language,
            pelicula.runtime,
            pelicula.vote_average,
            pelicula.status,
            pelicula.overview,
            peliculaVM.cargarGeneros(pelicula.genres),
            peliculaVM.cargarCompañiasProduccion(pelicula.production_companies),
            peliculaVM.cargarPaisesProduccion(pelicula.production_countries),this)
    }

    private fun cargarDetallePeliculaDBLocal(pelicula: Pelicula)
    {
        val poster_path: String = "https://image.tmdb.org/t/p/original"+pelicula.poster
        Picasso.get()
            .load(poster_path)
            .into(imgPortada)

        txtTitulo.text = pelicula.titulo
        txtFechaLanzamiento.text = pelicula.fechaEstreno
        txtIdiomaOriginal.text = pelicula.idiomaOriginal
        txtDuracion.text = pelicula.duracion.toString()
        txtVotacion.text = pelicula.votacionPromedio.toString()
        txtEstado.text = pelicula.estado
        txtSinopsis.text = pelicula.sinopsis
        txtGenero.text = pelicula.generos
        txtCompañiasProduccion.text = pelicula.compañiasProduccion
        txtPaisesProduccion.text = pelicula.paisesProduccion
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        finish()
    }

    // Inflamos la vista del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detalle_pelicula,menu)
        return true
    }

    // Evento al presionar el botón salir del toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.opAtras)
            finish()
        return true
    }
}