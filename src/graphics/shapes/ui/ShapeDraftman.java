package graphics.shapes.ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {
	private Graphics g;	
	//private Shape shape;

	public ShapeDraftman(Graphics g) {
		this.g = g;
	}	
	
	// Dessin du carré de Sélection
	public void drawSelectionShape(Rectangle rect) {
		final int size = 5;
		g.setColor(Color.black);
		g.drawRect(rect.x-size+2, rect.y-size+2, size, size);
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
			}
			else {
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
			}
			else {
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
			while(itr.hasNext()) {
				shape = itr.next();
				shape.accept(this);				
				if (sA != null && sA.isSelected()) {
					drawSelectionShape(shape.getBounds());
				}
			}
						
		}
	}
	
}