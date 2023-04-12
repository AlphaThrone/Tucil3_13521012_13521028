package Main;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import Algorithm.WaypointPainterList;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import javax.swing.JButton;

public class WaypointRender extends WaypointPainterList<WayPoints>{
    
    @Override
    protected void doPaint(Graphics2D graph, JXMapViewer map, int width, int height){
        for(WayPoints wp:getWaypoints()){
            Point2D point = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
            Rectangle rectangle = map.getViewportBounds();
            int x = (int) (point.getX() - rectangle.getX());
            int y = (int) (point.getY() - rectangle.getY());
            JButton cmd = wp.getButton();
            cmd.setLocation(x - cmd.getWidth() / 2, y - cmd.getHeight());
        }
    }
}
