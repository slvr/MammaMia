/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;

// line 87 "../../../../../MammaMiaPersistence.ump"
// line 104 "../../../../../MammaMia.ump"
public class Pepper extends Ingredient implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pepper(int aCalories, float aPrice, Pizza aPizza, Menu aMenu)
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
  
  // line 90 "../../../../../MammaMiaPersistence.ump"
  private static final long serialVersionUID = 12L ;

  
}