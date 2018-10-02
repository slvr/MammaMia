/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 69 "Discovery.ump"
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private int calories;
  private float price;

  //Ingredient Associations
  private Pizza pizza;
  private Menu menu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(int aCalories, float aPrice, Pizza aPizza, Menu aMenu)
  {
    calories = aCalories;
    price = aPrice;
    boolean didAddPizza = setPizza(aPizza);
    if (!didAddPizza)
    {
      throw new RuntimeException("Unable to create ingredient due to pizza");
    }
    boolean didAddMenu = setMenu(aMenu);
    if (!didAddMenu)
    {
      throw new RuntimeException("Unable to create ingredient due to menu");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCalories(int aCalories)
  {
    boolean wasSet = false;
    // line 72 "Discovery.ump"
    if(aCalories < 0){
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
    // line 78 "Discovery.ump"
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
  /* Code from template association_GetOne */
  public Pizza getPizza()
  {
    return pizza;
  }
  /* Code from template association_GetOne */
  public Menu getMenu()
  {
    return menu;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPizza(Pizza aPizza)
  {
    boolean wasSet = false;
    if (aPizza == null)
    {
      return wasSet;
    }

    Pizza existingPizza = pizza;
    pizza = aPizza;
    if (existingPizza != null && !existingPizza.equals(aPizza))
    {
      existingPizza.removeIngredient(this);
    }
    pizza.addIngredient(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMenu(Menu aMenu)
  {
    boolean wasSet = false;
    if (aMenu == null)
    {
      return wasSet;
    }

    Menu existingMenu = menu;
    menu = aMenu;
    if (existingMenu != null && !existingMenu.equals(aMenu))
    {
      existingMenu.removeIngredient(this);
    }
    menu.addIngredient(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Pizza placeholderPizza = pizza;
    this.pizza = null;
    if(placeholderPizza != null)
    {
      placeholderPizza.removeIngredient(this);
    }
    Menu placeholderMenu = menu;
    this.menu = null;
    if(placeholderMenu != null)
    {
      placeholderMenu.removeIngredient(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "calories" + ":" + getCalories()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "pizza = "+(getPizza()!=null?Integer.toHexString(System.identityHashCode(getPizza())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null");
  }
}