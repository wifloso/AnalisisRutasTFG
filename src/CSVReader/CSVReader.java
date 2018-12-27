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
    private List<BasicEvent> EventList;
    
    @Autowired
    public static  EPLUtils epl = new EPLUtils();
    
    public static CoordinateList CoordinatesList = new CoordinateList();
    
    
    public void startSendingCoodinates() {
        epl.afterPropertiesSet();
        for (BasicEvent event : EventList) {
            epl.handle(event);
        }           
    }
    
    public CSVReader(String file) 
    {
        EventList = new ArrayList<BasicEvent>();
        readFile(file);
        changeOrder();
    }

    public List<BasicEvent> getEventList() 
    {
        return EventList;
    }
    
    private void readFile(String archivo)
    {
        BufferedReader br = null;
        try 
        {
            //Open file
            br = new BufferedReader(new FileReader("files/"+archivo));
            br.readLine(); //Avoid first row with column names
            String row = br.readLine();
            String [] fields;
            float longitud;
            float latitud;
            float speed;
            String day;  
            BasicEvent event;
            //rows reading
            while( row != null )
            {
                fields = row.split(";"); //All rows are limited by ";"
                latitud = Float.parseFloat(fields[0]);
                longitud = Float.parseFloat(fields[1]);
                speed= Float.parseFloat(fields[2]);
                day = fields[3];
                //to save new event in the list
                event = new BasicEvent(longitud,latitud,speed,day);
                EventList.add(event);
                row = br.readLine();
            }
            //se cierra el fichero
            br.close();
            //put the flags
            EventList.get(0).setFlag("fin");
            EventList.get(EventList.size()-1).setFlag("inicio");
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
    
    
    public void print()
    {
        int i = 0;
        for( BasicEvent event: EventList )
        {
            i++;
            System.out.println( i + event.toString() );
        }
    }
    
    //print the time difference between events
    public void difTime()
    {
        int i = 0;
        BasicEvent event = null;
        for( BasicEvent d: EventList )
        {
            if(i==0){
                i++;
                event = d;
            }else{               
                i++;
                System.out.println("Nuemro "+i+"--> segundos entre "+(-event.getTime().getTime().getTime()+d.getTime().getTime().getTime())/1000);   
                event = d;
            }
            
        }
    }
    
    //PRINT EVENT FORMAT ESPER-ONLINE
    public void generateDataEsperOnline()
    {
        int i = 0;
        long time;
        BasicEvent event = null;
        for( BasicEvent d: EventList ){
            if(i==0){
                i++;
                event = d;
                System.out.println(d.CEPString()+"\n");
            }else{
                time = (-event.getTime().getTime().getTime()+d.getTime().getTime().getTime())/1000;
                System.out.println("t = t.plus("+time+" seconds)");
                System.out.println(d.CEPString()+"\n");
                event = d;
                i++;
            }
        }
    }

    private void changeOrder() 
    {   
        List<BasicEvent> help = new ArrayList<BasicEvent>();
        help.addAll(EventList);
        for(int i=0; i<EventList.size(); i++){
            EventList.set(i, help.get(EventList.size()-i-1));
        }
    }

}
