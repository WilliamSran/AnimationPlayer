import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;


/* 
Author: William Sran and Gurveer Saund
Date: April 2022
Description: Shape class containing appropriate attributes(starting x & y coords, border color, border thickness, visibility, and rectangle, circle, and line arraylists)
*/

public class Shape extends JPanel{
    private int x;                    //x&y positions (location depends on shape)
    private int y;
    private int r, g, b;        //border colours, thickness, visibility
    private Color bordColor;
    private int bThick;
    private boolean visible;

    public ArrayList<Effects> effects = new ArrayList<Effects>();       //arraylist of effects and shapes

    public ArrayList <Rectangle> rect = new ArrayList <Rectangle>();
	public ArrayList <Circle> circ = new ArrayList <Circle>();;
	public ArrayList <Line> line = new ArrayList <Line>();

    public Shape(){             //default constructor with default  values
        this.x = 0;
        this.y = 0;
        this.bThick = 5;
        setBordColor(0, 0,0);
    }                 

    public Shape(int x, int y, int r, int g, int b, int bThick){        //shape method with inputted values

        this.x = x;
        this.y = y;
        this.bThick = bThick;
        setBordColor(r,g,b);

    }

    public int getR() {         //setters and getters for shape attributes
        return this.r;
    }

    public void setR(int r) {
        this.r = r;
    }
    public int getG() {
        return this.g;
    }

    public void setG(int g) {
        this.g = g;
    }
    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBThick() {
        return this.bThick;
    }

    public void setBThick(int bThick) {
        this.bThick = bThick;
    }

    public void setBordColor(int r, int g, int b){      //setter for bordor color

        setR(r);
        setG(g);
        setB(b);
        this.bordColor = new Color(r, g, b);
    }

    public Color getBordColor(){        //getter for bordor color

        return this.bordColor;
    }

    public boolean getVisible() {       //getter for visibilty
        return visible;
    }

    public void setVisible(boolean visible) {       //setter for visibility
        this.visible = visible;
    }

    public void setInColor(int r, int g, int b){ };

    	@Override
	public void paintComponent(Graphics g) {        //paint method

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

        for(int i = 0; i < rect.size(); i++){                                   //loop through each rectangle object inside arraylist

        g2.setStroke(new BasicStroke(rect.get(i).getBThick()));     //set thickness for order
    
		// rectangle
		g2.setColor(rect.get(i).getBordColor());                                                                //set bordor color and draw bordor
		g2.drawRect(rect.get(i).getX(), rect.get(i).getY(), rect.get(i).getL(), rect.get(i).getW());

        g2.setColor(rect.get(i).getColor());                                                                //set inside color and fill object (only for rectangle and circle objects)
        g2.fillRect(rect.get(i).getX(), rect.get(i).getY(), rect.get(i).getL(), rect.get(i).getW());

        }

        for(int i = 0; i < circ.size(); i++){                           //repeat for circle and line objects

        g2.setStroke(new BasicStroke(circ.get(i).getBThick()));

		// circle
		g2.setColor(circ.get(i).getBordColor());
		g2.drawOval(circ.get(i).getX(), circ.get(i).getY(), circ.get(i).getRadius()*2, circ.get(i).getRadius()*2);

        g2.setColor(circ.get(i).getColor());
        g2.fillOval(circ.get(i).getX(), circ.get(i).getY(),  circ.get(i).getRadius()*2,  circ.get(i).getRadius()*2);

        }

        for(int i = 0; i < line.size(); i++){
            
        g2.setStroke(new BasicStroke(line.get(i).getBThick()));

		// line
		g2.setColor(line.get(i).getBordColor());
		g2.drawLine(line.get(i).getX(), line.get(i).getY(), line.get(i).getEndX(), line.get(i).getEndY());

        }
	}

}
    

