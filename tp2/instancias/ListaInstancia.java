package tp2.instancias;
/**
 * Implementación de una lista enlazada simple para almacenar objetos
 * {@code Instancia} encapsulados en nodos {@link NodoInstancia}.
 * Permite inserción eficiente al final de la lista y mantiene un 
 * contador interno de elementos.
 */
public class ListaInstancia {
    private NodoInstancia first;
    private NodoInstancia last;
    private int size; // Contador de elementos.

    /**
     * Crea una lista vacía sin nodos iniciales.
     */
    public ListaInstancia() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Agrega una nueva instancia al final de la lista.
     *
     * @param ins la instancia que se insertara.
     */
    public void add(Instancia ins) {
        NodoInstancia nuevo = new NodoInstancia(ins);
        // Si la lista se encuentra vacia.
        if (this.first == null) {
            this.first = nuevo;
            this.last = nuevo;
        }
        else {
            this.last.setNext(nuevo);
            this.last = nuevo;
        }
        size++;
    }

    /**
     * @return El tamanho de la lista.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return El primer nodo de la lista.
     */
    public NodoInstancia getFirst() {
        return this.first;
    }

    /**
     * @return El ultimo nodo de la lista.
     */
    public NodoInstancia getLast() {
        return this.last;
    }

    /**
     * Imprime cada una de las instancias almacenadas
     * en los nodos de toda la lista
     */
    public void print() {
        NodoInstancia puntero = this.first;
        while (puntero != null) {
            puntero.print();
            puntero = puntero.getNext();
        }
    }

    /**
     * Imprime las instancias almacenadas en los nodos
     * hasta un limite definido
     * @param bound Limite
     */
    public void printUpTo(int bound) {
        NodoInstancia puntero = this.first;
        int count = 0;
        while (puntero != null && count < bound) {
            puntero.print();
            puntero = puntero.getNext();
            count++;
        }
    }

    /**
     * Retorna el nodo que se encuentre en una posicion especifia.
     * Si el parametro dado es menor a 1 retorna null.
     * 
     * @param index La posicion especifia.
     * @return El nodo en la posicion {@code index}
     */
    public NodoInstancia getByIndex(int index) {
        NodoInstancia resultado = null;
        NodoInstancia puntero = this.first;
        int count = 1;

        if (index > 0) {
            while (puntero != null) {
                if (count == index) {
                    resultado = puntero;
                    break;                 
                }
                puntero = puntero.getNext();
                count++;
            }
        }

        return resultado;
    }
}
