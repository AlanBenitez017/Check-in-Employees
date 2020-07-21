package Consultas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Impresion extends JFrame implements Printable{
	
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if(pageIndex == 0) {
			Graphics2D graphics2d = (Graphics2D) graphics;
			graphics2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			printAll(graphics2d);
			return PAGE_EXISTS;
		}else {
			return NO_SUCH_PAGE;
		}
		
	}

}
