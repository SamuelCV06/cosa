package tp2.bosque;
import tp2.arbol.*;
/**
 * Nodo que compone la estructura de la lista enlazada del Bosque Aleatorio.
 * Cada nodo almacena un objeto {@link ArbolBruto} y una referencia al
 * siguiente nodo en la lista.
 */
public class NodoBosque {
    private ArbolBruto arbol;
    private NodoBosque next;

    /**
     * Crea un nodo que almacena un arbol dado.
     *
     * @param ins el arbol a almacenar en este nodo.
     */
    public NodoBosque(ArbolBruto arbol) {
        this.arbol = arbol;
        this.next = null;
    }

    /**
     * @return El arbol almacenado.
     */
    public ArbolBruto getArbol() {
        return this.arbol;
    }

    /**
     * @return La referencia del siguiente nodo.
     */
    public NodoBosque getNext() {
        return this.next;
    }

    /**
     * Establece un nuevo nodo siguiente.
     * 
     * @param newNext Nuevo nodo siguiente
     */
    public void setNext(NodoBosque newNext) {
        this.next = newNext;
    }
}
