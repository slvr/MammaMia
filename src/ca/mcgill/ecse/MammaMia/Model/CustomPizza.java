/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;
import java.util.*;

// line 69 "../../../../../MammaMiaPersistence.ump"
// line 80 "../../../../../MammaMia.ump"
public class CustomPizza extends Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomPizza(int aCalories, float aPrice, Order aOrder, Menu aMenu)
  {
    super(aCalories, aPrice, aOrder, aMenu);
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
  
  // line 72 "../../../../../MammaMiaPersistence.ump"
  private static final long serialVersionUID = 9L ;

  
}