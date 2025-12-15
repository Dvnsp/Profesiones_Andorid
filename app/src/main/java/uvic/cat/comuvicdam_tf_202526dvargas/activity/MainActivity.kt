package uvic.cat.comuvicdam_tf_202526dvargas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.adapter.Producto_Adapter
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Profesion
import uvic.cat.comuvicdam_tf_202526dvargas.singleton.CarritoManager
import uvic.cat.comuvicdam_tf_202526dvargas.singleton.DataManager

class MainActivity : AppCompatActivity(), Producto_Adapter.OnItemClickListener {

    private lateinit var recyclerProfesiones: RecyclerView
    private lateinit var adapter: Producto_Adapter
    private lateinit var buttonReservar: Button
    private lateinit var fabAddProfesion: FloatingActionButton

    private lateinit var qrLauncher: ActivityResultLauncher<ScanOptions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.main_title)

        qrLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                mostrarResultadoQR(result.contents)
            } else {
                Log.i("MainActivity", "Escaneo QR cancelado")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val profesiones: List<Profesion> = DataManager.getProfesiones()

        recyclerProfesiones = findViewById(R.id.recyclerProfesiones)
        recyclerProfesiones.layoutManager = GridLayoutManager(this, 2)
        adapter = Producto_Adapter(profesiones, this)
        recyclerProfesiones.adapter = adapter

        buttonReservar = findViewById(R.id.buttonReservar)
        buttonReservar.setOnClickListener {
            irAReserva()
        }

        fabAddProfesion = findViewById(R.id.fabAddProfesion)
        fabAddProfesion.setOnClickListener {
            val intent = Intent(this, AddProfesionActivity::class.java)
            startActivity(intent)
        }
    }

    // ===== MENÚ SUPERIOR =====

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                mostrarCarrito()
                true
            }
            R.id.action_scan_qr -> {
                escanearQR()
                true
            }
            R.id.action_help -> {
                mostrarAyuda()
                true
            }
            R.id.action_about -> {
                mostrarAcercaDe()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // ===== CARRITO =====

    private fun mostrarCarrito() {
        val items = CarritoManager.getAll()

        if (items.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.cart_title))
                .setMessage(getString(R.string.cart_empty))
                .setPositiveButton(getString(R.string.dialog_ok), null)
                .show()
            return
        }

        val nombres = items.mapIndexed { index, profesion ->
            "${index + 1}. ${profesion.nombre} (${profesion.profesion})"
        }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.cart_title))
            .setItems(nombres) { _, which ->
                val profesionSeleccionada = items[which]
                confirmarEliminarDelCarrito(profesionSeleccionada)
            }
            .setPositiveButton(getString(R.string.cart_clear_all)) { _, _ ->
                CarritoManager.clear()
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.cart_title))
                    .setMessage(getString(R.string.cart_cleared_ok))
                    .setPositiveButton(getString(R.string.dialog_ok), null)
                    .show()
            }
            .setNegativeButton(getString(R.string.dialog_cancel), null)
            .show()
    }

    private fun confirmarEliminarDelCarrito(profesion: Profesion) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.cart_delete_title))
            .setMessage(
                getString(
                    R.string.cart_delete_message,
                    profesion.nombre,
                    profesion.profesion
                )
            )
            .setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                CarritoManager.remove(profesion)
                mostrarCarrito()
            }
            .setNegativeButton(getString(R.string.dialog_cancel), null)
            .show()
    }

    private fun mostrarAyuda() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.help_title))
            .setMessage(getString(R.string.help_main_message))
            .setPositiveButton(getString(R.string.dialog_ok), null)
            .show()
    }

    private fun mostrarAcercaDe() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.about_title))
            .setMessage(getString(R.string.about_main_message))
            .setPositiveButton(getString(R.string.dialog_ok), null)
            .show()
    }

    // ===== QR =====

    private fun escanearQR() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Escanea el código QR de la profesión")
            setCameraId(0)
            setBeepEnabled(true)
            setBarcodeImageEnabled(false)
        }

        qrLauncher.launch(options)
    }

    private fun mostrarResultadoQR(textoQR: String) {
        AlertDialog.Builder(this)
            .setTitle("QR leído")
            .setMessage(textoQR)
            .setPositiveButton(getString(R.string.dialog_ok), null)
            .show()
    }

    // ===== RESERVA =====

    private fun irAReserva() {
        if (CarritoManager.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.reservation_title))
                .setMessage(getString(R.string.reservation_need_items))
                .setPositiveButton(getString(R.string.dialog_ok), null)
                .show()
            return
        }

        val intent = Intent(this, ReservationActivity::class.java)
        startActivity(intent)
    }

    // ===== CLICK EN UNA PROFESIÓN =====

    override fun onItemClick(profesion: Profesion) {
        val intent = Intent(this, ProfesionDetailActivity::class.java)
        intent.putExtra("profesion", profesion)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        val profesionesActualizadas: List<Profesion> = DataManager.getProfesiones()
        adapter = Producto_Adapter(profesionesActualizadas, this)
        recyclerProfesiones.adapter = adapter
        invalidateOptionsMenu()
    }
}
