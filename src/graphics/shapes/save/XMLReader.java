package graphics.shapes.save;

import java.awt.Color;
import java.awt.Point;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class XMLReader extends ReaderStrategy {
	
	public XMLReader() {
		super();
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

			boolean filled = Boolean.parseBoolean(shape.getAttribute("filled"));
			boolean stroked = Boolean.parseBoolean(shape.getAttribute("stroked"));
			Color filledColor = Color.decode(shape.getAttribute("filledColor"));
			Color strokedColor = Color.decode(shape.getAttribute("strokedColor"));

			r.addAttributes(new ColorAttributes(filled, stroked, filledColor, strokedColor));
			r.addAttributes(new SelectionAttributes());

			return (r);
		}

		if (type == "circle") {

			int x = Integer.parseInt(shape.getAttribute("x"));

			int y = Integer.parseInt(shape.getAttribute("y"));

			int radius = Integer.parseInt(shape.getAttribute("radius"));

			SCircle c = new SCircle(new Point(x, y), radius);

			boolean filled = Boolean.parseBoolean(shape.getAttribute("filled"));
			boolean stroked = Boolean.parseBoolean(shape.getAttribute("stroked"));
			Color filledColor = Color.decode(shape.getAttribute("filledColor"));
			Color strokedColor = Color.decode(shape.getAttribute("strokedColor"));
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

			boolean filled = Boolean.parseBoolean(shape.getAttribute("filled"));

			boolean stroked = Boolean.parseBoolean(shape.getAttribute("stroked"));

			Color filledColor = Color.decode(shape.getAttribute("filledColor"));

			Color strokedColor = Color.decode(shape.getAttribute("strokedColor"));

			t.addAttributes(new ColorAttributes(filled, stroked, filledColor, strokedColor));
			t.addAttributes(new FontAttributes());
			t.addAttributes(new SelectionAttributes());

			return (t);
		}

		if (type == "collection") {

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
		
		
}

