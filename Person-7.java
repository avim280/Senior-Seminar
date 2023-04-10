//Avi Mahajan

import java.io.FileWriter;
import java.io.IOException;
public class Person {
  private String name = ""; //instance variables
  private int col1;
  private int col2;
  private int col3;
  private int col4;
  private int col5;
  private int[] availability; //avaiability at each time slot
  private String[] sessionName; //person's 5 sessions they are assigned to
  
 

public Person(String name, int col1, int col2, int col3, int col4, int col5) { // constructor
   
    this.availability = new int[5]; //
    this.sessionName = new String[5];
    this.name = name;
    this.col1 = col1;
    this.col2 = col2;
    this.col3 = col3;
    this.col4 = col4;
    this.col5 = col5;
  
} //getters
  public String getName() {
    return name;
  }

  public int getCol1() {
    return col1;
  }
  public int getCol2() {
    return col2;
  }
  public int getCol3(){
    return col3;
  }

 public int getCol4() {
    return col4;
  }
   public int getCol5() {
    return col5;
}
    public int[] getAvailability() {
    return availability;
}
     public String[] getSessionName() {
    return sessionName;
}
 //setter
   public void setSessionName(String name) { //sets the session a person is assigned to, to the sessionName array
     for (int i = 0; i < sessionName.length; i++) {
        if (this.sessionName[i]==null) {
        this.sessionName[i] = name;
        break;
        }
 }
   }

   public void removeSession(String name){ //removes a certain session out of the sessionName array
    for(int i = 0; i < sessionName.length; i++){
        if(sessionName[i]!=null){
        if(sessionName[i].equals(name)) {
            sessionName[i] = null;
        }
        }
    }
   }
   

 public void print() { //prints name of person
  System.out.println(this.getName());
}

  public boolean checkAvailability(int timeSlot) { //returns true if they are available at a time and false if not
    if (this.availability[timeSlot] == 0)
    { 
      return true;
    }
      return false;
  }

public void blockAvailability(int timeSlot) { //blocks a person's availabiltiy at a timeSlot
  availability[timeSlot] = 1;
}
public int getCol(int num) { //gets a certain column of the person's choices- either their 1st, 2nd, 3rd, 4th, or 5th choice

    if (num == 1) {
      return this.getCol1();
    }
     if (num == 2) {
      return this.getCol2();
    }
   if (num == 3) {
      return this.getCol3();
    }
   if (num == 4) {
      return this.getCol4();
    }
  else {

     return this.getCol5();
    }
  }


  
  

public void printAvailability() { //prints the availabality of a person
  System.out.println("\n" + this.name);
  for (int i = 0; i<this.availability.length; i++) {
    System.out.print(" " + this.availability[i]);
  }
  
}
public void printSessions(FileWriter outputFile) { //method that lists the name of the person and lists  every session they are assigned to
  try {
  StringBuilder line = new StringBuilder();
    line.append(this.getName()+ " Sessions: ");
    System.out.println(this.getName());
   
    for (int i = 0; i < 5; i++) { //for each spot in sessionName array
      System.out.println(sessionName[i]);
      line.append(sessionName[i] + ", ");
        
      
    }

   line.append("\n");
   outputFile.write(line.toString()); 

    }

    catch(IOException e) {
      System.out.println(e);
    }
 
  
  }
}


  


