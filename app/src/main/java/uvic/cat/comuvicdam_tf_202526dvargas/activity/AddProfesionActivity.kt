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

    private var profesionEdit: Profesion? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_profesion)

        configurarToolbar()
        inicializarVistas()
        cargarModoEdicion()
        configurarBotones()
    }

    private fun configurarToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbarAddProfesion)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun inicializarVistas() {
        editNombre = findViewById(R.id.editNombre)
        editProfesion = findViewById(R.id.editProfesion)
        editZonas = findViewById(R.id.editZonas)
        editPrecio = findViewById(R.id.editPrecio)
        buttonGuardar = findViewById(R.id.buttonGuardarProfesion)
        buttonCancelar = findViewById(R.id.buttonCancelarProfesion)
    }

    private fun cargarModoEdicion() {
        profesionEdit = intent.getSerializableExtra("profesion_edit") as? Profesion

        if (profesionEdit != null) {
            supportActionBar?.title = getString(R.string.detail_button_edit)

            editNombre.setText(profesionEdit!!.nombre)
            editProfesion.setText(profesionEdit!!.profesion)
            editZonas.setText(profesionEdit!!.zonasTrabajo ?: "")
            editPrecio.setText(profesionEdit!!.precio ?: "")
        } else {
            supportActionBar?.title = getString(R.string.add_prof_title)
        }
    }

    private fun configurarBotones() {
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

        // Validación básica
        if (nombre.isEmpty() || profesionTexto.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.add_prof_error_title))
                .setMessage(getString(R.string.add_prof_error_required))
                .setPositiveButton(getString(R.string.dialog_ok), null)
                .show()
            return
        }

        // Comprobar duplicados (ignorando la profesión que se está editando)
        val yaExiste = DataManager.getProfesiones().any {
            it.nombre == nombre &&
                    it.profesion == profesionTexto &&
                    it != profesionEdit
        }

        if (yaExiste) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.add_prof_error_duplicate_title))
                .setMessage(getString(R.string.add_prof_error_duplicate_message))
                .setPositiveButton(getString(R.string.dialog_ok), null)
                .show()
            return
        }

        if (profesionEdit == null) {
            // ===== CREAR =====
            val nueva = Profesion(
                null,
                nombre,
                profesionTexto,
                "",
                "",
                precio,
                zonas,
                "",
                "",
                ""
            )
            DataManager.addProfesion(nueva)

        } else {
            // ===== EDITAR =====
            profesionEdit!!.nombre = nombre
            profesionEdit!!.profesion = profesionTexto
            profesionEdit!!.zonasTrabajo = zonas
            profesionEdit!!.precio = precio

            DataManager.updateProfesion(profesionEdit!!)
        }

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.add_prof_success_title))
            .setMessage(getString(R.string.add_prof_success_message))
            .setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                finish()
            }
            .show()
    }
}
