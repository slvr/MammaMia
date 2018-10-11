package ca.mcgill.ecse.MammaMia.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import ca.mcgill.ecse.MammaMia.Model.CheesePizza;
import ca.mcgill.ecse.MammaMia.Model.CustomPizza;
import ca.mcgill.ecse.MammaMia.Model.Customer;
import ca.mcgill.ecse.MammaMia.Model.Ingredient;
import ca.mcgill.ecse.MammaMia.Model.Item;
import ca.mcgill.ecse.MammaMia.Model.MammaMia;
import ca.mcgill.ecse.MammaMia.Model.Order;
import ca.mcgill.ecse.MammaMia.Model.OrderDetails;
import ca.mcgill.ecse.MammaMia.Model.Pizza;
import ca.mcgill.ecse.MammaMia.Model.VeggiePizza;
import ca.mcgill.ecse.MammaMia.Model.Menu;
import ca.mcgill.ecse.MammaMia.Persistence.PersistenceObjectStream;



public class MammaMiaController {

	public MammaMiaController(){
	}
	
	public Customer createCustomer(String aName, long aPhoneNumber, String aEmail, String aAddress) throws InvalidInputException{
		if(aName == null || aName.length() == 0 || aName.length() > 20){
			throw new InvalidInputException("Customer name cannot be empty");
		}
		if((aPhoneNumber < 1000000000L || aPhoneNumber > 9999999999L) && aEmail == null){
			throw new InvalidInputException("Please enter a phone number or email");
		}
		if((aEmail == null || aEmail.length() == 0) && Objects.isNull(aPhoneNumber)){
			throw new InvalidInputException("Please enter a phone number or email");
		}
		if(aAddress == null || aAddress.length() == 0){
			throw new InvalidInputException("Please enter a delivery address");
		}
		MammaMia mm = MammaMia.getInstance();
		Customer c = new Customer(aName, aPhoneNumber, aEmail, aAddress, mm);
		PersistenceObjectStream.saveToXMLwithXStream(mm);
		return c;
	}
	
	public Order createOrder(Customer c) throws InvalidInputException{
		if(c == null){
			throw new InvalidInputException("Order must have a customer! ");
		}
		int orderNumber = PersistenceObjectStream.getNextOrderNumber();
		Order o = new Order(c);
		o.setOrderNumber(orderNumber);
		PersistenceObjectStream.saveToXMLwithXStream(MammaMia.getInstance());
		return o;
	}
	
	public void saveOrder(Order o) throws InvalidInputException{
		if(o == null){
			throw new InvalidInputException("An order must be created. ");
		}
		PersistenceObjectStream.saveToXMLwithXStream(MammaMia.getInstance());
	}
	
	public void deleteOrder(Order order) throws InvalidInputException{
		if(!(order.getStatus().equals(Order.Status.Delivered))){
			throw new InvalidInputException("Order must be delivered to be deleted. ");
		}
		List<Pizza> pizzas = order.getPizza();
		for(Pizza p : pizzas){
			p.delete();
		}
		order.delete();
		PersistenceObjectStream.saveToXMLwithXStream(MammaMia.getInstance());
	}
	
	public OrderDetails addPizzaToOrder(Pizza pizza, Order order, Customer customer, int quantity) throws InvalidInputException{
		String error = "";
		if(pizza == null){
			error += error + "A pizza must be created. ";
		}
		else if(customer == null && order == null){
			error += error + "Either a customer or an order must be specified. ";
		}
		error = error.trim();
		if(error.length() > 0){
			throw new InvalidInputException(error);
		}
		if(order != null){
			order.addPizza(pizza);
		}
		else{
			order = new Order(customer);
			order.addPizza(pizza);
		}
		OrderDetails od = order.addDetail(quantity, new Item(pizza.getCalories(), pizza.getPrice()));
		order.addDetail(od);
		PersistenceObjectStream.saveToXMLwithXStream(MammaMia.getInstance());

	}
	
	public Pizza createPizza(String name, int calories, float price, Order order, Menu menu){
		Pizza p;
		if(name.equals("Moon Cheese")){
			p = new CheesePizza(calories, price, order, menu);
		}
		else if(name.equals("Veggie Tales")){
			p = new VeggiePizza(calories, price, order, menu);
		}
		else{
			p = new CustomPizza(calories, price, order, menu);
		}
		return p;
	}
	
}
