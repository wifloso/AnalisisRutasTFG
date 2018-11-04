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
public class CambioDireccionEvent  extends ComplexEvent implements InterfaceEvent {
    
    float cambio;
    
    public CambioDireccionEvent(BasicEvent e,float cambio) {
        super(e);
        this.cambio = cambio;
    }

    public float getCambio() {
        return cambio;
    }
    
    
}
