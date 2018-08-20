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
public class PuntoEvent 
{
    private float longitud;
    private float latitud;
    private float speed;
    private Calendar time;
    
    public PuntoEvent(float longitud, float latitud, float velocidad, String momento) 
    {
        this.longitud = longitud;
        this.latitud = latitud;
        this.speed = velocidad;
        this.time = setTime(momento);
    }
    
    //metodo para pasar el String de la fecha a un objeto Calendar
    private Calendar setTime(String sDate)
    {
        String[] sDateSlitp = sDate.split(" ");
        String[] sTime = sDateSlitp[3].split(":");
        Calendar c = Calendar.getInstance();
        c.set( Calendar.YEAR, Integer.parseInt( sDateSlitp[5] ) );
        c.set( Calendar.MONTH, getMonth(sDateSlitp[1]) );
        c.set( Calendar.DAY_OF_MONTH, Integer.parseInt( sDateSlitp[2] ) );
        c.set( Calendar.HOUR_OF_DAY, Integer.parseInt( sTime[0] ) );
        c.set( Calendar.MINUTE, Integer.parseInt( sTime[1] ) );
        c.set( Calendar.SECOND, Integer.parseInt( sTime[2] ) );
        c.set( Calendar.MILLISECOND, 0 );
        return c;
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
