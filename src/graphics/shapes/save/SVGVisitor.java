package graphics.shapes.save;

import java.io.PrintWriter;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;

public class SVGVisitor extends FileVisitor {

	public SVGVisitor(PrintWriter o) {
		super(o);
	}

	@Override
	public void visitRectangle(SRectangle rect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitCircle(SCircle circle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitText(SText text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitCollection(SCollection collec) {
		// TODO Auto-generated method stub
		
	}

}
