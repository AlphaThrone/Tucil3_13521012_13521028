package Algorithm;

import Main.WayPoints;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.*;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;



public class Graph implements Painter<JXMapViewer>
{
    private boolean antiAlias = true;

    private List<WayPoints> track;
    private ArrayList<ArrayList<Double>> adjMatrix;

    public Boolean warna = false;

    /**
     * @param track the track
     */
    public Graph(List<WayPoints> track, ArrayList<ArrayList<Double>> adjMatrix)
    {
        this.track = track;
        this.adjMatrix = adjMatrix;
    }


    @Override
    public void paint(Graphics2D g, JXMapViewer map, int w, int h)
    {
        g = (Graphics2D) g.create();

        // convert from viewport to world bitmap
        Rectangle rect = map.getViewportBounds();
        g.translate(-rect.x, -rect.y);

        if (antiAlias)
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // do the drawing
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(4));

        drawRoute(g, map);
        
        if (warna == true) {
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(2));
            drawRoute(g, map);
        }
        // // do the drawing again
        // g.setColor(color);
        // g.setStroke(new BasicStroke(2));

        // drawRoute(g, map);

        g.dispose();
    }

    /**
     * @param g the graphics object
     * @param map the map
     */
    private void drawRoute(Graphics2D g, JXMapViewer map)
    {
        System.out.println("Mulaii");
        // Gambarkan garis dari node ke node yang lainnya jika bertetangga
        for (int i = 0; i < adjMatrix.size(); i++)
        {
            for (int j = 0; j < adjMatrix.get(i).size(); j++)
            {
                if (adjMatrix.get(i).get(j) != 0)
                {
                    Point2D pt1 = map.getTileFactory().geoToPixel(track.get(i).getPosition(), map.getZoom());
                    Point2D pt2 = map.getTileFactory().geoToPixel(track.get(j).getPosition(), map.getZoom());

                    g.drawLine((int) pt1.getX(), (int) pt1.getY(), (int) pt2.getX(), (int) pt2.getY());
                }
            }
        }
        System.out.println("Selesai");

    }


}