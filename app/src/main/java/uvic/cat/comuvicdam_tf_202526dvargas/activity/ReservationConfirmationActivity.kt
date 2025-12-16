package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import uvic.cat.comuvicdam_tf_202526dvargas.R

class ReservationConfirmationActivity : AppCompatActivity() {

    private lateinit var textDetalleConfirmacion: TextView
    private lateinit var buttonVolverCatalogo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_confirmation)

        textDetalleConfirmacion = findViewById(R.id.textDetalleConfirmacion)
        buttonVolverCatalogo = findViewById(R.id.buttonVolverCatalogo)

        val fecha = intent.getStringExtra("fecha") ?: getString(R.string.not_available)
        val hora = intent.getStringExtra("hora") ?: getString(R.string.not_available)

        textDetalleConfirmacion.text =
            getString(R.string.reservation_confirmation_detail, fecha, hora)

        buttonVolverCatalogo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
