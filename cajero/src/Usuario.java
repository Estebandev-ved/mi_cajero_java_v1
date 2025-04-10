public class Usuario {
    private String nombre;
    private Cuenta cuenta;
    
    
    public Usuario(String nombre, Cuenta cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }


    public String getNombre() {
        return nombre;
    }


    public Cuenta getCuenta() {
        return cuenta;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

}