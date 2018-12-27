/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.EndRouteEvent;
import Event.StarRouteEvent;
import Event.InterfaceEvent;
import Event.RouteEvent;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class RouteSubscriber  implements StatementSubscriber{
    
     private final String  Rule = "select a1, a2 " 
                + "from pattern [ every (a1 = StarRouteEvent -> a2 = EndRouteEvent) ]  ";
    
    @Override
    public String getStatement() {
        return Rule;
    } 
    
    
    public void update(Map<String, InterfaceEvent> eventMap) {
        
        StarRouteEvent event = (StarRouteEvent) eventMap.get("a1");
        EndRouteEvent fin = (EndRouteEvent) eventMap.get("a2");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [Desplazamiento inicio]: ");
        sb.append("\n latitud_ini = " + event.getLatitud());
        sb.append("\n longitud_ini = " + event.getLongitud());
        sb.append("\n speed_ini = " + event.getSpeed()); 
        sb.append("\n time_ini = " + event.getTimestamp().toString());
        sb.append("\n- [Desplazamiento fin]: ");
        sb.append("\n latitud_fin = " + fin.getLatitud());
        sb.append("\n longitud_fin = " + fin.getLongitud());
        sb.append("\n speed_fin = " + fin.getSpeed()); 
        sb.append("\n time_fin = " + fin.getTimestamp().toString());
        sb.append("\n---------------------------------");
        System.out.println(sb);

        CSVReader.epl.handle(new RouteEvent(event,fin));
        
        
    }
    
    
}
