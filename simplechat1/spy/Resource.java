import java.util.*;


public class Resource
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Resource Attributes
  private String name;
  private String location;

  //Resource Associations
  private Mission mission;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Resource(String aName, String aLocation, Mission aMission)
  {
    name = aName;
    location = aLocation;
    boolean didAddMission = setMission(aMission);
    if (!didAddMission)
    {
      throw new RuntimeException("Unable to create resource due to mission");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getLocation()
  {
    return location;
  }

  public Mission getMission()
  {
    return mission;
  }

  public boolean setMission(Mission aMission)
  {
    boolean wasSet = false;
    if (aMission == null)
    {
      return wasSet;
    }

    Mission existingMission = mission;
    mission = aMission;
    if (existingMission != null && !existingMission.equals(aMission))
    {
      existingMission.removeResource(this);
    }
    mission.addResource(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Mission placeholderMission = mission;
    this.mission = null;
    placeholderMission.removeResource(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "location" + ":" + getLocation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mission = "+(getMission()!=null?Integer.toHexString(System.identityHashCode(getMission())):"null")
     + outputString;
  }
}

