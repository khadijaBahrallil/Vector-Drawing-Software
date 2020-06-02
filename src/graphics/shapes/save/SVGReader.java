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

public class SVGReader extends ReaderStrategy {

	public SVGReader() {
		super();
	}

	@Override
	public Shape parse(Element shape) {
		String type = shape.getNodeName();
		System.out.println(shape.getNodeName());

		if (type == "rect") {

			int x = Integer.parseInt(shape.getAttribute("x"));
			int y = Integer.parseInt(shape.getAttribute("y"));
			int height = Integer.parseInt(shape.getAttribute("height"));
			int width = Integer.parseInt(shape.getAttribute("width"));
			SRectangle r = new SRectangle(new Point(x, y), width, height);

			
			Color strokedColor;
			Color filledColor;

			String style = shape.getAttribute("style");
			String fillHex = style.substring(7, 13);
			String strokeHex = style.substring(24, 30);
			
			boolean filled = true;
			boolean stroked= true;
			
			if (fillHex == "000000") {
				filled = false;
			}
			if (strokeHex == "000000") {
				stroked = false;
			}
			
			
			strokedColor = new Color (Integer.valueOf(strokeHex.substring(0, 2 ),16),Integer.valueOf(strokeHex.substring(2, 4 ),16),Integer.valueOf(strokeHex.substring(4, 6 ),16));
			filledColor = new Color (Integer.valueOf(fillHex.substring(0, 2 ),16),Integer.valueOf(fillHex.substring(2, 4 ),16),Integer.valueOf(fillHex.substring(4, 6 ),16));
			Color str = Color.decode(String.valueOf(strokedColor.getRGB()));
			Color fil = Color.decode(String.valueOf(filledColor.getRGB()));

			r.addAttributes(new ColorAttributes(filled, stroked, fil, str));
			r.addAttributes(new SelectionAttributes());

			return (r);
		}

		if (type == "circle") {

			int x = Integer.parseInt(shape.getAttribute("cx"));

			int y = Integer.parseInt(shape.getAttribute("cy"));

			int radius = Integer.parseInt(shape.getAttribute("r"));

			SCircle c = new SCircle(new Point(x, y), radius);

			Color strokedColor;
			Color filledColor;

			String style = shape.getAttribute("style");
			String fillHex = style.substring(7, 13);
			String strokeHex = style.substring(24, 30);
			
			
			boolean filled = true;
			boolean stroked= true;
			
			if (fillHex == "000000") {
				filled = false;
			}
			if (strokeHex == "000000") {
				stroked = false;
			}
			
			
			strokedColor = new Color (Integer.valueOf(strokeHex.substring(0, 2 ),16),Integer.valueOf(strokeHex.substring(2, 4 ),16),Integer.valueOf(strokeHex.substring(4, 6 ),16));
			filledColor = new Color (Integer.valueOf(fillHex.substring(0, 2 ),16),Integer.valueOf(fillHex.substring(2, 4 ),16),Integer.valueOf(fillHex.substring(4, 6 ),16));
			Color str = Color.decode(String.valueOf(strokedColor.getRGB()));
			Color fil = Color.decode(String.valueOf(filledColor.getRGB()));

			c.addAttributes(new ColorAttributes(filled, stroked, fil, str));
			c.addAttributes(new SelectionAttributes());

			return (c);
		}

		if (type == "text") {

			int x = Integer.parseInt(shape.getAttribute("x"));

			int y = Integer.parseInt(shape.getAttribute("y"));

			String text = shape.getTextContent();
			SText t = new SText(new Point(x, y), text);

			t.addAttributes(new FontAttributes());

			Color strokedColor;
			Color filledColor;

			String style = shape.getAttribute("style");
			String fillHex = style.substring(7, 13);
			String strokeHex = style.substring(24, 30);
			
		
			boolean filled = true;
			boolean stroked= true;
			
			if (fillHex == "000000") {
				filled = false;
			}
			if (strokeHex == "000000") {
				stroked = false;
			}
			
			strokedColor = new Color (Integer.valueOf(strokeHex.substring(0, 2 ),16),Integer.valueOf(strokeHex.substring(2, 4 ),16),Integer.valueOf(strokeHex.substring(4, 6 ),16));
			filledColor = new Color (Integer.valueOf(fillHex.substring(0, 2 ),16),Integer.valueOf(fillHex.substring(2, 4 ),16),Integer.valueOf(fillHex.substring(4, 6 ),16));
			Color str = Color.decode(String.valueOf(strokedColor.getRGB()));
			Color fil = Color.decode(String.valueOf(filledColor.getRGB()));

			t.addAttributes(new ColorAttributes(filled, stroked, fil, str));
			t.addAttributes(new FontAttributes());
			t.addAttributes(new SelectionAttributes());

			return (t);
		}

		if (type == "g") {

			SCollection collection = new SCollection();
			final NodeList collectionNodes = shape.getChildNodes();
			final int nbColNodes = collectionNodes.getLength();

			for (int i = 0; i < nbColNodes; i++) {
				if (collectionNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element realShape = (Element) collectionNodes.item(i);
					collection.add(parse(realShape));
				}
			}
			//System.out.println(collection);
			return (collection);
		}
		return null;
	}

}
