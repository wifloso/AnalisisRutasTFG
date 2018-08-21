/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import Event.PuntoEvent;
import Subscriber.*;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 *
 * @author Carlos
 */
@Component
@Scope(value = "singleton")
public class EPLUtils implements InitializingBean{
    
    
    
    private EPServiceProvider epService;
    private EPStatement PuntoStatement;
    
    @Autowired
    @Qualifier("PuntoEventSubscriber")
    private StatementSubscriber PuntoEventSubscriber;
    
        public void initService() {

        Configuration config = new Configuration();
        config.addEventTypeAutoName("Event");
        epService = EPServiceProviderManager.getDefaultProvider(config);

        CreatePuntoEventExpression();
    }

    private void CreatePuntoEventExpression() {
        
        PuntoEventSubscriber = new PuntoEventSubscriber();
        PuntoStatement = epService.getEPAdministrator().createEPL(PuntoEventSubscriber.getStatement());
        PuntoStatement.setSubscriber(PuntoEventSubscriber);
    }
    
    public void handle(PuntoEvent event) {

        epService.getEPRuntime().sendEvent(event);

    }

    @Override
    public void afterPropertiesSet() {
        initService();
    }
    
}
