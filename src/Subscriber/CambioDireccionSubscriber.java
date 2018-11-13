/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.EPLUtils;
import CSVReader.CSVReader;
import Event.BasicEvent;
import Event.IncrementoDireccionEvent;
import Event.ComplexEvent;
import Event.FinDesplazamiento;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author CarlosAA
 */
public class CambioDireccionSubscriber implements StatementSubscriber{
    
    private final String  Rule = "select a1, a2 " 
                + "from pattern [ every (a1 = IncrementoDireccionEvent -> a2 = IncrementoDireccionEvent) ]  ";
    
    @Override
    public String getStatement() {
        return Rule;
    }
    
    
    public void update(Map<String, IncrementoDireccionEvent> eventMap) {
        
        IncrementoDireccionEvent a1 = (IncrementoDireccionEvent) eventMap.get("a1");
        IncrementoDireccionEvent a2 = (IncrementoDireccionEvent) eventMap.get("a2");
        float angulo,a,b;
        if(true){
            angulo = (float) Math.acos( a1.getCambio().x*a2.getCambio().x+a1.getCambio().y*a2.getCambio().y );
        
        }else{
            a = (float) Math.atan2(a1.getCambio().x,a1.getCambio().x);
            b = (float) Math.atan2(a2.getCambio().x,a2.getCambio().x);
            angulo = Math.abs(a-b);
            
        }
        
        boolean hayCambio = Math.abs(angulo)  >  Math.PI/5;
        
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n- [Cambio de direccion]: ");
        sb.append("\n- [Cambio de direccion]: ");
        sb.append("\n- Coordenadas de cambio... ");
        sb.append("\n- Latitud = " + a2.getLatitud() );
        sb.append("\n- Longitud = " + a2.getLongitud() );
        sb.append("\n- Tiempo = " + a2.getTimestamp().toString());
        sb.append("\n- Pi = " + Math.PI/4);
        sb.append("\n- Tiene que ser mayor = " + angulo);

        
        if(hayCambio){
            System.out.println(sb);
            CSVReader.coordenadasList.putEventMap(Color.green, a2.getLatitud(), a2.getLongitud());
        }
        
        
    }
    
}


