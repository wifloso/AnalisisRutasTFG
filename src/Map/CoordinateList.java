/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

/**
 *
 * @author Carlos
 */
public class CoordinateList {
    List<MapMarkerDot> EventMap;
    
    public CoordinateList(){
         EventMap = new  ArrayList<MapMarkerDot>();
    }

    public List<MapMarkerDot> getEventPos() {
        return EventMap;
    }
    
    public void putEventMap(Color color,double latitud,double longitud){
        MapMarkerDot a = new MapMarkerDot(Color.BLACK, latitud, longitud);
        a.setBackColor(color);
        EventMap.add(a);
    }
    
}
