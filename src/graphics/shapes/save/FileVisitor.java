package graphics.shapes.save;

import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;

public abstract class FileVisitor implements ShapeVisitor {

	protected PrintWriter o;

	protected ColorAttributes colorAttribute;

	protected DocumentBuilderFactory dbFactory;
	protected DocumentBuilder dBuilder;
	protected Document doc;
	protected Element rootElement ;

	// root element

	public FileVisitor(PrintWriter o) {
		try {

			this.o = o;
			colorAttribute = new ColorAttributes();

			dbFactory = DocumentBuilderFactory.newInstance();

			dBuilder = dbFactory.newDocumentBuilder();

			doc = dBuilder.newDocument();

			rootElement = doc.createElement("shapes");
			doc.appendChild(rootElement);
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract void visitRectangle(SRectangle rect);

	public abstract void visitCircle(SCircle circle);

	public abstract void visitText(SText text);

	public abstract void visitCollection(SCollection collec);

}
