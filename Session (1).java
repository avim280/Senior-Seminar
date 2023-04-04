public class Session {
  private int count;
  private String[] namesInSession = new String[16];
  private int time;
  private String sessionName = "";
  
  

  public Session (String[] namesInSession, int time, String sessionName) {
    this.count = 0;
    this.namesInSession= namesInSession;
    this.time = time;
    this.sessionName = sessionName;
  }

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

  public void printNamesInSession() {
    System.out.println("People in Session " + this.getSessionName() + ":" );
    for (int i = 0; i < namesInSession.length; i++) {
      if (namesInSession[i] == null) {
        break;
      }
      System.out.println(namesInSession[i]);
    }
  }
  

  public void setNamesInSession(String name) {
    namesInSession[count] = name;
  }
  public void setTime() {
  //  return time;
  }

  public boolean isFull() {
    if (count < 16) {
      return false;
    }
      return true;
  }


public void addPerson(Person person) {
   setNamesInSession(person.getName());
   setCount();
}
}