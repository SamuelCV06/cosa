package tp2;

import tp2.bosque.BosqueAleatorio;
import tp2.dataset.Dataset;
import tp2.lectorCSV.LectorCSV;
import tp2.io.*;

public class Principal {
    public static void main(String [] args) 
    {
        if(args.length > 1)
        {
            System.out.println("Error de ingreso del documento");
        }
        else
        {
        String argumeto = args[0];
        EntradaDatos laEntradaDatos = new EntradaDatos(argumeto);//Aqui iria args 0
        laEntradaDatos.UsarPrograma();
        }
    }
}
