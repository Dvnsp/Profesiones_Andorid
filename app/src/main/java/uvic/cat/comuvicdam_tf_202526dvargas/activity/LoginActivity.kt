package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.data.UsuarioRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var textLoginError: TextView
    private lateinit var buttonLogin: Button
    private lateinit var textGoRegister: TextView

    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Referencias UI
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        textLoginError = findViewById(R.id.textLoginError)
        buttonLogin = findViewById(R.id.buttonLogin)
        textGoRegister = findViewById(R.id.textGoRegister)

        // Repo de usuarios
        usuarioRepository = UsuarioRepository(this)

        buttonLogin.setOnClickListener {
            textLoginError.text = ""  // limpiar error anterior
            realizarLogin()
        }

        textGoRegister.setOnClickListener {
            // Más adelante irá a la pantalla de registro (RegisterActivity)
            // De momento, solo dejaremos el Intent preparado y la crearemos luego.
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun realizarLogin() {
        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            textLoginError.text = "Usuario y contraseña son obligatorios."
            return
        }

        val usuario = usuarioRepository.findByUsernameAndPassword(username, password)

        if (usuario != null) {
            Log.i("LoginActivity", "Login correcto: $usuario")
            // Ir a MainActivity (catálogo) y cerrar el login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            textLoginError.text = "Usuario o contraseña incorrectos."
            Log.i("LoginActivity", "Login fallido para username=$username")
        }
    }
}
