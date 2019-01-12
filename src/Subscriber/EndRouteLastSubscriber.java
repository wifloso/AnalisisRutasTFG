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

/**
 *
 * @author Carlos
 */
public class EndRouteLastSubscriber implements StatementSubscriber{
    
    private final String  Rule = "select a1 " +
               " from BasicEvent as a1 where flag = \"fin\" ";
    
    @Override
    public String getStatement() {
        return Rule;
    }
    
    public void update(Map<String, BasicEvent> eventMap) {

            BasicEvent event = (BasicEvent) eventMap.get("a1");
        
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------------------");
            sb.append("\n- [Fin Secuencia]: ");
            sb.append("\n latitud = " + event.getLatitud() );
            sb.append("\n longitud = " + event.getLongitud() );
            sb.append("\n speed = " + event.getSpeed() ); 
            sb.append("\n time = " + event.getTimestamp().toString());
            sb.append("\n---------------------------------");
            System.out.println(sb);
            CSVReader.EPL.handle(new EndRouteEvent(event));
            //Trazo.coordenadasList.putEventMap(Color.RED, event.getLatitud(), event.getLongitud());
    }
}
