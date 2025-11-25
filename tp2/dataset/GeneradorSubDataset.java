package tp2.dataset;
import tp2.instancias.*;
import java.util.Random;

public class GeneradorSubDataset {

    private Random random;

    public GeneradorSubDataset() {
        this.random = new Random();
    }

    public Dataset generar(Dataset origen, int tamano) {

        int total = origen.getSize();
        Dataset nuevo = new Dataset();

        for (int i = 0; i < tamano; i++) {

            int idx = random.nextInt(total);

            NodoInstancia nodo = origen.getByIndex(idx + 1); 

            Instancia inst = nodo.getInstancia();

            nuevo.add(inst);
        }

        Dataset resultado = nuevo;
        return resultado;
    }
}
