/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.BasicEvent;
import java.awt.Color;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */
@Component
public class BasicEventSubscriber implements StatementSubscriber{

    private final String  Query = 
              "select a "
            + "from BasicEvent as a";
    private int SlowSpeed = 3;
    private int FastSpeed = 6;
    private int FakeSpeed = 100;
    
    @Override
    public String getStatement() {
        return Query;
    }
    
    public void update(Map<String, BasicEvent> eventMap) {
        BasicEvent event = (BasicEvent) eventMap.get("a");

        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [PuntoEvent]: ");
        sb.append("\n latitud = " + event.getLatitud() );
        sb.append("\n longitud = " + event.getLongitud() );
        sb.append("\n speed = " + event.getSpeed() );    
        sb.append("\n timestamp = " + event.getTimestamp().toString() );  
        sb.append("\n---------------------------------");
        System.out.println(sb);
 
        if(event.getSpeed() < SlowSpeed){
            CSVReader.CoordinatesList.putDot(Color.green, event.getLatitud(), event.getLongitud());
        }else if(event.getSpeed() >= SlowSpeed && event.getSpeed() < FastSpeed){
            CSVReader.CoordinatesList.putDot(Color.yellow, event.getLatitud(), event.getLongitud());
        }else if(event.getSpeed() >= FastSpeed && event.getSpeed() < FakeSpeed){
            CSVReader.CoordinatesList.putDot(Color.red, event.getLatitud(), event.getLongitud());
        }
        
    }   

}
