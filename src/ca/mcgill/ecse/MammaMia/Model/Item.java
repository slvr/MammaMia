/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;
import java.util.*;

// line 38 "../../../../../MammaMiaPersistence.ump"
// line 22 "../../../../../MammaMia.ump"
public class Item implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private int calories;
  private float price;

  //Item Associations
  private List<OrderDetails> details;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(int aCalories, float aPrice)
  {
    calories = aCalories;
    price = aPrice;
    details = new ArrayList<OrderDetails>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCalories(int aCalories)
  {
    boolean wasSet = false;
    calories = aCalories;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getCalories()
  {
    return calories;
  }

  public float getPrice()
  {
    return price;
  }
  /* Code from template association_GetMany */
  public OrderDetails getDetail(int index)
  {
    OrderDetails aDetail = details.get(index);
    return aDetail;
  }

  public List<OrderDetails> getDetails()
  {
    List<OrderDetails> newDetails = Collections.unmodifiableList(details);
    return newDetails;
  }

  public int numberOfDetails()
  {
    int number = details.size();
    return number;
  }

  public boolean hasDetails()
  {
    boolean has = details.size() > 0;
    return has;
  }

  public int indexOfDetail(OrderDetails aDetail)
  {
    int index = details.indexOf(aDetail);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDetails()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderDetails addDetail(int aQuantity, Order aOrder)
  {
    return new OrderDetails(aQuantity, this, aOrder);
  }

  public boolean addDetail(OrderDetails aDetail)
  {
    boolean wasAdded = false;
    if (details.contains(aDetail)) { return false; }
    Item existingItems = aDetail.getItems();
    boolean isNewItems = existingItems != null && !this.equals(existingItems);
    if (isNewItems)
    {
      aDetail.setItems(this);
    }
    else
    {
      details.add(aDetail);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDetail(OrderDetails aDetail)
  {
    boolean wasRemoved = false;
    //Unable to remove aDetail, as it must always have a items
    if (!this.equals(aDetail.getItems()))
    {
      details.remove(aDetail);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDetailAt(OrderDetails aDetail, int index)
  {  
    boolean wasAdded = false;
    if(addDetail(aDetail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDetails()) { index = numberOfDetails() - 1; }
      details.remove(aDetail);
      details.add(index, aDetail);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDetailAt(OrderDetails aDetail, int index)
  {
    boolean wasAdded = false;
    if(details.contains(aDetail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDetails()) { index = numberOfDetails() - 1; }
      details.remove(aDetail);
      details.add(index, aDetail);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDetailAt(aDetail, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=details.size(); i > 0; i--)
    {
      OrderDetails aDetail = details.get(i - 1);
      aDetail.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "calories" + ":" + getCalories()+ "," +
            "price" + ":" + getPrice()+ "]";
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 41 "../../../../../MammaMiaPersistence.ump"
  private static final long serialVersionUID = 4L ;

  
}