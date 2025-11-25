package tp2.bosque;

import tp2.arbol.*;
import tp2.dataset.*;
import tp2.instancias.*;
import tp2.io.*;

public class BosqueAleatorio {
    private NodoBosque first;
    private NodoBosque last;
    private int cantidadArboles;
    private Dataset entrenamiento;
    private int[] evaluacion;

    public BosqueAleatorio(Dataset entrenamiento, int cantidadArboles, int profundidad) {
        this.first = null;
        this.last = null;
        this.entrenamiento = entrenamiento;
        this.cantidadArboles = cantidadArboles;
        this.evaluacion = new int[4];
        entrenarBosque(profundidad);
    }
    
    private void entrenarBosque(int profundidad) {
        GeneradorSubDataset gen = new GeneradorSubDataset();

        for (int i = 0; i < cantidadArboles; i++) {

            Dataset mini = gen.generar(entrenamiento, 5); 
            //Tamaño fijo de 5 instancias
            double acc = -1.0;
            while (acc < 1.0) {    // En este ciclo se genera un arbol por fuerza bruta
                ArbolBruto arbol = new ArbolBruto(mini, profundidad);
                arbol.construirArbol();

                acc = calcularAccuracy(arbol, mini);
                if (acc == 1.0) {
                    this.add(arbol);
                    break;
                }
            } 
        }
    }

    public void add(ArbolBruto arbol) {
        NodoBosque nuevo = new NodoBosque(arbol);
        // Si la lista se encuentra vacia.
        if (this.first == null) {
            this.first = nuevo;
            this.last = nuevo;
        }
        else {
            this.last.setNext(nuevo);
            this.last = nuevo;
        }
    }

    private double calcularAccuracy(ArbolBruto arbol, Dataset sub) {

        int correctos = 0;
        NodoInstancia p = sub.getFirst();

        while (p != null) {
            int pred = arbol.predecir(p.getInstancia());
            int real = p.getInstancia().getSobrevivio();

            if (pred == real) {
                correctos++;
            }
            p = p.getNext();
        }

        double resultado = correctos / (double) sub.getSize();
        return resultado;
    }

    public int predecirBosque(Instancia inst) {

        NodoBosque p = this.first;
        int votos1 = 0;
        int votos0 = 0;

        while (p != null) {
            int voto = p.getArbol().predecir(inst);

            if (voto == 1) {
                votos1++;
            } else {
                votos0++;
            }

            p = p.getNext();
        }

        int resultado;

        if (votos1 > votos0) {
            resultado = 1;
        } else if (votos0 > votos1) {
            resultado = 0;
        } else {
            // Empate aleatorio
            resultado = (Math.random() < 0.5) ? 1 : 0;
        }

        return resultado;
    }

    public int[] evaluarBosque(Dataset evaluacion) 
    {

        int TP = 0;
        int TN = 0;
        int FP = 0;
        int FN = 0;

        NodoInstancia p = evaluacion.getFirst();

        while (p != null) {

            int real = p.getInstancia().getSobrevivio();
            int pred = predecirBosque(p.getInstancia());

            if (pred == 1 && real == 1) {
                TP++;
            } 
            else if (pred == 0 && real == 0) {
                TN++;
            } 
            else if (pred == 1 && real == 0) {
                FP++;
            } 
            else if (pred == 0 && real == 1) {
                FN++;
            }

            p = p.getNext();
        }
        this.evaluacion[0] = TP;
        this.evaluacion[1] = TN;
        this.evaluacion[2] = FP;
        this.evaluacion[3] = FN;
        return this.evaluacion;
    }

    public void evaluarBosqueDebug(Dataset evaluacion) 
    {

        int TP = 0;
        int TN = 0;
        int FP = 0;
        int FN = 0;

        NodoInstancia p = evaluacion.getFirst();

        while (p != null) {

            int real = p.getInstancia().getSobrevivio();
            int pred = predecirBosque(p.getInstancia());

            if (pred == 1 && real == 1) {
                TP++;
            } 
            else if (pred == 0 && real == 0) {
                TN++;
            } 
            else if (pred == 1 && real == 0) {
                FP++;
            } 
            else if (pred == 0 && real == 1) {
                FN++;
            }

            p = p.getNext();
        }

        imprimirMetricas(TP, TN, FP, FN);
    }

