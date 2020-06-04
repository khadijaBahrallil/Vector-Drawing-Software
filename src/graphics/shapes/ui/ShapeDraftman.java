package graphics.shapes.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SPath;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.PathAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

public class ShapeDraftman implements ShapeVisitor {

	private Graphics2D g;
	private SelectionAttributes s = new SelectionAttributes();
	private ColorAttributes c = new ColorAttributes();
	private FontAttributes f = new FontAttributes();
	private PathAttributes p = new PathAttributes();
	
	
	

	public ShapeDraftman(Graphics g) {
		this.g = (Graphics2D) g;
	}

	// Dessin du carré de Sélection

	public void drawSelectionShape(Rectangle rect) {
		final int size = 5;
		g.setColor(Color.black);
		g.drawRect(rect.x - size + 2, rect.y - size + 2, size, size);
	}

	// Visit Shapes
	@Override
	public void visitRectangle(SRectangle rect) {
		if (rect != null) {
			int sX = rect.getRect().x; // ou sX = rect.getLoc().x;
			int sY = rect.getRect().y; // ou sY = rect.getLoc().y;
			int sW = rect.getRect().width;
			int sH = rect.getRect().height;

			ColorAttributes cA = (ColorAttributes) rect.getAttributes(c.getId());
			if (cA != null) {
				if (cA.filled()) {
					g.setColor(cA.filledColor());
					g.fillRect(sX, sY, sW, sH);
				}
				if (cA.stroked()) {
					g.setColor(cA.strokedColor());
					g.drawRect(sX, sY, sW, sH);
				}
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(sX, sY, sW, sH);
			}

			SelectionAttributes sA = (SelectionAttributes) rect.getAttributes(s.getId());
			if (sA != null && sA.isSelected()) {
				drawSelectionShape(rect.getBounds());
			}
		}
	}

	@Override
	public void visitCircle(SCircle scircle) {
		if (scircle != null) {
			int sX = scircle.getLoc().x;
			int sY = scircle.getLoc().y;
			int diam = scircle.getRadius() * 2;

			ColorAttributes cA = (ColorAttributes) scircle.getAttributes(c.getId());
			if (cA != null) {
				if (cA.filled()) {
					g.setColor(cA.filledColor());
					g.fillOval(sX, sY, diam, diam);
				}
				if (cA.stroked()) {
					g.setColor(cA.strokedColor());
					g.drawOval(sX, sY, diam, diam);
				}
			} else {
				g.setColor(Color.BLACK);
				g.fillOval(sX, sY, diam, diam);
			}

			SelectionAttributes sA = (SelectionAttributes) scircle.getAttributes(s.getId());
			if (sA != null && sA.isSelected()) {
				drawSelectionShape(scircle.getBounds());
			}
		}
	}

	@Override
	public void visitText(SText stext) {
		if (stext != null) {
			Point loc = stext.getLoc();
			String text = stext.getText();

			FontAttributes fA = (FontAttributes) stext.getAttributes(f.getId());
			if (fA != null) {
				FontMetrics fMetrics = g.getFontMetrics(fA.font());
				fA.setFontMetrics(fMetrics);

				ColorAttributes cA = (ColorAttributes) stext.getAttributes(c.getId());
				if (cA != null) {
					if (cA.filled()) {
						if (stext.getBounds() != null) {
							int sX = stext.getBounds().x;
							int sY = stext.getBounds().y;
							int sW = stext.getBounds().width;
							int sH = stext.getBounds().height;
							g.setColor(cA.filledColor());
							g.fillRect(sX, sY, sW, sH);
						}
					}
					if (cA.stroked()) {
						g.setColor(cA.strokedColor());
						g.drawString(text, loc.x, loc.y);
					}
				}

				SelectionAttributes sA = (SelectionAttributes) stext.getAttributes(s.getId());
				if (sA != null && sA.isSelected()) {
					drawSelectionShape(stext.getBounds());
				}
			}
		}
	}

	@Override
	public void visitCollection(SCollection scollec) {
		Shape shape;
		if (scollec != null) {
			Iterator<Shape> itr = scollec.iterator();
			SelectionAttributes sA = (SelectionAttributes) scollec.getAttributes(s.getId());
			while (itr.hasNext()) {
				shape = itr.next();
				shape.accept(this);
				if (sA != null && sA.isSelected()) {
					drawSelectionShape(shape.getBounds());
				}
			}

		}
	}

	@Override
	public void visitPolygon(SPolygon poly) {
		Polygon polygon = new Polygon();
		if (poly != null) {

			for (Point pt : poly.getPoints()) {

				int x = (int) pt.getX();
				int y = (int) pt.getY();

				polygon.addPoint(x, y);

			}

			ColorAttributes cA = (ColorAttributes) poly.getAttributes(c.getId());
			if (cA != null) {
				if (cA.filled()) {
					g.setColor(cA.filledColor());
					g.fillPolygon(polygon);
				}
				if (cA.stroked()) {
					g.setColor(cA.strokedColor());
					g.drawPolygon(polygon);
				}
			} else {
				g.setColor(Color.BLACK);
				g.fillPolygon(polygon);
			}

			SelectionAttributes sA = (SelectionAttributes) poly.getAttributes(s.getId());
			if (sA != null && sA.isSelected()) {
				drawSelectionShape(poly.getBounds());
			}
		}

	}


	@Override
	public void visitPath(SPath path) {
		if (path != null) {
			PathAttributes pA = (PathAttributes) path.getAttributes(p.getId());
			ColorAttributes cA = (ColorAttributes) path.getAttributes(c.getId());
			if (pA != null) {

				if (cA != null) {
					if (cA.stroked()) {
						g.setColor(cA.strokedColor());
					}
				} else {
					g.setColor(Color.BLACK);
				}
				switch(pA.getMethod()) {
				case method_Points:
					for (Point p : path.getPoints()) {
						g.drawLine((int) p.getX(), (int) p.getY(), (int) p.getX(), (int) p.getY());
					}
					break;
				case method_Lines:  
					ArrayList<Point> points = path.getPoints();
					for (int i = 0; i < points.size() - 1; i++) {

						g.drawLine((int) points.get(i).getX(), (int) points.get(i).getY(),
								(int) points.get(i + 1).getX(), (int) points.get(i + 1).getY());
					}
					break;
				case method_Interpolation:
					ArrayList<Point> points1 = path.getPoints();

					if (points1.size() > 2) {
						double[] xs = new double[points1.size()];
						double[] ys = new double[points1.size()];
						ArrayList<WeightedObservedPoint> observations = new ArrayList<>();

						
						 for (int i = 0; i < points1.size(); i++) { 
							 observations.add(new WeightedObservedPoint(1.0, points1.get(i).getX(), points1.get(i).getY())); 
							 xs[i]= points1.get(i).getX(); 
						} 
						 PolynomialCurveFitter function = PolynomialCurveFitter.create(3);
						 
						 PolynomialFunction func = new PolynomialFunction(function.fit(observations));
						 
						 for (int i = 0; i < xs.length; i++) { 
							 ys[i] = func.value(xs[i]); 
						} 
						 for (int i= 0; i < xs.length - 1; i++) { 
							 g.drawLine((int) xs[i], (int) ys[i], (int) xs[i], (int) ys[i]); 
						} 
					}
					break;
				
				

				}
			}

			SelectionAttributes sA = (SelectionAttributes) path.getAttributes(s.getId());
			if (sA != null && sA.isSelected()) {
				drawSelectionShape(path.getBounds());
			}
		}

	}

}