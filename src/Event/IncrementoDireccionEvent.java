/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import com.sun.javafx.geom.Vec2f;

/**
 *
 * @author Carlos
 */
public class IncrementoDireccionEvent  extends ComplexEvent implements InterfaceEvent {
    
    Vec2f cambio;
    
    public IncrementoDireccionEvent(BasicEvent e,Vec2f cambio) {
        super(e);
        this.cambio = cambio;
    }

    public Vec2f getCambio() {
        return cambio;
    }
    
    
}
