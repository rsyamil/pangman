package pacman;

import java.util.ArrayList;
import java.util.Random;
public class Token {

    /**
     * @param args
     */
    public Token (ArrayList<ArrayList<Character>> board, int intype){
        Random r = new Random();       
        int randomInt, randomInt2;
        
        
        char [] types ={'@','$','%'};
        char c;
        
        randomInt = r.nextInt(17);
        randomInt2 = r.nextInt(17);
        
        
        c=types[intype];
        if (board.get(randomInt+1).get(randomInt2+1) != '#'){
            board.get(randomInt+1).set(randomInt2+1, c);                
        } else {
          while (board.get(randomInt+1).get(randomInt2+1) == '#') {
              randomInt= r.nextInt(17);
          }
          board.get(randomInt+1).set(randomInt2+1, c);                
        }            
        
        
    }

}
