/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis_rutas;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class EPLUtils {
    
    EPServiceProvider engine;
    EPAdministrator admin;
    List<String> Normas = Arrays.asList("select * from PuntoEvent(speed<1)","select * from PuntoEvent");
    EPStatement statement;
    
    public EPLUtils(){
        engine = EPServiceProviderManager.getDefaultProvider();
        admin = engine.getEPAdministrator();
        engine.getEPAdministrator().getConfiguration().addEventType(PuntoEvent.class);
        for(String a : Normas){
            statement = admin.createEPL(a);
        }
        
        statement.addListener( (newData, oldData) -> {
                float longitud = (float) newData[0].get("longitud");
                float latitud = (float) newData[0].get("latitud");
                float speed = (float) newData[0].get("speed");                
                System.out.println(String.format("longitud: %f, latitud: %f speed: %f", longitud, latitud, speed));
            });
        
        
        
    }
    
    public void ReadData(List<PuntoEvent> data){
        for(PuntoEvent p: data){
            engine.getEPRuntime().sendEvent(p);
        }   
    }
    
}
