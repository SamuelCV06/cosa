package tp2.instancias;
/**
 * Nodo que compone la estructura de la lista enlazada de instancias.
 * Cada nodo almacena un objeto {@link Instancia} y una referencia al
 * siguiente nodo en la lista.
 */
public class NodoInstancia {
    private Instancia instancia;
    private NodoInstancia next;

    /**
     * Crea un nodo que almacena una instancia dada.
     *
     * @param ins la instancia a almacenar en este nodo.
     */
    public NodoInstancia(Instancia ins) {
        this.instancia = ins;
        this.next = null;
    }

    /**
     * @return La instancia almacenada
     */
    public Instancia getInstancia() {
        return this.instancia;
    }

    /**
     * @return La referencia del siguiente nodo.
     */
    public NodoInstancia getNext() {
        return this.next;
    }

    /**
     * Establece un nuevo nodo siguiente.
     * 
     * @param newNext Nuevo nodo siguiente
     */
    public void setNext(NodoInstancia newNext) {
        this.next = newNext;
    }

    /**
     * Imprime la instancia almacenada
     */
    public void print() {
        System.out.println(instancia);
    }
}
