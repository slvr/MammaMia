/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.MammaMia.model;
import java.io.Serializable;
import java.util.*;

// line 71 "../../../../../MammaMiaPersistence.ump"
// line 84 "../../../../../MammaMia.ump"
public class Menu implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Associations
  private List<Pizza> pizza;
  private List<Ingredient> ingredients;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu()
  {
    pizza = new ArrayList<Pizza>();
    ingredients = new ArrayList<Ingredient>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPizzaValid()
  {
    boolean isValid = numberOfPizza() >= minimumNumberOfPizza();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPizza()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Pizza addPizza(int aCalories, float aPrice, Order aOrder)
  {
    Pizza aNewPizza = new Pizza(aCalories, aPrice, aOrder, this);
    return aNewPizza;
  }

  public boolean addPizza(Pizza aPizza)
  {
    boolean wasAdded = false;
    if (pizza.contains(aPizza)) { return false; }
    Menu existingMenu = aPizza.getMenu();
    boolean isNewMenu = existingMenu != null && !this.equals(existingMenu);

    if (isNewMenu && existingMenu.numberOfPizza() <= minimumNumberOfPizza())
    {
      return wasAdded;
    }
    if (isNewMenu)
    {
      aPizza.setMenu(this);
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
    //Unable to remove aPizza, as it must always have a menu
    if (this.equals(aPizza.getMenu()))
    {
      return wasRemoved;
    }

    //menu already at minimum (1)
    if (numberOfPizza() <= minimumNumberOfPizza())
    {
      return wasRemoved;
    }

    pizza.remove(aPizza);
    wasRemoved = true;
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
  public static int minimumNumberOfIngredients()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Ingredient addIngredient(int aCalories, float aPrice, Pizza aPizza)
  {
    return new Ingredient(aCalories, aPrice, aPizza, this);
  }

  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    Menu existingMenu = aIngredient.getMenu();
    boolean isNewMenu = existingMenu != null && !this.equals(existingMenu);
    if (isNewMenu)
    {
      aIngredient.setMenu(this);
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
    //Unable to remove aIngredient, as it must always have a menu
    if (!this.equals(aIngredient.getMenu()))
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

  public void delete()
  {
    while (pizza.size() > 0)
    {
      Pizza aPizza = pizza.get(pizza.size() - 1);
      aPizza.delete();
      pizza.remove(aPizza);
    }
    
    while (ingredients.size() > 0)
    {
      Ingredient aIngredient = ingredients.get(ingredients.size() - 1);
      aIngredient.delete();
      ingredients.remove(aIngredient);
    }
    
  }

}