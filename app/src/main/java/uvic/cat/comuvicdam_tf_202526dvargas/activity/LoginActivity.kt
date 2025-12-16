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

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        textLoginError = findViewById(R.id.textLoginError)
        buttonLogin = findViewById(R.id.buttonLogin)
        textGoRegister = findViewById(R.id.textGoRegister)

        usuarioRepository = UsuarioRepository(this)

        buttonLogin.setOnClickListener {
            textLoginError.text = ""
            realizarLogin()
        }

        textGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun realizarLogin() {
        val username = editTextUsername.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            textLoginError.text = getString(R.string.login_error_empty_fields)
            return
        }

        val usuario = usuarioRepository.findByUsernameAndPassword(username, password)

        if (usuario != null) {
            Log.i("LoginActivity", "Login correcto: $usuario")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            textLoginError.text = getString(R.string.login_error_incorrect)
            Log.i("LoginActivity", "Login fallido para username=$username")
        }
    }
}
