/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

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
        return "select a2 " +
                "from Test a2";
    } 
    
    
    public void update(Map<String, Test> eventMap) {
        
        Test event = (Test) eventMap.get("a2");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [Inicio Desplazamiento]: ");
        sb.append("\n latitud = " + event.getLatitud());
        sb.append("\n longitud = " + event.getLongitud());
        sb.append("\n speed = " + event.getSpeed()); 
        sb.append("\n time = " + event.getTimestamp().toString());
        sb.append("\n---------------------------------");
        System.out.println(sb);
    }
}
