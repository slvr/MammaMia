/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;
import java.util.*;

// line 63 "../../../../../MammaMiaPersistence.ump"
// line 76 "../../../../../MammaMia.ump"
public class VeggiePizza extends Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public VeggiePizza(int aCalories, float aPrice, Order aOrder, Menu aMenu)
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
  
  // line 66 "../../../../../MammaMiaPersistence.ump"
  private static final long serialVersionUID = 8L ;

  
}