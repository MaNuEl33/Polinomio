package polinomio;

public class Lista {
    
    private Nodo primero; 
    private int talla; // cantidad de nodos
    
    public Lista() {
        this.primero = null;
        this.talla = 0;
    }

    public Nodo getPrimero() {
        return primero;
    }

    public int getTalla() {
        return talla;
    }
    
    public void insertarTermino(Termino x) {
        
        Nodo nuevo = new Nodo(x);
        Nodo aux = primero;
        
        if(aux == null) { // Si no hay nodos
            primero = nuevo; // primero es igual al nuevo
        } else {
            while(aux.getSiguiente()!= null) { // mientras el nodo siguiente no sea el ultimo
                aux = aux.getSiguiente(); // el nodo ahora es el siguiente
            }
            
            aux.setSiguiente(nuevo); // asignamos a 'nuevo' el siguiente del ultimo nodo
        }
        
        talla++;
    }
    
    public void eliminarTermino(Termino x) {
        
        Nodo aux = primero; // nodo actual
        Nodo ant = null; // nodo anterior
        
        while(aux != null && !aux.getDato().equals(x)) { // Mientras haya nodos que recorrer y el nodo no sea el que queremos 
            ant = aux;
            aux = aux.getSiguiente();
        }
        
        if(aux != null) { // Si es que encontro el nodo
          talla--; // disminuimos la cantidad de nodos
          
          if(ant == null) { // Si es el primer nodo
              primero = aux.getSiguiente();
          } else {
              ant.setSiguiente(aux.getSiguiente());
          }
        }
    }
    
    public String mostrarPolinomio() {
        
        Nodo aux = primero; 
        int cont = 0; // para verificar si es el primer termino
        
        String p = ""; // aqui se guardara la cadena que representa el polinomio
        
        while(aux != null) { // mientras haya nodos que recorrer
            if(cont == 0) { // si es el primero
                p += aux.getDato().getCoeficiente(); // agregar el coeficiente
            } else {
                if(aux.getDato().getCoeficiente() >= 0) { // si es positivo 
                    p += "+" + aux.getDato().getCoeficiente(); // agregar el signo '+' más el coeficiente
                } else {
                    p += aux.getDato().getCoeficiente(); // agregar el coeficiente
                }
            }
            
            p += "x^" + aux.getDato().getExponente(); // agregar la variable x más el exponente
            
            cont++; // aumentar el contador
            aux = aux.getSiguiente(); //asignamos el nodo siguiente a 'aux'
        }
        
        return p; // retornamos la cadena que representa el polinomio
    }
}
