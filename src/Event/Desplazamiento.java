/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author Carlos
 */
public class Desplazamiento extends ComplexEvent implements InterfaceEvent{
    
    private float longitud2;
    private float latitud2;
    private float speed2;
    private Calendar time2;
    private Date timestamp2;
    private String flag2;     
        
    public Desplazamiento(ComplexEvent e1,ComplexEvent e2) {
        super(e1);
        longitud2 = e2.getLongitud();
        latitud2 = e2.getLatitud();
        speed2 = e2.getSpeed();
        time2 = e2.getTime();
        timestamp2 = e2.getTimestamp();
        flag2 = e2.getFlag();
    }

    public float getLongitud2() {
        return longitud2;
    }

    public float getLatitud2() {
        return latitud2;
    }

    public float getSpeed2() {
        return speed2;
    }

    public Calendar getTime2() {
        return time2;
    }

    public Date getTimestamp2() {
        return timestamp2;
    }

    public String getFlag2() {
        return flag2;
    }
    
    
}
