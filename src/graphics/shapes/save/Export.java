package graphics.shapes.save;

import javax.imageio.ImageIO;
import javax.swing.*;

import graphics.shapes.ui.ShapesView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Export {


	//takes a screenshot of the panel and store it as jpg
	public void takePicture(ShapesView sview) {
		BufferedImage img = new BufferedImage(sview.getWidth(), sview.getHeight(), BufferedImage.TYPE_INT_RGB);
		sview.print(img.getGraphics());
		try {
			String name = JOptionPane.showInputDialog("File Name  :  ");
			ImageIO.write(img, "jpg", new File("Files/"+name + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}