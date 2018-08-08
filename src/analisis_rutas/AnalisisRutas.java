/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis_rutas;

import com.espertech.esper.client.*;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class AnalisisRutas 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Intruduzca el nombre del archivo dentro de la carpeta files: \n");
        String entradaTeclado = "campsa.csv";
        //Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        //entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        Trazo t = new Trazo(entradaTeclado); //ejemplo: campsa.scv
        //t.imprimir();
        //t.difTime();
        //t.generateDataCEP();
        
        EPLUtils epl = new EPLUtils();
        epl.ReadData(t.puntos);
    }
    
}
