/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.CSVReader;
import Event.DirectionEvent;
import Event.InterfaceEvent;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author CarlosAA
 */
public class ChangeDirectionSubscriber implements StatementSubscriber{
    
    private final String  Query = 
            "select a1, a2 " 
          + "from pattern [ every a1 = DirectionEvent -> a2 = DirectionEvent ]";
    
    @Override
    public String getStatement() {
        return Query;
    }
    
    
    public void update(Map<String, InterfaceEvent> eventMap) {
        
        DirectionEvent a1 = (DirectionEvent) eventMap.get("a1");
        DirectionEvent a2 = (DirectionEvent) eventMap.get("a2");
        float angulo;
        
        angulo = (float) Math.acos( a1.getDirection().x*a2.getDirection().x+a1.getDirection().y*a2.getDirection().y );
        boolean hayCambio = Math.abs(angulo)  >  Math.PI/5;
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n- [Cambio de direccion]: ");
        sb.append("\n- [Cambio de direccion]: ");
        sb.append("\n- Coordenadas de cambio... ");
        sb.append("\n- Latitud = " + a2.getLatitud() );
        sb.append("\n- Longitud = " + a2.getLongitud() );
        sb.append("\n- Tiempo = " + a2.getTimestamp().toString());
        sb.append("\n- Pi = " + Math.PI/5);
        sb.append("\n- Tiene que ser mayor = " + angulo);

        if(hayCambio){
            System.out.println(sb);
            CSVReader.CoordinatesList.putDot(Color.blue, a2.getLatitud(), a2.getLongitud());
        }
    }
    
}


