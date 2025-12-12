package uvic.cat.comuvicdam_tf_202526dvargas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uvic.cat.comuvicdam_tf_202526dvargas.R
import uvic.cat.comuvicdam_tf_202526dvargas.entities.Profesion

class Producto_Adapter(
    private val listaProfesiones: List<Profesion>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<Producto_Adapter.ProfesionViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(profesion: Profesion)
    }

    inner class ProfesionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageProfesion: ImageView = itemView.findViewById(R.id.imageProfesion)
        val textNombreProfesion: TextView = itemView.findViewById(R.id.textNombreProfesion)
        val textDescripcionProfesion: TextView = itemView.findViewById(R.id.textDescripcionProfesion)
        val textPrecioProfesion: TextView = itemView.findViewById(R.id.textPrecioProfesion)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profesion = listaProfesiones[position]
                    listener.onItemClick(profesion)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProfesionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfesionViewHolder, position: Int) {
        val profesion = listaProfesiones[position]

        // Nombre + tipo de profesión
        val nombreMostrar = "${profesion.nombre} (${profesion.profesion})"
        holder.textNombreProfesion.text = nombreMostrar

        // Descripción corta: Zonas
        val zonas = profesion.zonasTrabajo ?: ""
        holder.textDescripcionProfesion.text = "Zonas: $zonas"

        // Precio
        val precio = profesion.precio ?: ""
        holder.textPrecioProfesion.text = precio

        // Imagen según tipo de profesión (de momento logo genérico)
        // Más adelante aquí pondremos un when(profesion.profesion) para cambiar el icono
        holder.imageProfesion.setImageResource(R.drawable.logo_bricco)
    }

    override fun getItemCount(): Int = listaProfesiones.size
}
