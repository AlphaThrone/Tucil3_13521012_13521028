
package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class WayPoints extends DefaultWaypoint{
    private String name;
    private JButton button;
    private PointType pointType;
    private int idx;

    private void initButton(EventWaypoint event){
        button = new ButtonWaypoint();
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(WayPoints.this);
            }    
        });
    }

    
    public static enum PointType{
        START, END, NODE;
    }
    
    public WayPoints(){
        /*Empty*/
    }

    public WayPoints(String name, PointType pointType, EventWaypoint event, GeoPosition coordinate, int indeks){
        super(coordinate);
        this.name = name;
        this.pointType = pointType;
        this.idx = indeks;
        initButton(event);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public JButton getButton(){
        return button;
    }

    public void setButton(JButton button){
        this.button = button;
    }

    public PointType getPointType(){
        return this.pointType;
    }

    public void setPointType(PointType pointType){
        this.pointType = pointType;
    }

    public int getIdx(){
        return this.idx;
    }
}
