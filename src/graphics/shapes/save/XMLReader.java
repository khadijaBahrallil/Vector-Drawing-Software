package graphics.shapes.save;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SPath;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.PathAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class XMLReader extends ReaderStrategy {
	
	public XMLReader() {
		super();
		extension = ".xml";
	}

	@Override
	public Shape parse(Element shape) {
		String type = shape.getNodeName();

		if (type == "rectangle") {
			

			int x = Integer.parseInt(shape.getAttribute("x"));
			int y = Integer.parseInt(shape.getAttribute("y"));
			int height = Integer.parseInt(shape.getAttribute("height"));
			int width = Integer.parseInt(shape.getAttribute("width"));
			SRectangle r = new SRectangle(new Point(x, y), width, height);

			boolean filled = Boolean.parseBoolean(shape.getAttribute("isfilled"));
			boolean stroked = Boolean.parseBoolean(shape.getAttribute("isstroked"));
			Color filledColor = Color.decode(shape.getAttribute("filled"));
			Color strokedColor = Color.decode(shape.getAttribute("stroked"));

			r.addAttributes(new ColorAttributes(filled, stroked, filledColor, strokedColor));
			r.addAttributes(new SelectionAttributes());

			return (r);
		}

		if (type == "circle") {

			int x = Integer.parseInt(shape.getAttribute("x"));

			int y = Integer.parseInt(shape.getAttribute("y"));

			int radius = Integer.parseInt(shape.getAttribute("radius"));

			SCircle c = new SCircle(new Point(x, y), radius);

			boolean filled = Boolean.parseBoolean(shape.getAttribute("isfilled"));
			boolean stroked = Boolean.parseBoolean(shape.getAttribute("isstroked"));
			Color filledColor = Color.decode(shape.getAttribute("filled"));
			Color strokedColor = Color.decode(shape.getAttribute("stroked"));
			c.addAttributes(new ColorAttributes(filled, stroked, filledColor, strokedColor));
			c.addAttributes(new SelectionAttributes());

			return (c);
		}

		if (type == "text") {

			int x = Integer.parseInt(shape.getAttribute("x"));

			int y = Integer.parseInt(shape.getAttribute("y"));

			String text = shape.getAttribute("text");
			SText t = new SText(new Point(x, y), text);

			t.addAttributes(new FontAttributes());

			boolean filled = Boolean.parseBoolean(shape.getAttribute("isfilled"));

			boolean stroked = Boolean.parseBoolean(shape.getAttribute("isstroked"));

			Color filledColor = Color.decode(shape.getAttribute("filled"));

			Color strokedColor = Color.decode(shape.getAttribute("stroked"));

			t.addAttributes(new ColorAttributes(filled, stroked, filledColor, strokedColor));
			t.addAttributes(new FontAttributes());
			t.addAttributes(new SelectionAttributes());

			return (t);
		}  if (type == "polygon") {

			Point pt[] = toArray(shape.getAttribute("points"));
			

			SPolygon p = new SPolygon(pt);

			boolean filled = Boolean.parseBoolean(shape.getAttribute("isfilled"));
			boolean stroked = Boolean.parseBoolean(shape.getAttribute("isstroked"));
			Color filledColor = Color.decode(shape.getAttribute("filled"));
			Color strokedColor = Color.decode(shape.getAttribute("stroked"));

			p.addAttributes(new ColorAttributes(filled, stroked, filledColor, strokedColor));
			p.addAttributes(new SelectionAttributes());
			return (p);
		}  if (type == "path") {

			ArrayList<Point> pt = toArrayList(shape.getAttribute("points"));
			

			SPath p = new SPath(pt);

			boolean filled = Boolean.parseBoolean(shape.getAttribute("isfilled"));
			boolean stroked = Boolean.parseBoolean(shape.getAttribute("isstroked"));
			Color filledColor = Color.decode(shape.getAttribute("filled"));
			Color strokedColor = Color.decode(shape.getAttribute("stroked"));

			p.addAttributes(new ColorAttributes(filled, stroked, filledColor, strokedColor));
			p.addAttributes(new SelectionAttributes());
			p.addAttributes(new PathAttributes(PathAttributes.Type.valueOf(shape.getAttribute("method")),null));
			return (p);
		} if (type == "collection") {

			SCollection collection = new SCollection();
			final NodeList collectionNodes = shape.getChildNodes();
			final int nbColNodes = collectionNodes.getLength();

			for (int i = 0; i < nbColNodes; i++) {
				if (collectionNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element realShape = (Element) collectionNodes.item(i);
					collection.add(parse(realShape));
				}
			}
			return (collection);
		}
		return null;
		
	}
	
	
	private Point[] toArray(String string) {
		
		String[] parts;

		parts = string.split(" ");
		System.out.println( parts.length );
		Point p[] = new Point[parts.length];

		for (int i = 0; i < parts.length; i++) {
			
			String res[]=parts[i].split(",");
			
			int x = Integer.parseInt(res[0]);
			int y = Integer.parseInt(res[1]);
			Point pt = new Point(x,y);
			p[i] = pt;
			
		}
		return p;
	}
		
	private ArrayList<Point> toArrayList(String string) {
		
		String[] parts;

		parts = string.split(" ");
		//System.out.println( parts.length );
		ArrayList<Point> p  = new ArrayList<Point>();

		for (int i = 0; i < parts.length; i++) {
			
			String res[]=parts[i].split(",");
			
			int x = Integer.parseInt(res[0]);
			int y = Integer.parseInt(res[1]);
			Point pt = new Point(x,y);
			p.add(pt);
			
		}
		return p;
	}
		
}

