/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.Model;

import java.util.*;

// line 7 "Discovery.ump"
public class Customer
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Status { OrderIn, Preparation, InOven, OutForDelivery, Delivered }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String name;
  private long phoneNumber;
  private String email;
  private String address;

  //Customer Associations
  private List<Order> order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, long aPhoneNumber, String aEmail, String aAddress)
  {
    name = aName;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    address = aAddress;
    order = new ArrayList<Order>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 10 "Discovery.ump"
    if(aName == null || aName.length() == 0 || aName.length() > 20){
    			return false;
    		}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(long aPhoneNumber)
  {
    boolean wasSet = false;
    // line 16 "Discovery.ump"
    if(aPhoneNumber < 1000000000L || aPhoneNumber > 9999999999L){
    			return false;
    		}
    // END OF UMPLE BEFORE INJECTION
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    // line 22 "Discovery.ump"
    if(aEmail == null || aEmail.length() == 0){ //TODO: email parsing
    			return false;
    		}
    // END OF UMPLE BEFORE INJECTION
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    // line 28 "Discovery.ump"
    if(aAddress == null || aAddress.length() == 0){
    			return false;
    		}
    // END OF UMPLE BEFORE INJECTION
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public long getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }

  public String getAddress()
  {
    return address;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = order.get(index);
    return aOrder;
  }

  public List<Order> getOrder()
  {
    List<Order> newOrder = Collections.unmodifiableList(order);
    return newOrder;
  }

  public int numberOfOrder()
  {
    int number = order.size();
    return number;
  }

  public boolean hasOrder()
  {
    boolean has = order.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = order.indexOf(aOrder);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrder()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder()
  {
    return new Order(this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (order.contains(aOrder)) { return false; }
    Customer existingCustomer = aOrder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aOrder.setCustomer(this);
    }
    else
    {
      order.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a customer
    if (!this.equals(aOrder.getCustomer()))
    {
      order.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(order.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrder()) { index = numberOfOrder() - 1; }
      order.remove(aOrder);
      order.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (order.size() > 0)
    {
      Order aOrder = order.get(order.size() - 1);
      aOrder.delete();
      order.remove(aOrder);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "email" + ":" + getEmail()+ "," +
            "address" + ":" + getAddress()+ "]";
  }
}