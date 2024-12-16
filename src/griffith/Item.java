package griffith;

public class Item {
	//Instance variables
    private String name;
    private double weight;
    
    // Constructor
    public Item (String name,double weight) {
    	setName(name);
    	setWeight(weight);
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName (String name) {
    	this.name=name;
    }
    
    public double getWeight() {
    	return weight;
    }
    
    public void setWeight(double weight) {
    	this.weight = weight;
    }
}
