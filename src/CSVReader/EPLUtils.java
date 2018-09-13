/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import Event.PuntoEvent;
import Event.Test;
import Subscriber.*;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.time.CurrentTimeEvent;
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
    @Qualifier("PuntoEvent")
    private StatementSubscriber PuntoEventSubscriber;
    
    @Autowired
    @Qualifier("FinDesplazamiento")
    private StatementSubscriber FinDesplazamientoSubscriber;
    
    @Autowired
    @Qualifier("InicioDesplazamiento")
    private StatementSubscriber InicioDesplazamientoSubscriber;
    
    @Autowired
    @Qualifier("Test")
    private StatementSubscriber TestSubscriber;
    
        public void initService() {

        Configuration config = new Configuration();
        config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
        config.addEventTypeAutoName("Event");
        epService = EPServiceProviderManager.getDefaultProvider(config);

        CreatePuntoEventExpression();
        CreateFinDesplazamientoExpesion();
        CreateTestExpression();
        CreateInicioDesplazamientoExpression();
        
    }

    private void CreateTestExpression() {
        
        TestSubscriber = new TestSubscriber();
        PuntoStatement = epService.getEPAdministrator().createEPL(TestSubscriber.getStatement());
        PuntoStatement.setSubscriber(TestSubscriber);
    }
        
    private void CreateInicioDesplazamientoExpression() {
        
        InicioDesplazamientoSubscriber = new InicioDesplazamientoSubscriber();
        PuntoStatement = epService.getEPAdministrator().createEPL(InicioDesplazamientoSubscriber.getStatement());
        PuntoStatement.setSubscriber(InicioDesplazamientoSubscriber);
    }    
        
    private void CreatePuntoEventExpression() {
        
        PuntoEventSubscriber = new PuntoEventSubscriber();
        PuntoStatement = epService.getEPAdministrator().createEPL(PuntoEventSubscriber.getStatement());
        PuntoStatement.setSubscriber(PuntoEventSubscriber);
    }
    
    private void CreateFinDesplazamientoExpesion(){
        FinDesplazamientoSubscriber = new FinDesplazamientoSubscriber();
        PuntoStatement = epService.getEPAdministrator().createEPL(FinDesplazamientoSubscriber.getStatement());
        PuntoStatement.setSubscriber(FinDesplazamientoSubscriber);
    }
    
    public void handle(PuntoEvent event) {
        //CurrentTimeEvent
        epService.getEPRuntime().sendEvent(new CurrentTimeEvent(event.getDateTime().getTime()));
        epService.getEPRuntime().sendEvent(event);

    }
    
    public void handle(Test event) {
        epService.getEPRuntime().sendEvent(event);

    }

    @Override
    public void afterPropertiesSet() {
        initService();
    }
    
}
