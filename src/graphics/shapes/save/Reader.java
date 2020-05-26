package graphics.shapes.save;

import java.io.File;
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
	
	
	public Reader() {
		this.file = JOptionPane.showInputDialog("Enter file name : ");
		this.path = "Files/";
	}
	
	
	public void read(ReaderStrategy strat) {
		
		this.strat = strat ;
		//this.strat.parse(file);
		
		SCollection model = new SCollection();
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();

			this.file = JOptionPane.showInputDialog("Enter file name : ");

			final Document document = builder.parse(new File(path + file + ".xml"));

			final Element root = document.getDocumentElement();

			final NodeList rootNodes = root.getChildNodes();
			final int nbRootNodes = rootNodes.getLength();

			for (int i = 0; i < nbRootNodes; i++) {
				if (rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					final Element shape = (Element) rootNodes.item(i);
					model.add(this.strat.parse(shape));
				}
			}
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		Editor self = new Editor(model);
		self.pack();
		self.setVisible(true);
		
	}

}
