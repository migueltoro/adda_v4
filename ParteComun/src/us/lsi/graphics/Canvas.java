package us.lsi.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.*;

import us.lsi.common.Preconditions;

import java.awt.geom.*;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Class {@code Canvas} - a class to allow for simple graphical 
 * drawing on a canvas.
 * 
 * @author Michael Kölling (mik)
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */
public class Canvas {
	
	
    public static Canvas of(String title, String cabecera) {
		return new Canvas(title,cabecera);
	}

	private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor = Color.WHITE;
    private Image canvasImage;
    public Integer width = 1040;
    public Integer height = 640;
    public Integer widthBorder = 100;
    public Integer heightBorder = 70;
    public IntPunto2D x0;
    public IntPunto2D x1;
    public IntPunto2D x2;
    public IntPunto2D x3;
    public Integer widthCentral;
    public Integer heightCentral;
    public IntPunto2D origin;
    public Integer n = 1;
    private Double xMin;
	private Double xMax; 
	private Double yMin;
	private Double yMax;
	private Double xEscala;
	private Double yEscala;
    

    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgColor  the desired background color of the canvas
     */
    private Canvas(String title, String cabecera) {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setPreferredSize(new Dimension(this.width, this.height));
        this.x0 = IntPunto2D.of(this.widthBorder, this.heightBorder);
        this.x1 = IntPunto2D.of(this.width - this.widthBorder, this.heightBorder);
        this.x2 = IntPunto2D.of(this.widthBorder, this.height - this.heightBorder);
        this.x3 = IntPunto2D.of(this.width - this.widthBorder, this.height - this.heightBorder);
        this.origin = this.x2;
        this.widthCentral = this.width - 2*this.widthBorder;
        this.heightCentral = this.height - 2*this.heightBorder;
        frame.pack();
        setVisible(true);
        Graphics2D gc = this.graphic();
		gc.setStroke(new BasicStroke(2));        
        this.drawLine(x0.x(),x0.y(), x1.x(), x1.y());
        this.drawLine(x0.x(),x0.y(), x2.x(), x2.y());
        this.drawLine(x2.x(),x2.y(), x3.x(), x3.y());
        this.drawLine(x1.x(),x1.y(), x3.x(), x3.y()); 
        Integer dx = this.widthCentral/5;
        Integer dy = this.heightCentral/5;
        for (int i = 1; i < 5; i++) {
			this.drawString("|",x2.x()+i*dx,x2.y()+7);
		}
        for (int i = 1; i < 5; i++) {
			this.drawString("_",x0.x()-7,x0.y()+i*dy);
		}
        Font trb = new Font("Sans_serif", Font.BOLD, 20);
    	this.setFont(trb);
    	this.drawString(cabecera,x0.x()+2*dx, x0.y()-30);
    }
    
    private static Integer scale(Double d) {
    	Locale.setDefault(Locale.of("en", "US"));
		String s = ""+d.longValue();
		return s.length()-1;
    }
    
    public void axes(List<Double> xs, List<Double> ys) {
    	this.xMin = xs.stream().min(Comparator.naturalOrder()).get();
    	this.xMax = xs.stream().max(Comparator.naturalOrder()).get();
    	this.yMin = ys.stream().min(Comparator.naturalOrder()).get();
    	this.yMax = ys.stream().max(Comparator.naturalOrder()).get();
    	this.xEscala = (xMax-xMin)/this.widthCentral;
    	this.yEscala = (yMax-yMin)/this.heightCentral;
    	Double xe = Math.pow(10.,scale(this.xMax));
    	Double ye = Math.pow(10.,scale(this.yMax));
    	Font trb = new Font("Sans_serif", Font.BOLD, 14);
    	this.setFont(trb);
		this.setForegroundColor(Color.BLACK);
		Integer dx = this.widthCentral / 5;
		Integer dy = this.heightCentral / 5;
		Double dxv = (xMax - xMin) / 5;
		Double dyv = (yMax - yMin) / 5;
		for (int i = 0; i < 5; i++) {
			this.drawString(String.format(Locale.US,"%.2f", (xMin + i * dxv)/xe), x2.x() + i * dx, x2.y() + 30);
		}
		int j = 5;
		this.drawString(String.format(Locale.US,"%.2g", xMin + j * dxv), x2.x() + j * dx, x2.y() + 30);
		j=0;
		this.drawString(String.format(Locale.US,"%.2g", yMax - j * dyv), x0.x() - 65, x0.y() + j * dy);
		for (int i = 1; i < 6; i++) {
			this.drawString(String.format(Locale.US,"%.2f", (yMax - i * dyv)/ye), x0.x() - 65, x0.y() + i * dy);
		} 
    }
    
