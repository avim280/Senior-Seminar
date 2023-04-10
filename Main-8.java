//Avi Mahajan
//4/9/22
//This code generates a schedule for students with the fewest conflicts


import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;



class Main {
  public static void main(String[] args)   {
  
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
          Session s1 = new Session(arr, r+1, sessionName[unsorted_index]); //creates new instance of session
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
    addPersonCompletely(1, schedule, names, sessionName); //call to function 5 times for each choice
    addPersonCompletely(2, schedule, names, sessionName);
    addPersonCompletely(3, schedule, names, sessionName);
    addPersonCompletely(4, schedule, names, sessionName);
    addPersonCompletely(5, schedule, names, sessionName);

    addTheRest(names, schedule); //fills the rest of schedule with people

    for (int h = 0; h < names.size(); h++) {
      names.get(h).printAvailability(); // prints availability for each person at each time slot (0 is available, 1 is unavailable)
    }

    printSchedule(sessionName, schedule); //prints schedule 

    File myob = new File("Print.csv"); //create a filewriter in order to print the output in a csv file
    try {
    FileWriter outputFile = new FileWriter(myob); //new instance of fileWriter
    
    for (int r = 0; r < 5; r++) { //nested for loop
      for (int c = 0; c < 5; c++) {

       schedule[r][c].printNamesInSession(outputFile); // for each session (of 25) prints name of the session and every single person in the session to the csv file
      
        }
      }
    outputFile.close();
    }
    catch(IOException e) {
      System.out.print(e.getMessage());
      }


    
    File myo = new File("Print2.csv"); //create a filewriter in order to print the output in a csv file
    try {
    FileWriter outputFile2 = new FileWriter(myo);//new instance of fileWriter
    
    for( int x = 0; x < names.size(); x++) {
      names.get(x).printSessions(outputFile2); //for each person (of 74) prints the sessions they are in
       
    }
        
       outputFile2.close();
        }
    catch(IOException e) {
      System.out.print(e.getMessage());
      }


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

public static void printSchedule(String[] sessionName, Session[][] schedule) { // function tha prints schedule
     //first for loop is to print each row
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


  public static int[] searchSession(int startR, int startC, Session[][] schedule, String sessionName) { //function that goes through each session in schedule and returns an array(row and column) of the session that the person wants to be in
    int [] arr = new int[2];
    for (int r = startR ; r < schedule.length; r++) {
          for (int c = startC; c < schedule.length; c++) {
            if (schedule[c][r].getSessionName().equals(sessionName)) {
              arr[0] = r; //the column
              arr[1] = c; //the row
              return arr;
            }
          }
    }
              arr[0] = 0;
              arr[1] = 0;
              return arr;
  }


  public static boolean checkContainsSession(Person name, Session [][] schedule, int r, int c) { //function that checks if a person is already attending a session at a previous timeSlot- returns true if they are, false if not
     
    String[] arr = name.getSessionName();
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != null) {
          if (arr[i].equals(schedule[r][c].getSessionName())) {
              return true;
          }
      }
    }
    return false;
  }

 public static void addPersonCompletely(int t, Session[][] schedule, ArrayList<Person> names, String[] sessionName) { // functions that completely adds a person to a session
      
       for (int e = 0; e <names.size(); e++) { // for each person
      Person name = names.get(e); //gets a person
      int choice = name.getCol(t); //gets a certain column of every person's choice
      String session = sessionName[choice-1]; //associates number with sessionName
      int[] search = searchSession(0,0, schedule, session); //find the session in Schedule of their choice
      int row = search[0];
      int col = search[1];
      if (name.checkAvailability(row) && !schedule[row][col].isFull() && !checkContainsSession(name, schedule, row, col)) { //if the person is available, the session is not full, and the person doesn't already attend this session
        
        schedule[row][col].addPerson(name); //adds person to session

          name.setSessionName(schedule[row][col].getSessionName()); //sets the name of the session to person's sessionName array 
         name.blockAvailability(row); //blocks their availability
       
      }
      else { // likely if the session if full
      int[] search1 = searchSession(row, col+1, schedule, session); //begins the search where it left off to see if the same session appears again
      int row1 = search1[0];
      int col1 = search1[1];
        if (name.checkAvailability(row1) && !schedule[row1][col1].isFull() && !checkContainsSession(name, schedule, row1, col1))  {
          schedule[row1][col1].addPerson(name);
          name.setSessionName(schedule[row1][col1].getSessionName());
          name.blockAvailability(row1);
      }
      }
}
 }

 public static Person searchPeople(String name, ArrayList<Person> people){
      //helper function that returns a person associating with a name
      for(int i = 0; i < people.size(); i++){
          if(name != null) {
              if (name.equals(people.get(i).getName())) {
                  return people.get(i);
              }
          }
      }
      return null;
 }

 public static void redundantSessions(Session [][] schedule, Person name, int r, int c, int count, ArrayList<Person>names) { //function that takes the removed person for removePersonAddNew, finds the person with that name, and checks if they can be put into the session where the original person was supposed to go. Also officially adds the new person to the previous session
     String removed = "";
     if(c == 0){ //if the session is in the first column of the timeSlot
         removed = schedule[r][c + 1].removePersonAddNew(name, 15 - count); //new person is added to the later column of that timeSlot
     }
     else{ //new person is added to the prior column of that timeSlot
         removed = schedule[r][c - 1].removePersonAddNew(name, 15 - count);
     }
      
     Person p = searchPeople(removed, names);// find the person associated with the person removed from their assigned column
     if(p != null) {
         if (c == 0) {
             p.removeSession(schedule[r][c +1].getSessionName()); //officialy removes the old person
             name.setSessionName(schedule[r][c+1].getSessionName()); //adds the new person to the session
             name.blockAvailability(r); //blocks their availability
         } else {
             p.removeSession(schedule[r][c - 1].getSessionName()); //same but to the prior session
             name.setSessionName(schedule[r][c-1].getSessionName());
             name.blockAvailability(r);
         }
         p.getAvailability()[r] = 0; //changes the availability of the old person at the row they were previously assigned to (but not anymore) to available
         if (!checkContainsSession(p, schedule, r, c)) { //if the removed person does't already attend the session
             schedule[r][c].addPerson(p); //officially added to r,c
             p.setSessionName(schedule[r][c].getSessionName());
             p.blockAvailability(r);
         }
         else{ //if they already attend session
             count++; //increment count
             redundantSessions(schedule, p, r, c, count, names); //recursion- call function again to resassign them
         }
     }
 }

  public static void addTheRest(ArrayList<Person> names, Session[][] schedule) { //function that doesnt take in people's choices- randomly adds people to sessions 
      for (int e = 0; e < names.size(); e++) { //for each person
          Person name = names.get(e); //gets person
          for (int r = 0; r < 5; r++) { //for each row
              int count = 0;
              for (int c = 0; c < 5; c++) { //for each column
                  if (!schedule[r][c].isFull() && name.checkAvailability(r) && !checkContainsSession(name, schedule, r, c)) { //if the person is available, the session is not full, and the person doesn't already attend this session
                      schedule[r][c].addPerson(name); //adds person to session
                      name.setSessionName(schedule[r][c].getSessionName());
                      name.blockAvailability(r);
                  }
                 
                  else if(!schedule[r][c].isFull() &&  checkContainsSession(name, schedule, r, c) && name.checkAvailability(r)){ //if the person already is in the session at a prior time
                      redundantSessions(schedule, name, r, c, count, names); //adds them to a different session and adds the person already in that session to this session
                      count++; //increments count
                  }
              }
              }
          }
      }

  }
