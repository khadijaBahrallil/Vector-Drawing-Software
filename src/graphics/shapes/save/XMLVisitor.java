package graphics.shapes.save;

import java.io.PrintWriter;
import java.util.Iterator;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Selection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;

public class XMLVisitor extends FileVisitor {
	 
	protected Element currentElement;
	protected Element rootElement ;

    

	public XMLVisitor(PrintWriter o) {
		super(o);
		rootElement = doc.createElement("shapes");
		doc.appendChild(rootElement);
		
	}
	
	
	@Override
	public void visitRectangle(SRectangle rect) {
		ColorAttributes colorAtt = (ColorAttributes) rect.getAttributes(colorAttribute.getId());
		int f = 0;
		int s = 0;
		if (colorAtt.filled()) {
			f = colorAtt.filledColor().getRGB();
		}
		if (colorAtt.stroked()) {
			s = colorAtt.strokedColor().getRGB();
		}

		
		Element rec = doc.createElement("rectangle");
        rootElement.appendChild(rec);

        // setting attribute to element
        Attr stroked = doc.createAttribute("stroked");
        stroked.setValue(Integer.toString(s));
        rec.setAttributeNode(stroked);
        
        Attr filled = doc.createAttribute("filled");
        filled.setValue(Integer.toString(f));
        rec.setAttributeNode(filled);

        Attr isstroked = doc.createAttribute("isstroked");
        isstroked.setValue(Boolean.toString(colorAtt.stroked()));
        rec.setAttributeNode(isstroked);

        Attr isfilled = doc.createAttribute("isfilled");
        isfilled.setValue(Boolean.toString(colorAtt.filled()));
        rec.setAttributeNode(isfilled);
        

        Attr width = doc.createAttribute("width");
        width.setValue(Integer.toString(rect.getRect().width));
        rec.setAttributeNode(width);
       
        Attr height = doc.createAttribute("height");
        height.setValue(Integer.toString(rect.getRect().height));
        rec.setAttributeNode(height);    
        
        Attr y = doc.createAttribute("y");
        y.setValue(Integer.toString(rect.getLoc().y));
        rec.setAttributeNode(y); 
        
        Attr x = doc.createAttribute("x");
        x.setValue(Integer.toString(rect.getLoc().x));
        rec.setAttributeNode(x);
       
        currentElement = rec ;
	}

	@Override
	public void visitCircle(SCircle circle) {
		ColorAttributes colorAtt = (ColorAttributes) circle.getAttributes(colorAttribute.getId());
		int f = 0;
		int s = 0;
		if (colorAtt.filled())
			f = colorAtt.filledColor().getRGB();
		if (colorAtt.stroked())
			s = colorAtt.strokedColor().getRGB();
		/*
		o.println("	<circle x=\"" + circle.getLoc().x + "\" y=\"" + circle.getLoc().y + "\"" + " radius=\""
				+ circle.getRadius() + "\"" + " isfilled=\"" + colorAtt.filled() + "\"" + " isstroked=\""
				+ colorAtt.stroked() + "\"" + " filled=\"" + f + "\"" + " stroked=\"" + s + "\"/>");
				*/
		
		Element cir = doc.createElement("circle");
        rootElement.appendChild(cir);

        // setting attribute to element
        
        Attr stroked = doc.createAttribute("stroked");
        stroked.setValue(Integer.toString(s));
        cir.setAttributeNode(stroked);
        
        Attr filled = doc.createAttribute("filled");
        filled.setValue(Integer.toString(f));
        cir.setAttributeNode(filled);
        
        Attr isstroked = doc.createAttribute("isstroked");
        isstroked.setValue(Boolean.toString(colorAtt.stroked()));
        cir.setAttributeNode(isstroked);
        
        Attr isfilled = doc.createAttribute("isfilled");
        isfilled.setValue(Boolean.toString(colorAtt.filled()));
        cir.setAttributeNode(isfilled);
        
        Attr radius = doc.createAttribute("radius");
        radius.setValue(Integer.toString(circle.getRadius()));
        cir.setAttributeNode(radius);
        
        Attr y = doc.createAttribute("y");
        y.setValue(Integer.toString(circle.getLoc().y));
        cir.setAttributeNode(y);
        
        Attr x = doc.createAttribute("x");
        x.setValue(Integer.toString(circle.getLoc().x));
        cir.setAttributeNode(x);
        
        
        
        currentElement = cir ;
        
        
	}

	@Override
	public void visitText(SText text) {
		ColorAttributes colorAttributes = (ColorAttributes) text.getAttributes(colorAttribute.getId());
		int f = 0;
		int s = 0;

		if (colorAttributes.filled())
			f = colorAttributes.filledColor().getRGB();
		if (colorAttributes.stroked())
			s = colorAttributes.strokedColor().getRGB();
		
		/*
		o.println(" <text text=\"" + text.getText() + "\"" + " x=\"" + text.getLoc().x + "\" y=\"" + text.getLoc().y
				+ "\"" + " filled=\"" + colorAttributes.filled() + "\"" + " stroked=\"" + colorAttributes.stroked()
				+ "\"" + " filledColor=\"" + f + "\"" + " strokedColor=\"" + s + "\" />");	
		*/
		Element tex = doc.createElement("text");
        rootElement.appendChild(tex);

        // setting attribute to element
        
        Attr isfilled = doc.createAttribute("isfilled");
        isfilled.setValue(Boolean.toString(colorAttributes.filled()));
        tex.setAttributeNode(isfilled);
        
        Attr isstroked = doc.createAttribute("isstroked");
        isstroked.setValue(Boolean.toString(colorAttributes.stroked()));
        tex.setAttributeNode(isstroked);
        
        
        Attr stroked = doc.createAttribute("stroked");
        stroked.setValue(Integer.toString(s));
        tex.setAttributeNode(stroked);
        
        Attr filled = doc.createAttribute("filled");
        filled.setValue(Integer.toString(f));
        tex.setAttributeNode(filled);
        
        Attr y = doc.createAttribute("y");
        y.setValue(Integer.toString(text.getLoc().y));
        tex.setAttributeNode(y);
        
        Attr x = doc.createAttribute("x");
        x.setValue(Integer.toString(text.getLoc().x));
        tex.setAttributeNode(x);
        
        Attr t = doc.createAttribute("text");
        t.setValue(text.getText());
        tex.setAttributeNode(t);
        
    
        
        currentElement = tex ;
        
		
		
		
	}

	@Override
	public void visitCollection(SCollection collec) {
		//o.println("<collection>");
		
		Element collection = doc.createElement("collection");
		for (Iterator<Shape> it = collec.iterator(); it.hasNext();) {
			Shape realShape = it.next();
			realShape.accept(this);
			
			collection.appendChild(currentElement);
			
		}
		rootElement.appendChild(collection);
		
	}


	@Override
	public void visitSelection(Selection selection) {
		// TODO Auto-generated method stub
		
	}
	
	

}
