package domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persona {
    
    private int idPersona;
    private String nombre;
    private String Apellido;
    private String email;
    private String telefono;

    public Persona() {
    }

    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(int idPersona, String nombre, String Apellido, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public Persona(String nombre, String Apellido, String email, String telefono) {
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.email = email;
        this.telefono = telefono;
    }
    
    

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Persona{");
        sb.append("idPersona=").append(idPersona);
        sb.append(", nombre=").append(nombre);
        sb.append(", Apellido=").append(Apellido);
        sb.append(", email=").append(email);
        sb.append(", telefono=").append(telefono);
        sb.append('}');
        return sb.toString();
    }
}