/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import Event.BasicEvent;
import Map.CoordinateList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Carlos
 */
public class CSVReader 
{
    private List<BasicEvent> puntos;
    
    @Autowired
    public static  EPLUtils epl = new EPLUtils();
    
    public static CoordinateList coordenadasList = new CoordinateList();
    
    
    public void startSendingCoodinates() {


                epl.afterPropertiesSet();
                for (BasicEvent event : puntos) {
                    epl.handle(event);
                }           
    }
    
    public CSVReader(String archivo) 
    {
        puntos = new ArrayList<BasicEvent>();
        reedRutine(archivo);
        changeOrder();
    }

    public List<BasicEvent> getPuntos() 
    {
        return puntos;
    }
    
    //lectura del csv
    private void reedRutine(String archivo)
    {
        BufferedReader br = null;
        try 
        {
            //abrir fichero
            br = new BufferedReader(new FileReader("files/"+archivo));
            br.readLine(); //la primera linea no se lee porque contiene el nombre de las columnas
            String line = br.readLine();
            String [] fields;
            float longitud;
            float latitud;
            float velocidad;
            String dia;  
            BasicEvent p;
            //lectura del fichero
            while( line != null )
            {
                fields = line.split(";"); //las columnas estan separadas por ";"
                latitud = Float.parseFloat(fields[0]);
                longitud = Float.parseFloat(fields[1]);
                velocidad= Float.parseFloat(fields[2]);
                dia = fields[3];
                //creamos un nuevo punto y lo guardamos en una lista
                p = new BasicEvent(longitud,latitud,velocidad,dia);
                puntos.add(p);
                //siguiente linea;
                line = br.readLine();
            }
            //se cierra el fichero
            br.close();
            //put the flags
            puntos.get(0).setFlag("fin");
            puntos.get(puntos.size()-1).setFlag("inicio");
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("El fichero \""+archivo+"\"sa no existe");
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
    public void imprimir()
    {
        int i = 0;
        for( BasicEvent p: puntos )
        {
            i++;
            System.out.println( i + p.toString() );
        }
    }
    
    //imprime la diferencia de tiempo (en segundo) entre dos puntos
    public void difTime()
    {
        int i = 0;
        String s;
        BasicEvent p = null;
        for( BasicEvent d: puntos )
        {
            if(i==0){
                i++;
                p = d;
            }else{               
                i++;
                System.out.println("Nuemro "+i+"--> segundos entre "+(-p.getTime().getTime().getTime()+d.getTime().getTime().getTime())/1000);   
                p = d;
            }
            
        }
    }
    
    //imprime los datos en formato CEP
    public void generateDataCEP()
    {
        int i = 0;
        String s;
        long time;
        BasicEvent p = null;
        for( BasicEvent d: puntos ){
            if(i==0){
                i++;
                p = d;
                System.out.println(d.CEPString()+"\n");
            }else{
                time = (-p.getTime().getTime().getTime()+d.getTime().getTime().getTime())/1000;
                System.out.println("t = t.plus("+time+" seconds)");
                System.out.println(d.CEPString()+"\n");
                p = d;
                i++;
            }
        }
    }

    private void changeOrder() 
    {   
        List<BasicEvent> help = new ArrayList<BasicEvent>();
        help.addAll(puntos);
        for(int i=0; i<puntos.size(); i++){
            puntos.set(i, help.get(puntos.size()-i-1));
        }
    }

}