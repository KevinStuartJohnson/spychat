/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4789 modeling language!*/
package spy;

import java.util.*;

// line 10 "model.ump"
// line 33 "model.ump"
public class Mission
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Mission Attributes
  private long assignmentDate;
  private long endDate;
  private String description;
  private String missionPassword;

  //Mission Associations
  private List<Resource> resources;
  private Operative operative;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Mission(long aAssignmentDate, long aEndDate, String aDescription)
  {
    assignmentDate = aAssignmentDate;
    endDate = aEndDate;
    description = aDescription;
    resources = new ArrayList<Resource>();
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

  public boolean setEndDate(long aEndDate)
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

  public long getAssignmentDate()
  {
    return assignmentDate;
  }

  public long getEndDate()
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

  public Operative getOperative()
  {
    return operative;
  }

  public boolean hasOperative()
  {
    boolean has = operative != null;
    return has;
  }

  public static int minimumNumberOfResources()
  {
    return 0;
  }

  public Resource addResource(String aName, String aLocation, String aPrice)
  {
    return new Resource(aName, aLocation, aPrice);
  }

  public boolean addResource(Resource aResource)
  {
    boolean wasAdded = false;
    if (resources.contains(aResource)) { return false; }
    resources.add(aResource);
    wasAdded = true;
    return wasAdded;
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

  public boolean setOperative(Operative aNewOperative)
  {
    boolean wasSet = false;
    operative = aNewOperative;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
	resources = null;
    operative = null;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "assignmentDate" + ":" + getAssignmentDate()+ "," +
            "endDate" + ":" + getEndDate()+ "," +
            "description" + ":" + getDescription()+ "," +
            "missionPassword" + ":" + getMissionPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "operative = "+(getOperative()!=null?Integer.toHexString(System.identityHashCode(getOperative())):"null")
     + outputString;
  }
}