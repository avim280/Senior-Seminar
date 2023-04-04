public class Person {
  private String name = ""; 
  private int col1;
  private int col2;
  private int col3;
  private int col4;
  private int col5;
  private int[] availability;
  
 

public Person(String name, int col1, int col2, int col3, int col4, int col5) {
   
    this.availability = new int[5];
    this.name = name;
    this.col1 = col1;
    this.col2 = col2;
    this.col3 = col3;
    this.col4 = col4;
    this.col5 = col5;
}
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

  
   

 public void print() {
  System.out.println(this.getName());
}

  public boolean checkAvailability(int timeSlot) {
    if (this.availability[timeSlot] == 0)
    {
      return true;
    }
      return false;
  }

public void blockAvailability(int timeSlot) {
  this.availability[timeSlot] = 1;
}
public int getCol(int num) {

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


  
  

public void printAvailability() {
  System.out.println("\n" + this.name);
  for (int i = 0; i<this.availability.length; i++) {
    System.out.print(" " + this.availability[i]);
  }
  
}
public void printSessions() {
  
}
  
}

