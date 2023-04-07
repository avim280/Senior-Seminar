import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Main {
  public static void main(String[] args) {
  
    int[][] numInSession = new int[5][5];
    int[] weights = new int[18]; // initializing new arr 
    int i = 0; // indexer
    int d = 0; // indexer
    Session schedule [][] = new Session[5][5]; //2d array of type session objects tht is the schedule
    int[] sorted = new int[18]; // initialzing new array
     String[] sessionName = new String[18]; // initializing array 
     ArrayList<Person> names = new ArrayList<Person>(); //initializing array list
   
    
    try { //Get Data
      File myObj = new File("Data.txt");  //go through Data file
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] arr = data.split(",");
        int one = Integer.parseInt(arr[1]);
        int two = Integer.parseInt(arr[2]);
        int three = Integer.parseInt(arr[3]);
        int four = Integer.parseInt(arr[4]);
        int five = Integer.parseInt(arr[5]);
       names.add(new Person(arr[0], one, two, three, four, five));  //fill the names arraylist with 74 people that includes their choices
   
      }

      
     
      File file = new File("weights.txt"); // go through weights file
      Scanner scan = new Scanner(file);
       while (scan.hasNextLine()) {
       weights[i] = scan.nextInt(); //add the weights to the array- weights are the sessions that are the most frequently picked- 5 points for 1st choice, 4 pts for second choice, and so on.
       i++;
 }
      File myobj2 = new File("table.txt"); //go through table file
      Scanner sc = new Scanner(myobj2);
      while (sc.hasNextLine()) {
      String data1 = sc.nextLine();
      String[] arr2 = data1.split(",");
      sessionName[d] = arr2[0]; //add the name of the sessions to the array
      d++;
 }
     
       
      
      
        
    } catch(FileNotFoundException e) {
    System.out.print(e); }
    finally {};
    sorted = Arrays.copyOf(weights, weights.length); 
    Arrays.sort(sorted); //sorts the weight array with the highest weight on bottom
    reverse(sorted);  //reverses the order so the highest weight is on top

     for (int b = 0; b < names.size(); b++) {
    //names.get(b).print();
  }

    int index = 0;
    int unsorted_index = 0;

    
 
  for (int c = 0; c < 5; c++) {
     for (int r = 0; r < 5; r++) {
         if (index < 18) {  //for the first 18 sessions
          unsorted_index = findIndex(weights, sorted[index]); //associates the session with the highest weight to the correct session number- session with highest weight at first time slot, next highest weight at second time slot
          String arr[] = new String[16];
          Session s1 = new Session(arr, r+1, sessionName[unsorted_index]); //creates new instance of seesion
          schedule[r][c] = s1; // sets a spot in session to object of type session
           //puts that session in the spot
          index ++;
         } 
         else if (index < 20 || (index >= 23 && index < 25)) { //for the the first two sessions that repeat and last two that repeat
           String arr[] = new String[16];
          Session s2 = new Session(arr, r+1, schedule[r-3][c-3].getSessionName());
          schedule[r][c] = s2;//repeats the seven most frequent sessions
            index ++; 
          }
         else if (index < 23) { // for the middle three sessions that repeat
           String arr[] = new String [16];
          Session s3= new Session(arr, r+1, schedule[r+2][c-4].getSessionName());
            schedule[r][c] = s3;
            index ++;
          }
    }

     
 }
    addPersonCompletely(1, schedule, names, sessionName);
    addPersonCompletely(2, schedule, names, sessionName);
    addPersonCompletely(3, schedule, names, sessionName);
    addPersonCompletely(4, schedule, names, sessionName);
    addPersonCompletely(5, schedule, names, sessionName);

    fillTheRest(names, schedule);

    

    for (int h = 0; h < names.size(); h++) {
      names.get(h).printAvailability(); // prints availability for each person at each time slot (0 is available, 1 is unavailable)
    }
    

   for (int r = 0; r <schedule.length; r++ ) {
      for (int c = 0; c <schedule.length;c++ ) {

        schedule[c][r].printNamesInSession(); //prints the names in each session
        
    }
    }

    printSchedule(sessionName, schedule); //prints schedule 
    

    File myob = new File("Print.csv"); //create a filewriter in order to print the output in a csv file
    try {
    FileWriter outputFile = new FileWriter(myob);
    
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        StringBuilder line = new StringBuilder();
        line.append(schedule[r][c].getSessionName());   
        line.append(schedule[r][c].getTime());// prints name of each session
        line.append(":,");
      for (int v = 0; v<schedule[r][c].getNamesInSession().length; v++) {
        if  (schedule[r][c].getNamesInSession()[v] == null) { //gets rid of null 
          break;
        }
        line.append(" ");
        line.append(schedule[r][c].getNamesInSession()[v]); //[print names in each session
   
        if (v != schedule[r][c].getNamesInSession().length-1) {
          line.append(",");
        }
      
        }
        
       line.append("\n");
       outputFile.write(line.toString());
      }
          
    }

    outputFile.close();
    }
    catch(IOException e) {
      System.out.print(e.getMessage());
      }

   finally {}



    
    File myo = new File("Print2.csv"); //create a filewriter in order to print the output in a csv file
    try {
    FileWriter outputFile2 = new FileWriter(myo);
    
    for( int x = 0; x < names.size(); x++) {
        StringBuilder line = new StringBuilder();
        line.append(names.get(x).printSessions());  
      
       line.append("\n");
       outputFile2.write(line.toString());
        }
        

          
    

    outputFile2.close();
    }
    catch(IOException e) {
      System.out.print(e.getMessage());
      }


   finally {}

  } 
      
    


  

  





  

  public static void reverse(int[] weights) { //reverses the order of an array

       int n = weights.length;
 
        // Swapping the first half elements with last half
        // elements
        for (int i = 0; i < n / 2; i++) {
 
            // Storing the first half elements temporarily
            int temp = weights[i];
 
            // Assigning the first half to the last half
          weights[i] = weights[n - i - 1];
 
            // Assigning the last half to the first half
            weights[n - i - 1] = temp;
        }
    
    
  }

  public static int findIndex(int [] weights, int index) { //assosiates a session or index with the correct weight
    int count = 0;
    while (count < weights.length) {
      if (weights[count] == index) { // if the weight equals index(weight in sorted array), returns index of the weight- correct session with the weight
        return count;
      }
    
      else {
        count ++; 
      }
    
    
  }
    return -1;
  }


