package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class SCircle extends Shape {
	private Point loc;
	private int radius;
	
	public SCircle(Point point, int radius) {
		this.setLoc(point);
		this.setRadius(radius);
	}
	
	public SCircle(int radius) {
		this.point = new Point((int) (Math.random() * 280), (int) (Math.random() * 280));
		this.setLoc(point);
		this.setRadius(radius);
	}

	@Override
	public Point getLoc() {
		return this.loc;
	}

	@Override
	public void setLoc(Point point) {
		this.loc = point;		
	}
	
	public int getRadius() {
		return this.radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;		
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
		return new Rectangle(this.loc.x,this.loc.y,this.radius*2,this.radius*2);
	}

	@Override
	public void accept(ShapeVisitor sVisitor) {
		sVisitor.visitCircle(this);		
	}

	@Override
	public void resize() {
		int askRadius = Integer.parseInt(JOptionPane.showInputDialog("Please enter radius : "));
		this.radius = askRadius;		
	}
	
}