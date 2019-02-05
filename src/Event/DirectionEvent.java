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
public class DirectionEvent  extends ComplexEvent {
    
    Vec2f direction;
    
    public DirectionEvent(BasicEvent e,Vec2f vectorDirection) {
        super(e);
        this.direction = vectorDirection;
    }

    public Vec2f getDirection() {
        return direction;
    }
    
    
}
