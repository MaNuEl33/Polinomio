package polinomio;

public class Termino {
    
    private float coeficiente;
    private int exponente;
    
    public Termino(){
        
    }

    public Termino(float coeficiente, int exponente) {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
    }

    public float getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(float coeficiente) {
        this.coeficiente = coeficiente;
    }

    public int getExponente() {
        return exponente;
    }

    public void setExponente(int exponente) {
        this.exponente = exponente;
    }
    
}
