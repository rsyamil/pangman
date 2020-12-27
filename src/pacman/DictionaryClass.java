package pacman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.lang.String;

class wordStruct {  
    public int Difficulty;
    public String theWord;    
}

public class DictionaryClass {

    /** Public Variables */
    
    public static char[] wordWithBlanks;    
    static public String fullWord;    
    public int Difficulty;    
    private int counterBlanks = 0;    
    private int totalBlanks = 0;    
    
    /** Private Variables */
    
    private ArrayList< ArrayList<wordStruct>> Category = new ArrayList< ArrayList<wordStruct>>();    
    private ArrayList<String> Word = new ArrayList<String>();
    private boolean isComplete = false;   
    Random rand = new Random();                  
    
    /** Functions */
    
    public DictionaryClass(int hardLVl, int catLvl) {        
          try {            
              BufferedReader in = new BufferedReader(new FileReader("CategoryWords.txt"));              
              ArrayList<wordStruct> temp = new ArrayList<wordStruct>();              
              int counter = 0;
              
              while (in.ready()) {             
                  String wholeLine = in.readLine();  
                  wholeLine = wholeLine.toLowerCase();
                  String delims = "[-]";
                  
                  if (wholeLine.charAt(0) == ':') {                  
                      Category.add(temp);
                      temp = new ArrayList<wordStruct>();
                      continue;                  
                  } else if (wholeLine.charAt(0) == '=') {              
                      Category.add(temp);
                      temp = new ArrayList<wordStruct>();
                      break;                  
                  } else {                
                      String[] tokens = wholeLine.split(delims);
                      wordStruct wordIn = new wordStruct();
                      wordIn.Difficulty = Integer.parseInt(tokens[1]);                      
                      wordIn.theWord = tokens[0];                  
                      temp.add(wordIn);                  
                  }                   
              }              
          } catch (Exception A) {            
              System.out.println("Wrong File type");
              return;
          }        
          Populate(catLvl, hardLVl);
    }
    
    /**
     * Check if letter is contained in the word
     * @param Letter
     * @return
     */
    boolean letterIsContained(char Letter){
        boolean check = false;
        
        for (int i = 0; i < fullWord.length(); i++) {        
            if (fullWord.charAt(i) == Letter) {                
                if (wordWithBlanks[i] == Letter) {                
                    break;                   
                }                
                wordWithBlanks[i] = Letter;
                counterBlanks--;                  
                check = true;           
            }            
        }    
        if (check) {
            Sound tmp = new Sound(1);
            tmp.play();
        } else {
            Sound tmp = new Sound(2);
            tmp.play();
        }
        return check;
    }       
    
    /** @return true if word completed */
    boolean wordIsFinished(){    
        for (int i = 0; i < this.fullWord.length(); i++) {
            if (DictionaryClass.wordWithBlanks[i] == '_') {
                return false;
            }
        }
        return true;
    }
    
    void OutputCat() {      
        for(int i = 0; i < Word.size(); i++) {        
              System.out.println(Word.get(i));         
        }
    }
    
    void OutputWord() {      
        System.out.println(fullWord);        
    }
    
    public String getFullWord() {
        System.out.println(fullWord);
        return fullWord;
    }
    
    static char wildCardCall() {
        
        for(int i = 0; i < fullWord.length(); i++){
          
            if(fullWord.charAt(i) != wordWithBlanks[i]){
              
                return fullWord.charAt(i);
                
            }
        }
        
        char A = '0';
        
        return A;
    }
    
    void OutputBlank() {      
      for(int i = 0; i < fullWord.length(); i++) {      
        System.out.print(wordWithBlanks[i]);
      }
      System.out.println();        
    }
    
    void Populate(int catNumb, int theDiff) {          
          for (int i = 0; i < Category.get(catNumb).size(); i++) {            
              if (Category.get(catNumb).get(i).Difficulty == theDiff) {                
                  Word.add(Category.get(catNumb).get(i).theWord);              
              }                
          }        
          fullWord = Word.get(rand.nextInt(Word.size()));
          makeBlanked();        
    }
    
    void makeBlanked(){
        
        wordWithBlanks = fullWord.toCharArray();
                
      if(fullWord.length() < 5){
         
          for (int z = 0; z < 2; z++){
              
              rand = new Random();
              int foo = rand.nextInt(fullWord.length()-1);
              if(wordWithBlanks[foo] == '_' && z < 2){
                  z--;
                  continue;
              }
              else{
              
                  for(int i = 0; i < fullWord.length(); i++){
                    
                      System.out.print(i);
                      if(i == foo){
                        
                          continue;
                          
                      }
                      if(Character.toLowerCase(wordWithBlanks[i]) == Character.toLowerCase(wordWithBlanks[foo])){
                        
                          wordWithBlanks[i] = '_';
                          z++;
                      }
                      
                  }
                      
                  wordWithBlanks[foo] = '_';
              }
          
          }

      }
      else if (fullWord.length() < 8) {
          
          for (int z = 0; z < 4; z++){
              
              
              rand = new Random();
              int foo = rand.nextInt(fullWord.length()-1);
              if(wordWithBlanks[foo] == '_' && z < 4){
                  z--;
                  continue;
              }
              else{
                
                  for(int i = 0; i < fullWord.length(); i++){
                    
                      System.out.print(i);
                      
                      if(i == foo){
                        
                          continue;
                          
                      }
                      
                      if(Character.toLowerCase(wordWithBlanks[i]) == Character.toLowerCase(wordWithBlanks[foo])){
                        
                          wordWithBlanks[i] = '_';
                      
                      }
                      
                  }
                  wordWithBlanks[foo] = '_';
              }
          
          }
          
      }
      else {
          
          for (int z = 0; z < 5; z++){
              
              rand = new Random();
              int foo = rand.nextInt(fullWord.length()-1);
              if(wordWithBlanks[foo] == '_' && z < 5){
                  z--;
                  continue;
              }
              else{
                
                  for(int i = 0; i < fullWord.length(); i++){
                      
                      System.out.print(i);
                      
                      if(i == foo){
                        
                          continue;
                          
                      }
                      
                      
                      if(Character.toLowerCase(wordWithBlanks[i]) == Character.toLowerCase(wordWithBlanks[foo])){
                        
                          wordWithBlanks[i] = '_';
                      
                      }
                      
                  }
                  wordWithBlanks[foo] = '_';

              }
          
          }
        
      }
        
    
    
    }
    
    /** a function to return the percentage solved, return 1-10 */
    public int returnPercentSolved() {
        double num = (10)*(counterBlanks*(1.0)/totalBlanks*(1.0));
        return (int)num;              
    }
       
    public char[] viewBlanked() {
         return wordWithBlanks;                    
    }
}

