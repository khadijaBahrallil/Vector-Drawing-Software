package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
	private PathAttributes p = new PathAttributes();
	
	
	

	public ShapeDraftman(Graphics g) {
		this.g = (Graphics2D) g;
	}


	public void drawSelectionShape(Rectangle rect) {
		final int size = 5;
		g.setColor(Color.black);
		g.drawRect(rect.x - size + 2, rect.y - size + 2, size, size);
	}

	// Visit Shapes
	@Override
	public void visitRectangle(SRectangle rect) {
		if (rect != null) {
			int sX = rect.getRect().x; 
			int sY = rect.getRect().y; 
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
		Graphics2D g2d = (Graphics2D) g.create();
		if (stext != null) {
			Point loc = stext.getLoc();
			String text = stext.getText();

			FontAttributes fA = (FontAttributes) stext.getAttributes("fontAttributes");
			if (fA != null) {
				FontMetrics fMetrics = g.getFontMetrics(fA.font());
				fA.setFontMetrics(fMetrics);
				int sX = stext.getBounds().x;
				int sY = stext.getBounds().y;
				int sW = stext.getBounds().width;
				int sH = stext.getBounds().height;

				g2d.translate(sX + sW / 2, sY + sH / 2);
				g2d.translate(-sX - sW / 2, -sY - sH / 2);
				ColorAttributes cA = (ColorAttributes) stext.getAttributes("colorAttributes");

				Font fonte = new Font("Helvetica", Font.BOLD, stext.getSizeText());
				g2d.setFont(fonte);

				if (cA != null) {
					if (cA.filled()) {
						if (stext.getBounds() != null) {
							g2d.setColor(cA.filledColor());
							g2d.fillRect(sX, sY, sW, sH);
						}
					}
					if (cA.stroked()) {
						g2d.setColor(cA.strokedColor());
						g2d.drawString(text, loc.x, loc.y);
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
	public void visitPolygon (SPolygon spolygone) {
			Graphics2D g2d = (Graphics2D) g.create();
			if (spolygone != null) {
				int sX = spolygone.getBounds().x;
				int sY = spolygone.getBounds().y;
				int sW = spolygone.getBounds().width;
				int sH = spolygone.getBounds().height;

				g2d.translate(sX + sW / 2, sY + sH / 2);
				g2d.scale(spolygone.getScale(), spolygone.getScale());
				g2d.translate(-sX - sW / 2, -sY - sH / 2);

				ColorAttributes cA = (ColorAttributes) spolygone.getAttributes("colorAttributes");
				if (cA != null) {
					if (cA.filled()) {
						g2d.setColor(cA.filledColor());
						g2d.fillPolygon(spolygone.getX(), spolygone.getY(), spolygone.getPoints().length);
					}
					if (cA.stroked()) {
						g2d.setColor(cA.strokedColor());
						g2d.drawPolygon(spolygone.getX(), spolygone.getY(), spolygone.getPoints().length);
					}
				} else {
					g2d.setColor(Color.BLACK);
					g2d.drawPolygon(spolygone.getX(), spolygone.getY(), spolygone.getPoints().length);
				}

				SelectionAttributes sA = (SelectionAttributes) spolygone.getAttributes("selectionAttributes");
				if (sA != null && sA.isSelected()) {
					drawSelectionShape(spolygone.getBounds());
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
 							observations
 									.add(new WeightedObservedPoint(1.0, points1.get(i).getX(), points1.get(i).getY()));
 							xs[i] = points1.get(i).getX();
 						}
 						PolynomialCurveFitter function = PolynomialCurveFitter.create(3);

 						PolynomialFunction func = new PolynomialFunction(function.fit(observations));

 						for (int i = 0; i < xs.length; i++) {
 							ys[i] = func.value(xs[i]);
 						}
 						for (int i = 0; i < xs.length; i++) {
 							g.drawLine((int) xs[i], (int) ys[i], (int) xs[i], (int) ys[i]);
 						}
 					}
 					break;
 				case method_Test:
 					if (path.getPoints().size() > 10) {
 						ArrayList<Point> paths = path.getPoints();
 						ArrayList<Point> draw = new ArrayList<>();
 						int np = 5;
 						int space = paths.size() / np;
 						double[] xs = new double[space];
 						double[] ys = new double[space];
 						ArrayList<WeightedObservedPoint> observations = new ArrayList<>();
 						for (int i = 1; i <= np; i++) {
 							for (int j = (i - 1) * space; j < i * space; j++) {
 								observations
 										.add(new WeightedObservedPoint(1.0, paths.get(j).getX(), paths.get(j).getY()));
 								xs[j%space] = paths.get(j).getX();
 							}
 							PolynomialCurveFitter function = PolynomialCurveFitter.create(3);

 							PolynomialFunction func = new PolynomialFunction(function.fit(observations));
 							for (int t = 0; t < xs.length; t++) {
 								ys[t] = func.value(xs[t]);
 								draw.add(new Point((int) xs[t],(int)ys[t]));
 							}
 						}
 						for (int i = 0; i < draw.size(); i++) {
 							g.drawLine((int)draw.get(i).getX(),(int)draw.get(i).getY(), (int)draw.get(i).getX(),(int)draw.get(i).getY());
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