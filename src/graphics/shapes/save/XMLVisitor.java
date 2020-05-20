package graphics.shapes.save;

import java.io.PrintWriter;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;

public class XMLVisitor implements FileVisitor {
	 
	private  PrintWriter o;
    

	
	public XMLVisitor(PrintWriter o) {
		this.o = o;
	}

	
	@Override
	public void visitRectangle(SRectangle rect) {
		ColorAttributes colorAtt = (ColorAttributes) rect.getAttributes(ColorAttributes.id);
		int f = 0;
		int s = 0;
		if (colorAtt.filled()) {
			f = colorAtt.filledColor().getRGB();
		}
		if (colorAtt.stroked()) {
			s = colorAtt.strokedColor().getRGB();
		}
		o.println("	<rectangle x=\"" + rect.getLoc().x + "\" y=\"" + rect.getLoc().y + "\"" + " height=\""
				+ rect.getRect().height + "\"" + " width=\"" + rect.getRect().width + "\"" + " isfilled=\""
				+ colorAtt.filled() + "\"" + " isstroked=\"" + colorAtt.stroked() + "\"" + " filled=\"" + f
				+ "\"" + " stroked=\"" + s + "\"/>");		
	}

	@Override
	public void visitCircle(SCircle circle) {
		ColorAttributes colorAtt = (ColorAttributes) circle.getAttributes(ColorAttributes.id);
		int f = 0;
		int s = 0;
		if (colorAtt.filled())
			f = colorAtt.filledColor().getRGB();
		if (colorAtt.stroked())
			s = colorAtt.strokedColor().getRGB();
		o.println("	<circle x=\"" + circle.getLoc().x + "\" y=\"" + circle.getLoc().y + "\"" + " radius=\""
				+ circle.getRadius() + "\"" + " isfilled=\"" + colorAtt.filled() + "\"" + " isstroked=\""
				+ colorAtt.stroked() + "\"" + " filled=\"" + f + "\"" + " stroked=\"" + s + "\"/>");
				
	}

	@Override
	public void visitText(SText text) {
		ColorAttributes colorAttributes = (ColorAttributes) text.getAttributes(ColorAttributes.id);
		int f = 0;
		int s = 0;

		if (colorAttributes.filled())
			f = colorAttributes.filledColor().getRGB();
		if (colorAttributes.stroked())
			s = colorAttributes.strokedColor().getRGB();
		o.println(" <text text=\"" + text.getText() + "\"" + " x=\"" + text.getLoc().x + "\" y=\"" + text.getLoc().y
				+ "\"" + " filled=\"" + colorAttributes.filled() + "\"" + " stroked=\"" + colorAttributes.stroked()
				+ "\"" + " filledColor=\"" + f + "\"" + " strokedColor=\"" + s + "\" />");		
	}

	@Override
	public void visitCollection(SCollection collec) {
		o.println("<collection>");
		for (Iterator<Shape> it = collec.iterator(); it.hasNext();) {
			Shape realShape = it.next();
			realShape.accept(this);
		}
		o.println("</collection>");
	}
	
	 public static XMLVisitor GetInstance(PrintWriter o) {
	        return new XMLVisitor(o);
	    }

}
