/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSVReader;

import Event.EndRouteEvent;
import Event.StarRouteEvent;
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
    
      
    private EPServiceProvider EPLService;
    private EPStatement Statement;
    
    @Autowired
    @Qualifier("Basic")
    private StatementSubscriber BasicEventSubscriber;
    
    @Autowired
    @Qualifier("FinDesplazamiento")
    private StatementSubscriber EndRouteSubscriber;
    
    @Autowired
    @Qualifier("InicioDesplazamiento")
    private StatementSubscriber StartRouteSubscriber;
    
    @Autowired
    @Qualifier("Test")
    private StatementSubscriber TestSubscriber;
    
    
    @Autowired
    @Qualifier("InicioSecuenciaSubscriber")
    private StatementSubscriber StartRouteFirstSubscriber;
    
    @Autowired
    @Qualifier("FinSecuenciaSubscriber")
    private StatementSubscriber EndRouteLastSubscriber;
    
    @Autowired
    @Qualifier("DesplazamientoSubscriber")
    private StatementSubscriber RouteSubscriber;
       
    @Autowired
    @Qualifier("DesplazamientoTrustlySubscriber")
    private StatementSubscriber RouteTrustlySubscriber;
    
    @Autowired
    @Qualifier("IncrementoDireccciónSubcriber")
    private StatementSubscriber DirectionSubcriber;
    
    @Autowired
    @Qualifier("CambioDireccionSubscriber")
    private StatementSubscriber ChangeDirectionSubscriber;
    
        public void initService() {

        Configuration config = new Configuration();
        config.getEngineDefaults().getThreading().setInternalTimerEnabled(false);
        config.addEventTypeAutoName("Event");
        EPLService = EPServiceProviderManager.getDefaultProvider(config);

        CreateBasicEventExpression();
        CreateEndRouteExpesion();
        CreateTestExpression();
        CreateStartRouteExpression();
        CreateStartRouteFirstExpresion();
        CreateEndRouteLastExpresion();
        CreateRouteExpresion();
        CreateRouteTrustlyExpresion();
        CreateChangeDirectionExpresion();
        CreateDirectionExpresion();
    }
    private void CreateDirectionExpresion(){
        DirectionSubcriber = new DirectionSubcriber();
        Statement = EPLService.getEPAdministrator().createEPL(DirectionSubcriber.getStatement());
        Statement.setSubscriber(DirectionSubcriber);  
    }

    private void CreateChangeDirectionExpresion(){
        ChangeDirectionSubscriber = new ChangeDirectionSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(ChangeDirectionSubscriber.getStatement());
        Statement.setSubscriber(ChangeDirectionSubscriber);        
    }    
        
    private void CreateRouteTrustlyExpresion(){
        RouteTrustlySubscriber = new RouteTrustlySubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(RouteTrustlySubscriber.getStatement());
        Statement.setSubscriber(RouteTrustlySubscriber);  
    }
                
                
    private void CreateRouteExpresion(){
        RouteSubscriber = new RouteSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(RouteSubscriber.getStatement());
        Statement.setSubscriber(RouteSubscriber);  
    }
        
    private void CreateStartRouteFirstExpresion() {
        StartRouteFirstSubscriber = new StartRouteFirstSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(StartRouteFirstSubscriber.getStatement());
        Statement.setSubscriber(StartRouteFirstSubscriber);
    }

    private void CreateEndRouteLastExpresion() {
        EndRouteLastSubscriber = new EndRouteLastSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(EndRouteLastSubscriber.getStatement());
        Statement.setSubscriber(EndRouteLastSubscriber);
    }
        
    private void CreateTestExpression(){
        TestSubscriber = new TestSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(TestSubscriber.getStatement());
        Statement.setSubscriber(TestSubscriber);
    }
        
    private void CreateStartRouteExpression() {        
        StartRouteSubscriber = new StartRouteSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(StartRouteSubscriber.getStatement());
        Statement.setSubscriber(StartRouteSubscriber);
    }    
        
    private void CreateBasicEventExpression() {
        
        BasicEventSubscriber = new BasicEventSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(BasicEventSubscriber.getStatement());
        Statement.setSubscriber(BasicEventSubscriber);
    }
    
    private void CreateEndRouteExpesion(){
        EndRouteSubscriber = new EndRouteSubscriber();
        Statement = EPLService.getEPAdministrator().createEPL(EndRouteSubscriber.getStatement());
        Statement.setSubscriber(EndRouteSubscriber);
    }
    
    public void handle(BasicEvent event) {
        //CurrentTimeEvent
        EPLService.getEPRuntime().sendEvent(new CurrentTimeEvent(event.getDateTime().getTime()));
        EPLService.getEPRuntime().sendEvent(event);

    }
    
    public void handle(InterfaceEvent event) {
        EPLService.getEPRuntime().sendEvent(new CurrentTimeEvent(event.getDateTime().getTime()));
        EPLService.getEPRuntime().sendEvent(event);
    }
    
    @Override
    public void afterPropertiesSet() {
        initService();
    }


    
}
