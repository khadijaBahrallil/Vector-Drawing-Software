package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class SPolygon extends Shape {
	private double scale;

	private Point[] points;
	private int x[];
	private int y[];

	public SPolygon(Point[] p) {
		this.scale = 1;
		this.points = p;
		x = new int[p.length];
		y = new int[p.length];
		for (int i = 0; i < p.length; ++i) {
			x[i] = p[i].x;
			y[i] = p[i].y;

		}

	}

	public SPolygon() {
		this.points = null;
	}

	public Point getLoc() {
		return this.getBounds().getLocation();
	}

	public void setLoc(Point p) {
		int dx = p.x - this.getBounds().x;
		int dy = p.y - this.getBounds().y;
		for (Point pt : this.points) {
			pt.x += dx;
			pt.y += dy;
		}
	}

	@Override
	public void translate(int dx, int dy) {
		for (Point p : this.points) {
			p.setLocation(p.getX() + dx, p.getY() + dy);
			;
		}
	}

	@Override
	public Rectangle getBounds() {
		double x = this.points[0].getX();
		double y = this.points[0].getY();
		double w = 0;
		double h = 0;
		for (Point p : this.points) {
			if (p.getX() < x)
				x = p.getX();
			if (p.getY() < y)
				y = p.getY();
			if (p.getX() > w)
				w = p.getX();
			if (p.getY() > h)
				h = p.getY();
		}
		return new Rectangle((int) x - 3, (int) y - 3, (int) (w - x + 3), (int) (h - y + 3));
	}

	@Override
	public void accept(ShapeVisitor sVisitor) {
		sVisitor.visitPolygon(this);
	}

	public Point[] getPoints() {
		return points;
	}

	public double getScale() {
		return this.scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public String printPoints() {
		String s = "";
		for (Point p : this.points) {
			s += p.x + "," + p.y + " ";
		}
		return s.substring(0, s.length() - 1);
	}

	public int[] getX() {
		return x;
	}

	public void setX(int[] x) {
		this.x = x;
	}

	public int[] getY() {
		return y;
	}

	public void setY(int[] y) {
		this.y = y;
	}

	public void resize() {
		int askscale = Integer.parseInt(JOptionPane.showInputDialog("Please enter scale : "));
		this.setScale(askscale);

	}

}
