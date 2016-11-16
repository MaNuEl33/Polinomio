package polinomio;

import java.text.DecimalFormat;

public class Polinomio {
    
    private Nodo primero; 
    private int talla; // cantidad de nodos
    
    public Polinomio() {
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
        
        if(x.getCoeficiente() == 0) { // Si el coeficiente del termino ingresado es 0
            return;
        }
        
        
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
        
        // Formato para que no se visualice cero a la derecha del punto decimal. Ejm.: 3.0 -> 3
        DecimalFormat formato = new DecimalFormat("0.#");
        
        Nodo aux = primero; 
        int cont = 0; // para verificar si es el primer termino
        
        String p; // aqui se guardara la cadena que representa el polinomio
        
        if(aux == null) { // Si el polinomio no posee terminos
            p = "0"; // El polinomio es 0
            
            return p;
            
        } else {
            p = "";
        }
        
        while(aux != null) { // mientras haya nodos que recorrer
            if(cont == 0) { // si es el primero
                p += formato.format(aux.getDato().getCoeficiente()); // agregar el coeficiente
            } else {
                if(aux.getDato().getCoeficiente() >= 0) { // si es positivo 
                    p += "+" + formato.format(aux.getDato().getCoeficiente()); // agregar el signo '+' más el coeficiente
                } else {
                    p += formato.format(aux.getDato().getCoeficiente()); // agregar el coeficiente
                }
            }
            
            // Si el exponente es diferente de 0. Ya que no es necesario mostrar x⁰
            if(aux.getDato().getExponente() != 0) {
                if(aux.getDato().getExponente() == 1) { // Si el exponente es uno no es necesario mostrar x¹
                    p += "x";
                } else {
                    p += "x^" + aux.getDato().getExponente(); // agregar la variable x más el exponente   
                }
            }
            
            cont++; // aumentar el contador
            aux = aux.getSiguiente(); //asignamos el nodo siguiente a 'aux'
        }
        
        return p; // retornamos la cadena que representa el polinomio
    }
    
    public static Polinomio unir(Polinomio a, Polinomio b) { // Crea un polinomio uniendo dos
        
        Polinomio c = new Polinomio(); // Creamos el nuevo polinomio
        
        Nodo aux = a.getPrimero(); // Primer nodo del primer polinomio
        
        // Recorremos el primer polinomio e insertamos los nodos al nuevo
        while(aux != null) {
            c.insertarTermino(aux.getDato());
            aux = aux.getSiguiente();
        }
        
        Nodo aux2 = b.getPrimero(); // Primer nodo del segundo polinomio
        
        // Recorremos el segundo polinomio e insertamos los nodos al nuevo
        while(aux2  != null) {
            c.insertarTermino(aux2.getDato());
            aux2 = aux2.getSiguiente();
        }
        
        return c; // Retornamos el polinomio
    }
    
    public static Polinomio negativo(Polinomio s) { // Vuelve negativo los terminos de un polinomio creando una nueva lista
        
        Polinomio negativos = new Polinomio(); // Creamos el nuevo polinomio
        
        Nodo actual = s.getPrimero(); // Primer nodo del polinomio a recorrer
        
        // Mientras haya nodos por recorrer
        while(actual!= null) {
            
            // Insertamos al nuevo polinomio cambiando el coeficiente por su negativo correspondiente
            negativos.insertarTermino(new Termino(actual.getDato().getCoeficiente() * -1, actual.getDato().getExponente()));
            
            actual = actual.getSiguiente();
        }
        
        return negativos; // Retornamos el polinomio
    }
    
