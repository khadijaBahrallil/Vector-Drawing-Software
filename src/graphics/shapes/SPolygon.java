package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SPolygon extends Shape{

	private Point[] points;
	
	public SPolygon(Point[] p) {
		this.points = p;
	}
	
	public SPolygon() {
		this.points = null;
	}
	

	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLoc(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translate(int dx, int dy) {
		for(Point p : this.points) {
			p.setLocation(p.getX()+dx, p.getY()+dy);;
		}
		
	}

	@Override
	public Rectangle getBounds() {
		double x=this.points[0].getX();
		double y=this.points[0].getY();
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
		sVisitor.visitPolygon(this);			
	}

	public Point[] getPoints() {
		return points;
	}

	/*public int[] getAllX() {
		ArrayList<Integer> xs = new ArrayList<>();
		int tabx;
		return null;
	}*/
	
	public void setPoints(Point[] points) {
		this.points = points;
	}

}
