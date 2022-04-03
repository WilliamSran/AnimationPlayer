import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Author: William Sran
 * Date: April 2022
 * Description: Picture Class containing appropriate attributes
 */

public class Pictures extends JComponent{

	// instance data or attributes
	private Color c;
	private int xPos, yPos, myWidth, myHeight;
	
	/**
	 * Default constructor
	 */
	public Pictures() {
		this.c = Color.red;
		this.xPos = 0;
		this.yPos = 0;
		this.myWidth = 50;
		this.myHeight = 25;
		repaint();
	}
	
	/**
	 * Overloaded Constructor
	 */
	public Pictures(int x, int y, int w, int h) {
		this.c = Color.RED;
		this.xPos = x;
		this.yPos = y;
		this.myWidth = w;
		this.myHeight = h;
		repaint();
	}
	
	
	
	/**
	 * @return the c
	 */
	public Color getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(Color c) {
		this.c = c;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @return the myWidth
	 */
	public int getMyWidth() {
		return myWidth;
	}

	/**
	 * @return the myHeight
	 */
	public int getMyHeight() {
		return myHeight;
	}

	/**
	 * Method to change the color
	 */
	public void setColor(Color c) {
		this.c = c;
		repaint();
	}
	
	/**
	 * Paint method / overriding from JComponent
	 */
	public void paint(Graphics g) {
		g.setColor(this.c);
		g.drawRect(this.xPos, this.yPos, this.myWidth, this.myHeight);
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
		repaint();
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
		repaint();
	}

	/**
	 * @param myWidth the myWidth to set
	 */
	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
		repaint();
	}

	/**
	 * @param myHeight the myHeight to set
	 */
	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
		repaint();
	}
	
	/**
	 * @param args
	 * self-testing main method
	 */
	public static void main(String[] args) {
		// create a window to try my picture
		JFrame f = new JFrame("Testing");
		
		//create an object of Picture
		Pictures p = new Pictures();
		
		f.setSize(400, 300);
		f.add(p);
		f.setVisible(true);
		
		//wait
		JOptionPane.showMessageDialog(null,  "Wait");
		
		// testing the new constructor
		Pictures p1 = new Pictures(200, 50, 100, 150);
		
		f.add(p1); // add the new picture object
		f.setVisible(true);
		
		//wait
		JOptionPane.showMessageDialog(null,  "Wait");
		
		// test the setColor method
		p1.setColor(Color.BLUE);
		
		//wait
		JOptionPane.showMessageDialog(null,  "Wait");
		
		// test my setters
		p.setxPos(100);
		p.setyPos(100);
		p.setMyWidth(100);
		p.setMyHeight(200);

	}



}
