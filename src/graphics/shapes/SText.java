package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape {
	private Point loc;
	private String text;
	
	public SText(Point loc, String text) {
		this.setLoc(loc);
		this.setText(text);
	}
	
	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point point) {
		this.loc = point;		
	}
	
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;		
	}
	
	
	@Override
	public void translate(int dx, int dy) {
		Point point = this.loc;
		point.x += dx;
		point.y += dy;
		this.setLoc(point);		
	}
	
	@Override
	public Rectangle getBounds() {
		FontAttributes fA = (FontAttributes) this.getAttributes("fontAttributes");
		if (fA != null) {
			Rectangle bounds = fA.getBounds(this.text);			
			return new Rectangle(loc.x,loc.y-bounds.height,bounds.width,bounds.height);
		}
		else return null;		
	}


	@Override
	public void accept(ShapeVisitor sVisitor) {
		sVisitor.visitText(this);		
	}
	
}
