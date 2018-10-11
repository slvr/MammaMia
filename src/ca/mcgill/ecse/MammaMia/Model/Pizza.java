/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;
import java.util.*;

// line 47 "../../../../../MammaMiaPersistence.ump"
// line 55 "../../../../../MammaMia.ump"
public class Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Pizza Attributes
  private int calories;
  private float price;

  //Pizza Associations
  private List<Ingredient> ingredients;
  private Order order;
  private Menu menu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pizza(int aCalories, float aPrice, Order aOrder, Menu aMenu)
  {
    calories = aCalories;
    price = aPrice;
    ingredients = new ArrayList<Ingredient>();
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create pizza due to order");
    }
    boolean didAddMenu = setMenu(aMenu);
    if (!didAddMenu)
    {
      throw new RuntimeException("Unable to create pizza due to menu");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCalories(int aCalories)
  {
    boolean wasSet = false;
    // line 58 "../../../../../MammaMia.ump"
    if(aCalories <= 0){
    			return false;
    		}
    // END OF UMPLE BEFORE INJECTION
    calories = aCalories;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    // line 64 "../../../../../MammaMia.ump"
    if(aPrice < 0){
    			return false;
    		}
    // END OF UMPLE BEFORE INJECTION
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
  public Ingredient getIngredient(int index)
  {
    Ingredient aIngredient = ingredients.get(index);
    return aIngredient;
  }

  public List<Ingredient> getIngredients()
  {
    List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
    return newIngredients;
  }

  public int numberOfIngredients()
  {
    int number = ingredients.size();
    return number;
  }

  public boolean hasIngredients()
  {
    boolean has = ingredients.size() > 0;
    return has;
  }

  public int indexOfIngredient(Ingredient aIngredient)
  {
    int index = ingredients.indexOf(aIngredient);
    return index;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_GetOne */
  public Menu getMenu()
  {
    return menu;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfIngredients()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Ingredient addIngredient(int aCalories, float aPrice, Menu aMenu)
  {
    return new Ingredient(aCalories, aPrice, this, aMenu);
  }

  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    Pizza existingPizza = aIngredient.getPizza();
    boolean isNewPizza = existingPizza != null && !this.equals(existingPizza);
    if (isNewPizza)
    {
      aIngredient.setPizza(this);
    }
    else
    {
      ingredients.add(aIngredient);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeIngredient(Ingredient aIngredient)
  {
    boolean wasRemoved = false;
    //Unable to remove aIngredient, as it must always have a pizza
    if (!this.equals(aIngredient.getPizza()))
    {
      ingredients.remove(aIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addIngredientAt(Ingredient aIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addIngredient(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(ingredients.contains(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addIngredientAt(aIngredient, index);
    }
    return wasAdded;
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
      existingOrder.removePizza(this);
    }
    order.addPizza(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setMenu(Menu aMenu)
  {
    boolean wasSet = false;
    //Must provide menu to pizza
    if (aMenu == null)
    {
      return wasSet;
    }

    if (menu != null && menu.numberOfPizza() <= Menu.minimumNumberOfPizza())
    {
      return wasSet;
    }

    Menu existingMenu = menu;
    menu = aMenu;
    if (existingMenu != null && !existingMenu.equals(aMenu))
    {
      boolean didRemove = existingMenu.removePizza(this);
      if (!didRemove)
      {
        menu = existingMenu;
        return wasSet;
      }
    }
    menu.addPizza(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=ingredients.size(); i > 0; i--)
    {
      Ingredient aIngredient = ingredients.get(i - 1);
      aIngredient.delete();
    }
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removePizza(this);
    }
    Menu placeholderMenu = menu;
    this.menu = null;
    if(placeholderMenu != null)
    {
      placeholderMenu.removePizza(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "calories" + ":" + getCalories()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null");
  }
}