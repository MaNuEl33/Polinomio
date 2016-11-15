package polinomio;

public class Nodo {
    
    private Termino dato;
    
    private Nodo siguiente;
    
    public Nodo() {
        
    }

    public Nodo(Termino dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Termino getDato() {
        return dato;
    }

    public void setDato(Termino dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }    
}
