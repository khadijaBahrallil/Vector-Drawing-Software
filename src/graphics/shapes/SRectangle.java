package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class SRectangle extends Shape {
	private Rectangle rect;	
	
	public SRectangle(Point point, int rWidth, int rHeight) {
		this.point = point;
		this.rect = new Rectangle((int)point.getX(), (int)point.getY(), rWidth, rHeight); // Utilisation de la classe java Rectangle
	}
	
	public SRectangle(int width, int height) {
		this.point = new Point((int) (Math.random() * 280), (int) (Math.random() * 280));
		this.rect = new Rectangle((int) point.getX(), (int) point.getY(), width, height);
	}

	public Rectangle getRect() {
		return rect;
	}
	
	@Override
	public Point getLoc() {
		//return point;
		return this.rect.getLocation(); // Si on utilise les m�thodes de la classe java Point
	}
	
	@Override
	public void setLoc(Point point) {
		this.point = point;
		this.rect = new Rectangle((int)point.getX(), (int)point.getY(), this.rect.width, this.rect.height);		
	}
	
	@Override
	public void translate(int dx, int dy) {
		Point point = this.point;
		point.x += dx;
		point.y += dy;
		this.setLoc(point);
	}
	
	@Override
	public Rectangle getBounds() {
		return rect;
	}
	
	@Override
	public void accept(ShapeVisitor sVisitor) {
		sVisitor.visitRectangle(this);
	}

	public void resize() {
		int askWidth = Integer.parseInt(JOptionPane.showInputDialog("Please enter width : "));
		int askHeight = Integer.parseInt(JOptionPane.showInputDialog("Please enter height : "));
		this.rect.width = askWidth;
		this.rect.height = askHeight;
	}
	
	
}