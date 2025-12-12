package uvic.cat.comuvicdam_tf_202526dvargas.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BriccoDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tabla usuarios
        db.execSQL(SQL_CREATE_USUARIOS)
        // Crear tabla profesiones
        db.execSQL(SQL_CREATE_PROFESIONES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // De momento, si cambia la versión: borrar y volver a crear
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PROFESIONES")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "bricco.db"
        private const val DATABASE_VERSION = 1

        // ==========
        // TABLA USUARIOS
        // ==========
        const val TABLE_USUARIOS = "usuarios"

        const val COL_USUARIO_ID = "id"
        const val COL_USUARIO_USERNAME = "username"
        const val COL_USUARIO_PASSWORD = "password"
        const val COL_USUARIO_NOMBRE = "nombre_completo"
        const val COL_USUARIO_TELEFONO = "telefono"
        const val COL_USUARIO_EMAIL = "email"

        // SQL crear tabla usuarios
        private const val SQL_CREATE_USUARIOS =
            "CREATE TABLE $TABLE_USUARIOS (" +
                    "$COL_USUARIO_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COL_USUARIO_USERNAME TEXT NOT NULL, " +
                    "$COL_USUARIO_PASSWORD TEXT NOT NULL, " +
                    "$COL_USUARIO_NOMBRE TEXT, " +
                    "$COL_USUARIO_TELEFONO TEXT, " +
                    "$COL_USUARIO_EMAIL TEXT" +
                    ");"

        // ==========
        // TABLA PROFESIONES
        // ==========
        const val TABLE_PROFESIONES = "profesiones"

        const val COL_PROF_ID = "id"
        const val COL_PROF_NOMBRE = "nombre"
        const val COL_PROF_PROFESION = "profesion"
        const val COL_PROF_TELEFONO = "telefono"
        const val COL_PROF_CATEGORIA = "categoria"
        const val COL_PROF_PRECIO = "precio"
        const val COL_PROF_ZONAS = "zonas_trabajo"
        const val COL_PROF_DESCRIPCION = "descripcion"
        const val COL_PROF_CONTACTO = "contacto"
        const val COL_PROF_REDES = "redes"

        // SQL crear tabla profesiones
        private const val SQL_CREATE_PROFESIONES =
            "CREATE TABLE $TABLE_PROFESIONES (" +
                    "$COL_PROF_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COL_PROF_NOMBRE TEXT NOT NULL, " +
                    "$COL_PROF_PROFESION TEXT NOT NULL, " +
                    "$COL_PROF_TELEFONO TEXT NOT NULL, " +
                    "$COL_PROF_CATEGORIA TEXT, " +
                    "$COL_PROF_PRECIO TEXT, " +
                    "$COL_PROF_ZONAS TEXT, " +
                    "$COL_PROF_DESCRIPCION TEXT, " +
                    "$COL_PROF_CONTACTO TEXT, " +
                    "$COL_PROF_REDES TEXT" +
                    ");"
    }
}
