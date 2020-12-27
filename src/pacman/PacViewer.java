package pacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class PacViewer extends Canvas {

    private Pacman world;                   /** holds the Pacman */        
    private final int Ratio = 25;           /** controls the ratio of the window */
    
    /** 
     * Constructor for the viewer. Sets the size if the viewer. 
     * @param world
     */
    public PacViewer(Pacman world) {
        this.world = world;
        this.setSize(world.getWidth()*Ratio, world.getHeight()*Ratio);
    }
    /**
     * Called in response to repaint
     */
    public void update(Graphics g) {
        Image im = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        paint(im.getGraphics());
        g.drawImage(im, 0, 0, null);
    }
    /**
     * Overridden to paint Canvas
     */
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);                            /** sets current color of the background */
        g.fillRect(0,0,getWidth(), getHeight());            /** fill rect with current color of g */       
        g.setColor(Color.BLACK);                            /** now change current color of g */
        
        
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                char c = world.getCell(x,y);                /** for all entry in board, we will paint it! */
                switch (c) {
                    case '.': 
                        if ((world.time / 8) % 4 != 0) {    /** controls the blinking of the dots */  
                            g.setColor(Color.pink);
                            g.drawOval(x*Ratio+Ratio/4, y*Ratio+Ratio/4, Ratio/4, Ratio/4); 
                            g.fillOval(x*Ratio+Ratio/4, y*Ratio+Ratio/4, Ratio/4, Ratio/4);
                            g.setColor(Color.BLACK);
                        }
                        break;
                    case '#': g.fillRect(x*Ratio, y*Ratio, Ratio, Ratio); 
                        break;                  
                    case ' ': 
                        break;
                    default: 
                        Font font = new Font("Arial", Font.BOLD, 20);
                        g.setFont(font);               
                        g.setColor(Color.RED);
                        g.drawString(c + "" , x*Ratio + 8, y*Ratio + 14);
                        g.setColor(Color.BLACK);
                }
            }
        }
        
        g.setColor(new Color(255, 215, 0));
        g.fillArc((int)(world.pacx*Ratio), (int)(world.pacy*Ratio), Ratio, Ratio, 6*world.pacmouth, 360-2*6*world.pacmouth);
        g.setColor(Color.BLACK);
        g.drawArc((int)(world.pacx*Ratio), (int)(world.pacy*Ratio), Ratio, Ratio, 6*world.pacmouth, 360-2*6*world.pacmouth);
        g.fillOval((int)(world.pacx*Ratio)+Ratio/3, (int)(world.pacy*Ratio)+Ratio/4, Ratio/10, Ratio/10);
        
        for (Ghost ghost : world.ghosts) {
            g.setColor(ghost.color);
            g.fillOval((int)(ghost.x*Ratio), (int)(ghost.y*Ratio), Ratio, Ratio);
            g.fillRect((int)(ghost.x*Ratio), (int)(ghost.y*Ratio) + Ratio/2, Ratio, Ratio/2);
            g.setColor(Color.WHITE);
            g.fillOval((int)(ghost.x*Ratio) + Ratio/3 - Ratio/10, (int)(ghost.y*Ratio) + Ratio/2 - Ratio/10, Ratio/5, Ratio/5);
            g.fillOval((int)(ghost.x*Ratio) + 2*(Ratio/3) - Ratio/10, (int)(ghost.y*Ratio) + Ratio/2 - Ratio/10, Ratio/5, Ratio/5);
        }
    }
}

