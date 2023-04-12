/*
 * WaypointMapOverlay.java
 *
 * Created on April 1, 2006, 4:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Algorithm;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.jxmapviewer.viewer.DefaultWaypointRenderer;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointRenderer;
 
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.AbstractPainter;
 
/**
 * Paints waypoints on the JXMapViewer. This is an 
 * instance of Painter that only can draw on to JXMapViewers.
 * @param <W> the waypoint type
 * @originalAuthor rbair
 * @editedBy AlphaThrone
 * @logChange changed set to list atribute for custom implementation 
 */
public class WaypointPainterList<W extends Waypoint> extends AbstractPainter<JXMapViewer>
{
    private WaypointRenderer<? super W> renderer = new DefaultWaypointRenderer();
    private List<W> waypoints = new ArrayList<W>();

    /**
     * Creates a new instance of WaypointPainter
     */
    public WaypointPainterList()
    {
        setAntialiasing(true);
        setCacheable(false);
    }

    /**
     * Sets the waypoint renderer to use when painting waypoints
     * @param r the new WaypointRenderer to use
     */
    public void setRenderer(WaypointRenderer<W> r)
    {
        this.renderer = r;
    }

    /**
     * Gets the current set of waypoints to paint
     * @return a typed Set of Waypoints
     */
   public List<W> getWaypoints()
   {
       return waypoints;
   }

    /**
     * Sets the current set of waypoints to paint
     * @param waypoints the new Set of Waypoints to use
     */
    public void setWaypoints(List<? extends W> waypoints)
    {
        this.waypoints.clear();
        this.waypoints.addAll(waypoints);
    }

    @Override
    protected void doPaint(Graphics2D g, JXMapViewer map, int width, int height)
    {
        if (renderer == null)
        {
            return;
        }

        Rectangle viewportBounds = map.getViewportBounds();

        g.translate(-viewportBounds.getX(), -viewportBounds.getY());

        for (W w : getWaypoints())
        {
            renderer.paintWaypoint(g, map, w);
        }

        g.translate(viewportBounds.getX(), viewportBounds.getY());

    }

}
 
