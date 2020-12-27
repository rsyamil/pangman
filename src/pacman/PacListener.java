package pacman;
/**
 * Please add to keyPressed function for any action corresponding
 * to any key pressed.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PacListener implements KeyListener {
    
    public Pacman world;
    public JFrame mainFrame;
    
    public PacListener(Pacman world, JFrame mainFrame) {
        this.world = world;
        this.mainFrame = mainFrame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_LEFT) {
            world.nextpacdir = world.LEFT;
        } else if (code == KeyEvent.VK_RIGHT) {
            world.nextpacdir = world.RIGHT;
        } else if (code == KeyEvent.VK_UP) {
            world.nextpacdir = world.UP;
        } else if (code == KeyEvent.VK_DOWN) {
            world.nextpacdir = world.DOWN;
        } else if (code == KeyEvent.VK_P) {
           
            world.pauseGame = true;
            
        } else if (code == KeyEvent.VK_E) {
            
            world.die = true;
            
        } else {}            
    }

    @Override
    public void keyReleased(KeyEvent arg0) {}

    @Override
    public void keyTyped(KeyEvent arg0) {}

}