public static void printSchedule(String[] sessionName, Session[][] schedule) { // function that creates the enemy board
     //first for loop is to print each tow
    //print header row
    for (int x=0;x<schedule.length;x++) { //prints header of each column
      System.out.print("\t" + (x+1));
    }
    System.out.print("\n"); 
                                                
    for (int r=0;r<schedule.length;r++) {
      System.out.print(r+1);
        for (int c=0; c<schedule[r].length;c++) {  //print the cols WITHIN a row
                                                
       String sName =  schedule[r][c].getSessionName();                                 
       for (int f = 0; f< sessionName.length; f++) {
         if (sessionName[f].equals(sName)) {
           int temp = f+1;
           System.out.print("\t" + temp);
         }
       }
      }
      System.out.print("\n");
}
  }


  public static int[] searchSession(int startR, int startC, Session[][] schedule, String sessionName) {
    int [] arr = new int[2];
    for (int r = startR ; r < schedule.length; r++) {
          for (int c = startC; c < schedule.length; c++) {
            if (schedule[c][r].getSessionName().equals(sessionName)) {
              arr[0] = c;
              arr[1] = r;
              return arr;
            }
          
          }
    }
              arr[0] = 0;
              arr[1] = 0;
              return arr;
            
  }


 public static void addPersonCompletely(int t, Session[][] schedule, ArrayList<Person> names, String[] sessionName) {
      
       for (int e = 0; e <names.size(); e++) {
      Person name = names.get(e);
      int choice = name.getCol(t);
      String session = sessionName[choice-1];
      int[] search = searchSession(0,0, schedule, session);
      int row = search[0];
      int col = search[1];
      if (name.checkAvailability(row) && !schedule[row][col].isFull()) {
        schedule[row][col].addPerson(name);
          name.setSessionName(schedule[row][col].getSessionName());
        name.blockAvailability(row);
      }
      else {
      int[] search1 = searchSession(row, col+1, schedule, session);
      int row1 = search1[0];
      int col1 = search1[1];
        if (name.checkAvailability(row1) && !schedule[row1][col1].isFull())  {
        schedule[row1][col1].addPerson(name);
        name.setSessionName(schedule[row1][col1].getSessionName());
        name.blockAvailability(row1);
      }
    

         
      }


   
 }
 }

  public static void fillTheRest(ArrayList<Person> names, Session[][] schedule) {

    for (int i = 0; i<names.size(); i++) {
      for (int j = 0; j< 5; j++) {
        for (int c = 0; c < 5; c++) {
           if (!schedule[j][c].isFull() && names.get(i).checkAvailability(j)) {
              schedule[j][c].addPerson(names.get(i));  
names.get(j).setSessionName(schedule[j][c].getSessionName());
             names.get(i).blockAvailability(j);
            }
          
        
        }
 
   }
      
  }
} 
  
  }
  
  

