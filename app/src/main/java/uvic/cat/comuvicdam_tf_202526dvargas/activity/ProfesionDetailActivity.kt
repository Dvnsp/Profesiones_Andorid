package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Profesion
import uvic.cat.comuvicdam_tf_202526dvargas.singleton.CarritoManager

class ProfesionDetailActivity : AppCompatActivity() {

    private lateinit var imageProfesionDetail: ImageView
    private lateinit var textNombreProfesionDetail: TextView
    private lateinit var textZonasProfesionDetail: TextView
    private lateinit var textPrecioProfesionDetail: TextView
    private lateinit var imageQrProfesion: ImageView
    private lateinit var buttonAddToCart: Button
    private lateinit var buttonCerrarDetalle: Button

    private var profesion: Profesion? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profesion_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbarDetail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalle"

        imageProfesionDetail = findViewById(R.id.imageProfesionDetail)
        textNombreProfesionDetail = findViewById(R.id.textNombreProfesionDetail)
        textZonasProfesionDetail = findViewById(R.id.textZonasProfesionDetail)
        textPrecioProfesionDetail = findViewById(R.id.textPrecioProfesionDetail)
        imageQrProfesion = findViewById(R.id.imageQrProfesion)
        buttonAddToCart = findViewById(R.id.buttonAddToCart)
        buttonCerrarDetalle = findViewById(R.id.buttonCerrarDetalle)

        // Recuperar la profesión enviada desde MainActivity
        profesion = intent.getSerializableExtra("profesion") as? Profesion

        if (profesion == null) {
            // Si por algún motivo no llega, cerramos la pantalla
            finish()
            return
        }

        mostrarDatosProfesion(profesion!!)

        buttonAddToCart.setOnClickListener {
            profesion?.let { p ->
                CarritoManager.add(p)

                AlertDialog.Builder(this)
                    .setTitle("Carrito")
                    .setMessage("Profesión añadida al carrito:\n\n${p.nombre} (${p.profesion})")
                    .setPositiveButton("Aceptar") { _, _ ->
                        // Cuando el usuario pulse Aceptar volvemos a la lista de profesiones
                        finish()
                    }
                    .show()
            }
        }

        buttonCerrarDetalle.setOnClickListener {
            finish()
        }
    }

    private fun mostrarDatosProfesion(p: Profesion) {
        val nombreMostrar = "${p.nombre} (${p.profesion})"
        textNombreProfesionDetail.text = nombreMostrar

        val zonas = p.zonasTrabajo ?: "Zonas no especificadas"
        textZonasProfesionDetail.text = "Zonas de trabajo: $zonas"

        val precio = p.precio ?: "Precio no disponible"
        textPrecioProfesionDetail.text = precio

        // Imagen de la profesión: de momento usamos el mismo icono genérico
        imageProfesionDetail.setImageResource(R.drawable.logo_bricco)

        // Placeholder de QR: se sustituirá en la fase de QR real
        imageQrProfesion.setImageResource(R.drawable.logo_bricco)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
