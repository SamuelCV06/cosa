package tp2.arbol;
import tp2.instancias.*;

/**
 * Representa un nodo dentro del árbol de decisión generado por fuerza bruta.
 * El nodo puede ser:
 * - Un nodo interno (hace una pregunta de atributo)
 * - Una hoja (devuelve 0 o 1 como predicción final)
 */
public class NodoArbol {
    // Tipo de atributo que el nodo pregunta
    // 0 = AGE
    // 1 = PCLASS
    // 2 = SIBSP
    // 3 = PARCH
    // 4 = SEX  (usa compareTo)
    public int atributo;

    public double umbralNumerico;           // Threshold numérico (solo se usa para atributos 0–3)
    public String umbralSex;                // Umbral para SEX (string)
    public NodoArbol hijoIzquierdo;
    public NodoArbol hijoDerecho;

    // Si es hoja:
    //   -1 significa "no es hoja"
    //    0 o 1 significa la predicción final
    public int valorHoja;


    // Constructor de nodo interno numérico
    public NodoArbol(int atributo, double umbralNumerico) {
        this.atributo = atributo;
        this.umbralNumerico = umbralNumerico;
        this.umbralSex = null;     // no aplica
        this.valorHoja = -1;       // no es hoja
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }


    // Constructor de nodo interno para SEX
    public NodoArbol(int atributo, String umbralSex) {
        this.atributo = atributo;
        this.umbralNumerico = -1;  // no aplica
        this.umbralSex = umbralSex;
        this.valorHoja = -1;       // no es hoja
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }


    // Constructor de hoja (predicción final)
    public NodoArbol(int valorHoja) {
        this.valorHoja = valorHoja; // 0 o 1
        this.atributo = -1;         // no aplica
        this.umbralNumerico = -1;
        this.umbralSex = null;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public int predecir(Instancia inst) {
        int resultado = 0;

        // Caso hoja
        if (valorHoja != -1) {
            resultado = valorHoja;
        }

        // Nodo numérico
        else if (atributo >= 0 && atributo <= 3) {
            double valor;

            switch (atributo) {
                case 0:
                    valor = inst.getAge();
                    break;

                case 1:
                    valor = inst.getPclass();
                    break;

                case 2:
                    valor = inst.getSibsp();
                    break;

                default:
                    valor = inst.getParch();
                    break;
            }
            //Aqui decide donde sigue la recursion
            if (valor < umbralNumerico) {
                resultado = hijoIzquierdo.predecir(inst);
            } else {
                resultado = hijoDerecho.predecir(inst);
            }
        }

        // Nodo sexo
        else if (atributo == 4) {

            String sexInst = inst.getSex();

            if (sexInst != null && sexInst.equals(umbralSex)) {
                resultado = hijoIzquierdo.predecir(inst);
            } else {
                resultado = hijoDerecho.predecir(inst);
            }
        }

        return resultado;
    }

    public NodoArbol getIzq() {
        return this.hijoIzquierdo;
    }

    public NodoArbol getDer() {
        return this.hijoDerecho;
    }

    public int getValorHoja() {
        return this.valorHoja;
    }
}