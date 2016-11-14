package polinomio;

public class Polinomio {
    
    public static Lista p = new Lista(); // Polinomio p
    public static Lista q = new Lista(); // Polinomio q
    public static Lista s, sum; // Para la suma
    public static Lista qNeg, r, res; // Para la resta

    public static void main(String[] args) {
        
        Interfaz miInterfaz = new Interfaz(); // Crear un objeto de tipo Intefaz
        miInterfaz.setVisible(true); // Para visualizar la interfaz
        
//        p.insertarTermino(new Termino(2, 2));
//        p.insertarTermino(new Termino(-2, 3));
//        q.insertarTermino(new Termino(3, 2));
//        
//        r = Lista.unir(p, q);
//        
//        System.out.println(r.mostrarPolinomio());
    }
    
}