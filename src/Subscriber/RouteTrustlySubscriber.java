/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.RouteEvent;
import Event.EndRouteEvent;
import Event.StarRouteEvent;
import Event.InterfaceEvent;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class RouteTrustlySubscriber implements StatementSubscriber{
     
    private final String  Rule = "select a1 " 
                + "from RouteEvent a1  "
             + "where (timestamp2().getTime() - timestamp().getTime())/1000 > 120 ";
    
    @Override
    public String getStatement() {
        return Rule;
    } 
    
    
    public void update(Map<String, InterfaceEvent> eventMap) {
        
        RouteEvent event = (RouteEvent) eventMap.get("a1");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("---------------------------------");
        sb.append("---------------------------------");
        sb.append("---------------------------------");
        sb.append("\n- [Desplazamiento Trustly inicio]: ");
        sb.append("\n latitud_ini = " + event.getLatitud());
        sb.append("\n longitud_ini = " + event.getLongitud());
        sb.append("\n speed_ini = " + event.getSpeed()); 
        sb.append("\n time_ini = " + event.getTimestamp().toString());
        sb.append("\n- [Desplazamiento fin]: ");
        sb.append("\n latitud_fin = " + event.getLatitud2());
        sb.append("\n longitud_fin = " + event.getLongitud2());
        sb.append("\n speed_fin = " + event.getSpeed2()); 
        sb.append("\n time_fin = " + event.getTimestamp2().toString());
        sb.append("\n---------------------------------");
        System.out.println(sb);
        //todo: named window to speed analysis
        CSVReader.coordenadasList.putEventMap(Color.WHITE, event.getLatitud(), event.getLongitud());
        CSVReader.coordenadasList.putEventMap(Color.BLACK, event.getLatitud2(), event.getLongitud2());
        
        
    }
}
