/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;
import java.util.*;

// line 41 "../../../../../MammaMiaPersistence.ump"
// line 27 "../../../../../MammaMia.ump"
public class Customer implements Serializable
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
  private MammaMia store;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, long aPhoneNumber, String aEmail, String aAddress, MammaMia aStore)
  {
    name = aName;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    address = aAddress;
    order = new ArrayList<Order>();
    boolean didAddStore = setStore(aStore);
    if (!didAddStore)
    {
      throw new RuntimeException("Unable to create customer due to store");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 30 "../../../../../MammaMia.ump"
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
    // line 36 "../../../../../MammaMia.ump"
    if((aPhoneNumber < 1000000000L || aPhoneNumber > 9999999999L) && email.equals(null)){
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
    // line 42 "../../../../../MammaMia.ump"
    if((aEmail == null || aEmail.length() == 0) && Objects.isNull(phoneNumber)){
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
    // line 48 "../../../../../MammaMia.ump"
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
  /* Code from template association_GetOne */
  public MammaMia getStore()
  {
    return store;
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
  /* Code from template association_SetOneToMany */
  public boolean setStore(MammaMia aStore)
  {
    boolean wasSet = false;
    if (aStore == null)
    {
      return wasSet;
    }

    MammaMia existingStore = store;
    store = aStore;
    if (existingStore != null && !existingStore.equals(aStore))
    {
      existingStore.removeCustomer(this);
    }
    store.addCustomer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (order.size() > 0)
    {
      Order aOrder = order.get(order.size() - 1);
      aOrder.delete();
      order.remove(aOrder);
    }
    
    MammaMia placeholderStore = store;
    this.store = null;
    if(placeholderStore != null)
    {
      placeholderStore.removeCustomer(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "email" + ":" + getEmail()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null");
  }
}