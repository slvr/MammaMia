/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.Model;

import java.util.*;

// line 1 "MammaMia.ump"
public class Order
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { OrderIn, Preparation, InOven, OutForDelivery, Delivered }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Status status;
  private int orderNumber;

  //Order Associations
  private List<Pizza> pizza;
  private List<OrderDetails> details;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Customer aCustomer)
  {
    status = Status.OrderIn;
    orderNumber = 0;
    pizza = new ArrayList<Pizza>();
    details = new ArrayList<OrderDetails>();
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create order due to customer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderNumber(int aOrderNumber)
  {
    boolean wasSet = false;
    orderNumber = aOrderNumber;
    wasSet = true;
    return wasSet;
  }

  public Status getStatus()
  {
    return status;
  }

  public int getOrderNumber()
  {
    return orderNumber;
  }
  /* Code from template association_GetMany */
  public Pizza getPizza(int index)
  {
    Pizza aPizza = pizza.get(index);
    return aPizza;
  }

  public List<Pizza> getPizza()
  {
    List<Pizza> newPizza = Collections.unmodifiableList(pizza);
    return newPizza;
  }

  public int numberOfPizza()
  {
    int number = pizza.size();
    return number;
  }

  public boolean hasPizza()
  {
    boolean has = pizza.size() > 0;
    return has;
  }

  public int indexOfPizza(Pizza aPizza)
  {
    int index = pizza.indexOf(aPizza);
    return index;
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
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPizza()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Pizza addPizza(int aCalories, float aPrice, Menu aMenu)
  {
    return new Pizza(aCalories, aPrice, this, aMenu);
  }

  public boolean addPizza(Pizza aPizza)
  {
    boolean wasAdded = false;
    if (pizza.contains(aPizza)) { return false; }
    Order existingOrder = aPizza.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aPizza.setOrder(this);
    }
    else
    {
      pizza.add(aPizza);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePizza(Pizza aPizza)
  {
    boolean wasRemoved = false;
    //Unable to remove aPizza, as it must always have a order
    if (!this.equals(aPizza.getOrder()))
    {
      pizza.remove(aPizza);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPizzaAt(Pizza aPizza, int index)
  {  
    boolean wasAdded = false;
    if(addPizza(aPizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPizza()) { index = numberOfPizza() - 1; }
      pizza.remove(aPizza);
      pizza.add(index, aPizza);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePizzaAt(Pizza aPizza, int index)
  {
    boolean wasAdded = false;
    if(pizza.contains(aPizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPizza()) { index = numberOfPizza() - 1; }
      pizza.remove(aPizza);
      pizza.add(index, aPizza);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPizzaAt(aPizza, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDetails()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderDetails addDetail(int aQuantity, Item aItems)
  {
    return new OrderDetails(aQuantity, aItems, this);
  }

  public boolean addDetail(OrderDetails aDetail)
  {
    boolean wasAdded = false;
    if (details.contains(aDetail)) { return false; }
    Order existingOrder = aDetail.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aDetail.setOrder(this);
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
    //Unable to remove aDetail, as it must always have a order
    if (!this.equals(aDetail.getOrder()))
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
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeOrder(this);
    }
    customer.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (pizza.size() > 0)
    {
      Pizza aPizza = pizza.get(pizza.size() - 1);
      aPizza.delete();
      pizza.remove(aPizza);
    }
    
    while (details.size() > 0)
    {
      OrderDetails aDetail = details.get(details.size() - 1);
      aDetail.delete();
      details.remove(aDetail);
    }
    
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "orderNumber" + ":" + getOrderNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}