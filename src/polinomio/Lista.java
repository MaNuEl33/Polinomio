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
    
    public void insertarTermino(Termino x) { // Inserta terminos ordenandolos de mayor a menor
        
        Nodo nuevo = new Nodo(x); // Nuevo nodo que contendra el termino
       
        // Nodos para recorrer la lista
        Nodo actual = primero; // Nodo actual
        Nodo anterior = null; // Nodo anterior
      
        // Si aun hay nodos por recorrer y el exponente del nodo actual es mayor igual que el exponente del nodo nuevo
        while(actual != null && (actual.getDato().getExponente() >= nuevo.getDato().getExponente())) {
            anterior = actual; // Nodo anterior apunta al nodo actual
            actual = actual.getSiguiente(); // Nodo actual apunta al siguiente
        }
            
        if(actual == primero) { // Insertar al inicio
            primero = nuevo; // Primer nodo apunta al nuevo nodo
            nuevo.setSiguiente(actual); // Nuevo nodo apunta al actual
        } else {
            anterior.setSiguiente(nuevo); // Nodo anterior apunta al nodo nuevo
        }
        
        nuevo.setSiguiente(actual); // Nodo nuevo apunta al nodo actual

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
    
    public static Lista unir(Lista a, Lista b) { // Crea una lista uniendo dos
        
        Lista c = new Lista(); // Creamos la nueva lista
        
        Nodo aux = a.getPrimero(); // Primer nodo de la primera lista
        
        // Recorremos la primera lista e insertamos los nodos a la nueva
        while(aux != null) {
            c.insertarTermino(aux.getDato());
            aux = aux.getSiguiente();
        }
        
        Nodo aux2 = b.getPrimero(); // Primer nodo de la segunda lista
        
        // Recorremos la segunda lista e insertamos los nodos a la nueva
        while(aux2  != null) {
            c.insertarTermino(aux2.getDato());
            aux2 = aux2.getSiguiente();
        }
        
        return c; // Retornamos la lista
    }
    
    public static Lista negativo(Lista s) { // Vuelve negativo los terminos de un polinomio creando una nueva lista
        
        Lista negativos = new Lista(); // Creamos la nueva lista
        
        Nodo actual = s.getPrimero(); // Primer nodo de la lista a recorrer
        
        // Mientras haya nodos por recorrer
        while(actual!= null) {
            
            // Insertamos a la nueva lista cambiando el coeficiente por su negativo correspondiente
            negativos.insertarTermino(new Termino(actual.getDato().getCoeficiente() * -1, actual.getDato().getExponente()));
            
            actual = actual.getSiguiente();
        }
        
        return negativos; // Retornamos la lista
    }
    
    public static Lista simplifica(Lista s) { // Simplifica un polinomio. Ejm.: 2x³+3x³+5x²+8x² -> 5x³+11x². Lo usaremos para las sumas
        
        Lista suma = new Lista(); // Creamos la nueva lista que contendra el polinomio simplificado
        
        Nodo actual = s.getPrimero(); // Primer nodo de la lista ingresada
        
        int exponente = actual.getDato().getExponente(); // Exponente del nodo actual
        int sum = 0; // Suma de los coeficientes de un mismo exponente
        
        while(actual != null) { // Mientras haya nodos por recorrer (terminos)
            
            if(actual.getDato().getExponente() != exponente) { // Si el exponente del nodo actual es diferente al que venimos obteniendo
                suma.insertarTermino(new Termino(sum, exponente)); // Insertamos un nuevo termino que contiene la suma de los terminos del polinomio de un mismo grado
                exponente = actual.getDato().getExponente(); // Ahora el exponente a evaluar cambia
                sum = 0; // Nueva suma para los terminos de diferente grado
            }
            
            sum += actual.getDato().getCoeficiente(); // Suma de los coeficientes de los terminos del mismo grado
            
            actual = actual.getSiguiente();
        }
        
        suma.insertarTermino(new Termino(sum, exponente)); // Suma para el ultimo termino
        
        return suma; // Retornamos el polinomio simplificado
    }
}
