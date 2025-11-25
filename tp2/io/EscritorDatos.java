package tp2.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import tp2.bosque.BosqueAleatorio;
import tp2.bosque.NodoBosque;
import tp2.dataset.Dataset;
import tp2.instancias.NodoInstancia;
import tp2.instancias.Instancia;

public class EscritorDatos {

    public EscritorDatos() {
        try {
            File elLog = new File("clasificacion.txt");
            if (elLog.createNewFile()) {
                System.out.println("El archivo ha sido creado exitosamente.");
            } else {
                System.out.println("El archivo ya existe, será reescrito a continuación.");
            }
        } catch (IOException e) {
            System.err.println("Error inesperado al crear el archivo.");
            e.printStackTrace();
        }
    }

    /**
     * Recorre el dataset de evaluación y genera el archivo clasificacion.txt
     * mostrando los votos de cada árbol y la predicción final del bosque.
     *
     * @param bosque BosqueAleatorio a usar
     * @param evaluacion Dataset de evaluación
     * @param cantidadArboles Cantidad de árboles en el bosque
     */
    public void ejecutarDataset(BosqueAleatorio bosque, Dataset evaluacion, int cantidadArboles) {
        try {
            FileWriter elEscritor = new FileWriter("clasificacion.txt");

            // Positivos y Negativos
            int[] resultados = bosque.evaluarBosque(evaluacion);
            elEscritor.write("TP (Verdaderos Positivos): " + resultados[0] + "\n");
            elEscritor.write("FP (Falsos Positivos): " + resultados[2] + "\n");
            elEscritor.write("TN (Verdaderos Negativos): " + resultados[1] + "\n");
            elEscritor.write("FN (Falsos Negativos): " + resultados[3] + "\n\n");
            

            // Cabecera de la tabla
            elEscritor.write("InstanciaID | VotosPorArbol | PredicciónFinalBosque | ResultadoReal \n");
            elEscritor.write("---------------------------------------------------------\n");

            NodoInstancia nodoInstancia = evaluacion.getFirst();

            while (nodoInstancia != null) {

                Instancia inst = nodoInstancia.getInstancia();
                NodoBosque nodoArbol = bosque.getFirst();
                StringBuilder votosPorArbol = new StringBuilder();

                int votos1 = 0;
                int votos0 = 0;

                int arbolIndex = 1;
                while (nodoArbol != null) {
                    int voto = nodoArbol.getArbol().predecir(inst);
                    votosPorArbol.append("A").append(arbolIndex).append(":").append(voto).append(" ");

                    if (voto == 1) votos1++;
                    else votos0++;

                    nodoArbol = nodoArbol.getNext();
                    arbolIndex++;
                }

                int predFinal = (votos1 >= votos0 ? 1 : 0);

                // Escribir línea por instancia
                elEscritor.write(inst.getID() + " | " + votosPorArbol.toString() + "| " + predFinal + " | " + inst.getSobrevivio() + "\n");

                nodoInstancia = nodoInstancia.getNext();
            }

            elEscritor.close();
            System.out.println("Archivo clasificacion.txt generado correctamente.");

        } catch (IOException e) {
            System.err.println("Error inesperado al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}