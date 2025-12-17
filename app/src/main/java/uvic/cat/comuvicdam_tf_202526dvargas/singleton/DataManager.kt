package uvic.cat.comuvicdam_tf_202526dvargas.singleton

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import uvic.cat.comuvicdam_tf_202526dvargas.data.BriccoDbHelper
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Profesion

object DataManager {

    private const val TAG = "DataManager"

    private var dbHelper: BriccoDbHelper? = null

    /**
     * Se debe llamar UNA VEZ al iniciar la app (por ejemplo en SplashActivity).
     */
    fun init(context: Context) {
        if (dbHelper == null) {
            dbHelper = BriccoDbHelper(context.applicationContext)
            // Insertar profesiones base sólo si la tabla está vacía
            ensureBaseProfesiones()
        }
    }

    private fun getReadableDb(): SQLiteDatabase {
        return dbHelper?.readableDatabase
            ?: throw IllegalStateException("DataManager no inicializado. Llama a DataManager.init(context) primero.")
    }

    private fun getWritableDb(): SQLiteDatabase {
        return dbHelper?.writableDatabase
            ?: throw IllegalStateException("DataManager no inicializado. Llama a DataManager.init(context) primero.")
    }

    /**
     * Añadir una nueva profesión usando la BD (INSERT).
     * Deja la profesión guardada en SQLite y actualiza su id si es posible.
     */
    fun addProfesion(profesion: Profesion) {
        // Guardamos en la BD usando el método que ya tienes
        val newId = insertProfesion(profesion)

        // Si tu clase Profesion tiene setId(Long), lo actualizamos:
        try {
            profesion.id = newId   // Kotlin usa getId/setId de la clase Java
        } catch (e: Exception) {
            Log.w(TAG, "No se pudo actualizar el id en el objeto Profesion, pero se insertó en BD con id=$newId")
        }
    }
    fun updateProfesion(profesion: Profesion) {
        val id = profesion.id
            ?: throw IllegalArgumentException("No se puede actualizar una profesión sin ID")

        val db = getWritableDb()

        val values = ContentValues().apply {
            put(BriccoDbHelper.COL_PROF_NOMBRE, profesion.getNombre())
            put(BriccoDbHelper.COL_PROF_PROFESION, profesion.getProfesion())
            put(BriccoDbHelper.COL_PROF_TELEFONO, profesion.getTelefono())
            put(BriccoDbHelper.COL_PROF_CATEGORIA, profesion.getCategoria())
            put(BriccoDbHelper.COL_PROF_PRECIO, profesion.getPrecio())
            put(BriccoDbHelper.COL_PROF_ZONAS, profesion.getZonasTrabajo())
            put(BriccoDbHelper.COL_PROF_DESCRIPCION, profesion.getDescripcion())
            put(BriccoDbHelper.COL_PROF_CONTACTO, profesion.getContacto())
            put(BriccoDbHelper.COL_PROF_REDES, profesion.getRedes())
        }

        val rows = db.update(
            BriccoDbHelper.TABLE_PROFESIONES,
            values,
            "${BriccoDbHelper.COL_PROF_ID} = ?",
            arrayOf(id.toString())
        )

        Log.i(TAG, "updateProfesion: actualizadas $rows filas para id=$id")
    }



