package graphics.shapes.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
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

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

public class ShapeDraftman implements ShapeVisitor {
	private Graphics g;
	// private Shape shape;

	public ShapeDraftman(Graphics g) {
		this.g = g;
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

			ColorAttributes cA = (ColorAttributes) rect.getAttributes("colorAttributes");
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

			SelectionAttributes sA = (SelectionAttributes) rect.getAttributes("selectionAttributes");
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

			ColorAttributes cA = (ColorAttributes) scircle.getAttributes("colorAttributes");
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

			SelectionAttributes sA = (SelectionAttributes) scircle.getAttributes("selectionAttributes");
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

			FontAttributes fA = (FontAttributes) stext.getAttributes("fontAttributes");
			if (fA != null) {
				FontMetrics fMetrics = g.getFontMetrics(fA.font());
				fA.setFontMetrics(fMetrics);

				ColorAttributes cA = (ColorAttributes) stext.getAttributes("colorAttributes");
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

				SelectionAttributes sA = (SelectionAttributes) stext.getAttributes("selectionAttributes");
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
			SelectionAttributes sA = (SelectionAttributes) scollec.getAttributes("selectionAttributes");
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
		if (poly != null) {
			Polygon polygon = new Polygon();
			for (Point point : poly.getPoints()) {
				polygon.addPoint((int) point.getX(), (int) point.getY());
			}

			ColorAttributes cA = (ColorAttributes) poly.getAttributes("colorAttributes");
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

			SelectionAttributes sA = (SelectionAttributes) poly.getAttributes("selectionAttributes");
			if (sA != null && sA.isSelected()) {
				drawSelectionShape(poly.getBounds());
			}
		}

	}

	@Override
	public void visitPath(SPath pass) {
		if (pass != null) {
			PathAttributes pA = (PathAttributes) pass.getAttributes("pathAttributes");
			ColorAttributes cA = (ColorAttributes) pass.getAttributes("colorAttributes");
			if (pA != null) {

				if (cA != null) {
					if (cA.stroked()) {
						g.setColor(cA.strokedColor());
					}
				} else {
					g.setColor(Color.BLACK);
				}

				if (pA.getMethod().equals("Points")) {
					for (Point p : pass.getPoints()) {
						g.drawLine((int) p.getX(), (int) p.getY(), (int) p.getX(), (int) p.getY());
					}
				}
				if (pA.getMethod().equals("Lines")) {
					ArrayList<Point> points = pass.getPoints();
					for (int i = 0; i < points.size() - 1; i++) {

						g.drawLine((int) points.get(i).getX(), (int) points.get(i).getY(),
								(int) points.get(i + 1).getX(), (int) points.get(i + 1).getY());
					}
				}
				if (pA.getMethod().equals("Interpolation")) {
					ArrayList<Point> points = pass.getPoints();

					ArrayList<Point> clear = new ArrayList<>();
					if (points.size() > 100) {
						clear.add(points.get(0));
						for (int i = 1; i < points.size(); i++) {
							if (points.get(i).getX() > points.get(i - 1).getX()) {
								clear.add(points.get(i));
							}
						}
						int size = 200;
						double[] xs = new double[clear.size()];
						double[] ys = new double[clear.size()];
						ArrayList<WeightedObservedPoint> observations = new ArrayList<>();

						/*for (int i = 0; i < clear.size(); i += size) {
							if (i + size <= clear.size()) {
								for (int j = i; j < i + size; j++) {
									observations.add(
											new WeightedObservedPoint(1.0, clear.get(j).getX(), clear.get(j).getY()));
									xs[j % size] = clear.get(j).getX();
								}
								PolynomialCurveFitter function = PolynomialCurveFitter.create(3);

								PolynomialFunction func = new PolynomialFunction(function.fit(observations));

								for (int j = 0; j < size; j++) {
									ys[j] = func.value(xs[j]);
								}
								for (int i1 = 0; i1 < size - 1; i1++) {
									g.drawLine((int) xs[i1], (int) ys[i1], (int) xs[i1 + 1], (int) ys[i1 + 1]);
								}
							}
						}*/

						
						 for (int i = 0; i < clear.size(); i++) { 
							 observations.add(new WeightedObservedPoint(1.0, clear.get(i).getX(), clear.get(i).getY())); 
							 xs[i]= clear.get(i).getX(); 
						} 
						 PolynomialCurveFitter function = PolynomialCurveFitter.create(3);
						 
						 PolynomialFunction func = new PolynomialFunction(function.fit(observations));
						 
						 for (int i = 0; i < xs.length; i++) { 
							 ys[i] = func.value(xs[i]); 
						} 
						 for (int i= 0; i < xs.length - 1; i++) { 
							 g.drawLine((int) xs[i], (int) ys[i], (int) xs[i + 1], (int) ys[i + 1]); 
						} 
						
					}

				}
			}

			SelectionAttributes sA = (SelectionAttributes) pass.getAttributes("selectionAttributes");
			if (sA != null && sA.isSelected()) {
				drawSelectionShape(pass.getBounds());
			}
		}

	}

}