/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.EPLUtils;
import CSVReader.CSVReader;
import Event.BasicEvent;
import Event.CambioDireccionEvent;
import Event.ComplexEvent;
import Event.FinDesplazamiento;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author CarlosAA
 */
public class TestSubscriber implements StatementSubscriber{
    
    private final String  Rule = "select a1, a2 " 
                + "from pattern [ every (a1 = CambioDireccionEvent -> a2 = CambioDireccionEvent) ]  ";
    
    @Override
    public String getStatement() {
        return Rule;
    }
    
    
    public void update(Map<String, CambioDireccionEvent> eventMap) {
        
        CambioDireccionEvent a1 = (CambioDireccionEvent) eventMap.get("a1");
        CambioDireccionEvent a2 = (CambioDireccionEvent) eventMap.get("a2");
        boolean hayCambio = Math.abs(a1.getCambio()-a2.getCambio())>Math.PI/4;
        StringBuilder sb = new StringBuilder();
        sb.append("\n- [Cambio de direccion]: ");
        sb.append("\n- [Cambio de direccion]: ");
        sb.append("\n- Coordenadas de cambio... ");
        sb.append("\n- Latitud = " + a2.getLatitud() );
        sb.append("\n- Longitud = " + a2.getLongitud() );
        sb.append("\n- Tiempo = " + a2.getTimestamp().toString());
        sb.append("\n- Pi = " + Math.PI/2);
        sb.append("\n- Tiene que ser mayor = " + Math.abs(a1.getCambio()-a2.getCambio()));

        
        if(hayCambio){
            System.out.println(sb);
            CSVReader.coordenadasList.putEventMap(Color.green, a1.getLatitud(), a1.getLongitud());
        }
        
        
    }
    
}
