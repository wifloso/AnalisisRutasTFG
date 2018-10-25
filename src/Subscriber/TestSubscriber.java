/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.EPLUtils;
import CSVReader.CSVReader;
import Event.BasicEvent;
import Event.ComplexEvent;
import java.util.Map;

/**
 *
 * @author CarlosAA
 */
public class TestSubscriber implements StatementSubscriber{
    
    private final String  Rule = "select a1 "
                + " from BasicEvent as a1 where flag = \"inicio\" ";
    
    @Override
    public String getStatement() {
        return Rule;
    }
    
    
    public void update(Map<String, BasicEvent> eventMap) {
        
        BasicEvent event = (BasicEvent) eventMap.get("a1");
        
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------");
        sb.append("\n- [Test]: ");
        sb.append("\n- [Test]: ");
        sb.append("\n- [Test]: ");
        sb.append("\n latitud = " + event.getLatitud());
        sb.append("\n longitud = " + event.getLongitud());
        sb.append("\n speed = " + event.getSpeed()); 
        sb.append("\n time = " + event.getTimestamp().toString());
        sb.append("\n---------------------------------");
        System.out.println(sb);
        //Trazo.epl.handle(new ComplexEvent(event));
    }
    
}
