/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.BasicEvent;
import Event.CambioDireccionEvent;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class CambioDireccionSubscriber implements StatementSubscriber{
    
    private final String  Rule = "select a1, a2 " 
                + "from pattern [ every (a1 = BasicEvent -> a2 = BasicEvent) ]  ";
    
    @Override
    public String getStatement() {
        return Rule;
    }
    
    
    public void update(Map<String, BasicEvent> eventMap) {
        
        BasicEvent a1 = (BasicEvent) eventMap.get("a1");
        BasicEvent a2 = (BasicEvent) eventMap.get("a2");
        
        float a = (a1.getLatitud()-a2.getLatitud())*11111;
        float b = (a1.getLongitud()-a2.getLongitud());
        b = (float) Math.cos(a2.getLatitud()*Math.PI/180)*b*11111  ;
        float c = (float) Math.atan2(b, a);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n- [Cambio de direccion]: ");
        sb.append("\n Incremento de latitud en metros entre dos puntos consecutivos = " + a);
        sb.append("\n Incremento de longitud en metros entre dos puntos consecutivos = " + b);
        sb.append("\n Presenta una discontinuidad en la dirección Oeste = " + c); 
        System.out.println(sb);
        CSVReader.epl.handle(new CambioDireccionEvent(a1,c));
    }
    
}
