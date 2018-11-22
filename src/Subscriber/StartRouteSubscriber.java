/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.EndRouteEvent;
import Event.StarRouteEvent;
import Event.BasicEvent;
import Event.ComplexEvent;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class StartRouteSubscriber implements StatementSubscriber{
    
    private final String  Rule = "select a2 "
                + " from pattern [every a1 = EndRouteEvent -> a2 = BasicEvent] ";
    
    @Override
    public String getStatement() {
        return Rule;
    } 
    
    
    public void update(Map<String, BasicEvent> eventMap) {
        
        BasicEvent event = (BasicEvent) eventMap.get("a2");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [Inicio Desplazamiento]: ");
        sb.append("\n latitud = " + event.getLatitud());
        sb.append("\n longitud = " + event.getLongitud());
        sb.append("\n speed = " + event.getSpeed()); 
        sb.append("\n time = " + event.getTimestamp().toString());
        sb.append("\n---------------------------------");
        System.out.println(sb);
        CSVReader.epl.handle(new StarRouteEvent(event));
        //Trazo.coordenadasList.putEventMap(Color.GREEN, event.getLatitud(), event.getLongitud());
    }
}
