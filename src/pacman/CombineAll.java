package pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CombineAll extends JFrame {
    
    Pacman world;     
    PacViewer screen;
    public static HangmanPanel hangman;
    DictionaryClass dictionary;
    DictionaryView dictionaryView;
    public static boolean restartGame = false;
    public static boolean quitThisGame = false;
    static JLabel output;
 
    public CombineAll(String title) {
        super(title);        
        
        world = new Pacman();
        screen = new PacViewer(world); 
        
        hangman = new HangmanPanel();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        screen.addKeyListener(new PacListener(world, this));
                
        JMenuBar menuBar;        
        JMenuItem menufile1;
        JMenuItem menufile2;
        JMenuItem menufile3;
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        menufile1 = new JMenuItem("Load Game");      
        menu.add(menufile1);
        menufile2 = new JMenuItem("Save Game");
        menu.add(menufile2);
        menufile3 = new JMenuItem("Exit Game"); 
        menu.add(menufile3);
        
        /** topPanel is the menuBar */
        JPanel topPanel;
        topPanel = new JPanel(new BorderLayout());
        //topPanel.add(menuBar, BorderLayout.NORTH);  
        
        /** Request user of the category that they want, and level of hardness */
        StartWindow start = new StartWindow(this, "Welcome");   
        
        start.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        /** Declare HangMan */ 
        //hangman.setSize(screen.getSize());
        //System.out.println("This is the size of hangman : " + hangman.getSize());
        
        /** Declare DictionaryClass */
        dictionary = new DictionaryClass(Pacman.hardness, Pacman.category);
        dictionaryView = new DictionaryView(dictionary.wordWithBlanks);  
        dictionaryView.setSize(400, 80);
        
        /** Declare middle Panel */        
        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.add(screen);
        middlePanel.add(hangman);
        middlePanel.setVisible(true);
        
        /** Declare bottom Panel */
        JPanel bottomPanel = new JPanel(new FlowLayout());
               
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("token.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        JLabel picLabel = new JLabel();          
        picLabel.setIcon(new ImageIcon(myPicture));
        bottomPanel.add(picLabel);
        bottomPanel.repaint();
        
        bottomPanel.add(dictionaryView);    
        
        /** Add all to app Frame */  
        setLayout(new BorderLayout());        
        this.add(topPanel, BorderLayout.NORTH); 
        topPanel.setVisible(true);
        this.add(middlePanel, BorderLayout.CENTER);
        middlePanel.setVisible(true);          
        
        /** add in wildcard label */
        output = new JLabel("Wildcard Free Letter! : ");
        bottomPanel.add(output);
        
        this.add(bottomPanel, BorderLayout.SOUTH);  
        bottomPanel.setVisible(true);
        this.pack();
        this.setVisible(true);              
        
        /** Maze */
        Maze maze = new Maze();
        maze.setWord(dictionary.getFullWord());
        maze.setWordWithBlanks(DictionaryClass.wordWithBlanks);
        maze.createMaze(world.board); 
        
        
    }
    
    public boolean playGame() {
        
        int hangmanCount = 0;
        
        while (!world.gameOver()) {

            if (dictionary.wordIsFinished()) {
                // death when word is finished
                
                Sound tmp = new Sound(4);
                tmp.play();
                          
                RestartGame res = new RestartGame(this, "Game Over", 2);
                restartGame = RestartGame.boolRestart;    
                res.setDefaultCloseOperation(DISPOSE_ON_CLOSE);                
                return restartGame;
            } 
          
            world.updateBoard();
            this.pack();
            
            if (Pacman.isAlpha) {
                char temp = Pacman.curChar;
                /** if letter is the right letter for the word */
                if (dictionary.letterIsContained(temp)) {                    
                    dictionaryView.Update();                                      
                } else {
                    hangmanCount++; 
                    hangman.addItem(hangmanCount, temp);   
                                    
                    if (hangmanCount == 10) {    
                        // death because of hangman
                        Sound hangmanOver = new Sound(5);
                        hangmanOver.play();
                        RestartGame res = new RestartGame(this, "Game Over", 1);
                        restartGame = RestartGame.boolRestart;  
                        res.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        
                        
                        /** GAME OVER if its equal to 10, add in extra stuff later */
                        return restartGame;
                    }
                }
            }                   
            screen.repaint();                        
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {}   
        } 
        // death because of Ghost
        
        Sound tmp = new Sound(5);
        tmp.play();
        
        RestartGame res = new RestartGame(this, "Game Over", 1);
        restartGame = RestartGame.boolRestart;  
        res.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        return restartGame;
    }     
       
    
}    
 
                
        
        

               

        


                





        


    



