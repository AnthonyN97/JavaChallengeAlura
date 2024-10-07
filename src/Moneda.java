public class Moneda {
    private String nombre;
    private String abreviatura;
    private float cambio;

    public Moneda(String nombre, String abreviatura, float cambio) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.cambio = cambio;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public float CambioMoneda(float cantidad, Moneda moneda1, Moneda moneda2){
        return (cantidad/moneda1.cambio)*moneda2.cambio;
    }

    @Override
    public String toString(){
        return nombre + " ("+abreviatura+"): "+cambio;
    }
}