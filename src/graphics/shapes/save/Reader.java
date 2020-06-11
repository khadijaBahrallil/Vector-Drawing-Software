package graphics.shapes.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import graphics.shapes.SCollection;
import graphics.shapes.ui.Editor;

public class Reader {

	ReaderStrategy strat;
	String file;
	String path;
	SCollection model;
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;
	Element root;

	public Reader(String file) {
		this.file = file;
		this.path = "Files/";
	}

	public void read(ReaderStrategy strat) {
		try {
			this.strat = strat;

			model = new SCollection();
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(path + file + this.strat.extension));
			root = document.getDocumentElement();

			final NodeList rootNodes = root.getChildNodes();
			final int nbRootNodes = rootNodes.getLength();

			for (int i = 0; i < nbRootNodes; i++) {
				if (rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element shape = (Element) rootNodes.item(i);
					this.model.add(this.strat.parse(shape));
				}
			}
			System.out.println(model.toString());
			Editor self = new Editor(model);
			self.pack();
			self.setVisible(true);
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}

}
