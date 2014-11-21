/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4777 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 25 "model.ump"
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
  private Mission mission;
  private List<Operative> supervisor;
  private List<Operative> operatives;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Operative(String aCodeName, String aPrivatePassword, String aDynamicPassword)
  {
    codeName = aCodeName;
    privatePassword = aPrivatePassword;
    dynamicPassword = aDynamicPassword;
    supervisor = new ArrayList<Operative>();
    operatives = new ArrayList<Operative>();
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

  public String getDynamicPassword()
  {
    return dynamicPassword;
  }

  public Mission getMission()
  {
    return mission;
  }

  public boolean hasMission()
  {
    boolean has = mission != null;
    return has;
  }

  public Operative getSupervisor(int index)
  {
    Operative aSupervisor = supervisor.get(index);
    return aSupervisor;
  }

  public List<Operative> getSupervisor()
  {
    List<Operative> newSupervisor = Collections.unmodifiableList(supervisor);
    return newSupervisor;
  }

  public int numberOfSupervisor()
  {
    int number = supervisor.size();
    return number;
  }

  public boolean hasSupervisor()
  {
    boolean has = supervisor.size() > 0;
    return has;
  }

  public int indexOfSupervisor(Operative aSupervisor)
  {
    int index = supervisor.indexOf(aSupervisor);
    return index;
  }

  public Operative getOperative(int index)
  {
    Operative aOperative = operatives.get(index);
    return aOperative;
  }

  public List<Operative> getOperatives()
  {
    List<Operative> newOperatives = Collections.unmodifiableList(operatives);
    return newOperatives;
  }

  public int numberOfOperatives()
  {
    int number = operatives.size();
    return number;
  }

  public boolean hasOperatives()
  {
    boolean has = operatives.size() > 0;
    return has;
  }

  public int indexOfOperative(Operative aOperative)
  {
    int index = operatives.indexOf(aOperative);
    return index;
  }

  public boolean setMission(Mission aMission)
  {
    boolean wasSet = false;
    Mission existingMission = mission;
    mission = aMission;
    if (existingMission != null && !existingMission.equals(aMission))
    {
      existingMission.removeOperative(this);
    }
    if (aMission != null)
    {
      aMission.addOperative(this);
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSupervisor()
  {
    return 0;
  }

  public boolean addSupervisor(Operative aSupervisor)
  {
    boolean wasAdded = false;
    if (supervisor.contains(aSupervisor)) { return false; }
    supervisor.add(aSupervisor);
    if (aSupervisor.indexOfOperative(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSupervisor.addOperative(this);
      if (!wasAdded)
      {
        supervisor.remove(aSupervisor);
      }
    }
    return wasAdded;
  }

  public boolean removeSupervisor(Operative aSupervisor)
  {
    boolean wasRemoved = false;
    if (!supervisor.contains(aSupervisor))
    {
      return wasRemoved;
    }

    int oldIndex = supervisor.indexOf(aSupervisor);
    supervisor.remove(oldIndex);
    if (aSupervisor.indexOfOperative(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSupervisor.removeOperative(this);
      if (!wasRemoved)
      {
        supervisor.add(oldIndex,aSupervisor);
      }
    }
    return wasRemoved;
  }

  public boolean addSupervisorAt(Operative aSupervisor, int index)
  {  
    boolean wasAdded = false;
    if(addSupervisor(aSupervisor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupervisor()) { index = numberOfSupervisor() - 1; }
      supervisor.remove(aSupervisor);
      supervisor.add(index, aSupervisor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupervisorAt(Operative aSupervisor, int index)
  {
    boolean wasAdded = false;
    if(supervisor.contains(aSupervisor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupervisor()) { index = numberOfSupervisor() - 1; }
      supervisor.remove(aSupervisor);
      supervisor.add(index, aSupervisor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupervisorAt(aSupervisor, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfOperatives()
  {
    return 0;
  }

  public boolean addOperative(Operative aOperative)
  {
    boolean wasAdded = false;
    if (operatives.contains(aOperative)) { return false; }
    operatives.add(aOperative);
    if (aOperative.indexOfSupervisor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOperative.addSupervisor(this);
      if (!wasAdded)
      {
        operatives.remove(aOperative);
      }
    }
    return wasAdded;
  }

  public boolean removeOperative(Operative aOperative)
  {
    boolean wasRemoved = false;
    if (!operatives.contains(aOperative))
    {
      return wasRemoved;
    }

    int oldIndex = operatives.indexOf(aOperative);
    operatives.remove(oldIndex);
    if (aOperative.indexOfSupervisor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOperative.removeSupervisor(this);
      if (!wasRemoved)
      {
        operatives.add(oldIndex,aOperative);
      }
    }
    return wasRemoved;
  }

  public boolean addOperativeAt(Operative aOperative, int index)
  {  
    boolean wasAdded = false;
    if(addOperative(aOperative))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperatives()) { index = numberOfOperatives() - 1; }
      operatives.remove(aOperative);
      operatives.add(index, aOperative);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOperativeAt(Operative aOperative, int index)
  {
    boolean wasAdded = false;
    if(operatives.contains(aOperative))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOperatives()) { index = numberOfOperatives() - 1; }
      operatives.remove(aOperative);
      operatives.add(index, aOperative);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOperativeAt(aOperative, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    if (mission != null)
    {
      Mission placeholderMission = mission;
      this.mission = null;
      placeholderMission.removeOperative(this);
    }
    ArrayList<Operative> copyOfSupervisor = new ArrayList<Operative>(supervisor);
    supervisor.clear();
    for(Operative aSupervisor : copyOfSupervisor)
    {
      aSupervisor.removeOperative(this);
    }
    ArrayList<Operative> copyOfOperatives = new ArrayList<Operative>(operatives);
    operatives.clear();
    for(Operative aOperative : copyOfOperatives)
    {
      aOperative.removeSupervisor(this);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "codeName" + ":" + getCodeName()+ "," +
            "privatePassword" + ":" + getPrivatePassword()+ "," +
            "dynamicPassword" + ":" + getDynamicPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mission = "+(getMission()!=null?Integer.toHexString(System.identityHashCode(getMission())):"null")
     + outputString;
  }
}