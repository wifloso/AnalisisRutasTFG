/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import Event.PuntoEvent;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */
@Component
public class FinDesplazamientoSubscriber  implements StatementSubscriber{

    @Override
    public String getStatement() {
        return "select a1.latitud as latitud, a1.longitud as longitud , a1.timestamp as timestamp " +
               " from pattern [every a1 = PuntoEvent -> (timer:interval(150 seconds) and not a2 = PuntoEvent)]";
    }
    
    public void update(Map<String, PuntoEvent> eventMap) {

            PuntoEvent a1 = (PuntoEvent) eventMap.get("a1");
            PuntoEvent a2 = (PuntoEvent) eventMap.get("a2");
        
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------------------");
            sb.append("\n- [Fin desplazamiento]: ");
            sb.append("\n latitud = " + a1.getLatitud() );
            sb.append("\n longitud = " + a1.getLongitud() );
            sb.append("\n speed = " + a1.getSpeed() );       
            sb.append("\n---------------------------------");
            System.out.println(sb);
    }
    
}
