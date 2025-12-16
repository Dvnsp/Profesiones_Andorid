package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.singleton.CarritoManager

class ReservationActivity : AppCompatActivity() {

    private lateinit var textResumenCarrito: TextView
    private lateinit var textListadoProfesiones: TextView
    private lateinit var datePickerReserva: DatePicker
    private lateinit var timePickerReserva: TimePicker
    private lateinit var buttonConfirmarReserva: Button
    private lateinit var buttonCancelarReserva: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)

        val toolbar: Toolbar = findViewById(R.id.toolbarReservation)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.reservation_title)

        if (CarritoManager.isEmpty()) {
            finish()
            return
        }

        textResumenCarrito = findViewById(R.id.textResumenCarrito)
        textListadoProfesiones = findViewById(R.id.textListadoProfesiones)
        datePickerReserva = findViewById(R.id.datePickerReserva)
        timePickerReserva = findViewById(R.id.timePickerReserva)
        buttonConfirmarReserva = findViewById(R.id.buttonConfirmarReserva)
        buttonCancelarReserva = findViewById(R.id.buttonCancelarReserva)

        val items = CarritoManager.getAll()
        textResumenCarrito.text =
            getString(R.string.reservation_cart_summary, items.size)

        val builder = StringBuilder()
        items.forEachIndexed { index, profesion ->
            builder.append(
                getString(
                    R.string.reservation_item_format,
                    index + 1,
                    profesion.nombre,
                    profesion.profesion
                )
            ).append("\n")
        }
        textListadoProfesiones.text = builder.toString()

        timePickerReserva.setIs24HourView(true)

        buttonConfirmarReserva.setOnClickListener {
            confirmarReserva()
        }

        buttonCancelarReserva.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun confirmarReserva() {
        val year = datePickerReserva.year
        val month = datePickerReserva.month + 1
        val day = datePickerReserva.dayOfMonth

        val hour = timePickerReserva.hour
        val minute = timePickerReserva.minute

        val fechaTexto = String.format("%02d/%02d/%04d", day, month, year)
        val horaTexto = String.format("%02d:%02d", hour, minute)

        val mensaje = getString(
            R.string.reservation_confirm_message,
            fechaTexto,
            horaTexto
        )

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.reservation_confirm_title))
            .setMessage(mensaje)
            .setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
                CarritoManager.clear()
                irAConfirmacion(fechaTexto, horaTexto)
            }
            .setNegativeButton(getString(R.string.dialog_no), null)
            .show()
    }

    private fun irAConfirmacion(fecha: String, hora: String) {
        val intent = Intent(this, ReservationConfirmationActivity::class.java)
        intent.putExtra("fecha", fecha)
        intent.putExtra("hora", hora)
        startActivity(intent)
        finish()
    }
}
