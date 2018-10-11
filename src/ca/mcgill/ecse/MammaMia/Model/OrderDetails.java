/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;

// line 29 "../../../../../MammaMiaPersistence.ump"
// line 17 "../../../../../MammaMia.ump"
public class OrderDetails implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderDetails Attributes
  private int quantity;

  //OrderDetails Associations
  private Item items;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderDetails(int aQuantity, Item aItems, Order aOrder)
  {
    quantity = aQuantity;
    boolean didAddItems = setItems(aItems);
    if (!didAddItems)
    {
      throw new RuntimeException("Unable to create detail due to items");
    }
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create detail due to order");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }
  /* Code from template association_GetOne */
  public Item getItems()
  {
    return items;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_SetOneToMany */
  public boolean setItems(Item aItems)
  {
    boolean wasSet = false;
    if (aItems == null)
    {
      return wasSet;
    }

    Item existingItems = items;
    items = aItems;
    if (existingItems != null && !existingItems.equals(aItems))
    {
      existingItems.removeDetail(this);
    }
    items.addDetail(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeDetail(this);
    }
    order.addDetail(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Item placeholderItems = items;
    this.items = null;
    if(placeholderItems != null)
    {
      placeholderItems.removeDetail(this);
    }
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeDetail(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "items = "+(getItems()!=null?Integer.toHexString(System.identityHashCode(getItems())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}