package uvic.cat.comuvicdam_tf_202526dvargas.singleton

import uvic.cat.comuvicdam_tf_202526dvargas.entities.Profesion

object CarritoManager {

    private val items = mutableListOf<Profesion>()

    fun add(profesion: Profesion) {
        items.add(profesion)
    }

    fun getAll(): List<Profesion> {
        return items.toList()   // copia solo lectura
    }

    fun clear() {
        items.clear()
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun remove(profesion: Profesion) {
        items.remove(profesion)
    }
}
