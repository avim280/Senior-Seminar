//Avi Mahajan
import java.io.FileWriter;
import java.io.IOException;
public class Session {
  private int count; //instance variables
  private String[] namesInSession = new String[16]; //every person's name in a session
  private int time;
  private String sessionName = ""; //name of the session
  
  

  public Session (String[] namesInSession, int time, String sessionName) { // constructor
    this.count = 0;
    this.namesInSession= namesInSession;
    this.time = time;
    this.sessionName = sessionName;
  }


  //getters
  public int getCount() {
    return count;
  }

  public String[] getNamesInSession() {
    return namesInSession;
  }
  public int getTime() {
    return time;
  }

  public String getSessionName() {
    return sessionName;
  }

  public void setCount() {
    count++;
  }

  public void printNamesInSession(FileWriter outputFile) { //method that lists the name of a session and every person in the session
    try {
    StringBuilder line = new StringBuilder();
      line.append(this.getTime() + ", ");
    line.append(this.getSessionName()+ " People: ");
   
    for (int i = 0; i < namesInSession.length; i++) { // for each person in the namesInSession array

       if  (this.getNamesInSession()[i] == null) { //gets rid of null values 
          break;
        }
      line.append(namesInSession[i] + ", "); //seperates names by commas
        
      
    }

   line.append("\n"); //next line for next session
   outputFile.write(line.toString()); 

    }

    catch(IOException e) {
      System.out.println(e);
    }
 
  
  }
  
//setter
  public void setNamesInSession(String name) { //sets the namesInSession array at count index to the name of a person
    namesInSession[count] = name;
  }
 
  public boolean isFull() { //if the session is not full, returns true, otherwise returns false
    if (count < 16) {
      return false;
    }
      return true;
  }


public void addPerson(Person person) { //adds the name of a person to the setNamesInSession array and increments count
   setNamesInSession(person.getName());
   setCount();
}


public String removePersonAddNew(Person person, int loc){ //function that puts a person (who cannot be put into a different session because they are already in that session at a previous time) into the prior or latter session at that time slot, and returns the last person already in that session(to remove them)
    String oldName = namesInSession[loc];
    namesInSession[loc] = person.getName();
    return oldName;
}
}