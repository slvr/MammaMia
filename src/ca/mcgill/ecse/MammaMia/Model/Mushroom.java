/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;

// line 93 "../../../../../MammaMiaPersistence.ump"
// line 108 "../../../../../MammaMia.ump"
public class Mushroom extends Ingredient implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Mushroom(int aCalories, float aPrice, Pizza aPizza, Menu aMenu)
  {
    super(aCalories, aPrice, aPizza, aMenu);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 96 "../../../../../MammaMiaPersistence.ump"
  private static final long serialVersionUID = 13L ;

  
}