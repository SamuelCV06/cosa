package tp2.instancias;

/**
 * La clase {@code Instancia} representa un registro individual perteneciente
 * a una linea del conjunto de datos utilizado para el analisis del Titanic.
 * Cada objeto de esta clase contiene los atributos relevantes de un pasajero,
 * tales como si sobrevivio, su sexo, clase social y la cantidad de familiares a bordo.
 * 
 * <p><b>Atributos incluidos:</b></p>
 * <ul>
 *      <li>{@code passID}: Indica el ID del pasajero.</li>
 *      <li>{@code sobrevivio}: Indica si el pasajero sobrevivio (0 o 1).</li>
 *      <li>{@code sex}: Puede ser "male" o "female".</li>
 *      <li>{@code age}: Edad del pasajero (≥ 0).</li>
 *      <li>{@code pclass}: Clase social del pasajero (1, 2 o 3).</li>
 *      <li>{@code sibsp}: (Siblings / Spouse) Numero de hermanos o pareja a bordo (≥ 0).</li>
 *      <li>{@code parch}: (Parents / Children) Numero de padres o hijos a bordo (≥ 0).</li>
 * </ul>
 *
 * <p>Cada atributo cuenta con su respectivo verificador en los metodos 
 * {@code set}, los cuales lanzan excepciones {@link IllegalArgumentException} 
 * en caso de recibir valores fuera del dominio valido, por si llegaran a
 * existir datos mal formateados.</p>
 */
public class Instancia {
    private int passID;
    private int sobrevivio;
    private String sex;
    private int age;
    private int pclass;
    private int sibsp;
    private int parch;

    /**
     * Crea una nueva instancia del registro con los datos proporcionados.
     * El constructor utiliza los métodos {@code set} correspondientes 
     * para validar y asignar cada atributo.
     *
     * @param passID ID de pasajero segun la base de datos.
     * @param sobrevivio Valor 0 o 1 que indica si la persona sobrevivió.
     * @param sex Sexo de la persona ("male" o "female").
     * @param age Edad de la persona.
     * @param pclass Clase del pasajero (1, 2 o 3).
     * @param sibsp Cantidad de hermanos/pareja a bordo.
     * @param parch Cantidad de padres/hijos a bordo.
     * @throws IllegalArgumentException si alguno de los valores es inválido 
     *         según las restricciones definidas en los métodos {@code set}.
     */
    public Instancia(int passID, int sobrevivio, String sex, int age, int pclass, int sibsp, int parch) {
        this.passID = passID;
        this.setSobrevivio(sobrevivio);
        this.setSex(sex);
        this.setAge(age);
        this.setPclass(pclass);
        this.setSibsp(sibsp);
        this.setParch(parch);
    }


    public Instancia() {

    }

    /*
     * GETTERS
     */
    public int getID() {
        return this.passID;
    }
    public int getSobrevivio() {
        return this.sobrevivio;
    }

    public String getSex() {
        return this.sex;
    }

    public int getPclass() {
        return this.pclass;
    }

    public int getSibsp() {
        return this.sibsp;
    }
    
    public int getParch() {
        return this.parch;
    }

    public int getAge()
    {
        return this.age;
    }
    
    /*
     * SETTERS
     */

    /**
     * Establece el valor del atributo {@code sobrevivio} de la clase {@code Instancia}
     * Este atributo solo puede tener como valores 0 o 1.
     * 
     * @param s Nuevo valor para el atributo
     * @throws IllegalArgumentException Si el nuevo valor es diferente a 0 o 1.
     */
    public void setSobrevivio(int s) {
        if (s == 0 || s == 1) { // Verificador
            this.sobrevivio = s;
        }
        else {
            throw new IllegalArgumentException("Valor invalido para \"sobrevivio\". Debe ser 0 o 1.");
        }
    }

    /**
     * Establece el valor del atributo {@code sex} de la clase {@code Instancia}
     * Este atributo solo puede tener como valores "masculino" o "femenino".
     * 
     * @param sex Nuevo valor para el atributo.
     * @throws IllegalArgumentException Si el nuevo valor es diferente a "male" o "female".
     */
    public void setSex(String sex) {
        if (sex.equals("male") || sex.equals("female")) {
            this.sex = sex;
        }
        else {
            throw new IllegalArgumentException("Valor invalido para \"sex\". Debe ser \"male\" o \"female\".");
        }
    }

    /**
     * Establece el valor del atributo {@code age} de la clase {@code Instancia}
     * Este atributo no admite valores negativos.
     * 
     * @param newAge Nuevo valor para el atributo.
     * @throws IllegalArgumentException Si el nuevo valor es diferente a "male" o "female".
     */
    public void setAge(int newAge) {
        if (newAge >= 0) {
            this.age = newAge;
        }
        else {
            throw new IllegalArgumentException("Valor invalido para \"age\". No puede ser un valor negativo.");
        }
    }

    /**
     * Establece el valor del atributo {@code pclass} de la clase {@code Instancia}
     * Este atributo solo puede admitir valores en el rango inclusivo de 1 a 3.
     * 
     * @param newPclass Nuevo valor para el atributo.
     * @throws IllegalArgumentException Si el nuevo valor no pertenece al rango de 1 a 3.
     */
    public void setPclass(int newPclass) {
        if (newPclass > 0 && newPclass <= 3) {
            this.pclass = newPclass;
        }
        else {
            throw new IllegalArgumentException("Valor invalido para \"pclass\". Debe ser un valor entre 1 y 3");
        }
    }

    /**
     * Establece el valor del atributo {@code sibsp} de la clase {@code Instancia}
     * Este atributo no admite valores negativos.
     * 
     * @param sibsp Nuevo valor para el atributo.
     * @throws IllegalArgumentException Si el nuevo valor es negativo.
     */
    public void setSibsp(int newSibsp) {
        if (newSibsp >= 0) {
            this.sibsp = newSibsp;
        }
        else {
            throw new IllegalArgumentException("Valor invalido para \"sibsp\". No puede ser un valor negativo.");
        }
    }

    /**
     * Establece el valor del atributo {@code parch} de la clase {@code Instancia}
     * Este atributo no admite valores negativos.
     * 
     * @param newParch Nuevo valor para el atributo.
     * @throws IllegalArgumentException Si el nuevo valor es negativo.
     */
    public void setParch(int newParch) {
        if (newParch >= 0) {
            this.parch = newParch;
        }
        else {
            throw new IllegalArgumentException("Valor invalido para \"parch\". No puede ser un valor negativo.");
        }
    }

    /**
     * Devuelve una representación en texto del objeto {@code Instancia},
     * mostrando los valores de todos sus atributos en un formato legible.
     *
     * @return Una cadena que resume la información del pasajero, incluyendo su ID,
     * si sobrevivio, su sexo, clase social y la cantidad de familiares a bordo.
     */
    @Override
    public String toString() {
        return "DATOS DEL PASAJERO" +
                "\nID: "+ this.passID +
                "\nSobrevivio: " + this.sobrevivio +
                "\nSexo: " + this.sex +
                "\nEdad: " + this.age +
                "\nClase: " + this.pclass +
                "\nHermanos o Pareja: " + this.sibsp +
                "\nPadres o Hijos: " + this.parch;
    }
}
