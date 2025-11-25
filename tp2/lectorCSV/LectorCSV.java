package tp2.lectorCSV;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import tp2.dataset.Dataset;
import tp2.instancias.Instancia;

/**
 * Clase encargada de leer archivos CSV y convertir cada línea válida
 * en un objeto {@code Instancia}. Su función principal es llenar los 
 * datasets de entrenamiento y evaluación segun una distribución 80/20.
 * 
 * Esta clase no mantiene estado interno; únicamente proporciona métodos
 * utilitarios para la lectura y procesamiento del archivo CSV.
 */
public class LectorCSV {

    public LectorCSV() {
    }

    /**
     * Lee un archivo CSV línea por línea y distribuye sus instancias en dos 
     * objetos {@code Dataset}: uno de entrenamiento y otro de evaluación.
     * 
     * El archivo debe tener formato CSV separado por punto y coma. Se asume que
     * la primera línea es un encabezado y será ignorada.
     * 
     * Por cada línea (a partir de la segunda), se crea un objeto {@code Instancia}
     * mediante el método {@code crearInstancia(String)}. Luego, cada instancia es
     * asignada aleatoriamente a uno de los dos datasets:
     * <ul>
     *      <li>80% de probabilidad: {@code entrenamiento}</li>
     *      <li>20% de probabilidad: {@code evaluacion}</li>
     * </ul>
     * 
     * El método maneja errores comunes de lectura de archivos, tales como:
     * <ul>
     *      <li>Archivo no encontrado</li>
     *      <li>Errores de entrada/salida al leer el archivo</li>
     * </ul>
     * 
     * @param fileName Ruta/Nombre al archivo CSV que se desea leer.
     * @param entrenamiento {@code Dataset} donde se almacenarán aproximadamente
     *        el 80% de las instancias.
     * @param evaluacion {@code Dataset} donde se almacenará el 20% restante.
     * 
     * @throws NullPointerException si alguno de los datasets proporcionados es nulo.
     */
    public void readFile(String fileName, Dataset entrenamiento, Dataset evaluacion) {

        BufferedReader br = null;

        try {
            
            String line;
            br = new BufferedReader(new FileReader(fileName));
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null)  {

                // Se salta la primera linea
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                else {   
                    Instancia ins = this.crearInstancia(line);

                    double prob = Math.random();

                    // Distribuye la instancia segun la probabilidad
                    if (prob < 0.8) {
                        entrenamiento.add(ins);
                    }
                    else {
                        evaluacion.add(ins);
                    }
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());            
        } 
        catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } 
        finally {
            // Cerrar el BufferedReader si se abrió
            if (br != null) {
                try {
                    br.close();
                } 
                catch (IOException e) {
                    System.out.println("Error al cerrar el archivo: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Crea una nueva instancia de la clase {@code Instancia} a partir de una línea
     * de texto con formato CSV separado por punto y coma.
     * 
     * El formato esperado de la línea es:
     * ID;Sobrevivio;Pclass;Sex;Age;SibSp;Parch;...
     * 
     * De este formato, el método extrae únicamente los siguientes campos:
     * <ul>
     *      <li>parts[0]: ID del pasajero (passID)</li>
     *      <li>parts[1]: Indicador de supervivencia (0 o 1)</li>
     *      <li>parts[2]: Clase del pasajero (1, 2 o 3)</li>
     *      <li>parts[3]: Sexo del pasajero ("male" o "female")</li>
     *      <li>parts[4]: Edad del pasajero</li>
     *      <li>parts[5]: Número de hermanos/pareja a bordo (sibsp)</li>
     *      <li>parts[6]: Número de padres/hijos a bordo (parch)</li>
     * </ul>
     * 
     * @param line Línea completa del archivo CSV original.
     * @return Una nueva instancia de {@code Instancia} con los campos relevantes
     *         extraídos del CSV.
     * @throws NumberFormatException Si alguno de los atributos numericos no es válido.
     * @throws ArrayIndexOutOfBoundsException Si la linea no contiene suficientes campos.
     */
    private Instancia crearInstancia(String line) {
        String[] parts = line.split(";");

        int passID = Integer.parseInt(parts[0]);
        int sobrevivio = Integer.parseInt(parts[1]);
        int pclass = Integer.parseInt(parts[2]);
        String sex = parts[3].trim().toLowerCase();
        int age = Integer.parseInt(parts[4]);
        int sibsp = Integer.parseInt(parts[5]);
        int parch = Integer.parseInt(parts[6]);

        Instancia nueva = new Instancia(passID, sobrevivio, sex, age, pclass, sibsp, parch);
        return nueva;
    }
}