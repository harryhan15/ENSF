/*
 * started by: M. Moussavi
 * Date: Feb 2015
 * Modified by: STUDENTS NAMES
 */
class Colour implements Cloneable
{
    private String colour;
    
	public Colour(String s) {
		colour = new String(s);
	}
	
    public void setColour(String newColour){
    	colour = newColour;
    }
    
	@Override
	public String toString(){
		return colour;
	}

}
