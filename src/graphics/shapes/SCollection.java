package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape {
	private ArrayList<Shape> collection;
	
	public SCollection() {
		collection = new ArrayList<Shape>();
	}
	
	/*
	public ArrayList<Shape> getCollection() {
		return this.collection;
	}
	*/
	
	public void add(Shape shape) {
		this.collection.add(shape);
	}
	
	
	

	public void remove(Shape s) {
		collection.remove(s);
	}
	
	public Iterator<Shape> iterator() {
		return collection.iterator();
	}
	
	@Override
	public Point getLoc() {
		// On retourne la position de la 1ere forme
		// A v�rifier
		if (collection != null)
			return collection.get(0).getLoc();
		else return null;
	}

	@Override
	public void setLoc(Point point) {
		// A v�rifier aussi
		Iterator<Shape> itr = collection.iterator();
		while (itr.hasNext()) {
			itr.next().setLoc(point);
		}		
	}

	@Override
	public void translate(int dx, int dy) {
		Iterator<Shape> itr = collection.iterator();
		while (itr.hasNext()) {
			Shape shape = itr.next();
			shape.translate(dx, dy);
		}		
	}
	
	@Override
	public Rectangle getBounds() {
		Iterator<Shape> itr = collection.iterator();
		// Deux fa�ons de faire pour d�clarer bounds:		
		// 1
		/*
		Rectangle bounds = itr.next().getBounds();
		while (itr.hasNext()) {
			Shape shape = itr.next();
			bounds = bounds.union(shape.getBounds());
		}
		*/		
		//2
		Rectangle bounds = new Rectangle(-1,-1); // Rectangle trait� comme non-existant (bounds = null ne fonctionnant pas)
		while (itr.hasNext()) {
			Shape shape = itr.next();
			bounds = bounds.union(shape.getBounds());
		}
		return bounds;
		
		//3 -> Bugs
		/*
		Rectangle bounds = new Rectangle();
		for (Iterator<Shape> itr = collection.iterator(); itr.hasNext();) {
			bounds.union(((Shape) itr.next()).getBounds());
		}
		return bounds;
		*/
	}
	

	@Override
	public void accept(ShapeVisitor sVisitor) {
		sVisitor.visitCollection(this);		
	}

	public ArrayList<Shape> getCollection() {
		return collection;
	}

	public void delete(Shape shape) {
		this.collection.remove(shape);
	}
}
