public class Cuenta {
    private String numeroCuenta;
    private double saldo;
    private String pin;
    private int intentosFallidos;
    private TipoDeCuenta tipoDeCuenta;

    // Constructor completo
    public Cuenta(String numeroCuenta, double saldo, String pin, int intentosFallidos, TipoDeCuenta tipoDeCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.pin = pin;
        this.intentosFallidos = intentosFallidos;
        this.tipoDeCuenta = tipoDeCuenta;
    }

    // Getters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getPin() {
        return pin;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public TipoDeCuenta getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    // Setters
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public void setTipoDeCuenta(TipoDeCuenta tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }

    // Métodos útiles (opcional)
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return true;
        } else {
            return false;
        }
    }
}
