/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.21.0.4789 modeling language!*/

package spy;

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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Resource(String aName, String aLocation, String aPrice)
  {
    name = aName;
    location = aLocation;
    price = aPrice;
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



  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "location" + ":" + getLocation()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
             outputString;
  }
}