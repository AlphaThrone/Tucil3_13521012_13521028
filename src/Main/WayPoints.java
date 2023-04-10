
package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class WayPoints extends DefaultWaypoint{
    private String name;
    private JButton button;
    private void initButton(EventWaypoint event){
        button = new ButtonWaypoint();
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(WayPoints.this);
            }    
        });
    }
    
    
    
    public WayPoints(){
        /*Empty*/
    }

    public WayPoints(String name, EventWaypoint event, GeoPosition coordinate){
        super(coordinate);
        this.name = name;
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



}
