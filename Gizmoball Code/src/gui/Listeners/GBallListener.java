package gui.Listeners;

import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;

public interface GBallListener extends ActionListener, KeyListener, MouseInputListener{

    public void setMouseListener(MouseInputListener mouseListener);

    public void setKeyboardListener(KeyListener keyboardListener);

}
