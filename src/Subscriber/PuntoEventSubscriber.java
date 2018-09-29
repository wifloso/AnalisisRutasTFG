/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.Trazo;
import Event.PuntoEvent;
import java.awt.Color;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */
@Component
public class PuntoEventSubscriber implements StatementSubscriber{


    
    @Override
    public String getStatement() {
        return "select * from PuntoEvent as a";
    }
    
    public void update(Map<String, PuntoEvent> eventMap) {
        // average temp over 10 secs
        PuntoEvent punto = (PuntoEvent) eventMap.get("a");

        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [PuntoEvent]: ");
        sb.append("\n latitud = " + punto.getLatitud() );
        sb.append("\n longitud = " + punto.getLongitud() );
        sb.append("\n speed = " + punto.getSpeed() );    
        sb.append("\n timestamp = " + punto.getTimestamp().toString() );  
        sb.append("\n---------------------------------");
        //System.out.println(sb);
        Trazo.coordenadasList.putEventMap(Color.yellow, punto.getLatitud(), punto.getLongitud());
    }   

}
