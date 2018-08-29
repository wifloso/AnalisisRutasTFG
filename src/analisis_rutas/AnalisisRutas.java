/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis_rutas;

import CSVReader.EPLUtils;
import CSVReader.Trazo;
import Event.PuntoEvent;
import com.espertech.esper.client.*;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos
 */
public class AnalisisRutas 
{

    /**
     * Creates simple random Temperature events and lets the implementation class handle them.
     */

    
    public static void main(String[] args) 
    {       
        System.out.println("Intruduzca el nombre del archivo dentro de la carpeta files");
        System.out.println("Si pulsa intro se leera la ruta predeterminada");
        System.out.println("...");
        String entradaTeclado = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        if(entradaTeclado.isEmpty()){
            System.out.println("Lectura de ruta predeterminada... \n");
            entradaTeclado = "campsa.csv";
        }else{
            System.out.println("Lectura de "+entradaTeclado+"... \n");
        }
        Trazo t = new Trazo(entradaTeclado); //ejemplo: campsa.scv
        //t.imprimir();
        //t.difTime();
        //t.generateDataCEP();       
        t.startSendingCoodinates();
    }
    
}
