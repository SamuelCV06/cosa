package tp2.dataset;
import tp2.instancias.*;

public class Dataset {
    private ListaInstancia lista;

    public Dataset() {
        this.lista = new ListaInstancia();
    }

    public void add(Instancia ins) {
        this.lista.add(ins);
    }

    public ListaInstancia getLista() {
        return this.lista;
    }

    public NodoInstancia getFirst() {
        return this.lista.getFirst();
    }

    public NodoInstancia getByIndex(int index) {
        return this.lista.getByIndex(index);
    }

    /**
     * Imprime todo el Dataset
     */
    public void print() {
        this.lista.print();
    }

    /**
     * Imprime el Dataset hasta un limite definido
     * @param bound Limite
     */
    public void printUpTo(int bound) {
        this.lista.printUpTo(bound);
    }

    public void setLista(ListaInstancia lista)
    {
        this.lista=lista;
    }

    /**
     * @return Tamanho del Dataset
     */
    public int getSize() {
        return this.lista.getSize();
    }
}
