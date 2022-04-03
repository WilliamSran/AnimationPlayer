
import java.awt.Color;

/* 
Author: William Sran
Date: April 2022
Description: Circle class containing appropriate attributes(radius and inside color values)
*/

public class Circle extends Shape{      //extends shape class

    private int rIn, bIn, gIn;     //declare attributes for shape including rgb values for inside colour and radius
    private int radius;
    public Color inColor;

    public Circle(){        //default constructor
        super();
        this.radius = 20;
        setBordColor(0, 255, 0);
    }

    //overloaded constructor

    public Circle(int radius, int rIn, int gIn, int bIn, int x, int y, int r, int g, int b, int bThick){

        super(x, y, r, g, b, bThick);       //call superclass and intialize values
    
        this.radius = radius;           //set radius and inside color values
        setInColor(rIn, gIn, bIn);
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

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor(){

        return this.inColor;
    }

    @Override
    public void setInColor(int rIn, int gIn, int bIn){

        setR(rIn);
        setG(gIn);
        setB(bIn);
        this.inColor = new Color(rIn, gIn, bIn);
    }

}