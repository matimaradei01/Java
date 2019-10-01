package domain;

public class PersonaDTO {
    
    private int idpersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    public PersonaDTO(int idpersona, String nombre, String apellido, String email, String telefono) {
        this.idpersona = idpersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public PersonaDTO(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }
    

    public int getId_persona() {
        return idpersona;
    }

    public void setId_persona(int id_persona) {
        this.idpersona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    

    @Override
    public String toString() {
        return "id" + idpersona + ", nombre: " + nombre + ", apellido: " + apellido + ", email: " + email + ", telefono: " + telefono + '}';
    }
    
    
}
