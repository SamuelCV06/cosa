package tp2.arbol;

import tp2.dataset.*;
import tp2.instancias.*;

public class ArbolBruto {
    public final int profundidadMax;    // Máxima profundidad permitida (5 pregutas)
    public NodoArbol raiz;                  // Raíz del árbol
    public Dataset subdataset;              // Mini dataset usado para entrenamiento del árbol

    // Constructor básico
    public ArbolBruto(Dataset subdataset, int profundidad) {
        this.subdataset = subdataset;
        this.raiz = null;

        // Limitar a [1, 5]
        if (profundidad < 1) {
            this.profundidadMax = 1;
        } else if (profundidad > 5) {
            this.profundidadMax = 5;
        } else {
            this.profundidadMax = profundidad;
        }
    }

    // Método principal para construir un árbol completo random
    public void construirArbol() {
        boolean[] usados = new boolean[5]; // 5 atributos (0..4)
        this.raiz = construirNodo(0, usados); // Construye a la raiz y empieza la recursion.
    }

    // Backtracking: construir nodo aleatorio
    private NodoArbol construirNodo(int profundidad, boolean[] usados) {

        // Si ya se hicieron "n" preguntas, crear una hoja aleatoria.
        if (profundidad >= profundidadMax) {

            // Valor final basado en el subdataset (brute force)
            int resultado = sacarPrediccionAleatoria();
            return new NodoArbol(resultado);
        }

        // Elegir un atributo NO usado aún
        int atributoElegido = elegirAtributo(usados);

        // Marcarlo usado
        usados[atributoElegido] = true;

        // Crear umbral
        NodoArbol nodo;

        if (atributoElegido == 4) {
            // SEX: umbral string
            String th = elegirUmbralSex();
            nodo = new NodoArbol(atributoElegido, th);

        } else {
            // Resto: umbral numérico
            int th = elegirUmbralNumerico(atributoElegido);
            nodo = new NodoArbol(atributoElegido, th);
        }

        // Crear hijos con backtracking
        nodo.hijoIzquierdo = construirNodo(profundidad + 1, usados);
        nodo.hijoDerecho  = construirNodo(profundidad + 1, usados);

        // Desmarcar atributo (volver atrás para ramas paralelas)
        usados[atributoElegido] = false;

        return nodo;
    }

    // Elegir atributo no repetido
    private int elegirAtributo(boolean[] usados) {

        while (true) {
            int a = (int)(Math.random() * 5); // 0..4
            if (!usados[a]) {
                return a;
            }
        }
    }

    // Umbral numérico simple y aleatorio
    private int elegirUmbralNumerico(int atributo) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Buscar min/max en el subdataset
        NodoInstancia puntero = subdataset.getFirst();

        while (puntero != null) {

            int valor;

            if (atributo == 0) {
                valor = puntero.getInstancia().getAge();
            }
            else if (atributo == 1) {
                valor = puntero.getInstancia().getPclass();
            }
            else if (atributo == 2) {
                valor = puntero.getInstancia().getSibsp();
            }  
            else {
                valor = puntero.getInstancia().getParch();
            }
            if (valor < min) min = valor;
            if (valor > max) max = valor;

            puntero = puntero.getNext();
        }

        // threshold aleatorio entre min y max
        return min + (int)(Math.random() * (max - min + 1));
    }

    // Umbral sex aleatorio
    private String elegirUmbralSex() {
        String umbral;
        if (Math.random() < 0.5) {
            umbral = "female";
        }
        else {
            umbral = "male";
        }
        return umbral;
    }

    private int sacarPrediccionAleatoria() {
        return Math.random() < 0.5 ? 0 : 1;
    }

    // Predicción final
    public int predecir(Instancia inst) {
        return raiz.predecir(inst);
    }

    public NodoArbol getRaiz() {
        return this.raiz;
    }
}