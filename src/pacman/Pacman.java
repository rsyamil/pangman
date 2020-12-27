package pacman;

import java.util.Random;
/**
 * initilized the PacMan game 
 */

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.lang.Character;

public class Pacman {
    
    /** At Start-Up */
    
    public boolean pauseGame = false;
    
    int points=0;
    int randTime=0;
    boolean timeSlowPlaced=false;
    int timeSlowedAt=0;
    public char wildChar;
    public static int hardness;
    public static int category;   
    
    public double speedHorizontalPac = .1;
    public double speedVerticalPac = .1;
    
    public double speedHorizontalGhost = .08;
    public double speedVerticalGhost = .08;
    
    public ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();     /** holds the board */
    public ArrayList<Ghost> ghosts = new ArrayList<Ghost>();                                  /** stores the ghost */
    
    public int time = 0;
    public float pacx = 3, pacy = 3;                                                          /** initial loc for PacMan*/               
    
    public final static int STILL = 0, UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;
    
    public int pacdir = RIGHT;                                                                /** initially pacman moves right */
    public int nextpacdir = pacdir;
    public int pacmouth = 10;                                                                 /** controls the tempo/size of the mouth */
    private boolean closing = true;  
    public static char curChar;                                                                      /** current char eaten */
    public static boolean isAlpha = false;
    public boolean die = false;
    
    private String startboarddemo= 
            
            "###################\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "#.................#\n"
          + "###################";
    
    public Pacman()
    {
        for (String row : startboarddemo.split("\n")) {                 /** use REGEXP and split the board line by line */                
            ArrayList<Character> temp = new ArrayList<Character>();
            for (int i = 0; i < row.length(); i++)
                temp.add(row.charAt(i));
            
            board.add(temp);
        }
        
        ghosts.add(new Ghost(3, 10, new Color(173, 255, 47)));                    /** adds two ghosts, depending on difficulty, add more */
        ghosts.add(new Ghost(10, 15, new Color(205, 92, 92)));
        ghosts.add(new Ghost(17, 17 , new Color(0, 206, 209)));
        
    }

    public int getHeight() {
        return board.size();
    }

    public int getWidth() {
        return board.get(0).size();
    }
    
    private void setCell(int x, int y, char c) {
        board.get(y).set(x,  c);
    }

    public char getCell(int x, int y) {
        return board.get(y).get(x);
    }
        
