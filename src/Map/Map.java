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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.GZIPInputStream;
import javax.swing.JFrame;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.DefaultMapController;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
//import org.openstreetmap.osmosis.core.xml.common.DateParser;

public class Map extends JFrame  {
        /**
         *
         */
        private static final long serialVersionUID = -2976479683829295126L;
        public static Map instance;
        private JMapViewer map;
        private Timer repaintTimer;
        private Image overlayI;
        private long currTime;
        private int seqNum;
        private Coordinate bboxur = new Coordinate(58.00, 2.00);
        private int maxNodes = 4000;
        private Desktop desk;
        private boolean dloadRunning = false;
        private boolean redrawRunning = false;
        public CoordinateList Events;
        
        public Map(Coordinate bboxll, Coordinate bboxur, CoordinateList Events) {
                GridBagLayout gbl = new GridBagLayout();
                GridBagConstraints gbc = new GridBagConstraints();
                this.Events = Events;
                this.setTitle("LiveEditMapViewer");
                this.setLayout(gbl);
                this.bboxur = bboxur;
                desk = Desktop.getDesktop();
                map = new JMapViewer();
                DefaultMapController mapController = new DefaultMapController(map);
                mapController.setMovementMouseButton(MouseEvent.BUTTON1);
                map.setSize(800, 800);
                EditMap(Events);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                gbc.gridwidth = 1;
                gbc.weighty = 1;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.gridx = 0;
                gbc.gridy = 0;
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
        
        private void EditMap(CoordinateList Events){
            for(MapMarkerDot Point: Events.getEventPos()){
                 map.addMapMarker(Point);
            }

            map.getMapPosition(new Coordinate(36.718267,-4.432112));
            map.setZoom(6);
            map.setDisplayPosition(7990, 6394, 6);
            map.setZoom(13);
        }
        
        public void initChangeStream() {
                try {
                        HttpURLConnection conn = (HttpURLConnection) new URL("http://planet.openstreetmap.org/redaction-period/minute-replicate/state.txt").openConnection();
                        conn.setRequestProperty("User-Agent", "LiveEditMapViewerJ-r28184");
                        conn.setConnectTimeout(10000);
                        conn.setReadTimeout(25000);
                        int respCode = conn.getResponseCode();
                        if (respCode != 200) {
                                System.out.println("Failed to retrieve state file");
                                return;
                        }
                        BufferedReader br = new BufferedReader(
                                        new InputStreamReader(
                                                        new BufferedInputStream(conn.getInputStream())));
                        br.readLine();
                        String seqNumStr = br.readLine();
                        seqNum = Integer.parseInt(seqNumStr.substring(seqNumStr
                                        .indexOf("=") + 1));
                        br.readLine();
                        br.close();
                } catch (IOException ioe) {
                        ioe.printStackTrace();
                        System.exit(1);
                }
                //setupLatLonArray();
        }
        
         
       
        public void paintOverlay(Graphics g) {
                if (overlayI != null) {
                        g.drawImage(overlayI, 0, 0, null);
                }
        }


}