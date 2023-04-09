
package waypoint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class WayPoints extends DefaultWaypoint{
    private String name;
    private JButton button;
    private void initButton(){
        button = new ButtonWaypoint();
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Click: " + name);
            }    
        });
    }
    
    
    
    public WayPoints(){
        /*Empty*/
    }

    public WayPoints(String name, GeoPosition coordinate){
        super(coordinate);
        this.name = name;
        initButton();
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