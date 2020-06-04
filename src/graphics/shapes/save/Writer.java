package graphics.shapes.save;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import graphics.shapes.SCollection;
import graphics.shapes.ui.ShapesController;
import graphics.shapes.ui.ShapesView;

public class Writer {
	
	PrintWriter o;
	FileVisitor strategy ;
	ShapesController shapesController;
	String fileName;
	String path;
	
	public Writer(ShapesView sview) throws FileNotFoundException {
		
		this.fileName = JOptionPane.showInputDialog("Save As (without extension) :");
		this.path = "Files/";
		
		File file = new File(path);

		if (!file.isDirectory()) {
			
			file.mkdir();
			
		}
		this.shapesController = (ShapesController) sview.getController();
		
	}


	public void write(FileVisitor fileStrat) throws FileNotFoundException {
		try {
		
		this.strategy = fileStrat;
		o = new PrintWriter(new BufferedOutputStream(new FileOutputStream(this.strategy.nameFile(path,fileName))),
				true);
		
		this.strategy.visitCollection((SCollection) shapesController.getModel());
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(fileStrat.doc);
        StreamResult result = new StreamResult(o);
        transformer.transform(source, result);
        
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public PrintWriter getWriter() {
		return this.o;
	}

	
	
	

}
