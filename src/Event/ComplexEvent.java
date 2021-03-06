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
public class ComplexEvent implements InterfaceEvent{
    
    private float longitud;
    private float latitud;
    private float speed;
    private Calendar time;
    private Date timestamp;
    private String flag;

    public ComplexEvent(ComplexEvent e) 
    {
        longitud = e.getLongitud();
        latitud = e.getLatitud();
        speed = e.getSpeed();
        time = e.getTime();
        timestamp = e.getTimestamp();
        flag = e.getFlag();
    }
    
    public ComplexEvent(BasicEvent e) 
    {
        longitud = e.getLongitud();
        latitud = e.getLatitud();
        speed = e.getSpeed();
        time = e.getTime();
        timestamp = e.getTimestamp();
        flag = e.getFlag();
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    

    public String getFlag() {
        return flag;
    }

    
    public float getLongitud() 
    {
        return longitud;
    }

    public float getLatitud()
    {
        return latitud;
    }

    public float getSpeed() 
    {
        return speed;
    }

    public Calendar getTime() 
    {
        return time;
    }
    
    public Date getDateTime() 
    {
        return time.getTime();
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    
    
    
    public int getMonth(String s)
    {
        int num;   
       switch(s) {
        case ("Jan") :
            num = 0;
            break;
        case ("Feb") :
            num = 1;
            break;
        case ("Mar") :
            num = 2;
            break;
        case ("Apr") :
            num = 3;
            break;
        case ("May") :
            num = 4;
            break;
        case ("Jun") :
            num = 5;
            break;
        case ("Jul") :
            num = 6;
            break;
        case ("Agu") :
            num = 7;
            break;
        case ("Sep") :
            num = 8;
            break;
        case ("Oct") :
            num = 9;
            break;
        case ("Nov") :
            num = 10;
            break;
        case ("Dic") :
            num = 11;
            break;
        default:
            num=12;
            break;
        }
       return num;
    }
    

    
    @Override
    public String toString()
    {
        return "PuntoEvent[Latitud:"+latitud+" Longitud:"+longitud+" Velocidad:"+speed+"]";
    }
    

    public String CEPString()
    {
        return "PuntoEvent ={ timestamp = \""+time.getTime()+"\", latitud = "+latitud+", longitud = "+longitud+",Speed = "+speed+" }";
    }
}
