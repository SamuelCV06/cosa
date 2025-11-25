package tp2.io;

import java.util.Scanner;
import tp2.instancias.Instancia;
import tp2.lectorCSV.LectorCSV;
import tp2.PrintarArbol;
import tp2.arbol.*;
import tp2.bosque.*;
import tp2.dataset.Dataset;

public class EntradaDatos {
    private Scanner respuesta;
    private int cantidadArboles;
    private int nivelArboles;
    private LectorCSV lector;
    private Dataset train;
    private Dataset eval;
    private BosqueAleatorio bosque;
    private EscritorDatos esc;

    public EntradaDatos(String archivo) {
        this.respuesta = new Scanner(System.in);
        this.cantidadArboles = 0;
        this.nivelArboles = 0;
        this.lector = new LectorCSV();
        this.train = new Dataset();
        this.eval = new Dataset();
        this.lector.readFile(archivo, train, eval);
        this.esc = new EscritorDatos();
    }

    public void UsarPrograma() {
        boolean funcionaElBucle = true;
        int iGersimo;
        System.out.println("Bienvenido viajero, debe decidir que pasajeros salvar usando el Random Forest del futuro.");

        try {
            // Pedir cantidad de árboles
            while (this.cantidadArboles <= 0) {
                System.out.println("Ingrese la cantidad de arboles del Random Forest:");
                this.cantidadArboles = Integer.parseInt(this.respuesta.nextLine());
                if (this.cantidadArboles <= 0) {
                    System.out.println("Valor invalido.");
                }
            }

            // Pedir nivel de árboles
            while (this.nivelArboles <= 0 || this.nivelArboles > 5) {
                System.out.println("Ingrese el nivel de los arboles (1-5):");
                this.nivelArboles = Integer.parseInt(this.respuesta.nextLine());
                if (this.nivelArboles <= 0 || this.nivelArboles > 5) {
                    System.out.println("Valor invalido.");
                }
            }

            // Crear bosque
            this.bosque = new BosqueAleatorio(train, this.cantidadArboles, this.nivelArboles);
            System.out.println("Bosque aleatorio creado correctamente con " + this.cantidadArboles + " arboles de nivel " + this.nivelArboles + ".");

            // Bucle principal de interacción
            while (funcionaElBucle) {
                System.out.print(
                        "Seleccione accion:\n" +
                        "A. Mostrar i-esimo árbol\n" +
                        "B. Clasificar nueva instancia\n" +
                        "C. Ejecutar evaluacion\n" +
                        "D. Cerrar programa\n-"
                );
                String opcionUsuario = this.respuesta.nextLine().trim().toUpperCase();

                switch (opcionUsuario) {
                    case "A":
                        System.out.println("Digite el numero del arbol (1-" + this.cantidadArboles + "):");
                        iGersimo = Integer.parseInt(this.respuesta.nextLine());
                        if (iGersimo <= 0 || iGersimo > this.cantidadArboles) {
                            System.out.println("Número de arbol invalido.");
                            break;
                        }
                        ArbolBruto arbolImprimir = this.bosque.getByIndex(iGersimo).getArbol();
                        if (arbolImprimir != null) {
                            new PrintarArbol(arbolImprimir, iGersimo);
                            this.bosque.mostrarArbol(iGersimo);
                        }
                        break;

                    case "B":
                        Instancia instanciaUsuario = new Instancia(); // Inicializar correctamente
                        System.out.println("Ingrese datos de la instancia:");
                        System.out.print("Edad: ");
                        instanciaUsuario.setAge(Integer.parseInt(this.respuesta.nextLine()));
                        System.out.print("Sexo: ");
                        instanciaUsuario.setSex(this.respuesta.nextLine());
                        System.out.print("Clase del pasajero: ");
                        instanciaUsuario.setPclass(Integer.parseInt(this.respuesta.nextLine()));
                        System.out.print("Número de hermanos/parejas a bordo: ");
                        instanciaUsuario.setSibsp(Integer.parseInt(this.respuesta.nextLine()));
                        System.out.print("Número de padres/hijos a bordo: ");
                        instanciaUsuario.setParch(Integer.parseInt(this.respuesta.nextLine()));
                        System.out.print("¿Sobrevivio? (1 = sí, 0 = no): ");
                        instanciaUsuario.setSobrevivio(Integer.parseInt(this.respuesta.nextLine()));

                        this.bosque.clasificarInstanciaInteractiva(instanciaUsuario);
                        break;

                    case "C":
                        esc.ejecutarDataset(bosque, eval, cantidadArboles);
                        break;

                    case "D":
                        System.out.println("¡Adios!");
                        funcionaElBucle = false;
                        break;

                    default:
                        System.out.println("Opción invalida, intente de nuevo.");
                        break;
                }
            }

        }
        catch (Exception e) {
            System.out.println("Formato de entrada invalido. Reinicie el programa.");
        }
        finally {
            this.respuesta.close();
        }
    }
}
   
