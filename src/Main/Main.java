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

import Algorithm.FileReader;
import Algorithm.Graph;
import Algorithm.Node;
import Main.WayPoints.PointType;
import Algorithm.UCS;
import Algorithm.AStar;
import Algorithm.WaypointPainterList;

import org.jxmapviewer.VirtualEarthTileFactoryInfo;

public class Main extends javax.swing.JFrame {
    private static final Object[] options1 = { "Start", "End"};
    // private final Set<WayPoints> waypoints = new HashSet<WayPoints>();
    private WaypointPainterList<WayPoints> wp = new WaypointRender();
    private EventWaypoint event;
    private JPanel panel;
    private int result;
    static JLabel l;
    private List<WayPoints> waypoints = new ArrayList<WayPoints>();
    private ArrayList<Node> listAllNode = new ArrayList<Node>();
    private ArrayList<ArrayList<Double>> adjMatriks;
    int start = -1;
    int end = -1;
    
    private List<Painter<JXMapViewer>> painters;

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
        start = -1;
        end = -1;
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
                    start = waypoints.indexOf(waypoint);
                    // Ubah warna waypoint menjadi hijau

                } else if(result == 1){
                    end = waypoints.indexOf(waypoint);
                    // waypoint.setPointType(PointType.END);
                } else {

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

        jXMapViewer = new org.jxmapviewer.JXMapViewer();
        chooseFile = new javax.swing.JButton();
        comboMapType = new javax.swing.JComboBox<>();
        cmdClear = new javax.swing.JButton();
        cmdUCS = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // jXMapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
        //     public void mouseReleased(java.awt.event.MouseEvent evt) {
        //         jXMapViewerMouseReleased(evt);
        //     }
        // });

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 842, Short.MAX_VALUE)
        );

        chooseFile.setText("Choose File");
        chooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileActionPerformed(evt);
            }
        });

        comboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Street", "Virtual Earth", "Hybrid", "Satellite" }));
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

        cmdUCS.setText("UCS");
        cmdUCS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUCSActionPerformed(evt);
            }
        });

        jButton1.setText("A*");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMapType, 0, 107, Short.MAX_VALUE)
                            .addComponent(cmdClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(cmdUCS, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXMapViewer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chooseFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdUCS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

            // Output isi dari file
            System.out.println("Nama File : " + j.getSelectedFile().getName());
            System.out.println("Alamat : " + file.getMapLocation());
            System.out.println("Zoom : " + file.getZoom());
            System.out.println("Jumlah Node : " + file.getNodes().size());

            jXMapViewer.setAddressLocation(file.getMapLocation());
            jXMapViewer.setZoom(file.getZoom());
            int idx = 0;
            for (Node nodeFile : file.getNodes()) {
                
                addWaypoint(new WayPoints(nodeFile.getNode().getName(), PointType.NODE, event, nodeFile.getNode().getPosition(), idx));
                idx++;
            }

            adjMatriks = file.getAdjacencyMatrix();

            // Print adjacency matriks
            System.out.println("Adjacency Matriks : ");

            // Ngecek adjacency matriks
            for (int i = 0; i < file.getNodes().size(); i++) {
                for (int k = 0; k < file.getNodes().size(); k++) {
                    System.out.print(file.getAdjacencyMatrix().get(i).get(k) + " ");
                }
                System.out.println("");
            }

            // Isi waypoints dengan node
            for (int i = 0; i < file.getNodes().size(); i++) {
                waypoints.add(new WayPoints(file.getNodes().get(i).getNode().getName(), PointType.NODE, event, file.getNodes().get(i).getNode().getPosition(), i));
            }

            this.listAllNode = file.getNodes();

            // Print semua node beserta informasi berapa banyak tetangganya

            Graph graph = new Graph(waypoints, file.getAdjacencyMatrix());

            // Create a compound painter that uses both the route-painter and the waypoint-painter
            painters = new ArrayList<Painter<JXMapViewer>>();
            painters.add(graph);
            painters.add(wp);

            CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
            jXMapViewer.setOverlayPainter(painter);
        }

    }//GEN-LAST:event_chooseFileActionPerformed

    private void cmdUCSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUCSActionPerformed
        // TODO add your handling code here:
        // Cek apakah ada graph yang terbentuk dari file
        if ((start == -1) || (end == -1)) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Pilih dahulu titik awal atau titik akhir!"));
            JOptionPane.showMessageDialog(null, panel, "Peringatan!", JOptionPane.PLAIN_MESSAGE);
        }

        if (waypoints.size() > 0) {
            UCS algo = new UCS(listAllNode.size());
            
            for (int i = 0; i < adjMatriks.size(); i++) {
                for (int j = 0; j < adjMatriks.size(); j++) {

                    algo.addEdge(i, j, adjMatriks.get(i).get(j));
                   
                }
            }

            System.out.println("Sebelum UCS");
            // Cari jalur terpendek
            List<Integer> path = algo.ucs(start, end    );

            // Print jalur terpendek
            System.out.println("Jalur terpendek : ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + " ");
            }
            
            ArrayList<ArrayList<Double>> pathNode = new ArrayList<ArrayList<Double>>();
            //  Awalnya isi semua elemen pada pathNode adalah 0
            for (int i = 0; i < listAllNode.size(); i++) {
                pathNode.add(new ArrayList<Double>());
            }

            for (int i = 0; i < listAllNode.size(); i++) {
                for (int j = 0; j < listAllNode.size(); j++) {
                    pathNode.get(i).add(0.0);
                }
            }

            for (int i = 0; i < path.size() - 1 ; i++) {
                pathNode.get(path.get(i)).set(path.get(i + 1), 1.0);
            }

            // Coba print pathNode
            System.out.println("Path Node : ");
            for (int i = 0; i < pathNode.size(); i++) {
                for (int j = 0; j < pathNode.size(); j++) {
                    System.out.print(pathNode.get(i).get(j) + " ");
                }
                System.out.println("");
            }


            Graph graph = new Graph(waypoints, pathNode);
            graph.warna = true;
            
            System.out.println("Selesai 2");

            // Create a compound painter that uses both the route-painter and the waypoint-painter
            // List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
            painters.add(graph);
            painters.add(wp);

            CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
            jXMapViewer.setOverlayPainter(painter);

            
        }
    }//GEN-LAST:event_cmdUCSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        // Cek apakah ada graph yang terbentuk dari file
        if ((start == -1) || (end == -1)) {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Pilih dahulu titik awal atau titik akhir!"));
            JOptionPane.showMessageDialog(null, panel, "Peringatan!", JOptionPane.PLAIN_MESSAGE);
        }

        if (waypoints.size() > 0) {
            double[][] adjM = new double[adjMatriks.size()][adjMatriks.size()];
            for (int i = 0; i < adjMatriks.size(); i++) {
                for (int j = 0; j < adjMatriks.size(); j++) {
                    adjM[i][j] = adjMatriks.get(i).get(j);
                }
            }

            AStar algo = new AStar(adjM);

            System.out.println("Sebelum UCS");
            // Cari jalur terpendek
            ArrayList<Integer> path = algo.findShortestPath(start, end);

            // Print jalur terpendek
            System.out.println("Jalur terpendek : ");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + " ");
            }
            
            ArrayList<ArrayList<Double>> pathNode = new ArrayList<ArrayList<Double>>();
            //  Awalnya isi semua elemen pada pathNode adalah 0
            for (int i = 0; i < listAllNode.size(); i++) {
                pathNode.add(new ArrayList<Double>());
            }

            for (int i = 0; i < listAllNode.size(); i++) {
                for (int j = 0; j < listAllNode.size(); j++) {
                    pathNode.get(i).add(0.0);
                }
            }

            for (int i = 0; i < path.size() - 1 ; i++) {
                pathNode.get(path.get(i)).set(path.get(i + 1), 1.0);
            }

            // Coba print pathNode
            System.out.println("Path Node : ");
            for (int i = 0; i < pathNode.size(); i++) {
                for (int j = 0; j < pathNode.size(); j++) {
                    System.out.print(pathNode.get(i).get(j) + " ");
                }
                System.out.println("");
            }


            Graph graph = new Graph(waypoints, pathNode);
            graph.warna = true;
            
            System.out.println("Selesai 2");

            // Create a compound painter that uses both the route-painter and the waypoint-painter
            // List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
            painters.add(graph);
            painters.add(wp);

            CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
            jXMapViewer.setOverlayPainter(painter);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


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
    private javax.swing.JButton cmdUCS;
    private javax.swing.JComboBox<String> comboMapType;
    private javax.swing.JButton jButton1;
    private org.jxmapviewer.JXMapViewer jXMapViewer;
    // End of variables declaration//GEN-END:variables
}
