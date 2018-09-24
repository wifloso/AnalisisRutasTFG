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
public interface InterfaceEvent {
       
    
    public Date getTimestamp();
    

    public String getFlag();

    
    public float getLongitud();

    public float getLatitud();

    public float getSpeed();

    public Calendar getTime();
    
    public Date getDateTime();
    
    public int getMonth(String s);
    

    
    @Override
    public String toString();
    

    public String CEPString();
}
