package pacman;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PauseFrame extends JDialog {
    
    JButton okay;

    public PauseFrame(JFrame world, String title) {
        super(world, title, true);
        this.setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        
        JLabel label = new JLabel("Press OK to resume game");
        
        topPanel.add(label);
        
        okay = new JButton("OK");
        
        bottomPanel.add(okay);
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);  
        
        
    }
    
}
