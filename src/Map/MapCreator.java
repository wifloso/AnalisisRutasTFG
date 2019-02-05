/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

/**
 *
 * @author CarlosAA
 */
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;


public class MapCreator extends JFrame  {
        /**
         *
         */
        private static final long serialVersionUID = -2976479683829295126L;
        public static MapCreator instance;
        private JMapViewer map;
        private Image overlayI;

        
        public MapCreator() {
                GridBagLayout gbl = new GridBagLayout();
                GridBagConstraints gbc = new GridBagConstraints();
                this.setTitle("Route Analysis");
                this.setLayout(gbl);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridwidth = 1;
                gbc.weighty = 1;
                gbc.weightx = 1;
                gbc.gridx = 0;
                gbc.gridy = 0;

                map = new JMapViewer();
                DefaultMapController mapController = new DefaultMapController(map);
                mapController.setMovementMouseButton(MouseEvent.BUTTON1);
                map.setSize(800, 800);
                GotoMalaga();
                
                
                add(map, gbc);
                pack();
                setVisible(true);
                overlayI = new BufferedImage(getWidth(), getHeight(),
                                BufferedImage.TYPE_INT_ARGB);
                
                addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                                System.exit(0);
                        }
                });

        }
        
        public void GotoMalaga(){
            map.getMapPosition(new Coordinate(36.718267,-4.432112));
            map.setZoom(6);
            map.setDisplayPosition(7990, 6394, 6);
            map.setZoom(13);
        }
        
        public void EditMap(CoordinateList coordinateEvents) {
            for(MapMarkerDot Point: coordinateEvents.getDotEventList()){
                /*try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MapCreator.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                if(true){
                //if(Point.getBackColor() == Color.BLACK || Point.getBackColor() == Color.WHITE  ){
                //if(Point.getBackColor() != Color.BLUE  ){ 
                 map.addMapMarker(Point);   
                }
                 
            }
            for(MapMarkerDot Point: coordinateEvents.getDotEventList()){                
                if(Point.getBackColor() == Color.BLACK || Point.getBackColor() == Color.WHITE  ){
                //if(Point.getBackColor() != Color.BLUE  ){ 
                 map.addMapMarker(Point);   
                }
                 
            }
        }
}