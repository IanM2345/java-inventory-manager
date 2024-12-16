package griffith;

//Ian Mwai Gachoki
//3132394

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Test {
	
	// Constants that are used in the code
	private static final double Max_Weight = 100.0;
	private ArrayList<Item> inventory = new ArrayList<>();
	
	//Initializes an ArrayList<Item> variable called inventory with an empty ArrayList to ensure its availability and ensures each object in the Test class has its own distinct list of items
	private static final String File_Name= "inventory.txt";
	
	//Method to calculate total weight of inventory
	public double calculateWeight() {
		double totalWeight =0.0;
		for (Item item: inventory) {
			totalWeight += item.getWeight();
		}
		return totalWeight;
	}
	
	//Method to add an item to the inventory
	public void addItem(String name, double weight) {
		if (calculateWeight()+ weight <= Max_Weight) {
			inventory.add(new Item(name, weight));
			saveInventory();
			System.out.println("Item \""+name +"\"not in inventory.");
		}
		else {
			System.out.println("Item cannot be added as max weight has been reached.");
		}
	}
	
	//Method to remove an item from the inventory
	public void removeItem(String itemName) {
		boolean removed= false;
		 for (Item item : inventory) {
			 if (item.getName().equals(itemName)) {
				 inventory.remove(item);
				 saveInventory();
					System.out.println("Item \""+itemName +"\"not in inventory.");
				 removed= true;
				 break;
			 }
		 }
		 if (!removed) {
			 System.out.println("Item \""+itemName +"\"not in inventory.");
		 }
	}
	
	//Method to display the inventory
	public void showInventory() {
		if (inventory.isEmpty()) {
			
			System.out.println("The Inventory is empty");
			
		}
		else {
			System.out.println("Current Inventory: ");
			for (Item item: inventory) {
				System.out.println("Item: "+ item.getName()+", Weight: "+ item.getWeight());
			}
			
			System.out.println("Total inventory weight is : "+ calculateWeight());
		}
	}
	
	//Method to load an inventory file
	public void loadInventory() {
		try {
			//Open the inventory file for reading
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(File_Name), "UTF-8"));
			String line;
			// Read each line from the file
			while((line = reader.readLine())!=null) {
				//Split the line into parts using comma as the delimiter
				String[] parts = line.split(",");
				// Checks if the line contains both name and weight
				if (parts.length ==2) {
					//Extract the name and weight from the parts
					String name = parts[0];
					double weight = Double.parseDouble(parts[1]);
					//Create a new Item object and add it to the inventory
					inventory.add(new Item(name,weight));
				}
			}
		}
			catch (FileNotFoundException e) {
				System.out.println("Inventory file not found. It will start with an empty inventory");
			}
			catch(IOException e) {
				System.out.println("Error reading inventory file: " +e.getMessage());
			}
			catch (NumberFormatException e) {
				System.out.println("Error parsing inventory file: "+ e.getMessage());
			}
		
	}
	
	//Method to save an inventory file
	public void saveInventory() {
		try (PrintWriter writer = new PrintWriter(new FileWriter(File_Name))){
			for (Item item : inventory) {
				writer.println(item.getName()+","+item.getWeight());
			}
		}
		catch (IOException e) {
			System.out.println("Error writing to inventory file: " + e.getMessage());
		}
	}
     public static void main(String [] args) {
    	 // Creates an ArrayList to store circle objects
    	 ArrayList<Circle> circles= new ArrayList<>();
    	 // Create a Scanner object to read input from the user
    	 Scanner scan= new Scanner(System.in);
    	 
    	 //Loop three times to get input for three circles
    	 for (int i=0;i<3;i++) {
    		 //Prompt the user to enter the radius of the circle
    		 System.out.println("Enter the radius of circle number "+ (i+1));
    		 double radius = scan.nextDouble();
    		 scan.nextLine();//consume the newline input
    		 
    		 //Prompt the user to enter the color of the circle
    		 System.out.println("Enter the colour of circle number "+ (i+1));
    		 String colour = scan.nextLine();
    		 
    		 try {
    			 //Create a new Circle object with the given radius and color
    			 Circle circle= new Circle(radius,colour);
    			 System.out.println ("Radius = "+ circle.getRadius()+ ", Colour=" +circle.getColour());
    			 circles.add(circle);//Add the circle to the ArrayList
    		 }
    		 catch (IllegalArgumentException e) {
    			 System.out.println("Error: "+e.getMessage());
    			 i--;//Decrremt the loops counter to allow the user to retry entering data of the circle
    		 }
    	 }
    	 // Sort the circles based on their area in descending order
    	 Collections.sort(circles, Comparator.comparingDouble(Circle::area).reversed());
    	 
    	 System.out.println();
    	 System.out.println("Circle colours in order of area size from largest to smallest:");
    	 for (Circle circle : circles) {
    		 System.out.println(circle.getColour());
    	 }
    	 
    	 Test test = new Test();
    	 test.loadInventory();//Load inventory from file when the program starts
    	
    	 Scanner input = new Scanner(System.in);
    	 
    	 boolean quit = false;
    	 
    	 //Main menu loop
    	 while (!quit) {
    		 System.out.println("\nMenu:");
    		 System.out.println("1. Add new item");
    		 System.out.println("2. Remove item");
    		 System.out.println("3. Show inventory");
    		 System.out.println("4. Quit");
    		 System.out.println("Select the numer of your Choice");
    		 
    		 try {
    			 int choice = input.nextInt();
    			 input.nextLine();//Consume newline character
    			 
    			 //Switch statement to handle user's choice
    			 switch (choice) {
    			 
    			 case 1:
    				 System.out.println("Enter name of the item");
    				 String name = input.nextLine();
    				 System.out.println("Enter weight of the item");
    				 double weight = input.nextDouble();
    				 test.addItem(name,  weight);
    				 break;
    				 
    			 case 2:
    				 System.out.println("Enter name of the item you want to remove");
    				 String itemName= input.nextLine();
    				 test.removeItem(itemName);
    				 break;
    				 
    			 case 3:
    				 test.showInventory();
    				 break;
    				 
    			 case 4: 
    				 test.saveInventory();// Save inventory before quitting
    				 quit = true;
    				 break;
    				 
    			  default:
    				  System.out.println("Please enter the number between 1 and 4");
    				  break;
    			 }
    		 }
    		 catch(InputMismatchException e) {
    			 System.out.println("Please enter a number");
    			 input.nextLine();// Consume the new valid input
    		 }
    		 
    	 }
    	 input.close();
     }
}
