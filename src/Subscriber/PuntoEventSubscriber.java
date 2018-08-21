/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

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
        return "select latitud, longitud, speed from PuntoEvent";
    }
    
    
    public void update(Map<String, Float> eventMap) {
        // average temp over 10 secs
        float latitud = (float) eventMap.get("latitud");
        float longitud = (float) eventMap.get("longitud");
        float speed = (float) eventMap.get("speed");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [PuntoEvent]: ");
        sb.append("\n latitud = " + latitud);
        sb.append("\n longitud = " + longitud);
        sb.append("\n speed = " + speed);       
        sb.append("\n---------------------------------");
        System.out.println(sb);
    }
    
}
