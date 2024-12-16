package griffith;

public class Circle {
     private double radius;
     private String colour;
     
     // Constructor
     public Circle(double radius, String colour) {
    	 // Set the radius and color using setter methods to ensure validation
    	    setRadius(radius);
    	    setColour(colour);
    	}

     // Gets radius
     public double getRadius() {
    	 return radius;
     }
     
     public void setRadius(double radius) {
    	 //Validates the radius value
    	 if (radius<=0) {
    		 //Throws an exception if the radius is not positive
    		 throw new IllegalArgumentException("The value of the radius must be more than zero");
    	 }
    	 //Set the radius
    	 this.radius=radius;
     }
     //Method to calculate the area of the circle
     public double area() {
    	 return Math.PI *radius*radius;
     }
     
     public String getColour() {
    	 return colour;
     }
     
     public void setColour(String colour) {
    	 this.colour =colour;
     }
}
