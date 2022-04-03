import java.awt.Color;

/* 
Author: William Sran
Date: April 2022
Description: rectangle class containing appropriate attributes (length, width, inside solor)
*/

public class Rectangle extends Shape{       //extends shape class
    private int l;              //declare size attributes and rgb values for within rectangle
    private int w;
    private int rIn, bIn, gIn;

    public Color inColor;

    public Rectangle(){     //default constructor
        super();
        this.l = 10;
        this.w = 10;
        setBordColor(0, 255, 0);
    }

    //overloaded constructor 

    public Rectangle(int l, int w, int rIn, int gIn, int bIn, int x, int y, int r, int g, int b, int bThick){

        super(x, y, r, g, b, bThick);       //call superclass and initialize values
    
        this.l = l;
        this.w = w;
        setInColor(rIn, gIn, bIn);

    }

    public int getL() {     //getters and setters
        return this.l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getW() {
        return this.w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getRIn() {
        return this.rIn;
    }

    public void setRIn(int rIn) {
        this.rIn = rIn;
    }

    public int getBIn() {
        return this.bIn;
    }

    public void setBIn(int bIn) {
        this.bIn = bIn;
    }

    public int getGIn() {
        return this.gIn;
    }

    public void setGIn(int gIn) {
        this.gIn = gIn;
    }

    public Color getColor(){        //getter for inside color

        return this.inColor;
    }

    @Override
    public void setInColor(int rIn, int gIn, int bIn){              //setter for inside color

        setR(rIn);
        setG(gIn);
        setB(bIn);
        this.inColor = new Color(rIn, gIn, bIn);
    }

}