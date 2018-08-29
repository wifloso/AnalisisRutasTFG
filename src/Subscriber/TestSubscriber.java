/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import Event.PuntoEvent;
import java.util.Map;

/**
 *
 * @author CarlosAA
 */
public class TestSubscriber implements StatementSubscriber{
    
    
    @Override
    public String getStatement() {
        return "select a1 "
                + "from pattern [every a1 = PuntoEvent -> (timer:interval(150 seconds) and not a2 = PuntoEvent)] ";
    }
    
    
    public void update(Map<String, PuntoEvent> eventMap) {
        // average temp over 10 secs
        PuntoEvent event = (PuntoEvent) eventMap.get("a1");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [Test]: ");
        sb.append("\n- [Test]: ");
        sb.append("\n- [Test]: ");
        sb.append("\n latitud = " + event.getLatitud());
        sb.append("\n longitud = " + event.getLongitud());
        sb.append("\n speed = " + event.getSpeed());       
        sb.append("\n---------------------------------");
        System.out.println(sb);
    }
    
}
