/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;
import java.util.*;

// line 56 "../../../../../MammaMiaPersistence.ump"
// line 71 "../../../../../MammaMia.ump"
public class CheesePizza extends Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CheesePizza Attributes
  private int numCalories;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CheesePizza(int aCalories, float aPrice, Order aOrder, Menu aMenu)
  {
    super(aCalories, aPrice, aOrder, aMenu);
    numCalories = 1000;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumCalories(int aNumCalories)
  {
    boolean wasSet = false;
    numCalories = aNumCalories;
    wasSet = true;
    return wasSet;
  }

  public int getNumCalories()
  {
    return numCalories;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "numCalories" + ":" + getNumCalories()+ "]";
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 59 "../../../../../MammaMiaPersistence.ump"
  private static final long serialVersionUID = 7L ;

  
}