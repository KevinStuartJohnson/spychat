/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4777 modeling language!*/


import java.util.*;

// line 10 "model.ump"
// line 32 "model.ump"
public class Mission
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Mission Attributes
  private int assignmentDate;
  private int endDate;
  private String description;
  private String missionPassword;

  //Mission Associations
  private List<Resource> resources;
  private List<Operative> operatives;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Mission(int aAssignmentDate, int aEndDate, String aDescription, String aMissionPassword)
  {
    assignmentDate = aAssignmentDate;
    endDate = aEndDate;
    description = aDescription;
    missionPassword = aMissionPassword;
    resources = new ArrayList<Resource>();
    operatives = new ArrayList<Operative>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAssignmentDate(int aAssignmentDate)
  {
    boolean wasSet = false;
    assignmentDate = aAssignmentDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(int aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setMissionPassword(String aMissionPassword)
  {
    boolean wasSet = false;
    missionPassword = aMissionPassword;
    wasSet = true;
    return wasSet;
  }

  public int getAssignmentDate()
  {
    return assignmentDate;
  }

  public int getEndDate()
  {
    return endDate;
  }

  public String getDescription()
  {
    return description;
  }

  public String getMissionPassword()
  {
    return missionPassword;
  }

  public Resource getResource(int index)
  {
    Resource aResource = resources.get(index);
    return aResource;
  }

  public List<Resource> getResources()
  {
    List<Resource> newResources = Collections.unmodifiableList(resources);
    return newResources;
  }

  public int numberOfResources()
  {
    int number = resources.size();
    return number;
  }

  public boolean hasResources()
  {
    boolean has = resources.size() > 0;
    return has;
  }

  public int indexOfResource(Resource aResource)
  {
    int index = resources.indexOf(aResource);
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

  public static int minimumNumberOfResources()
  {
    return 0;
  }

  public Resource addResource(String aName, String aLocation, String aPrice)
  {
    return new Resource(aName, aLocation, aPrice, this);
  }

  public boolean addResource(Resource aResource)
  {
    boolean wasAdded = false;
    if (resources.contains(aResource)) { return false; }
    Mission existingMission = aResource.getMission();
    boolean isNewMission = existingMission != null && !this.equals(existingMission);
    if (isNewMission)
    {
      aResource.setMission(this);
    }
    else
    {
      resources.add(aResource);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeResource(Resource aResource)
  {
    boolean wasRemoved = false;
    //Unable to remove aResource, as it must always have a mission
    if (!this.equals(aResource.getMission()))
    {
      resources.remove(aResource);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addResourceAt(Resource aResource, int index)
  {  
    boolean wasAdded = false;
    if(addResource(aResource))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResources()) { index = numberOfResources() - 1; }
      resources.remove(aResource);
      resources.add(index, aResource);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResourceAt(Resource aResource, int index)
  {
    boolean wasAdded = false;
    if(resources.contains(aResource))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResources()) { index = numberOfResources() - 1; }
      resources.remove(aResource);
      resources.add(index, aResource);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResourceAt(aResource, index);
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
    Mission existingMission = aOperative.getMission();
    if (existingMission == null)
    {
      aOperative.setMission(this);
    }
    else if (!this.equals(existingMission))
    {
      existingMission.removeOperative(aOperative);
      addOperative(aOperative);
    }
    else
    {
      operatives.add(aOperative);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOperative(Operative aOperative)
  {
    boolean wasRemoved = false;
    if (operatives.contains(aOperative))
    {
      operatives.remove(aOperative);
      aOperative.setMission(null);
      wasRemoved = true;
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
    for(int i=resources.size(); i > 0; i--)
    {
      Resource aResource = resources.get(i - 1);
      aResource.delete();
    }
    while( !operatives.isEmpty() )
    {
      operatives.get(0).setMission(null);
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "assignmentDate" + ":" + getAssignmentDate()+ "," +
            "endDate" + ":" + getEndDate()+ "," +
            "description" + ":" + getDescription()+ "," +
            "missionPassword" + ":" + getMissionPassword()+ "]"
     + outputString;
  }
}