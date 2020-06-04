package graphics.shapes.save;

import java.io.PrintWriter;
import java.util.Iterator;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SPath;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.PathAttributes;

public class SVGVisitor extends FileVisitor {
	
	Element currentElement ;
	protected Element rootElement ;

	public SVGVisitor(PrintWriter o) {
		super(o);
		rootElement = doc.createElement("svg");
		
		Attr xmlns = doc.createAttribute("xmlns");
		xmlns.setValue("http://www.w3.org/2000/svg");
		rootElement.setAttributeNode(xmlns);
		
		Attr version = doc.createAttribute("version");
		version.setValue("1.1");
		rootElement.setAttributeNode(version);
		
		Attr width = doc.createAttribute("width");
		width.setValue("300");
		rootElement.setAttributeNode(width);
		
		Attr height = doc.createAttribute("height");
		height.setValue("300");
		rootElement.setAttributeNode(height);
		
		doc.appendChild(rootElement);
	}

	@Override
	public void visitRectangle(SRectangle rect) {
		ColorAttributes colorAtt = (ColorAttributes) rect.getAttributes(colorAttribute.getId());
		String fill = "#000000";
		String stroke = "#000000";
		if (colorAtt.filled()) {
			fill = String.format("#%02x%02x%02x", colorAtt.filledColor().getRed(), colorAtt.filledColor().getGreen(), colorAtt.filledColor().getBlue()); 
		} 
		if (colorAtt.stroked()) {
			stroke = String.format("#%02x%02x%02x", colorAtt.strokedColor().getRed(), colorAtt.strokedColor().getGreen(), colorAtt.strokedColor().getBlue()); 
		}
		
		
		
		Element rec = doc.createElement("rect");
        rootElement.appendChild(rec);


        Attr style = doc.createAttribute("style");
        style.setValue("fill: "+fill+"; stroke: "+stroke+";");
        rec.setAttributeNode(style);
  
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
		String fill = "#000000";
		String stroke = "#000000";
		if (colorAtt.filled()) {
			fill = String.format("#%02x%02x%02x", colorAtt.filledColor().getRed(), colorAtt.filledColor().getGreen(), colorAtt.filledColor().getBlue()); 
		} 
		if (colorAtt.stroked()) {
			stroke = String.format("#%02x%02x%02x", colorAtt.strokedColor().getRed(), colorAtt.strokedColor().getGreen(), colorAtt.strokedColor().getBlue()); 
		}
		
		
		
		Element cir = doc.createElement("circle");
        rootElement.appendChild(cir);


        Attr style = doc.createAttribute("style");
        style.setValue("fill: "+fill+"; stroke: "+stroke+";");
        cir.setAttributeNode(style);
  
        
        Attr radius = doc.createAttribute("r");
        radius.setValue(Integer.toString(circle.getRadius()));
        cir.setAttributeNode(radius);
        
        Attr y = doc.createAttribute("cy");
        y.setValue(Integer.toString(circle.getLoc().y));
        cir.setAttributeNode(y);
        
        Attr x = doc.createAttribute("cx");
        x.setValue(Integer.toString(circle.getLoc().x));
        cir.setAttributeNode(x);
        
        
        
        currentElement = cir ;
		
	}

	@Override
	public void visitText(SText text) {
		
		ColorAttributes colorAtt = (ColorAttributes) text.getAttributes(colorAttribute.getId());
		String fill = "000000";
		String stroke = "000000";
		if (colorAtt.filled()) {
			fill = String.format("#%02x%02x%02x", colorAtt.filledColor().getRed(), colorAtt.filledColor().getGreen(), colorAtt.filledColor().getBlue()); 
		} 
		if (colorAtt.stroked()) {
			stroke = String.format("#%02x%02x%02x", colorAtt.strokedColor().getRed(), colorAtt.strokedColor().getGreen(), colorAtt.strokedColor().getBlue()); 
		}
		
		
		
		Element tex = doc.createElement("text");
        rootElement.appendChild(tex);


        Attr style = doc.createAttribute("style");
        style.setValue("fill: "+fill+"; stroke: "+stroke+";");
        tex.setAttributeNode(style);
  
        
        Attr y = doc.createAttribute("y");
        y.setValue(Integer.toString(text.getLoc().y));
        tex.setAttributeNode(y);
        
        Attr x = doc.createAttribute("x");
        x.setValue(Integer.toString(text.getLoc().x));
        tex.setAttributeNode(x);
        
        
        tex.appendChild(doc.createTextNode(text.getText()));
    
        
        currentElement = tex ;
        
		
		
	}

	@Override
	public void visitCollection(SCollection collec) {
		Element collection = doc.createElement("g");
		for (Iterator<Shape> it = collec.iterator(); it.hasNext();) {
			Shape realShape = it.next();
			realShape.accept(this);
			
			collection.appendChild(currentElement);
			
		}
		rootElement.appendChild(collection);
	}

	

	@Override
	public void visitPath(SPath path) {
		ColorAttributes colorAtt = (ColorAttributes) path.getAttributes(colorAttribute.getId());
		String fill = "#000000";
		String stroke = "#000000";
		if (colorAtt.filled()) {
			fill = String.format("#%02x%02x%02x", colorAtt.filledColor().getRed(), colorAtt.filledColor().getGreen(), colorAtt.filledColor().getBlue()); 
		} 
		if (colorAtt.stroked()) {
			stroke = String.format("#%02x%02x%02x", colorAtt.strokedColor().getRed(), colorAtt.strokedColor().getGreen(), colorAtt.strokedColor().getBlue()); 
		}
		Element p = doc.createElement("path");
        rootElement.appendChild(p);

        
        Attr style = doc.createAttribute("style");
        style.setValue("fill: "+fill+"; stroke: "+stroke+";");
        p.setAttributeNode(style);
      
        
        Attr points = doc.createAttribute("points");
        points.setValue(path.printPoints());
        p.setAttributeNode(points);
       
        Attr method = doc.createAttribute("method");
        method.setValue(((PathAttributes)path.getAttributes("pathAttributes")).getMethod().toString());
        p.setAttributeNode(method);

        
        
        currentElement = p ;
		
	}

	@Override
	public void visitPolygon(SPolygon poly) {
		ColorAttributes colorAtt = (ColorAttributes) poly.getAttributes(colorAttribute.getId());
		String fill = "#000000";
		String stroke = "#000000";
		if (colorAtt.filled()) {
			fill = String.format("#%02x%02x%02x", colorAtt.filledColor().getRed(), colorAtt.filledColor().getGreen(), colorAtt.filledColor().getBlue()); 
		} 
		if (colorAtt.stroked()) {
			stroke = String.format("#%02x%02x%02x", colorAtt.strokedColor().getRed(), colorAtt.strokedColor().getGreen(), colorAtt.strokedColor().getBlue()); 
		}
		Element polyg = doc.createElement("polygon");
        rootElement.appendChild(polyg);

        
        Attr style = doc.createAttribute("style");
        style.setValue("fill: "+fill+"; stroke: "+stroke+";");
        polyg.setAttributeNode(style);
  
       
        
        Attr pts = doc.createAttribute("points");
        pts.setValue(poly.printPoints());
        polyg.setAttributeNode(pts);
       
        
        
        currentElement = polyg ;
		
	}
	public String nameFile(String path, String file) {
		String extension = ".svg";
		return path + file + extension ;
	}


}
