package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.data.UsuarioRepository
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Usuario

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextNewUsername: EditText
    private lateinit var editTextNewPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var textRegisterError: TextView
    private lateinit var buttonCrearCuenta: Button
    private lateinit var buttonCancelar: Button

    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        // Referencias UI
        editTextNewUsername = findViewById(R.id.editTextNewUsername)
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        editTextEmail = findViewById(R.id.editTextEmail)
        textRegisterError = findViewById(R.id.textRegisterError)
        buttonCrearCuenta = findViewById(R.id.buttonCrearCuenta)
        buttonCancelar = findViewById(R.id.buttonCancelar)

        usuarioRepository = UsuarioRepository(this)

        buttonCrearCuenta.setOnClickListener {
            textRegisterError.text = ""
            registrarUsuario()
        }

        buttonCancelar.setOnClickListener {
            // Simplemente volvemos al Login
            finish()
        }
    }

    private fun registrarUsuario() {
        val username = editTextNewUsername.text.toString().trim()
        val password = editTextNewPassword.text.toString().trim()
        val repeatPassword = editTextRepeatPassword.text.toString().trim()
        val nombre = editTextNombre.text.toString().trim()
        val telefono = editTextTelefono.text.toString().trim()
        val email = editTextEmail.text.toString().trim()

        // Validaciones básicas
        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            textRegisterError.text = "Usuario y contraseñas son obligatorios."
            return
        }

        if (password != repeatPassword) {
            textRegisterError.text = "Las contraseñas no coinciden."
            return
        }

        // Comprobar si ya existe el usuario
        val existente = usuarioRepository.findByUsername(username)
        if (existente != null) {
            textRegisterError.text = "Ese usuario ya existe. Prueba con otro."
            return
        }

        // Crear objeto Usuario (entidad Java)
        val nuevoUsuario = Usuario(
            null,
            username,
            password,
            nombre.ifEmpty { null },
            telefono.ifEmpty { null },
            email.ifEmpty { null }
        )

        val newId = usuarioRepository.insert(nuevoUsuario)

        if (newId > 0) {
            // Registro OK → volvemos al login
            finish()
        } else {
            textRegisterError.text = "Error al crear el usuario. Inténtalo de nuevo."
        }
    }
}
