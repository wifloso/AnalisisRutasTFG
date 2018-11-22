/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;


/**
 *
 * @author Carlos
 */
public class EndRouteEvent extends ComplexEvent implements InterfaceEvent {
         
    public EndRouteEvent(BasicEvent e) {
        super(e);
    }
}
