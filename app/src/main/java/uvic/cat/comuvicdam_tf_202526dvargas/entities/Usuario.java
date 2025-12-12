package uvic.cat.comuvicdam_tf_202526dvargas.entities;

public class Usuario {

    private Long id;                // lo usaremos más adelante en SQLite
    private String username;        // para iniciar sesión
    private String password;        // para iniciar sesión
    private String nombreCompleto;  // opcional
    private String telefono;        // opcional
    private String email;           // opcional

    // Constructor vacío (necesario para algunos usos con BD, etc.)
    public Usuario() {
    }

    // Constructor con campos principales
    public Usuario(Long id, String username, String password,
                   String nombreCompleto, String telefono, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
