package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Profesion
import uvic.cat.comuvicdam_tf_202526dvargas.singleton.CarritoManager
import uvic.cat.comuvicdam_tf_202526dvargas.singleton.DataManager

class ProfesionDetailActivity : AppCompatActivity() {

    private lateinit var imageProfesionDetail: ImageView
    private lateinit var textNombreProfesionDetail: TextView
    private lateinit var textZonasProfesionDetail: TextView
    private lateinit var textPrecioProfesionDetail: TextView
    private lateinit var imageQrProfesion: ImageView
    private lateinit var buttonAddToCart: Button
    private lateinit var buttonCerrarDetalle: Button

    private lateinit var buttonEditarProfesion: Button
    private lateinit var buttonEliminarProfesion: Button

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

        buttonEditarProfesion = findViewById(R.id.buttonEditarProfesion)
        buttonEliminarProfesion = findViewById(R.id.buttonEliminarProfesion)

        profesion = intent.getSerializableExtra("profesion") as? Profesion
        if (profesion == null) {
            finish()
            return
        }

        val p = profesion!!
        mostrarDatosProfesion(p)
        generarYMostrarQr(p)

        val esUsuario = esProfesionDeUsuario(p)
        if (esUsuario) {
            buttonEditarProfesion.visibility = View.VISIBLE
            buttonEliminarProfesion.visibility = View.VISIBLE
        } else {
            buttonEditarProfesion.visibility = View.GONE
            buttonEliminarProfesion.visibility = View.GONE
        }

        buttonAddToCart.setOnClickListener {
            CarritoManager.add(p)
            AlertDialog.Builder(this)
                .setTitle("Carrito")
                .setMessage("Profesión añadida al carrito:\n\n${p.nombre} (${p.profesion})")
                .setPositiveButton("Aceptar") { _, _ ->
                    finish()
                }
                .show()
        }

        buttonCerrarDetalle.setOnClickListener {
            finish()
        }

        buttonEditarProfesion.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Editar profesión")
                .setMessage("Modo edición se implementará más adelante reutilizando el formulario de alta.")
                .setPositiveButton("Aceptar", null)
                .show()
        }

        buttonEliminarProfesion.setOnClickListener {
            confirmarEliminarProfesion(p)
        }
    }

    private fun mostrarDatosProfesion(p: Profesion) {
        val nombreMostrar = "${p.nombre} (${p.profesion})"
        textNombreProfesionDetail.text = nombreMostrar

        val zonas = p.zonasTrabajo ?: "Zonas no especificadas"
        textZonasProfesionDetail.text = "Zonas de trabajo: $zonas"

        val precio = p.precio ?: "Precio no disponible"
        textPrecioProfesionDetail.text = precio

        imageProfesionDetail.setImageResource(R.drawable.logo_bricco)
        // El QR se genera en generarYMostrarQr(p)
    }

    private fun esProfesionDeUsuario(p: Profesion): Boolean {
        fun campoVacio(valor: String?): Boolean =
            valor == null || valor.trim().isEmpty()

        return campoVacio(p.telefono) &&
                campoVacio(p.categoria) &&
                campoVacio(p.descripcion) &&
                campoVacio(p.contacto) &&
                campoVacio(p.redes)
    }

    private fun confirmarEliminarProfesion(p: Profesion) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar profesión")
            .setMessage("¿Seguro que quieres eliminar '${p.nombre} (${p.profesion})' del catálogo?")
            .setPositiveButton("Eliminar") { _, _ ->
                DataManager.deleteProfesion(p)
                CarritoManager.remove(p)
                finish()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    // ========= QR =========

    // 1) Texto completo que llevará el QR (con email, redes, etc.)
    private fun generarTextoQr(p: Profesion): String {
        val precio = p.precio ?: "No indicado"
        val zonas = p.zonasTrabajo ?: "No especificadas"
        val contacto = p.contacto ?: "No indicado"
        val redes = p.redes ?: "No indicadas"

        return """
            BRICCO – TARJETA PROFESIONAL

            Profesión: ${p.profesion}
            Nombre comercial: ${p.nombre}
            Precio orientativo: $precio
            Zonas de trabajo: $zonas

            Contacto: $contacto
            Redes: $redes
        """.trimIndent()
    }

    // 2) Generar el bitmap del QR y colocarlo en imageQrProfesion
    private fun generarYMostrarQr(p: Profesion) {
        val contenido = generarTextoQr(p)
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(contenido, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

            for (x in 0 until width) {
                for (y in 0 until height) { bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }

            imageQrProfesion.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.e("ProfesionDetail", "Error generando QR", e)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