    /**
     * time counter. nextpacdir is determined by what key is pressed by player
     */
    public void updateBoard()
    {
        if (pauseGame) {
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {}         
            pauseGame = false;
        }  
        
        isAlpha = false;
        time += 1;
        
        /** overwrites the direction */
        if (pacdir == RIGHT && nextpacdir == LEFT) {
            pacdir = LEFT;
        } else if (pacdir == LEFT && nextpacdir == RIGHT) {
            pacdir = RIGHT;
        } else if (pacdir == UP && nextpacdir == DOWN) {
            pacdir = DOWN;
        } else if (pacdir == DOWN && nextpacdir == UP) {
            pacdir = UP;
        } else if (Math.abs(pacx - (int)pacx) < .1 && Math.abs(pacy - (int)pacy) < .1) {
            pacdir = nextpacdir;
        } else {}
        
        
        if (pacmouth == 0) {
            closing = false;
        } else if (pacmouth == 10) {
            closing = true;
        } else {}
        
        if (closing) {
            pacmouth--;
        } else {
            pacmouth++;
        }
        
        int x = Math.round(pacx);
        int y = Math.round(pacy);
        
        this.curChar = getCell(x,y);       
        
        if (curChar == '.') {
            setCell(x,y, ' ');     /** for every '.' eaten, set it to empty */
            points++;
            //System.out.println(points);
            if (points==5){
                Token token =new Token(board,1);
            }
        }
        if (curChar=='%'){//time slow
            Sound tmp = new Sound(3);
            tmp.play();
          
            setCell(x,y, ' ');
            speedHorizontalPac=.01;
            speedVerticalPac=.01;
            speedHorizontalGhost=.008;
            speedVerticalGhost=.008;
            timeSlowedAt=time;
        }
        if (time==(timeSlowedAt+100)){//for 5 sec
            speedHorizontalPac=.1;
            speedVerticalPac=.1;
            speedHorizontalGhost=.08;
            speedVerticalGhost=.08;
            //System.out.println("time normal");
        }
        if (curChar=='$'){
            Sound tmp = new Sound(3);
            tmp.play();
            
            setCell(x,y, ' ');
            //System.out.println();
             wildChar=DictionaryClass.wildCardCall();
             CombineAll.output.setText("Wildcard Free Letter! :  " + Character.toUpperCase(wildChar) );
             
            
        }
        if (curChar == '@'){
            Sound tmp = new Sound(3);
            tmp.play();
            setCell(x,y, ' ');     
            
            
            
            //get back a limb
        }
        
        if (time>500 && !timeSlowPlaced){
            if (randTime==0){
            Random randomGenerator = new Random();
            
            int randomInt;
            randomInt=randomGenerator.nextInt(300);
            randTime=500+randomInt;
            }
            else {
                Token token2 =new Token(board,2);
                //System.out.println(" time slow token places");
                timeSlowPlaced=true;
            }
        }
        
        
        /** for eaten alphabets, update here */
        
        if (Character.isLetter(curChar)) {
            isAlpha = true;
            
            /** then give to HangMan and Dictionary */
            setCell(x,y, ' ');              
        }
        
        /** pacman can't move over walls or '#' in this case */     
        
        if ((pacdir == RIGHT && getCell(x+1,y) == '#' && pacx >= x) 
            || (pacdir == LEFT && getCell(x-1,y) == '#' && pacx <= x)
            || (pacdir == UP && getCell(x,y-1) == '#' && pacy <= y)
            || (pacdir == DOWN && getCell(x,y+1) == '#' && pacy >= y)) {
            pacdir = STILL;                                                 
            pacx = x;
            pacy = y;
        }
        
        /** controls the speed of pacman, 0.1 is probably reasonable...
         * we can increase the speed corresponding to level of hardness
         */                        
        if (pacdir == RIGHT) {
            pacx += speedHorizontalPac;
        } else if (pacdir == LEFT) {
            pacx -= speedHorizontalPac;
        } else if (pacdir == UP) {
            pacy -= speedVerticalPac;
        } else if (pacdir == DOWN) {
            pacy += speedVerticalPac;
        } else {}
        
        for (Ghost g : ghosts) {
            /** decide when to change direction for ghost, x is in float and has 7 decimal points.
             * when ghost is about to move to next cell, we check the direction.
             */
            
            x = Math.round(g.x);
            y = Math.round(g.y);
            
            /** ghosts can't move over walls 
            if ((g.dir == RIGHT && getCell(x+1,y) == '#' && g.x >= x) 
                    || (g.dir == LEFT && getCell(x-1,y) == '#' && g.x <= x)
                    || (g.dir == UP && getCell(x,y-1) == '#' && g.y <= y)
                    || (g.dir == DOWN && getCell(x,y+1) == '#' && g.y >= y)) {
                
                    // make sure that ghost is moving forward 
                    if (g.dir == UP) {
                        g.dir = DOWN;
                    }
                    if (g.dir == DOWN) {
                        g.dir = UP;
                    }
                    if (g.dir == LEFT) {
                        g.dir = RIGHT;
                    }
                    if (g.dir == RIGHT) {
                        g.dir = LEFT;
                    }
                    g.x = x;
                    g.y = y;
            } */
            
            if (Math.abs(g.x - (int)g.x) < .1 && Math.abs(g.y - (int)g.y) < .1) {
        if (((int)g.x == (int)this.pacx) && ((int)g.y == (int)this.pacy)) {                    
                    this.die = true; 
                }      
               g.chooseDir(this);
            }
            
            /** ghost update speed, will be slower than pacman */
            if (g.dir == LEFT)
                g.x -= speedHorizontalGhost;
            else if (g.dir == RIGHT)
                g.x += speedHorizontalGhost;
            else if (g.dir == UP)
                g.y -= speedVerticalGhost;
            else
                g.y += speedVerticalGhost;
        }
    }
    
    public void increaseSpeedGame() {
        speedHorizontalPac = .2;
        speedVerticalPac = .2;
        
        speedHorizontalGhost = .16;
        speedVerticalGhost = .16;
        
    }

    public boolean gameOver() {
        return this.die;
    }
}
