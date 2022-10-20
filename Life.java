/**
 * @author Kunga Dolma
 * @version 10/6/22
 * @project name: Game of Life
 */

import java.util.Random;
import  java.util.Arrays;

import javax.swing.CellEditor;


// class name 
public class Life {
    // delcaring an array of cell, so that game can be played 
    private int [] arrayofCell;

    // creating a Constructor of life which has size as its parameter  
    public Life(int size){
        // put the parameter size in arrayofCell
        arrayofCell = new int[size];
        // import the random so the it can loop randomly
        Random random = new Random();
        // loop through each of arrayofCell 
        for(int i= 0; i<arrayofCell.length; i++){
        //after loops, assign the value of each index randomly , bound = value of 0,1
            arrayofCell[i]= random.nextInt(2); 
        }
    }
    
    // Created a method called isAlive which return boolean
    public boolean isALive(int i){
        // condition: if in arrayofCell there is a cell that is alive
        if( arrayofCell[i] == 1 ){
            // then return true
            return true;
        } 
        else{
        // else return false; if there is the cell i no alive 
            return false;
        }
    }
    
    //Created a method called shouldDie which return boolean
    public boolean shouldDie(int i){
        int arrayLength = arrayofCell.length;
        // conditions for the first edge cells  
        if(i == 0){
        // if the first cell and second cell is alive then it should die
            if (this.isALive(i) &&  this.isALive(i+1)){
            return true;
            } else{
                return false;
            }
        }
        //checking for middle cells if index is between index 0 and last index
        else if ((i>0) && (i<(arrayLength)-1)){
            // //if there is cell that is alive along with two neighbors alive
            if(this.isALive(i) && this.isALive(i-1) && this.isALive(i+1)){
                // then only return true
                return true;
            }else{
                return false;
            }
        }
       // condition for second to last edge cells 
        // if second to last cell is alive, last cell should die
        else {
            if(this.isALive(i) && this.isALive(i-1)){
               return true; 
            }else{
            return false;
         }
        }
    }
    // created a method called copyArray, return nothing and has two parameters. 
    public static void copyArray(int[] firstArray, int[] secondArray){
        // loop through each of firstArray
        for(int i = 0; i<firstArray.length; i++){
            // once done with looping, copy the firstArray into secondArray
                 secondArray[i] = firstArray[i];
        }
    }
    // created a method called toString that return string 
    public String toString(){
        // it will return string represent the array
        return Arrays.toString(arrayofCell);
    }
    
    //created a method called advanceTime, return nthing.
    //this method is for simulation of game of life 
    public void advanceTime(){
         // create new array 
        int [] cell = new int[arrayofCell.length];
        //copyy the new array 
        copyArray(arrayofCell,cell);
        //loop through each line 
        for(int i=0; i<cell.length; i++){
        //if cell is alive,its true  
            if ((isALive(i) == true)){
        // checking if the cell should die or be alive, and change it accordingly 
                if (shouldDie(i) == false){ 
        // checking through index expect last index 
                    if ((i<arrayofCell.length-1)){            
                    // if cell is alive and  neighbors on left side is dead 
                    if(i>0  && arrayofCell[i-1]==0){
                        // the left side neighore should be alive
                        cell[i-1]= 1;
                    }
                    //if cell is alive and  neighbors on right side is dead 
                    if(i>0 && arrayofCell[i+1]==0){
                        // make it alive 
                        cell[i+1]=1;
                    }
                }// first edge case
                    if(i==0 && arrayofCell[i+1]==0){
                        cell[i+1]=1;
                    }
                    //check last edge case
                    else if ((i==arrayofCell.length-1)  && arrayofCell[i-1]==0){
                      cell[i-1]=1;
                    }
                }
                // shouldDie is true,
                else if(shouldDie(i)==true){
                    // check first edge case
                    if(i==0 && arrayofCell[i+1]==1){
                        // make the cell to 0 
                        cell[i]=0;
                    }
                //check last edge case
                    if ((i==arrayofCell.length-1)  && arrayofCell[i-1]==1){
                        // make the cell 0
                        cell[i]=0;
                    }
                //checking through index  expect last index 
                    if ((i>0 && i<arrayofCell.length-1)){
                        if(arrayofCell[i-1]==1){
                            cell[i-1]=0;
                        } 
                //if cell is alive and  neighbors on right side isalive
                        if(arrayofCell[i+1]==1){
                            // make it dead
                            cell[i+1]=0;
                        }
                    } 
                }
            else{  
            //make the cell on 0 
                cell[i] = 0;
                }
            }
    }
         // update the new array 
         copyArray(cell, arrayofCell);
}

    // created a main method to run the code
    public static void main(String[] args){
      
        // created a varible name timeSteps  and set the value at 1
        int timeSteps = 10;
         // created a varible name gameSize and set the value at 5
        int gameSize =  10;
        //create a new lif object, with the use of gameSize as paramter. 
        Life newlife = new Life(gameSize);
        //print the object 
        System.out.println(newlife);
        //loop through Timestep 
        for (int i=0; i<timeSteps;i++){
            // call the advanceTime 
            newlife.advanceTime();
             //print the initial value that is the array
            System.out.println(newlife.toString());
        }
    }
}



