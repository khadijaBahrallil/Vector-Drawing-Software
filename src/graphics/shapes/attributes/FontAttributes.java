package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

public class FontAttributes extends Attributes {
	public static String id = "fontAttributes";
	private Font font;
	private Color color;
	private FontMetrics fontMetrics;	
	
	public FontAttributes() {
		// Utiliser le constructeur de Font
		
		//font = new Font("Arial", 0, 0);
		font = Font.decode("Helvetica");
		color = Color.BLUE;
	}
	
	public Font font() {
		return this.font;
	}
	
	public Color fontColor() {
		return this.color;
	}

	@Override
	public String getId() {
		return id;
	}
	
	// M�thode en plus par rapport au sujet
	public void setFontMetrics(FontMetrics fMetrics) {
		this.fontMetrics = fMetrics;
	}
	
	public Rectangle getBounds(String str) {	
		int width = this.fontMetrics.stringWidth(str);
		int height = this.fontMetrics.getHeight();		
		Rectangle bounds = new Rectangle(100,100,width,height);
		return bounds;
	}
	
}