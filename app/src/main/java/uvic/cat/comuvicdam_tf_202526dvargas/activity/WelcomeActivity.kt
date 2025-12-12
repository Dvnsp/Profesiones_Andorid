package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.activity.MainActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)

        val buttonEntrar: Button = findViewById(R.id.buttonEntrar)

        buttonEntrar.setOnClickListener {
            // De momento vamos a MainActivity.
            // Más adelante esto apuntará a LoginActivity.
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
