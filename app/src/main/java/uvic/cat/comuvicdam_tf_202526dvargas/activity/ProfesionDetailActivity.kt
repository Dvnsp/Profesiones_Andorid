package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

        configurarToolbar()
        inicializarVistas()
        cargarProfesion()
        configurarBotones()
    }

    private fun configurarToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbarDetail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.detail_title)
    }

    private fun inicializarVistas() {
        imageProfesionDetail = findViewById(R.id.imageProfesionDetail)
        textNombreProfesionDetail = findViewById(R.id.textNombreProfesionDetail)
        textZonasProfesionDetail = findViewById(R.id.textZonasProfesionDetail)
        textPrecioProfesionDetail = findViewById(R.id.textPrecioProfesionDetail)
        imageQrProfesion = findViewById(R.id.imageQrProfesion)
        buttonAddToCart = findViewById(R.id.buttonAddToCart)
        buttonCerrarDetalle = findViewById(R.id.buttonCerrarDetalle)
        buttonEditarProfesion = findViewById(R.id.buttonEditarProfesion)
        buttonEliminarProfesion = findViewById(R.id.buttonEliminarProfesion)
    }

    private fun cargarProfesion() {
        profesion = intent.getSerializableExtra("profesion") as? Profesion
        if (profesion == null) {
            finish()
            return
        }

        val p = profesion!!
        mostrarDatosProfesion(p)
        generarYMostrarQr(p)

        val esUsuario = esProfesionDeUsuario(p)
        buttonEditarProfesion.visibility = if (esUsuario) View.VISIBLE else View.GONE
        buttonEliminarProfesion.visibility = if (esUsuario) View.VISIBLE else View.GONE
    }

    private fun configurarBotones() {
        val p = profesion ?: return

        buttonAddToCart.setOnClickListener {
            CarritoManager.add(p)
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_cart_title))
                .setMessage(
                    getString(
                        R.string.dialog_cart_added_message,
                        p.nombre,
                        p.profesion
                    )
                )
                .setPositiveButton(getString(R.string.dialog_ok)) { _, _ -> finish() }
                .show()
        }

        buttonCerrarDetalle.setOnClickListener {
            finish()
        }

        buttonEditarProfesion.setOnClickListener {
            val intent = Intent(this, AddProfesionActivity::class.java)
            intent.putExtra("profesion_edit", p)
            startActivity(intent)
        }

        buttonEliminarProfesion.setOnClickListener {
            confirmarEliminarProfesion(p)
        }
    }

    private fun mostrarDatosProfesion(p: Profesion) {
        textNombreProfesionDetail.text =
            getString(R.string.detail_name_format, p.nombre, p.profesion)

        val zonas = p.zonasTrabajo?.ifBlank {
            getString(R.string.detail_default_zones)
        } ?: getString(R.string.detail_default_zones)

        textZonasProfesionDetail.text =
            getString(R.string.detail_zones_label, zonas)

        textPrecioProfesionDetail.text =
            p.precio?.ifBlank {
                getString(R.string.detail_default_price)
            } ?: getString(R.string.detail_default_price)

        imageProfesionDetail.setImageResource(R.drawable.logo_bricco)
    }

    private fun esProfesionDeUsuario(p: Profesion): Boolean {
        fun vacio(valor: String?) = valor.isNullOrBlank()
        return vacio(p.telefono) &&
                vacio(p.categoria) &&
                vacio(p.descripcion) &&
                vacio(p.contacto) &&
                vacio(p.redes)
    }

    private fun confirmarEliminarProfesion(p: Profesion) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_delete_title))
            .setMessage(
                getString(
                    R.string.dialog_delete_message,
                    p.nombre,
                    p.profesion
                )
            )
            .setPositiveButton(getString(R.string.dialog_delete_confirm)) { _, _ ->
                DataManager.deleteProfesion(p)
                CarritoManager.remove(p)
                finish()
            }
            .setNegativeButton(getString(R.string.dialog_cancel), null)
            .show()
    }

    // ===== QR =====

    private fun generarTextoQr(p: Profesion): String {
        return """
            ${getString(R.string.qr_header)}

            ${getString(R.string.qr_label_profession)}: ${p.profesion}
            ${getString(R.string.qr_label_business_name)}: ${p.nombre}
            ${getString(R.string.qr_label_price)}: ${p.precio ?: "-"}
            ${getString(R.string.qr_label_zones)}: ${p.zonasTrabajo ?: "-"}

            ${getString(R.string.qr_label_contact)}: ${p.contacto ?: "-"}
            ${getString(R.string.qr_label_social)}: ${p.redes ?: "-"}
        """.trimIndent()
    }

    private fun generarYMostrarQr(p: Profesion) {
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(
                generarTextoQr(p),
                BarcodeFormat.QR_CODE,
                512,
                512
            )

            val bitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.RGB_565)
            for (x in 0 until 512) {
                for (y in 0 until 512) {
                    bitmap.setPixel(
                        x,
                        y,
                        if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                    )
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
