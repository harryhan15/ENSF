import java.util.Iterator;
import java.util.TreeSet;
import java.util.Comparator;

public class Geometry implements Comparable{
	private TreeSet <Shape> shapes = new TreeSet <Shape>(new ShapeComparator());
	
	public void add(Rectangle r) {
		shapes.add(r);
	}
	
	public void add(Prism p) {
		shapes.add(p);
	}
	
	public void add(Circle c) {
		shapes.add(c);
	}
	
	public int compareTo(Object o) {
		if(this == o) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public void showAll() {		
		Iterator<Shape> it = shapes.iterator();

		while(it.hasNext() ) {
			System.out.println(it.next());
		}
	}
	
	public void calculator(Shape s) {
		System.out.printf("The area, perimeter, and volume of %s are: %.2f, %.2f, %.2f\n", 
			s.getName(), s.area(), s.perimeter(), s.volume());
	}
	
	public static void main(String[] args) {
	
		Rectangle r1 = new Rectangle(3.0, 4.0, 5.0, 6.0, "R1", new Colour("Black"));
        Circle c1 = new Circle (13.0, 14.0, 15.0, "C1",new Colour ("Green"));
        //System.out.println(r1);
        //System.out.println(c1);
        
		Rectangle r2 = new Rectangle(23.0, 24.0, 25.0, 26.0, "R2", new Colour("Black"));
        Circle c2 = new Circle (33.0, 34.0, 35.0, "C2", new Colour("Yellow"));
        //System.out.println(r2);
        //System.out.println(c2);
        
		Prism p1 = new Prism(43.0, 44.0, 45.0, 46.0, 47.0, "P1", new Colour("White"));
        Prism p2 = new Prism (53.0, 54.0, 55.0, 56.0, 57.0, "P2", new Colour("Gray"));
        //System.out.println(p1);
        //System.out.println(p2);
        
        
        //      SECTION B: THE FOLLOWING CODE SEGMENT MUST BE UNCOMMENTED ONLY
        //      FOR EXERCISE B
        
         Geometry demo = new Geometry();
         System.out.println("\nAdding Rectangle, Circle, and Prism objects to the list... ");
         demo.add(r1);
         demo.add(r2);
         demo.add(c1);
         demo.add(c2);
         demo.add(p1);
         demo.add(p2);
         
         System.out.println("\nShowing information about objects added to the list:");
         demo.showAll();
         
         System.out.println("\nShowing area, perimeter, and volume of objects in the list:");
         
         Iterator <Shape> it = demo.shapes.iterator();
         while(it.hasNext()) {
        	demo.calculator(it.next());
         }
	}

	class ShapeComparator implements Comparator<Shape> {
		@Override
		public int compare (Shape s1, Shape s2) {
			String name1 = s1.getName().trim();
			String name2 = s2.getName().trim();
		
			return name1.compareTo(name2);
		}
	} 
}
