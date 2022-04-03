
/* 
Author: William Sran
Date: April 2022
Description: Line class containing appropriate attributes
*/

public class Line extends Shape{

    private int endX, endY;     //declare ending xy coordinate attributes

    public Line(){      //defualt constructor 
        super();
    }

    public Line(int endX, int endY, int x, int y, int r, int g, int b, int bThick){

        super(x, y, r, g, b, bThick);       //call superclass and intialize values

        this.endX = endX;
        this.endY = endY;
    }


    public int getEndX() {          //getters and setters
        return this.endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return this.endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    @Override
    public void setInColor(int rIn, int gIn, int bIn){

        setBordColor(rIn, gIn, bIn);
    }
    
}