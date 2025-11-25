[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/mdEBeduM)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=21492952&assignment_repo_type=AssignmentRepo)
# Proyecto: Árboles de decisión y bosque aleatorio o algo así.

## Autores:
Samuel Chinchilla Vásquez
Joshua Rayo Arcia
Juan Pablo Quiros Espinoza

## Descripción
Este programa implementa desde cero un clasificador basado en Bosque Aleatorio (Random Forest) para predecir si un pasajero del Titanic sobreviviría o no, utilizando un archivo CSV con información real del dataset Titanic.

El usuario proporciona:
El archivo CSV desde la línea de comandos, el número de árboles que desea en el bosque y la profundidad máxima de cada árbol.

Luego el programa:
Carga y divide el dataset en entrenamiento (80%) y evaluación (20%), Construye un bosque aleatorio, donde cada árbol:
Se construye por fuerza bruta y backtracking utilizando preguntas basadas en atributos del dataset (sexo, clase, familiares, etc.).

Permite interactuar con el bosque mediante un menú como
Mostrar un árbol específico.
Clasificar una nueva instancia ingresada por el usuario.
Ejecutar la evaluación completa y generar el archivo clasificacion.txt.

Entrada del programa

El programa se ejecuta desde consola así:
<pre><code>
java -jar proyecto.jar archivo.csv
</code></pre>
El archivo CSV debe traer los datos en para los dataSet;

¿Cómo se organizan los datos?

El programa:
Lee el archivo línea por línea usando LectorCSV.
Cada línea se convierte en una instancia del objeto Instancia.
Cada instancia se asigna a:
Dataset de entrenamiento con probabilidad del 80%
Dataset de evaluación con probabilidad del 20%
Esto es automático: el usuario no debe particionar nada.

Construcción del Árbol de Decisión

Cada árbol se arma usando backtracking y fuerza bruta:
Tomando de forma aleatoria preguntas como:
¿El sexo es femenino?
¿La clase es ≤ cierto valor?
¿Tiene más de X familiares?
Etc.
Para integrarlas en las raices

En cada nodo, genera subárboles hasta alcanzar:
una profundidad máxima p (definida por el usuario y la cual no debe ser mayor a 5) 
Los nodos finales o hoja contienen una clasificación final: 0 (no sobrevivió) o 1 (sí sobrevivió).

## Instalación:
    
    1. Asegúrate de tener la última versión de java instalada

    2. Clona el repositorio
    Desde la consola de tu preferencia y desde la 
    ubicación deseada clona el repositorio
    ``` bash
    git clone https://github.com/CR-UCR-ECCI/tarea-bst-j-j-s.git
    ``` 
    3. Utiliza los comandos desde principal como se muestra acontinuacion.
