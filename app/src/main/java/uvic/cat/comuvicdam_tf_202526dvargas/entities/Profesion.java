package uvic.cat.comuvicdam_tf_202526dvargas.entities;

import java.io.Serializable;

public class Profesion implements Serializable {

    private Long id;              // id interno para SQLite más adelante

    // ✅ CAMPOS OBLIGATORIOS
    private String nombre;        // nombre comercial: "FontaExpress"
    private String profesion;     // tipo: "Fontanero"
    private String telefono;      // teléfono de contacto

    // 🟡 CAMPOS OPCIONALES
    private String categoria;     // "Fontanería", "Electricidad", etc.
    private String precio;        // "50 EUR/hora"
    private String zonasTrabajo;  // "Barcelona, Vic, Les Masies de Roda"
    private String descripcion;   // frase corta
    private String contacto;      // email o web
    private String redes;         // IG, TikTok...

    // Constructor vacío
    public Profesion() {
    }

    // Constructor con obligatorios
    public Profesion(Long id, String nombre, String profesion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.profesion = profesion;
        this.telefono = telefono;
    }

    // Constructor con todos los campos
    public Profesion(Long id, String nombre, String profesion, String telefono,
                     String categoria, String precio, String zonasTrabajo,
                     String descripcion, String contacto, String redes) {
        this.id = id;
        this.nombre = nombre;
        this.profesion = profesion;
        this.telefono = telefono;
        this.categoria = categoria;
        this.precio = precio;
        this.zonasTrabajo = zonasTrabajo;
        this.descripcion = descripcion;
        this.contacto = contacto;
        this.redes = redes;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getZonasTrabajo() {
        return zonasTrabajo;
    }

    public void setZonasTrabajo(String zonasTrabajo) {
        this.zonasTrabajo = zonasTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getRedes() {
        return redes;
    }

    public void setRedes(String redes) {
        this.redes = redes;
    }
    @Override
    public String toString() {
        return "Profesion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", profesion='" + profesion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio='" + precio + '\'' +
                ", zonasTrabajo='" + zonasTrabajo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contacto='" + contacto + '\'' +
                ", redes='" + redes + '\'' +
                '}';
    }

}
