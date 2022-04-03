import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Author: William Sran
 * Date: April 2022
 * Description: Text Picture class containing appropriate attributes
 */

public class TextPicture extends Pictures{

	/**
	 * Instance variables or attributes
	 */
	private String text;
	private Font myFont;

	public void setFont (Font myFont) {									//setters and getters for font and text
		this.myFont = myFont;
	}
	
	public void setText (String text) {
		this.text = text;
	}
	
	public Font getFont () {
		return myFont;
	}
	
	public String getText () {
		return text;
	}
	
	/**
	 * First Constructor
	 */
	public TextPicture(String title, int x, int y) {					// setting the values of font, text, and position of the text and painting
		super();
		setxPos(x);
		setyPos(y);
		this.myFont = new Font ("Arial", Font.BOLD, 20);
		this.text = title;
		repaint();
	}

	
	public void paint(Graphics g) {										//setting the color and font, and painting the text
		g.setColor(this.getC());
		g.setFont(this.myFont);
		g.drawString(this.text, this.getxPos(), this.getyPos());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a JFrame
		JFrame f = new JFrame("Texts and Fonts");

		//create TextPicture object
		TextPicture i = new TextPicture("Hello!", 50, 50);
		
		//set the size, add the text and set visible true
		f.setSize(400, 250);
		f.add(i);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null,  "wait");			// break to let the user see the changes that took place
		
		//move my text and change font
		i.setxPos(100);
		i.setyPos(120);
		i.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JOptionPane.showMessageDialog(null,  "wait");			// another break
		
		i.setColor(Color.BLUE);									//changing color and font
		i.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JOptionPane.showMessageDialog(null,  "wait");			// another break
		
		Font font = new Font("Serif", Font.ITALIC, 20);			// changing the font, text and position of the text
		String text2 = "Goodbye";
		
		i.setColor(new Color(128, 4, 0));
		
		i.setxPos(i.getxPos() + 100);							
		i.setyPos(i.getyPos() + 50);
		i.setText(text2);
		i.setFont(font);
		
		
		}

}