    public void drawData(String label, Color color, List<Double> xs, List<Double> ys, Boolean withEscala) {
    	Preconditions.checkArgument(xs.size() == ys.size(), "Los tamaños deben ser iguales");
    	this.setForegroundColor(color);
    	List<Integer> xst = xs.stream().map(x->(x-xMin)/xEscala + this.origin.x()).map(x->x.intValue()).toList();
    	List<Integer> yst = ys.stream().map(y->-(y-yMin)/yEscala + this.origin.y()).map(x->x.intValue()).toList();
    	for (int i = 0; i < xst.size()-1; i++) {
    		this.drawLine(xst.get(i),yst.get(i), xst.get(i+1), yst.get(i+1));
    	}
    	this.setForegroundColor(color);
    	Font trb = new Font("Arial", Font.BOLD, 18);
    	this.setFont(trb);
    	this.drawString(label,x0.x()+20, x0.y()+30*this.n);
    	this.n++;
    }
    
    public Graphics2D graphic() {
    	return this.graphic;
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible) {
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(true);
    }

    /**
     * Provide information on visibility of the Canvas.
     * @return  true if canvas is visible, false otherwise
     */
    public boolean isVisible() {
        return frame.isVisible();
    }

    /**
     * Draw the outline of a given shape onto the canvas.
     * @param  shape  the shape object to be drawn on the canvas
     */
    public void draw(Shape shape) {
        graphic.draw(shape);
        canvas.repaint();
    }
 
    /**
     * Fill the internal dimensions of a given shape with the current 
     * foreground color of the canvas.
     * @param  shape  the shape object to be filled 
     */
    public void fill(Shape shape) {
        graphic.fill(shape);
        canvas.repaint();
    }

    /**
     * Fill the internal dimensions of the given circle with the current 
     * foreground color of the canvas.
     * @param  xPos  The x-coordinate of the circle center point
     * @param  yPos  The y-coordinate of the circle center point
     * @param  diameter  The diameter of the circle to be drawn
     */
    public void fillCircle(int xPos, int yPos, int diameter) {
        Ellipse2D.Double circle = new Ellipse2D.Double(xPos, yPos, diameter, diameter);
        fill(circle);
    }

    /**
     * Fill the internal dimensions of the given rectangle with the current 
     * foreground color of the canvas. This is a convenience method. A similar 
     * effect can be achieved with the "fill" method.
     * @param  xPos  The x-coordinate of the top-left point of the rectangle
     * @param  yPos  The y-coordinate of the top-left point of the rectangle
     * @param  width  The width of the rectangle
     * @param  height  The height of the rectangle
     */
    public void fillRectangle(int xPos, int yPos, int width, int height) {
        fill(new Rectangle(xPos, yPos, width, height));
    }

