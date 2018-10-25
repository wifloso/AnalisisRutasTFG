/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.FinDesplazamiento;
import Event.InicioDesplazamiento;
import Event.InterfaceEvent;
import Event.BasicEvent;
import Event.Desplazamiento;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class DesplazamientoSubscriber  implements StatementSubscriber{
    
     private final String  Rule = "select a1, a2 " 
                + "from pattern [ every (a1 = InicioDesplazamiento -> a2 = FinDesplazamiento) ]  ";
    
    @Override
    public String getStatement() {
        return Rule;
    } 
    
    
    public void update(Map<String, InterfaceEvent> eventMap) {
        
        InicioDesplazamiento event = (InicioDesplazamiento) eventMap.get("a1");
        FinDesplazamiento fin = (FinDesplazamiento) eventMap.get("a2");
        
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

        CSVReader.epl.handle(new Desplazamiento(event,fin));
        
        
    }
    
    
}
