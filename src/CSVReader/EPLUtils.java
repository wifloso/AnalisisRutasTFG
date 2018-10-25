/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import Event.FinDesplazamiento;
import Event.InicioDesplazamiento;
import Event.InterfaceEvent;
import Event.BasicEvent;
import Event.ComplexEvent;
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
    private EPStatement Statement;
    
    @Autowired
    @Qualifier("Basic")
    private StatementSubscriber BasicEventSubscriber;
    
    @Autowired
    @Qualifier("FinDesplazamiento")
    private StatementSubscriber FinDesplazamientoSubscriber;
    
    @Autowired
    @Qualifier("InicioDesplazamiento")
    private StatementSubscriber InicioDesplazamientoSubscriber;
    
    @Autowired
    @Qualifier("Test")
    private StatementSubscriber TestSubscriber;
    
    
    @Autowired
    @Qualifier("InicioSecuenciaSubscriber")
    private StatementSubscriber InicioSecuenciaSubscriber;
    
    @Autowired
    @Qualifier("FinSecuenciaSubscriber")
    private StatementSubscriber FinSecuenciaSubscriber;
    
    @Autowired
    @Qualifier("DesplazamientoSubscriber")
    private StatementSubscriber DesplazamientoSubscriber;
    
    @Autowired
    @Qualifier("DesplazamientoTrustlySubscriber")
    private StatementSubscriber DesplazamientoTrustlySubscriber;
    
        public void initService() {

        Configuration config = new Configuration();
        config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
        config.addEventTypeAutoName("Event");
        epService = EPServiceProviderManager.getDefaultProvider(config);

        CreateBasicEventExpression();
        CreateFinDesplazamientoExpesion();
        CreateTestExpression();
        CreateInicioDesplazamientoExpression();
        CreateInicioSecuenciaExpresion();
        CreateFinSecuenciaExpresion();
        CreateDesplazamientoExpresion();
        CreateDesplazamientoTrustlyExpresion();
        
    }
        
        
    private void CreateDesplazamientoTrustlyExpresion(){
        DesplazamientoTrustlySubscriber = new DesplazamientoTrustlySubscriber();
        Statement = epService.getEPAdministrator().createEPL(DesplazamientoTrustlySubscriber.getStatement());
        Statement.setSubscriber(DesplazamientoTrustlySubscriber);  
    }
                
                
    private void CreateDesplazamientoExpresion(){
        DesplazamientoSubscriber = new DesplazamientoSubscriber();
        Statement = epService.getEPAdministrator().createEPL(DesplazamientoSubscriber.getStatement());
        Statement.setSubscriber(DesplazamientoSubscriber);  
    }
        
    private void CreateInicioSecuenciaExpresion() {
        InicioSecuenciaSubscriber = new InicioSecuenciaSubscriber();
        Statement = epService.getEPAdministrator().createEPL(InicioSecuenciaSubscriber.getStatement());
        Statement.setSubscriber(InicioSecuenciaSubscriber);
    }

    private void CreateFinSecuenciaExpresion() {
        FinSecuenciaSubscriber = new FinSecuenciaSubscriber();
        Statement = epService.getEPAdministrator().createEPL(FinSecuenciaSubscriber.getStatement());
        Statement.setSubscriber(FinSecuenciaSubscriber);
    }
        
    private void CreateTestExpression() {
        
        TestSubscriber = new TestSubscriber();
        Statement = epService.getEPAdministrator().createEPL(TestSubscriber.getStatement());
        Statement.setSubscriber(TestSubscriber);
    }
        
    private void CreateInicioDesplazamientoExpression() {        
        InicioDesplazamientoSubscriber = new InicioDesplazamientoSubscriber();
        Statement = epService.getEPAdministrator().createEPL(InicioDesplazamientoSubscriber.getStatement());
        Statement.setSubscriber(InicioDesplazamientoSubscriber);
    }    
        
    private void CreateBasicEventExpression() {
        
        BasicEventSubscriber = new BasicEventSubscriber();
        Statement = epService.getEPAdministrator().createEPL(BasicEventSubscriber.getStatement());
        Statement.setSubscriber(BasicEventSubscriber);
    }
    
    private void CreateFinDesplazamientoExpesion(){
        FinDesplazamientoSubscriber = new FinDesplazamientoSubscriber();
        Statement = epService.getEPAdministrator().createEPL(FinDesplazamientoSubscriber.getStatement());
        Statement.setSubscriber(FinDesplazamientoSubscriber);
    }
    
    public void handle(BasicEvent event) {
        //CurrentTimeEvent
        epService.getEPRuntime().sendEvent(new CurrentTimeEvent(event.getDateTime().getTime()));
        epService.getEPRuntime().sendEvent(event);

    }
    
    public void handle(InterfaceEvent event) {
        epService.getEPRuntime().sendEvent(event);
    }
    
    @Override
    public void afterPropertiesSet() {
        initService();
    }


    
}