    /**
     * Erase the whole canvas.
     */
    public void erase() {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Erase the internal dimensions of the given circle. This is a 
     * convenience method. A similar effect can be achieved with
     * the "erase" method.
     * @param  xPos  The x-coordinate of the circle center point
     * @param  yPos  The y-coordinate of the circle center point
     * @param  diameter  The diameter of the circle to be drawn
     */
    public void eraseCircle(int xPos, int yPos, int diameter) {
        Ellipse2D.Double circle = new Ellipse2D.Double(xPos, yPos, diameter, diameter);
        erase(circle);
    }

    /**
     * Erase the internal dimensions of the given rectangle. This is a 
     * convenience method. A similar effect can be achieved with
     * the "erase" method.
     * @param  xPos  The x-coordinate of the top-left point of the rectangle
     * @param  yPos  The y-coordinate of the top-left point of the rectangle
     * @param  width  The width of the rectangle
     * @param  height  The height of the rectangle
     */
    public void eraseRectangle(int xPos, int yPos, int width, int height) {
        erase(new Rectangle(xPos, yPos, width, height));
    }

    /**
     * Erase a given shape's interior on the screen.
     * @param  shape  the shape object to be erased 
     */
    public void erase(Shape shape) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.fill(shape);              // erase by filling background color
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Erases a given shape's outline on the screen.
     * @param  shape  the shape object to be erased 
     */
    public void eraseOutline(Shape shape) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.draw(shape);  // erase by drawing background color
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Draws an image onto the canvas.
     * @param  image   the Image object to be displayed 
     * @param  x       x co-ordinate for Image placement 
     * @param  y       y co-ordinate for Image placement 
     * @return  returns boolean value representing whether the image was 
     *          completely loaded 
     */
    public boolean drawImage(Image image, int x, int y) {
        boolean result = graphic.drawImage(image, x, y, null);
        canvas.repaint();
        return result;
    }

    /**
     * Draws a String on the Canvas.
     * @param  text   the String to be displayed 
     * @param  x      x co-ordinate for text placement 
     * @param  y      y co-ordinate for text placement
     */
    public void drawString(String text, int x, int y) {
        graphic.drawString(text, x, y);   
        canvas.repaint();
    }

    /**
     * Erases a String on the Canvas.
     * @param  text     the String to be displayed 
     * @param  x        x co-ordinate for text placement 
     * @param  y        y co-ordinate for text placement
     */
    public void eraseString(String text, int x, int y) {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.drawString(text, x, y);   
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Draws a line on the Canvas.
     * @param  x1   x co-ordinate of start of line 
     * @param  y1   y co-ordinate of start of line 
     * @param  x2   x co-ordinate of end of line 
     * @param  y2   y co-ordinate of end of line 
     */
    public void drawLine(int x1, int y1, int x2, int y2) {
        graphic.drawLine(x1, y1, x2, y2);   
        canvas.repaint();
    }

    /**
     * Sets the foreground color of the Canvas.
     * @param  newColor   the new color for the foreground of the Canvas 
     */
    public void setForegroundColor(Color newColor) {
        graphic.setColor(newColor);
    }

    /**
     * Returns the current color of the foreground.
     * @return   the color of the foreground of the Canvas 
     */
    public Color getForegroundColor() {
        return graphic.getColor();
    }

    /**
     * Sets the background color of the Canvas.
     * @param  newColor   the new color for the background of the Canvas 
     */
    public void setBackgroundColor(Color newColor) {
        backgroundColor = newColor;   
        graphic.setBackground(newColor);
    }

    /**
     * Returns the current color of the background
     * @return   the color of the background of the Canvas 
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * changes the current Font used on the Canvas
     * @param  newFont   new font to be used for String output
     */
    public void setFont(Font newFont) {
        graphic.setFont(newFont);
    }

    /**
     * Returns the current font of the canvas.
     * @return     the font currently in use
     **/
    public Font getFont() {
        return graphic.getFont();
    }

    /**
     * Sets the size of the canvas.
     * @param  width    new width 
     * @param  height   new height 
     */
    public void setSize(int width, int height) {
        canvas.setPreferredSize(new Dimension(width, height));
        Image oldImage = canvasImage;
        canvasImage = canvas.createImage(width, height);
        graphic = (Graphics2D)canvasImage.getGraphics();
        graphic.setColor(backgroundColor);
        graphic.fillRect(0, 0, width, height);
        graphic.drawImage(oldImage, 0, 0, null);
        frame.pack();
    }

    /**
     * Returns the size of the canvas.
     * @return     The current dimension of the canvas
     */
    public Dimension getSize() {
        return canvas.getSize();
    }

    /**
     * Waits for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds) {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e)
        {
            // ignoring exception at the moment
        }
    }
    
    public void close() {
    	this.frame.dispose();
    }

    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    public class CanvasPane extends JPanel {
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    public static record IntPunto2D(Integer x, Integer y) {
    	public static IntPunto2D of(Integer x, Integer y) { return new IntPunto2D(x,y);}
    }
}
