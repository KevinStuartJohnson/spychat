/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4789 modeling language!*/



// line 21 "model.ump"
// line 38 "model.ump"
public class Resource
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Resource Attributes
  private String name;
  private String location;
  private String price;

  //Resource Associations
  private Mission mission;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Resource(String aName, String aLocation, String aPrice, Mission aMission)
  {
    name = aName;
    location = aLocation;
    price = aPrice;
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

  public boolean setPrice(String aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
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

  public String getPrice()
  {
    return price;
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
            "location" + ":" + getLocation()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "mission = "+(getMission()!=null?Integer.toHexString(System.identityHashCode(getMission())):"null")
     + outputString;
  }
}