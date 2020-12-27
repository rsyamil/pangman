package pacman;

import java.util.Random;
import java.util.ArrayList;

public class Maze {
    // changed private ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();  in Pacman.java
    // ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();
    // changed map to 19x19 (from 19x20)    
    // word length has to be <=17
    public String word;
    public char[] wordWithBlanks;
    public String toMaze = "";
    
    public void setWord(String inWord) {
        word = inWord;        
    }    
    
    public void setWordWithBlanks(char[] inWord) {
        wordWithBlanks = inWord;
    }
    
    public void createMaze(ArrayList<ArrayList<Character>> board) {
        /**
         * creates 3 horseshoe walls (square with one side absent) each at different distance from the center
         * direction is chosen randomly (randomInt)
         * "holes" in the walls are set by randomInts as well
         * I tried to create maze easy where pacman could easily get away from ghosts and "eat" letters
         * Im not sure how difficulty would affect the shape of the maze
         */
        Random randomGenerator = new Random();       
        int randomInt, randomInt2, randomInt3, randomInt4;          
        char pound = '#';        
        
        randomInt = randomGenerator.nextInt(4);
        randomInt2 = randomGenerator.nextInt(4);
        randomInt3 = randomGenerator.nextInt(4);
        randomInt4 = randomGenerator.nextInt(4);
        for ( int row = 0 ; row < board.size(); row++ ){            //rows
            for ( int col = 0; col<board.get(0).size(); col++ ){    //columns

                    if ((row < 17) && (row > 1) && ((col == 2) || (col == 16)) && (row != (3 + randomInt*2)) && (row != (9 + randomInt*2)))
                        board.get(row).set(col,  pound);
                    
                    if ((col < 17) && (col > 1) && ((row == 2) || (row == 16)) && (col != (3 + randomInt3*2)) && ( col != (9 + randomInt3*2)))
                        board.get(row).set(col,  pound);
           
                    // middle
                    if ( (row < 15) && (row > 3) && ((col == 4) || (col == 14)) && (row != (5 + randomInt)) && (row != (10 + randomInt)))
                        board.get(row).set(col,  pound);

                    if ( (col < 15) && (col > 3) && ((row == 4) || (row == 14)) && (col != (5 + randomInt3)) && (col != (10 + randomInt3)))
                        board.get(row).set(col,  pound);

                    // inner
                    if ((row < 13) && (row > 5) && ((col == 6) || (col == 12)) && (row != (7 + randomInt)))
                        board.get(row).set(col,  pound);
                    
                     if ((col < 13) && (col > 5) && ((row == 6)|| (row == 12)) && (col != (7 + randomInt3)))
                         board.get(row).set(col,  pound);

                     if ( randomInt4 != 0 && (row < 11) && (row > 7) && (col == 8))
                         board.get(row).set(col,  pound);
                     if ( randomInt4 != 1 && (row < 11) && (row > 7) && (col == 10))
                         board.get(row).set(col,  pound);
                     if ( randomInt4 != 2 && (col < 11) && (col > 7) && (row == 8))
                         board.get(row).set(col,  pound);
                     if ( randomInt4 != 3 && (col < 11) && (col > 7) && (row == 10))
                         board.get(row).set(col,  pound);
            }
        }
        
        // Takes the word and adds random chars to it so that word's letters and random were placed on the board  
        // check stuff that is not in the word
        int realLength = word.length();       
        String missingChars = "";       /** has all the missing chars to complete the word */
        char [] charword = word.toCharArray();
        
        
        for (int i = 0; i < realLength; i++) {
            boolean noDup = true;
            int found = 0;
            if (wordWithBlanks[i] == '_') {
                // then check in missing char if it has already been added
                for (int k = 0; k < missingChars.length(); k++) {
                    char[] missingCharstemp = missingChars.toCharArray();
                    if (missingCharstemp[k] == charword[i]) {
                        found++;
                    }       
                    if (found >= 1) noDup = false;
                }                   
                if (noDup) missingChars = missingChars + charword[i];
            }        
        }
                
        // Keeps appending random char to the words
        // need to check if words are already in real word
        // also check if it has already been added
        while ((missingChars.length() + toMaze.length()) < 17) {
            boolean append = true;
            char c = (char)(randomGenerator.nextInt(26) + 'a'); 
            for (int i = 0; i < realLength; i++) {
                if (charword[i] == c) {
                    append = false;
                }
            }  
            char[] toMazeChar = toMaze.toCharArray();
            for (int i = 0; i < toMaze.length(); i++) {
                if (toMazeChar[i] == c) append = false;
            }            
            if (append) toMaze = toMaze + c;            
        }
        
        // word length now = 17
        //System.out.println("this is toMaze : " + toMaze);  
        
        //System.out.println("this is wordWithBlanks : " + new String(wordWithBlanks)); 
        
        //System.out.println("this is misingChar : " + missingChars);
        
        // combine alphabets missing from the word to random alphabets
               
        String combine = toMaze + missingChars;
        char[] combineChar = combine.toCharArray();
        
        // Mix letters of the word a little
        char temp;
        for (int j = 0; j < realLength; j++) {
            randomInt = randomGenerator.nextInt(17);
            temp = combineChar[j];
            combineChar[j] = combineChar[randomInt];
            combineChar[randomInt] = temp;            
        }
        
        word = String.valueOf(combineChar);
        
        
        // Place letters
        for (int i = 1; i <= 17; i++) {
            randomInt= randomGenerator.nextInt(17);
            /** place letters at random column in every row but on pacman's initial position */
            if (board.get(i).get(randomInt+1) != '#'){
                board.get(i).set(randomInt+1, word.charAt(i - 1));                
            } else {
              while (board.get(i).get(randomInt+1) == '#') {
                  randomInt= randomGenerator.nextInt(17);
              }
              board.get(i).set(randomInt+1, word.charAt(i - 1));                
            }            
        }        
    }
}
