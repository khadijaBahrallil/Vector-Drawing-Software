package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SPath extends Shape{
	private ArrayList<Point> points;
	
	
	public SPath(ArrayList<Point> pts) {
		this.points = pts;
	}
	
	public SPath() {
		this.points = new ArrayList<Point>();
	}
	
	public ArrayList<Point> getPoints() {
		return this.points;
	}
	
	public void addPoint(Point p) {
		this.points.add(p);
	}
	
	public void removePoint(Point p) {
		this.points.remove(p);
	}
	
	@Override
	public void translate(int dx, int dy) {
		for(Point p : this.points) {
			p.setLocation(p.getX()+dx, p.getY()+dy);;
		}
		
	
	}
	
	@Override
	public Rectangle getBounds() {
		double x=this.points.get(0).getX();
		double y=this.points.get(0).getY();
		double w=0;
		double h=0;
		for(Point p : this.points) {
			if(p.getX() < x)
				x=p.getX();
			if(p.getY() < y)
				y=p.getY();
			if(p.getX() > w)
				w=p.getX();
			if(p.getY() > h)
				h=p.getY();
		}
		return new Rectangle((int)x-3,(int)y-3,(int)(w-x+3),(int)(h-y+3));
	}
	
	@Override
	public void accept(ShapeVisitor sVisitor) {
		sVisitor.visitPass(this);		
	}

	@Override
	public Point getLoc() {
		return null;
	}

	@Override
	public void setLoc(Point point) {
	}
	
}
