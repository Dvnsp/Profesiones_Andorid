package uvic.cat.comuvicdam_tf_202526dvargas.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Usuario

class UsuarioRepository(context: Context) {

    private val dbHelper: BriccoDbHelper = BriccoDbHelper(context.applicationContext)

    private fun getReadableDb(): SQLiteDatabase = dbHelper.readableDatabase
    private fun getWritableDb(): SQLiteDatabase = dbHelper.writableDatabase

    /**
     * Inserta un nuevo usuario en la base de datos.
     * Devuelve el id autogenerado o -1 si hay error.
     */
    fun insert(usuario: Usuario): Long {
        val db = getWritableDb()

        val values = ContentValues().apply {
            put(BriccoDbHelper.COL_USUARIO_USERNAME, usuario.username)
            put(BriccoDbHelper.COL_USUARIO_PASSWORD, usuario.password)
            put(BriccoDbHelper.COL_USUARIO_NOMBRE, usuario.nombreCompleto)
            put(BriccoDbHelper.COL_USUARIO_TELEFONO, usuario.telefono)
            put(BriccoDbHelper.COL_USUARIO_EMAIL, usuario.email)
        }

        val newId = db.insert(BriccoDbHelper.TABLE_USUARIOS, null, values)
        Log.i("UsuarioRepository", "insert: nuevo usuario id=$newId, $usuario")
        return newId
    }

    /**
     * Busca un usuario por username.
     * Devuelve Usuario o null si no existe.
     */
    fun findByUsername(username: String): Usuario? {
        val db = getReadableDb()

        val cursor = db.query(
            BriccoDbHelper.TABLE_USUARIOS,
            null,
            "${BriccoDbHelper.COL_USUARIO_USERNAME} = ?",
            arrayOf(username),
            null,
            null,
            null
        )

        cursor.use {
            if (it.moveToFirst()) {
                val id = it.getLong(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_ID))
                val uName = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_USERNAME))
                val pass = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_PASSWORD))
                val nombre = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_NOMBRE))
                val telefono = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_TELEFONO))
                val email = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_EMAIL))

                return Usuario(id, uName, pass, nombre, telefono, email)
            }
        }

        return null
    }

    /**
     * Busca un usuario por username y password (para login).
     * Devuelve Usuario o null si no coincide.
     */
    fun findByUsernameAndPassword(username: String, password: String): Usuario? {
        val db = getReadableDb()

        val cursor = db.query(
            BriccoDbHelper.TABLE_USUARIOS,
            null,
            "${BriccoDbHelper.COL_USUARIO_USERNAME} = ? AND ${BriccoDbHelper.COL_USUARIO_PASSWORD} = ?",
            arrayOf(username, password),
            null,
            null,
            null
        )

        cursor.use {
            if (it.moveToFirst()) {
                val id = it.getLong(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_ID))
                val uName = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_USERNAME))
                val pass = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_PASSWORD))
                val nombre = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_NOMBRE))
                val telefono = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_TELEFONO))
                val email = it.getString(it.getColumnIndexOrThrow(BriccoDbHelper.COL_USUARIO_EMAIL))

                return Usuario(id, uName, pass, nombre, telefono, email)
            }
        }

        return null
    }
}
