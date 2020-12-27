package pacman;

import java.awt.Color;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Sound sd = new Sound(0);
        sd.loop();
        
        boolean continueGame = false;
        
        CombineAll game = new CombineAll("PacMan");
        
        
        continueGame = game.playGame();  
        
        
        game.dispose();
        
        
        while (continueGame) {
            
            CombineAll game2 = new CombineAll("PacMan");
            continueGame = game2.playGame();
            game2.dispose();
        }
        
        sd.stop();
    }

}