    /**
     * Devuelve TODAS las profesiones de la base de datos.
     */
    fun getProfesiones(): List<Profesion> {
        val lista = mutableListOf<Profesion>()

        val db = getReadableDb()
        val cursor = db.query(
            BriccoDbHelper.TABLE_PROFESIONES,
            null,   // todas las columnas
            null,
            null,
            null,
            null,
            null
        )

        cursor.use {
            val idxId = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_ID)
            val idxNombre = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_NOMBRE)
            val idxProfesion = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_PROFESION)
            val idxTelefono = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_TELEFONO)
            val idxCategoria = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_CATEGORIA)
            val idxPrecio = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_PRECIO)
            val idxZonas = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_ZONAS)
            val idxDescripcion = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_DESCRIPCION)
            val idxContacto = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_CONTACTO)
            val idxRedes = it.getColumnIndexOrThrow(BriccoDbHelper.COL_PROF_REDES)

            while (it.moveToNext()) {
                val profesion = Profesion(
                    it.getLong(idxId),
                    it.getString(idxNombre),
                    it.getString(idxProfesion),
                    it.getString(idxTelefono),
                    it.getString(idxCategoria),
                    it.getString(idxPrecio),
                    it.getString(idxZonas),
                    it.getString(idxDescripcion),
                    it.getString(idxContacto),
                    it.getString(idxRedes)
                )
                lista.add(profesion)
            }
        }

        return lista
    }

    /**
     * Inserta una nueva profesión en la base de datos.
     * Devuelve el id autogenerado.
     */
    fun insertProfesion(profesion: Profesion): Long {
        val db = getWritableDb()
        val values = ContentValues().apply {
            put(BriccoDbHelper.COL_PROF_NOMBRE, profesion.getNombre())
            put(BriccoDbHelper.COL_PROF_PROFESION, profesion.getProfesion())
            put(BriccoDbHelper.COL_PROF_TELEFONO, profesion.getTelefono())
            put(BriccoDbHelper.COL_PROF_CATEGORIA, profesion.getCategoria())
            put(BriccoDbHelper.COL_PROF_PRECIO, profesion.getPrecio())
            put(BriccoDbHelper.COL_PROF_ZONAS, profesion.getZonasTrabajo())
            put(BriccoDbHelper.COL_PROF_DESCRIPCION, profesion.getDescripcion())
            put(BriccoDbHelper.COL_PROF_CONTACTO, profesion.getContacto())
            put(BriccoDbHelper.COL_PROF_REDES, profesion.getRedes())
        }
        val newId = db.insert(BriccoDbHelper.TABLE_PROFESIONES, null, values)
        Log.i(TAG, "insertProfesion: insertado con id = $newId, $profesion")
        return newId
    }

    /**
     * Elimina una profesión de la base de datos por su id.
     */
    fun deleteProfesion(profesion: Profesion) {
        val id = profesion.id

        if (id == null) {
            Log.w(TAG, "deleteProfesion: profesión sin id, no se puede borrar: $profesion")
            return
        }

        val db = getWritableDb()
        val rows = db.delete(
            BriccoDbHelper.TABLE_PROFESIONES,
            "${BriccoDbHelper.COL_PROF_ID} = ?",
            arrayOf(id.toString())
        )

        Log.i(TAG, "deleteProfesion: borradas $rows filas para id = $id")
    }

    /**
     * Inserta las profesiones base sólo si la tabla está vacía.
     */
    private fun ensureBaseProfesiones() {
        val db = getReadableDb()
        val cursor = db.rawQuery(
            "SELECT COUNT(*) FROM ${BriccoDbHelper.TABLE_PROFESIONES}",
            null
        )

        var count = 0
        cursor.use {
            if (it.moveToFirst()) {
                count = it.getInt(0)
            }
        }

        if (count > 0) {
            Log.i(TAG, "ensureBaseProfesiones: ya hay $count profesiones en la BD, no se insertan las base.")
            return
        }

        Log.i(TAG, "ensureBaseProfesiones: tabla vacía, insertando profesiones base...")

        val baseProfesiones = listOf(
            Profesion(
                null,
                "FontaExpress",
                "Fontanero",
                "+34 600 111 001",
                "Fontanería",
                "50 EUR/hora",
                "Barcelona, Vic, Les Masies de Roda",
                "Reparación de fugas, desatascos y mantenimiento de instalaciones de agua.",
                "info@fontaexpress.com",
                "IG @fontaexpress"
            ),
            Profesion(
                null,
                "ElectroServe",
                "Electricista",
                "+34 600 111 002",
                "Electricidad",
                "45 EUR/hora",
                "Barcelona, Granollers, Vic",
                "Instalación y reparación de cuadros eléctricos, enchufes y averías urgentes.",
                "contacto@electroserve.com",
                "IG @electroserve"
            ),
            Profesion(
                null,
                "Carpintería Fusta&Home",
                "Carpintero",
                "+34 600 111 003",
                "Carpintería",
                "40 EUR/hora",
                "Vic, Manlleu, Torelló",
                "Muebles a medida, puertas, armarios y reparaciones de madera.",
                "info@fustahome.com",
                "IG @fusta_home"
            ),
            Profesion(
                null,
                "Pinturas ColorPlus",
                "Pintor",
                "+34 600 111 004",
                "Pintura",
                "35 EUR/hora",
                "Barcelona, Vic, Olot",
                "Pintura de interiores y exteriores, alisado de paredes y pequeñas reparaciones.",
                "info@colorplus.com",
                "IG @pinturas_colorplus"
            ),
            Profesion(
                null,
                "ObraFina Reformas",
                "Albañil",
                "+34 600 111 005",
                "Reformas",
                "60 EUR/hora",
                "Barcelona, Les Masies de Roda, Vic",
                "Pequeñas reformas, suelos, alicatados y reparaciones de obra.",
                "contacto@obrafina.com",
                "IG @obrafina_reformas"
            ),
            Profesion(
                null,
                "Jardins&Verde",
                "Jardinero",
                "+34 600 111 006",
                "Jardinería",
                "30 EUR/hora",
                "Vic, Olot, Manlleu",
                "Mantenimiento de jardines, poda y diseño de espacios verdes.",
                "info@jardinsverde.com",
                "IG @jardins_verde"
            ),
            Profesion(
                null,
                "Cerraja24h",
                "Cerrajero",
                "+34 600 111 007",
                "Cerrajería",
                "70 EUR/servicio",
                "Barcelona, Vic, Granollers",
                "Apertura de puertas, cambio de bombines y refuerzo de seguridad.",
                "soporte@cerraja24h.com",
                "IG @cerraja24h"
            ),
            Profesion(
                null,
                "ElectroHome Fix",
                "Técnico de electrodomésticos",
                "+34 600 111 008",
                "Electrodomésticos",
                "50 EUR/servicio",
                "Barcelona, Vic, Olot",
                "Reparación de lavadoras, neveras, hornos y más a domicilio.",
                "contacto@electrohomefix.com",
                "IG @electrohome_fix"
            ),
            Profesion(
                null,
                "ClimaPerfect",
                "Tecnico de aire acondicionado",
                "+34 600 111 009",
                "Climatización",
                "80 EUR/servicio",
                "Barcelona, Vic, Manlleu",
                "Instalación y mantenimiento de aire acondicionado y calefacción.",
                "info@climaperfect.com",
                "IG @climaperfect"
            ),
            Profesion(
                null,
                "Manitas Hogar+",
                "Manitas",
                "+34 600 111 010",
                "Mantenimiento",
                "25 EUR/hora",
                "Barcelona, Vic, Les Masies de Roda",
                "Montaje de muebles, colgar cuadros y pequeñas reparaciones.",
                "contacto@manitashogarplus.com",
                "IG @manitas_hogarplus"
            )
        )

        baseProfesiones.forEach { insertProfesion(it) }

        Log.i(TAG, "ensureBaseProfesiones: profesiones base insertadas.")
    }
}
