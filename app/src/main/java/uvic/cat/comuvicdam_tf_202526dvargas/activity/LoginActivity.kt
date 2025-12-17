package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.data.UsuarioRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var textInputUser: TextInputLayout
    private lateinit var textInputPass: TextInputLayout
    private lateinit var editTextUsername: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var textLoginError: TextView
    private lateinit var buttonLogin: MaterialButton
    private lateinit var textGoRegister: TextView

    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        inicializarVistas()
        configurarEventos()
    }

    private fun inicializarVistas() {
        textInputUser = findViewById(R.id.textInputUser)
        textInputPass = findViewById(R.id.textInputPass)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        textLoginError = findViewById(R.id.textLoginError)
        buttonLogin = findViewById(R.id.buttonLogin)
        textGoRegister = findViewById(R.id.textGoRegister)

        usuarioRepository = UsuarioRepository(this)
    }

    private fun configurarEventos() {
        buttonLogin.setOnClickListener {
            limpiarErrores()
            realizarLogin()
        }

        textGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun limpiarErrores() {
        textInputUser.error = null
        textInputPass.error = null
        textLoginError.visibility = View.GONE
    }

    private fun realizarLogin() {
        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (username.isEmpty()) {
            textInputUser.error = getString(R.string.login_error_user_required)
            return
        }

        if (password.isEmpty()) {
            textInputPass.error = getString(R.string.login_error_pass_required)
            return
        }

        val usuario = usuarioRepository.findByUsernameAndPassword(username, password)

        if (usuario != null) {
            Log.i("LoginActivity", "Login correcto: $usuario")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            textLoginError.text = getString(R.string.login_error_invalid_credentials)
            textLoginError.visibility = View.VISIBLE
        }
    }
}
