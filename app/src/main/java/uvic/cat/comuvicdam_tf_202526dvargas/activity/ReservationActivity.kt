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
import java.util.Calendar

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
        supportActionBar?.title = "Reserva"

        // Si por alguna razón el carrito está vacío, salimos
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

        // Rellenar resumen de carrito
        val items = CarritoManager.getAll()
        textResumenCarrito.text = "Profesiones en la reserva: ${items.size}"

        val builder = StringBuilder()
        items.forEachIndexed { index, profesion ->
            builder.append("${index + 1}. ${profesion.nombre} (${profesion.profesion})\n")
        }
        textListadoProfesiones.text = builder.toString()

        // Configurar TimePicker en 24h
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
        // Obtener fecha y hora seleccionadas
        val year = datePickerReserva.year
        val month = datePickerReserva.month + 1 // DatePicker month es 0-11
        val day = datePickerReserva.dayOfMonth

        val hour = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            timePickerReserva.hour
        } else {
            timePickerReserva.currentHour
        }

        val minute = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            timePickerReserva.minute
        } else {
            timePickerReserva.currentMinute
        }

        val fechaTexto = String.format("%02d/%02d/%04d", day, month, year)
        val horaTexto = String.format("%02d:%02d", hour, minute)

        val mensajeConfirmacion = "¿Seguro que quieres confirmar la reserva para el día $fechaTexto a las $horaTexto?"

        AlertDialog.Builder(this)
            .setTitle("Confirmar reserva")
            .setMessage(mensajeConfirmacion)
            .setPositiveButton("Sí") { _, _ ->
                // Aquí podríamos guardar en BD una tabla de reservas, etc.
                // De momento, vaciamos el carrito y vamos a la pantalla de confirmación.
                CarritoManager.clear()
                irAConfirmacion(fechaTexto, horaTexto)
            }
            .setNegativeButton("No", null)
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
