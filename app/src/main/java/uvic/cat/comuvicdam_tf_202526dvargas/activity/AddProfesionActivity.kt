package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Profesion
import uvic.cat.comuvicdam_tf_202526dvargas.singleton.DataManager

class AddProfesionActivity : AppCompatActivity() {

    private lateinit var editNombre: EditText
    private lateinit var editProfesion: EditText
    private lateinit var editZonas: EditText
    private lateinit var editPrecio: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var buttonCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 👇 Si tu layout se llama distinto, cambia este nombre
        setContentView(R.layout.activity_add_profesion)

        // Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbarAddProfesion)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Nueva profesión"

        // Referencias a vistas
        editNombre = findViewById(R.id.editNombre)
        editProfesion = findViewById(R.id.editProfesion)
        editZonas = findViewById(R.id.editZonas)
        editPrecio = findViewById(R.id.editPrecio)
        buttonGuardar = findViewById(R.id.buttonGuardarProfesion)
        buttonCancelar = findViewById(R.id.buttonCancelarProfesion)

        buttonGuardar.setOnClickListener {
            guardarProfesion()
        }

        buttonCancelar.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun guardarProfesion() {
        val nombre = editNombre.text.toString().trim()
        val profesionTexto = editProfesion.text.toString().trim()
        val zonas = editZonas.text.toString().trim()
        val precio = editPrecio.text.toString().trim()

        // 1) Validación básica
        if (nombre.isEmpty() || profesionTexto.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle("Datos incompletos")
                .setMessage("Nombre y profesión son obligatorios.")
                .setPositiveButton("Aceptar", null)
                .show()
            return
        }

        // 2) Comprobar duplicado (mismo nombre + profesión)
        val existentes = DataManager.getProfesiones()
        val yaExiste = existentes.any { it.nombre == nombre && it.profesion == profesionTexto }

        if (yaExiste) {
            AlertDialog.Builder(this)
                .setTitle("Profesión duplicada")
                .setMessage("Ya existe una profesión con ese nombre y tipo.")
                .setPositiveButton("Aceptar", null)
                .show()
            return
        }

        // 3) Crear la nueva Profesion usando el MISMO constructor que usa DataManager
        val nueva = Profesion(
            null,                   // id -> null (SQLite lo genera con autoincrement)
            nombre,                 // nombre comercial
            profesionTexto,         // tipo de profesión
            "",                     // teléfono (de momento vacío)
            "",                     // categoría (vacía)
            precio.ifEmpty { "" },  // precio
            zonas.ifEmpty { "" },   // zonas de trabajo
            "",                     // descripción
            "",                     // contacto
            ""                      // redes
        )

        // 4) Guardar en la BD mediante DataManager
        DataManager.addProfesion(nueva)

        // 5) Avisar y volver al catálogo
        AlertDialog.Builder(this)
            .setTitle("Profesión guardada")
            .setMessage("La nueva profesión se ha añadido al catálogo.")
            .setPositiveButton("Aceptar") { _, _ ->
                finish()
            }
            .show()
    }
}
