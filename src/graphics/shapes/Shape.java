package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;

public abstract class Shape {
	protected Point point;
	private Map <String,Attributes> attributes;
	
	public Shape() {
		attributes = new TreeMap <String,Attributes>();
	}
	
	public abstract Point getLoc();
	
	public abstract void setLoc(Point point);
	
	public abstract void translate(int dx, int dy);
	
	public abstract Rectangle getBounds();
	
	
	public void addAttributes(Attributes attribute) {
		this.attributes.put(attribute.getId(), attribute);
	}
	
	public Attributes getAttributes(String id) {
		return this.attributes.get(id);
	}
	
	public abstract void accept(ShapeVisitor sVisitor);
}