package pacman;

import java.awt.Color;
import java.util.ArrayList;

public class Ghost {
    
    public float x;    /** use float here, save space, don't need too much precision */
    public float y;
    public int dir;
    public Color color;    
    
    /**
     * Constructor for ghost
     * @param x     x-coordinate of ghost
     * @param y     y-coordinate of ghost
     * @param c     color of the ghost
     */
    public Ghost(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        color = c;
    }
    
    //new stuff
    public ArrayList<cell> q = new ArrayList<cell>();
    public class cell {
char c;
     float x,y;
     int dist;
     public  cell(char c, float x, float y,int dist){
         this.c=c;
         this.x=x;
         this.y=y;
   
       
         this.dist=dist;
     }
    }
    /**
     * This function sets the direction for the ghosts
     * by taking in the current world. Very simple
     * arithmetic, can insert more complex algorithm later
     * @param world     current view of the board
     */   
    
    //add point on east from x,y
    public void addEast(ArrayList<ArrayList<cell>> tempboard,Pacman world,float cellx,float celly, int dist){
        if (world.getCell(Math.round(cellx+1),Math.round(celly))!='#' &&
                tempboard.get(Math.round(celly)).get(Math.round(cellx+1)).dist<dist &&
                tempboard.get(Math.round(celly)).get(Math.round(cellx+1)).dist==0){
            cell eCell =new cell('.',Math.round(cellx+1),Math.round(celly),dist);
            tempboard.get(Math.round(celly)).set(Math.round(cellx+1),  eCell);
            q.add(eCell);
            //System.out.println(eCell.x+" "+eCell.y);
        }  
    }
    public void addNorth(ArrayList<ArrayList<cell>> tempboard,Pacman world,float cellx,float celly, int dist){
        if (world.getCell(Math.round(cellx),Math.round(celly-1))!='#' &&
                tempboard.get(Math.round(celly-1)).get(Math.round(cellx)).dist<dist &&
                tempboard.get(Math.round(celly-1)).get(Math.round(cellx)).dist==0){
            cell nCell =new cell('.',Math.round(cellx),Math.round(celly-1),dist);
            tempboard.get(Math.round(celly-1)).set(Math.round(cellx),  nCell);
            q.add(nCell);
           //System.out.println(nCell.x+" "+nCell.y);
        }
    }
    public void addSouth(ArrayList<ArrayList<cell>> tempboard,Pacman world,float cellx,float celly, int dist){
        if (world.getCell(Math.round(cellx),Math.round(celly+1))!='#' &&
                tempboard.get(Math.round(celly+1)).get(Math.round(cellx)).dist<dist &&
                tempboard.get(Math.round(celly+1)).get(Math.round(cellx)).dist==0){
            cell sCell =new cell('.',Math.round(cellx),Math.round(celly+1),dist);
            tempboard.get(Math.round(celly+1)).set(Math.round(cellx),  sCell);
            q.add(sCell);
           //System.out.println(sCell.x+" "+sCell.y);
        }
    }
    public void addWest(ArrayList<ArrayList<cell>> tempboard,Pacman world,float cellx,float celly, int dist){
        if (world.getCell(Math.round(cellx-1),Math.round(celly))!='#' &&
                tempboard.get(Math.round(celly)).get(Math.round(cellx-1)).dist<dist &&
                tempboard.get(Math.round(celly)).get(Math.round(cellx-1)).dist==0){
            cell wCell =new cell('.',Math.round(cellx-1),Math.round(celly),dist);
            tempboard.get(Math.round(celly)).set(Math.round(cellx-1),  wCell);
            q.add(wCell);
            //System.out.println(wCell.x+" "+wCell.y);
        }
    }
    
