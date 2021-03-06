/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.BasicEvent;
import Event.DirectionEvent;
import com.sun.javafx.geom.Vec2f;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class DirectionSubcriber implements StatementSubscriber{
    
    private final String  Query = "select a1, a2 " 
                + "from pattern [ every a1 = BasicEvent ->  a2 = BasicEvent ]";
    
    
    
    
    @Override
    public String getStatement() {
        return Query;
    }
    
    
    public void update(Map<String, BasicEvent> eventMap) {
        
        BasicEvent a1 = (BasicEvent) eventMap.get("a1");
        BasicEvent a2 = (BasicEvent) eventMap.get("a2");
        float a, b, c;
        
        a = a1.getLatitud()-a2.getLatitud();
        a = a*111111;
        b = a1.getLongitud()-a2.getLongitud();
        b = (float) ( b * Math.cos(a2.getLatitud()*Math.PI/180)*111111 );        
        c = (float) Math.sqrt(a*a + b*b);
        Vec2f result = new Vec2f(a/c, b/c);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n- [Incremento en]: ");
        sb.append("\n direccion latitud = " + a);
        sb.append("\n direccion longitud  = " + b);
        
        CSVReader.EPL.handle(new DirectionEvent(a1,result));
    }
    
}
