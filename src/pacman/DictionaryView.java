package pacman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.lang.String;

import static java.lang.System.out;

public class DictionaryView extends JPanel
{    
    JLabel blankedWord;
    
    public DictionaryView(char[] word){
        
        setLayout(new FlowLayout());
        
        char[] modify;
        
        String theBlanked = new String(word);
        
        modify = new char[theBlanked.length()*2];
        
        for(int i = 0; i < theBlanked.length(); i++) {            
            modify[i*2] = word[i];
            modify[i*2+1] = ' ';            
        }
        
        String newBlanked = new String(modify);
        
        blankedWord = new JLabel(newBlanked);
        blankedWord.setFont(new Font("Trebuchet MS", Font.PLAIN, 50));
        
        this.add(blankedWord);
    
    }
    
    public void Update() {   
        char[] newWord = DictionaryClass.wordWithBlanks;
        
        String newWordStr = new String(DictionaryClass.wordWithBlanks);
        
        char[] modify;
        modify = new char[newWordStr.length()*2];
        
        for(int i = 0; i < newWordStr.length(); i++) {            
            modify[i*2] = newWord[i];
            modify[i*2+1] = ' ';            
        }
        
        String readyNewWordStr = new String(modify);
        
        blankedWord.setText(readyNewWordStr);
        this.revalidate();        
    }    
}