    private void imprimirMetricas(int TP, int TN, int FP, int FN) {

        int total = TP + TN + FP + FN;

        double accuracy = (TP + TN) / (double) total;

        System.out.println("===== METRICAS DEL RANDOM FOREST =====");
        System.out.println("TP (Verdaderos Positivos): " + TP);
        System.out.println("TN (Verdaderos Negativos): " + TN);
        System.out.println("FP (Falsos Positivos)    : " + FP);
        System.out.println("FN (Falsos Negativos)    : " + FN);
        System.out.println("--------------------------------------");
        System.out.println("Accuracy                 : " + accuracy);
    }
    public NodoBosque getByIndex(int index) {
        NodoBosque resultado = null;
        NodoBosque puntero = this.first;
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

    public void clasificarInstanciaInteractiva(Instancia inst) {

        NodoBosque p = this.first;
        int votos1 = 0;
        int votos0 = 0;
        int numArbol = 1;

        System.out.println("===== CLASIFICACIÓN DE INSTANCIA NUEVA =====");

        // Evaluar con cada árbol
        while (p != null) {
            int voto = p.getArbol().predecir(inst);

            System.out.println("Árbol " + numArbol + ": predicción = " + voto);

            if (voto == 1) {
                votos1++;
            }
            else {
                votos0++;
            }
            numArbol++;
            p = p.getNext();
        }

        System.out.println("-------------------------------------------");
        System.out.println("Votos por 1 (sobrevivió): " + votos1);
        System.out.println("Votos por 0 (no sobrevivió): " + votos0);

        int resultado;

        if (votos1 > votos0){
            resultado = 1;
        }
        else if (votos0 > votos1) {
            resultado = 0;
        }
        else { 
            resultado = (Math.random() < 0.5) ? 1 : 0; // Empate
        }
        System.out.println("RESULTADO FINAL DEL BOSQUE: " + resultado);
    }

    // Método para mostrar un árbol del bosque por índice
    public void mostrarArbol(int index) {
        NodoBosque nodoBosque = getByIndex(index);
        if (nodoBosque != null) {
            System.out.println("Mostrando Arbol #" + index);
            imprimirNodoASCII(nodoBosque.getArbol().getRaiz(), "", true);
        } 
        else {
            System.out.println("Árbol no encontrado");
        }
    }

    // Método recursivo que imprime el árbol en ASCII
    private void imprimirNodoASCII(NodoArbol nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.print(prefijo);
            System.out.print(esUltimo ? "└── " : "├── ");

            // Mostrar nodo o hoja
            if (nodo.getValorHoja() != -1) {
                System.out.println("Sobrevive (0 = No, 1 = Si): " + nodo.getValorHoja());
            } 
            else {
                String pregunta = "";
                switch (nodo.atributo) {
                    case 0:
                        pregunta = "Edad < " + nodo.umbralNumerico;
                        break;
                    case 1:
                        pregunta = "Pclass < " + nodo.umbralNumerico;
                        break;
                    case 2:
                        pregunta = "Sibsp < " + nodo.umbralNumerico;
                        break;
                    case 3:
                        pregunta = "Parch < " + nodo.umbralNumerico;
                        break;
                    case 4:
                        pregunta = "Sex = " + nodo.umbralSex;
                        break;
                }
                System.out.println(pregunta + "?");
            }

            // Preparar prefijo para los hijos
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");

            // Llamadas recursivas a los hijos
            imprimirNodoASCII(nodo.getIzq(), nuevoPrefijo, false);
            imprimirNodoASCII(nodo.getDer(), nuevoPrefijo, true);
        }
    }

    public NodoBosque getFirst() {
        return this.first;
    }

    public int getCantArboles() {
        return this.cantidadArboles;
    }
}
