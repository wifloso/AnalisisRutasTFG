/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.BasicEvent;
import com.sun.javafx.geom.Vec2f;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author CarlosAA
 */
public class TestSubscriber implements StatementSubscriber{
    
      private final String  Query = "select a1 "
            + "from pattern [every a1 =   BasicEvent -> "
            + "(timer:interval(30 seconds) and not a2 = BasicEvent)]";
    
    
    @Override
    public String getStatement() {
        return Query;
    }
    
    
    public void update(Map<String, BasicEvent> eventMap) {
        
    BasicEvent event = (BasicEvent) eventMap.get("a1");
    //CSVReader.CoordinatesList.putDot(Color.MAGENTA, event.getLatitud(), event.getLongitud());


    }
    
}