    public int backtrace(ArrayList<ArrayList<cell>> tempboard, cell temp){
        int dir=0;
        cell c= new cell(temp.c, temp.x,temp.y,temp.dist);
        while (c.dist!=1){
          //north
          if (tempboard.get((int)(c.y-1)).get((int)(c.x)).dist==(c.dist-1)){
              //System.out.println("n");
              c.dist=c.dist-1;
              c.y=c.y-1;
             
          }
          //east
          else if (tempboard.get((int)(c.y)).get((int)(c.x+1)).dist==(c.dist-1)){
              //System.out.println("e");
              c.dist=c.dist-1;
              c.x=c.x+1;
          }
          //south
          else if (tempboard.get((int)(c.y+1)).get((int)(c.x)).dist==(c.dist-1)){
              //System.out.println("s");
              c.dist=c.dist-1;
              c.y=c.y+1;
          }
          //west
          else if (tempboard.get((int)(c.y)).get((int)(c.x-1)).dist==(c.dist-1)){
              //System.out.println("w");
              c.dist=c.dist-1;
              c.x=c.x-1;
          }
        }
        //System.out.println("c: "+c.x+ " "+c.y+ " "+c.dist);
        if (Math.abs(x-c.x)<0.5 && (y-c.y)>0)
            dir=1;
        else if (Math.abs(x-c.x)<0.5 && (y-c.y)<0)
            dir=3;
        else if (Math.abs(y-c.y)<0.5 && (x-c.x)>0)
            dir=4;
        else if (Math.abs(y-c.y)<0.5 && (x-c.x)<0)
            dir=2;
        return dir;
        
        
        
    }
    public void chooseDir(Pacman world) {
        //System.out.println("ghost: "+x+" "+y);
        //System.out.println("pac: "+world.pacx+" "+world.pacy);
               
        if (Math.abs(x-world.pacx)<0.2 && Math.abs(y-world.pacy)<0.2)
            dir = 0;
        else {
           final ArrayList<ArrayList<cell>> tempboard=new ArrayList<ArrayList<cell>>(); 
           char d;
           for ( float row = 0 ; row < world.board.size(); row++ ) {                                 
               ArrayList<cell> temp = new ArrayList<cell>();
               for (float col = 0; col < world.board.size(); col++){
                   //if (world.board.get((int)(row)).get((int)(col))!='#') {
                   d=world.board.get((int)(row)).get((int)(col));
                       cell tempcell = new cell(d,row,col,0); 
                       
                       temp.add(tempcell);
                   }
               tempboard.add(temp);
           }
           cell tempcell=new cell('.',Math.round(y),Math.round(x),7);//place ghost
           tempboard.get(Math.round(y)).set(Math.round(x),  tempcell);
                  
          boolean foundPacman =false;
         
          cell temp;
          q.clear();
          //System.out.println("size: "+q.size());
          addEast(tempboard,world,x,y,1);
          addSouth(tempboard,world,x,y,1);
          addWest(tempboard,world,x,y,1);
          addNorth(tempboard,world,x,y,1);
          
          //System.out.println("1");
          
          int dist;
          while (!foundPacman){
          
          
            if (!q.isEmpty()){
               temp =q.get(0);
               
               q.remove(0);
                //System.out.println("temp: "+temp.x+" "+temp.y);
                
               if (Math.abs(Math.round(temp.x-world.pacx))<0.5 && Math.abs(Math.round(temp.y-world.pacy))<0.5){
                   //System.out.println("found pacman");
                  
                   
                       foundPacman=true;
                       
                  
                   /*
                   for ( int row = 0 ; row < tempboard.size(); row++ ){//rows
                       for ( int col = 0; col<tempboard.get(0).size(); col++ ){//columns
                           System.out.print(tempboard.get(row).get(col).dist+ "  ");
                       }
                       
                       System.out.println();
                   }
                   */
                   dir = backtrace(tempboard,temp);
                   //System.out.println("dir: "+dir);
                   break;
               }
               dist=temp.dist;
               //System.out.println("dist: "+dist);
               addEast(tempboard,world,temp.x,temp.y,dist+1);
               addSouth(tempboard,world,temp.x,temp.y,dist+1);
             addWest(tempboard,world,temp.x,temp.y,dist+1);
               addNorth(tempboard,world,temp.x,temp.y,dist+1);
               //System.out.println("1");
            }
            
          }
          
        }
        
    }    
    
}
