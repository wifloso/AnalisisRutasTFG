/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subscriber;

import CSVReader.Trazo;
import Event.FinDesplazamiento;
import Event.PuntoEvent;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class FinSecuenciaSubscriber implements StatementSubscriber{
    
    @Override
    public String getStatement() {
        return "select a1 " +
               " from PuntoEvent as a1 where flag = \"fin\" ";
    }
    
    public void update(Map<String, PuntoEvent> eventMap) {

            PuntoEvent a1 = (PuntoEvent) eventMap.get("a1");
        
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------------------");
            sb.append("\n- [Fin Secuencia]: ");
            sb.append("\n latitud = " + a1.getLatitud() );
            sb.append("\n longitud = " + a1.getLongitud() );
            sb.append("\n speed = " + a1.getSpeed() ); 
            sb.append("\n time = " + a1.getTimestamp().toString());
            sb.append("\n---------------------------------");
            System.out.println(sb);
            Trazo.epl.handle(new FinDesplazamiento(a1));
    }
}
