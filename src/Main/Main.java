package Main;

import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import Algorithm.FileReader;
import Algorithm.Graph;
import Algorithm.Node;
import Main.WayPoints.PointType;

import org.jxmapviewer.VirtualEarthTileFactoryInfo;

public class Main extends javax.swing.JFrame {
    private static final Object[] options1 = { "Start", "End"};
    private final Set<WayPoints> waypoints = new HashSet<WayPoints>();
    private WaypointPainter<WayPoints> wp = new WaypointRender();
    private EventWaypoint event;
    private JPanel panel;
    private int result;
    static JLabel l;

    public Main() {
        initComponents();
        init();
    }
    
    private void init(){
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
        GeoPosition geo= new GeoPosition(-6.893442,107.6097034);
        jXMapViewer.setAddressLocation(geo);
        jXMapViewer.setZoom(2);
        
        MouseInputListener mouse = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mouse);
        jXMapViewer.addMouseMotionListener(mouse);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));
        event = getEvent();
    }
    
    private void initWaypoint(){
        wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        jXMapViewer.setOverlayPainter(wp);
        for (WayPoints i : waypoints) {
            jXMapViewer.add(i.getButton());
        }
        GeoPosition start = null;
        GeoPosition end = null;
        for (WayPoints w : waypoints) {
            if(w.getPointType() == WayPoints.PointType.START){
                start = w.getPosition();
            } else if (w.getPointType() == WayPoints.PointType.END){
                end = w.getPosition();
            }
        }
        if(start!=null && end!=null){
            //milih algo
        }
    }

    private void addWaypoint(WayPoints waypoint){
        for (WayPoints i : waypoints) {
            jXMapViewer.remove(i.getButton());
        }

        waypoints.add(waypoint);
        initWaypoint();
    }

    private void clearWaypoint(){
        for (WayPoints i : waypoints) {
            jXMapViewer.remove(i.getButton());
        }
        waypoints.clear();
        initWaypoint();
    }

    private EventWaypoint getEvent(){
        return new EventWaypoint() {
            @Override
            public void selected(WayPoints waypoint){
                panel = new JPanel();
                panel.add(new JLabel(waypoint.getName()));
                panel.add(new JLabel("Latitude : " + waypoint.getPosition().getLatitude()));
                panel.add(new JLabel("Longitude: "+ waypoint.getPosition().getLongitude()));
                result = JOptionPane.showOptionDialog(null, panel, "Enter a Number",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options1, null);
                System.out.println(result);
                if(result == 0){
                    waypoint.setPointType(PointType.START);
                } else {
                    waypoint.setPointType(PointType.END);
                }
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     * @SuppressWarning ("Unchecked")
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXMapViewer = new JXMapViewer();
        comboMapType = new javax.swing.JComboBox<>();
        cmdClear = new javax.swing.JButton();
        chooseFile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Stree", "Virtual Earth", "Hybrid", "Satellite" }));
        comboMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMapTypeActionPerformed(evt);
            }
        });

        cmdClear.setText("Clear");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        chooseFile.setText("Choose File");
        chooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addComponent(chooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 718, Short.MAX_VALUE)
                .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdClear)
                    .addComponent(chooseFile))
                .addGap(0, 633, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMapTypeActionPerformed
        TileFactoryInfo info;
        int index = comboMapType.getSelectedIndex();
        if(index == 0){
            info = new OSMTileFactoryInfo();
        } else if (index == 1){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        } else if (index == 2){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        } else {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
    }//GEN-LAST:event_comboMapTypeActionPerformed
                      
    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        clearWaypoint();
    }//GEN-LAST:event_cmdClearActionPerformed
    
    private void chooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileActionPerformed
        JFileChooser j = new JFileChooser();

        int response = j.showOpenDialog(null);
 
        if(response == JFileChooser.APPROVE_OPTION){
            FileReader file = new FileReader();
            file.read(j.getSelectedFile().getAbsolutePath());
            jXMapViewer.setAddressLocation(file.getMapLocation());
            jXMapViewer.setZoom(file.getZoom());
            for (Node nodeFile : file.getNodes()) {
                addWaypoint(new WayPoints(nodeFile.getNode().getName(), PointType.NODE, event, nodeFile.getNode().getPosition()));
            }
            Graph graph = new Graph(waypoints);

            // Create a compound painter that uses both the route-painter and the waypoint-painter
            List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
            painters.add(graph);
            painters.add(wp);

            CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
            jXMapViewer.setOverlayPainter(painter);
            
        }

    }//GEN-LAST:event_chooseFileActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
        l = new JLabel("no file selected");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseFile;
    private javax.swing.JButton cmdClear;
    private javax.swing.JComboBox<String> comboMapType;
    private JXMapViewer jXMapViewer;
    // End of variables declaration//GEN-END:variables
}
