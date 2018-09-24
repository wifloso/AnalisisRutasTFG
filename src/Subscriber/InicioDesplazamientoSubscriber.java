/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.Trazo;
import Event.FinDesplazamiento;
import Event.InicioDesplazamiento;
import Event.PuntoEvent;
import Event.Test;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class InicioDesplazamientoSubscriber implements StatementSubscriber{
    
    
    @Override
    public String getStatement() {
        return "select a2 "
                + " from pattern [every a1 = FinDesplazamiento -> a2 = PuntoEvent] ";
    } 
    
    
    public void update(Map<String, PuntoEvent> eventMap) {
        
        PuntoEvent event = (PuntoEvent) eventMap.get("a2");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [Inicio Desplazamiento]: ");
        sb.append("\n latitud = " + event.getLatitud());
        sb.append("\n longitud = " + event.getLongitud());
        sb.append("\n speed = " + event.getSpeed()); 
        sb.append("\n time = " + event.getTimestamp().toString());
        sb.append("\n---------------------------------");
        System.out.println(sb);
        Trazo.epl.handle(new InicioDesplazamiento(event));
    }
}
