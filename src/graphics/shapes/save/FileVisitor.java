package graphics.shapes.save;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.ShapeVisitor;

public interface FileVisitor extends ShapeVisitor {
	public void visitRectangle(SRectangle rect);
	public void visitCircle(SCircle circle);
	public void visitText(SText text);	
	public void visitCollection(SCollection collec);

}
