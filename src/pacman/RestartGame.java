package pacman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RestartGame extends JDialog {
    
    public JButton restart;
    public JButton quitGame;
    public static boolean boolRestart = true;
    public static boolean boolQuitGame = true;
    
    /**
     * @param i corresponds to the type of death
     * 1 - death by execution
     * 2 - win by completion
     * 3 - death eaten by ghost
     */
    public RestartGame(JFrame parent, String title, int i) {
        super(parent, title, true);
        this.setLayout(new BorderLayout());
        
        if (parent != null) {
            Dimension parentSize = parent.getSize();
            Point p = parent.getLocation();
            setLocation(p.x + parentSize.width/4, p.y + parentSize.height/4);
        }        
        
        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout());
        
        String picfile = "gameover.png";
       
        if (i == 1) {
            picfile = "gameover.png";
        } else if (i == 2) {
            picfile = "marry.jpg";
        } else {}
        
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(picfile));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        JLabel picLabel = new JLabel();                   
        
        picLabel.setIcon(new ImageIcon(myPicture));
        topPanel.add(picLabel, SwingConstants.CENTER);
        topPanel.repaint();

        restart = new JButton("Restart");
        restart.addActionListener(new RestartGameListener());
        quitGame = new JButton("Quit Game");
        quitGame.addActionListener(new RestartGameListener());
        bottomPanel.add(restart);
        bottomPanel.add(quitGame);
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
                
        
    }
    
    public class RestartGameListener implements ActionListener {        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == restart) {
                RestartGame.boolRestart = true;
                RestartGame.this.dispose();
            } else if (e.getSource() == quitGame) {
                RestartGame.boolRestart = false;
                RestartGame.this.dispose();
            } else {}           
        }
    }
    
}
