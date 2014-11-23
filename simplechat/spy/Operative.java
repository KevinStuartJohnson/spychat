/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4789 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 28 "model.ump"
public class Operative
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Operative Attributes
  private String codeName;
  private String privatePassword;
  private String dynamicPassword;

  //Operative Associations
  private List<Operative> supervises;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operative(String aCodeName, String aDynamicPassword)
  {
    codeName = aCodeName;
    privatePassword = aPrivatePassword;
    dynamicPassword = aDynamicPassword;
    supervises = new ArrayList<Operative>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCodeName(String aCodeName)
  {
    boolean wasSet = false;
    codeName = aCodeName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrivatePassword(String aPrivatePassword)
  {
    boolean wasSet = false;
    privatePassword = aPrivatePassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setDynamicPassword(String aDynamicPassword)
  {
    boolean wasSet = false;
    dynamicPassword = aDynamicPassword;
    wasSet = true;
    return wasSet;
  }

  public String getCodeName()
  {
    return codeName;
  }

  public String getPrivatePassword()
  {
    return privatePassword;
  }

  public void setPrivatePassword(String privatePassword)
  {
    this.privatePassword = privatePassword;
  }

  public String getDynamicPassword()
  {
    return dynamicPassword;
  }

  public Operative getSupervise(int index)
  {
    Operative aSupervise = supervises.get(index);
    return aSupervise;
  }

  public List<Operative> getSupervises()
  {
    List<Operative> newSupervises = Collections.unmodifiableList(supervises);
    return newSupervises;
  }

  public int numberOfSupervises()
  {
    int number = supervises.size();
    return number;
  }

  public boolean hasSupervises()
  {
    boolean has = supervises.size() > 0;
    return has;
  }

  public int indexOfSupervise(Operative aSupervise)
  {
    int index = supervises.indexOf(aSupervise);
    return index;
  }

  public static int minimumNumberOfSupervises()
  {
    return 0;
  }

  public boolean addSupervise(Operative aSupervise)
  {
    boolean wasAdded = false;
    if (supervises.contains(aSupervise)) { return false; }
    supervises.add(aSupervise);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupervise(Operative aSupervise)
  {
    boolean wasRemoved = false;
    if (supervises.contains(aSupervise))
    {
      supervises.remove(aSupervise);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSuperviseAt(Operative aSupervise, int index)
  {  
    boolean wasAdded = false;
    if(addSupervise(aSupervise))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupervises()) { index = numberOfSupervises() - 1; }
      supervises.remove(aSupervise);
      supervises.add(index, aSupervise);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSuperviseAt(Operative aSupervise, int index)
  {
    boolean wasAdded = false;
    if(supervises.contains(aSupervise))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupervises()) { index = numberOfSupervises() - 1; }
      supervises.remove(aSupervise);
      supervises.add(index, aSupervise);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSuperviseAt(aSupervise, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    supervises.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "codeName" + ":" + getCodeName()+ "," +
            "privatePassword" + ":" + getPrivatePassword()+ "," +
            "dynamicPassword" + ":" + getDynamicPassword()+ "]"
     + outputString;
  }
}