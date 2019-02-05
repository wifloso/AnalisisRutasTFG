/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.EndRouteEvent;
import Event.BasicEvent;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */
@Component
public class EndRouteSubscriber  implements StatementSubscriber{

    private final String  Query = "select a1 "
            + "from pattern [every a1 =   BasicEvent -> "
            + "(timer:interval(150 seconds) and not a2 = BasicEvent)]";
    
    @Override
    public String getStatement() {
        return Query;
    }
    
    public void update(Map<String, BasicEvent> eventMap) {

            BasicEvent event = (BasicEvent) eventMap.get("a1");
        
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------------------");
            sb.append("\n- [Fin desplazamiento]: ");
            sb.append("\n latitud = " + event.getLatitud() );
            sb.append("\n longitud = " + event.getLongitud() );
            sb.append("\n speed = " + event.getSpeed() ); 
            sb.append("\n time = " + event.getTimestamp().toString());
            sb.append("\n---------------------------------");
            System.out.println(sb);
            CSVReader.EPL.handle(new EndRouteEvent(event));
    }
    
}
