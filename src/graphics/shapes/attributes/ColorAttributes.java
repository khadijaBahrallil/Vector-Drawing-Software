package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {
	public static String id = "colorAttributes";
	private boolean filled;
	private boolean stroked;	
	private Color filledColor;
	private Color strokedColor;
	
	// Constructeur par d�faut
	public ColorAttributes () {
		
		this.filled = true;
		this.stroked = true;
		this.filledColor = Color.blue;
		this.strokedColor = Color.black;		
	}
	
	public ColorAttributes (boolean filled, boolean stroked, Color filledColor, Color strokedColor) {
		
		this.filled = filled;
		this.stroked = stroked;
		this.filledColor = filledColor;
		this.strokedColor = strokedColor;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean stroked() {
		return this.stroked;
	}
	
	public boolean filled() {
		return this.filled;
	}
	
	public Color filledColor() {
		return this.filledColor;
	}
	
	public Color strokedColor() {
		return this.strokedColor;
	}

}