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

    private final String  Rule = "select * from BasicEvent as a";
    
    @Override
    public String getStatement() {
        return Rule;
    }
    
    public void update(Map<String, BasicEvent> eventMap) {
        // average temp over 10 secs
        BasicEvent event = (BasicEvent) eventMap.get("a");

        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [PuntoEvent]: ");
        sb.append("\n latitud = " + event.getLatitud() );
        sb.append("\n longitud = " + event.getLongitud() );
        sb.append("\n speed = " + event.getSpeed() );    
        sb.append("\n timestamp = " + event.getTimestamp().toString() );  
        sb.append("\n---------------------------------");
        //System.out.println(sb);
        
        if(event.getSpeed() < 3){
            CSVReader.CoordinatesList.putEventMap(Color.yellow, event.getLatitud(), event.getLongitud());
        }else if(event.getSpeed() >= 3 && event.getSpeed() < 6){
            CSVReader.CoordinatesList.putEventMap(Color.orange, event.getLatitud(), event.getLongitud());
        }else if(event.getSpeed() >= 6 && event.getSpeed() < 100){
            CSVReader.CoordinatesList.putEventMap(Color.red, event.getLatitud(), event.getLongitud());
        }
        
    }   

}
