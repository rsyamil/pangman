package pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HangmanPanel extends JPanel {
    char[] wrongChar;
    int countChar = 0;
    
    BufferedImage myPicture = null;
    JLabel picLabel;
    JLabel txtLabel;
    String[] picShape = {"none.png","pic1.png", "pic2.png", "pic3.png", 
                "pic4.png", "pic5.png", "pic6.png", "pic7.png", 
                "pic8.png", "pic9.png", "pic10.png", };
    
    /*
     * initialize the vector that stores the shape
     */
    public HangmanPanel(){
        setBorder(BorderFactory.createLineBorder(Color.white));
        this.setLayout(new BorderLayout());
        
        wrongChar = new char[34];
        for (int i = 0; i < 34; i++){
            wrongChar[i] = ' ';
        }
        
        picLabel= new JLabel();
        txtLabel = new JLabel("");
        try {
            myPicture =  ImageIO.read(new File(picShape[0]));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        picLabel.removeAll();
        picLabel.setIcon(new ImageIcon (myPicture));
        this.add(txtLabel,BorderLayout.NORTH);
        this.add(picLabel, BorderLayout.SOUTH);
    }
    
     
    public Dimension getPreferredSize() {
        return new Dimension(400,400);
    }
    
    public void addChar(char i){
        wrongChar[countChar] = i;
        //wrongChar[++countChar] = ' ';
        countChar += 2;
        txtLabel.setFont(new Font("Trebuchet MS",Font.BOLD, 30));
        txtLabel.setText(new String(wrongChar));
    }
    
    /*
     * add shape according to the number of wrong letter taken by pacman
     */
    public void addShape(int i){
        try{
            
            myPicture =  ImageIO.read(new File(picShape[i]));
            picLabel.removeAll();
            picLabel.setIcon(new ImageIcon (myPicture));

        }catch(Exception e){
            
        }
    }
    
    public void addItem(int i, char a){
        addShape(i);
        addChar(a);
        
    }
    
    public void remove(int x){
        try{
            if(x>0){
            myPicture =  ImageIO.read(new File(picShape[x-1]));
            picLabel.removeAll();
            picLabel.setIcon(new ImageIcon (myPicture));
            }

        }catch(Exception e){}
        
    }
    
    public void reset(){
        
        try{
            
            myPicture =  ImageIO.read(new File(picShape[0]));
            picLabel.removeAll();
            picLabel.setIcon(new ImageIcon (myPicture));

        }catch(Exception e){}
    
        for( int i= 0; i <wrongChar.length; i++){
            wrongChar[i] = ' ';
        }
        txtLabel.setFont(new Font("Trebuchet MS",Font.BOLD, 30));
        txtLabel.setText(new String(wrongChar));
        
        countChar = 0;
    }
    
    
    

}
