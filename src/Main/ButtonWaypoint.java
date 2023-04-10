package Main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Dimension;

public class ButtonWaypoint extends JButton {

    public ButtonWaypoint(){
        setContentAreaFilled(false);
        setIcon(new ImageIcon(getClass().getResource("/icon/pins.png")));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(new Dimension(24,24));
    }

}
