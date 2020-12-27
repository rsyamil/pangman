package pacman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** This class requests player of category and level of hardness */
/** Category 1-7 Hardness 1-3 */

public class StartWindow extends JDialog {
    
    private int hardness;
    private int category;
    private JComboBox categoryBox;
    private JComboBox hardnessBox;
    private JButton startButton;
    private JLabel header = new JLabel();    
    
    public StartWindow(JFrame parent, String title) {
        super(parent, title, true);
        if (parent != null) {
            Dimension parentSize = parent.getSize();
            Point p = parent.getLocation();
            setLocation(p.x, p.y);
        }        
        
        JPanel topPanel;                /** stores the welcome photo */
        topPanel = new JPanel(new FlowLayout());
        JPanel middlePanel;             /** Ask user for Category of words */    
        middlePanel = new JPanel(new FlowLayout());
        JPanel bottomPanel;             /** Ask user for level of difficulty */
        bottomPanel = new JPanel(new BorderLayout());
        JPanel subBottomPanel1;
        subBottomPanel1 = new JPanel(new FlowLayout());
        JPanel subBottomPanel2;
        subBottomPanel2 = new JPanel(new FlowLayout());
        
        /** Set Up Top Panel */
        BufferedImage headerPicture = null;
        
        try {
            headerPicture = ImageIO.read(new File("HangMan.png"));         
            header = new JLabel();               
            header.setIcon(new ImageIcon(headerPicture));
            topPanel.add(header);

        } catch (Exception e8) {
            JOptionPane.showMessageDialog(StartWindow.this, "Header Image missing, is it in the same directory?",
                    "Bad Image File", JOptionPane.ERROR_MESSAGE);
        }
        
        
        /** Set Up middle Panel */
        String[] categories = {"Vehicle", "Sport", "Fruit", "Countries", "Brands", "Cities", "Animals"};
        categoryBox = new JComboBox(categories);
        categoryBox.setSelectedIndex(-1);        
        middlePanel.add(new JLabel("Please select a category for words: ", SwingConstants.RIGHT));         
        middlePanel.add(categoryBox);
        
        /** Set Up bottom Panel */
        String[] hardnessLevel = {"Easy", "Intermediate", "Hard"};
        hardnessBox = new JComboBox(hardnessLevel);
        hardnessBox.setSelectedIndex(-1);
        startButton = new JButton("Start Game");
        startButton.addActionListener(new StartWindowListener());
        subBottomPanel1.add(new JLabel("Please select hardness level: ", SwingConstants.RIGHT));
        subBottomPanel1.add(hardnessBox);
        subBottomPanel2.add(startButton);
        bottomPanel.add(subBottomPanel1, BorderLayout.NORTH);
        bottomPanel.add(subBottomPanel2, BorderLayout.SOUTH);
        
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.pack(); 
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
        
    }
        
    public class StartWindowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
                /** check if player has selected both category and hardness */
                int check = 0;
                
                 if (categoryBox.getSelectedIndex() == -1) {
                     JOptionPane.showMessageDialog(StartWindow.this, "Please select a category",
                             "Error", JOptionPane.WARNING_MESSAGE);
                     check++;
                 } 
                 if (hardnessBox.getSelectedIndex() == -1) {
                     JOptionPane.showMessageDialog(StartWindow.this, "Please select a hardness level",
                             "Error", JOptionPane.WARNING_MESSAGE);
                     check++;
                 }               
                 
                 if (check == 0) {
                     Pacman.hardness = hardnessBox.getSelectedIndex() +1;
                     //System.out.println("Hardness Level : " + Pacman.hardness);
                     Pacman.category = categoryBox.getSelectedIndex();
                     //System.out.println("Category Level : " + Pacman.category);
                     StartWindow.this.dispose();
                 }             
            }           
        }   
    }
    

}