    public static Polinomio simplifica(Polinomio s) { // Simplifica un polinomio. Ejm.: 2x³+3x³+5x²+8x² -> 5x³+11x². Lo usaremos para las sumas
        
        Polinomio suma = new Polinomio(); // Creamos un nuevo polinomio que contendra el polinomio simplificado
        
        Nodo actual = s.getPrimero(); // Primer nodo del polinomio ingresado
        
        int exponente = actual.getDato().getExponente(); // Exponente del nodo actual
        float sum = 0; // Suma de los coeficientes de un mismo exponente
        
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
    
    public static Polinomio derivar(Polinomio s) { // Deriva un polinomio
        
        Polinomio p = new Polinomio(); // Creamos un polinomio que sera el polinomio derivado
        
        s = Polinomio.simplifica(s); // Simplificamos el polinomio ingresado
        
        // Variables para los nuevos coeficientes y exponentes del polinomio
        float coeficiente;
        int exponente;
        
        Nodo actual = s.getPrimero(); // Primer nodo del polinomio ingresado
        
        while(actual != null) { // Mientras haya nodos por recorrer
            
            // Nuevo coeficiente que es igual al coeficiente actual por el exponente actual
            coeficiente = actual.getDato().getCoeficiente() * actual.getDato().getExponente();
            
            // Nuevo exponente que es igual a exponente actual - 1
            exponente = actual.getDato().getExponente() - 1;
            
            p.insertarTermino(new Termino(coeficiente, exponente)); // Insertamos el termino en el polinomio que hemos creado
            
            actual = actual.getSiguiente();
        }
        
        p = simplifica(p);
        
        return p; // Retornamos el polinomio resultante
    }
    
    public int evalua(int n) {
        
        int res = 0; // Variable que contendra el resultado. Se inicaliza en 0 porque es una suma
        
        Nodo actual = primero; // Primer nodo
        
        while(actual != null) { // Mientras haya nodos por recorrer
            
            // Los resultados son las sumas sucesivas de el coeficiente actual por el valor de n
            // ingresado elevado al exponente actual
            res += actual.getDato().getCoeficiente() * Math.pow(n, actual.getDato().getExponente());
            
            actual = actual.getSiguiente();
        }
        
        return res; // Retornamos el polinomio resultante
    }
    
    public static Polinomio resta(Polinomio p, Polinomio q) {
        
        Polinomio res; // Nuevo Polinomio que contendra el resultado de la resta
        
        res = negativo(q); // Negativo del polinomio q
        
        res = unir(p, res); // Creamos un polinomio con la suma de ambos P(x) + -Q(x) = P(x) - Q(x)
        
        res = simplifica(res); // Simplificamos el polinomio
            
        return res; // Retornamos el polinomio resultante
    }
    
    public static Polinomio suma(Polinomio p, Polinomio q) {
        
        Polinomio sum; // Nuevo Polinomio que contendra el resultante de la suma
        
        sum = unir(p, q); // Creamos un polinomio con la suma de ambos
         
        sum = simplifica(sum); // Simpflicamos el polinomio obtenido
        
        return sum; // Retornamose l polinomio resultante
    }
    
    public static Polinomio multiplicacion(Polinomio multiplicando, Polinomio multiplicador) {
        
        Polinomio mul = new  Polinomio(); // Nuevo polinomio que contendra el resultado de la multiplicacion
        
        multiplicando = simplifica(multiplicando); // Simplificamos el multiplicando
        
        multiplicador = simplifica(multiplicador); // Simplificamos el multiplicador
        
        // Variables para los productos parciales
        float coeficiente;
        int exponente;
        
        for(Nodo aux1 = multiplicando.getPrimero(); aux1 != null; aux1 = aux1.getSiguiente()) { // Recorremos todo el multiplicando
            for(Nodo aux2 = multiplicador.getPrimero(); aux2 != null; aux2 = aux2.getSiguiente()) { // Recorremos todo el multiplicador
                coeficiente = aux1.getDato().getCoeficiente() * aux2.getDato().getCoeficiente(); // Coeficiente del producto parcial
                exponente = aux1.getDato().getExponente() + aux2.getDato().getExponente(); // Exponente del producto parcial
                mul.insertarTermino(new Termino(coeficiente, exponente)); // Insertamos el producto parcial en el nuevo polinomio
            }
        }
        
        mul = simplifica(mul); // Simplificamos el polinomio multiplicacion
        
        return mul; // Retornamos el polinomio
    }
    
    public static Polinomio division(Polinomio dividendo, Polinomio divisor) {
        
        Polinomio cociente = new Polinomio(); // Polinomio cociente que contendra el resultado de la division
        
        Polinomio aux;  // Polinomio auxiliar que contendra el resultado de cada division parcial
        Termino termino; // Termino que contendra el coeficiente y exponente de las divisiones parciales
        
        // Mientras el dividendo no sea nulo o el grado del dividendo sea mayor igual que el grado del divisor
        while( dividendo.getPrimero() != null && (dividendo.getPrimero().getDato().getExponente() >= divisor.getPrimero().getDato().getExponente())) {
            
            // Coeficiente resultante de la division parcial
            float coeficiente = dividendo.getPrimero().getDato().getCoeficiente() / divisor.getPrimero().getDato().getCoeficiente();
            
            // Exponente resultante de la division parcial
            int exponente = dividendo.getPrimero().getDato().getExponente() - divisor.getPrimero().getDato().getExponente();
            
            termino = new Termino(coeficiente, exponente); // Definimos el termino con los resultados anteriores
        
            cociente.insertarTermino(termino); // Insertamos la division parcial al polinomio cociente
            
            aux = new Polinomio(); // Inicializamos el polinomio auxiliar
            
            aux.insertarTermino(termino); // Polinomio auxiliar sera igual al resultado de la division parcial
           
            // El nuevo dividendo sera el resultado de la resta del dividendo actual con la multiplicacion del divisor
            //  y el resultado de la division parcial
            dividendo = resta(dividendo, multiplicacion(aux, divisor)); 
        }
        
        return cociente; // Retornamos el polinomio cociente
    }
}
