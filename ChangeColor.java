/*
@Author Harman Singh
The subclass of the change color effect
*/

public class ChangeColor extends Effects{

    private int r = 1;
    private int g = 1;
    private int b = 1;
    
    public int getB() {             //getters and setters
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
 
    public ChangeColor(){
    }
    
    public ChangeColor(int r, int g, int b, int startFrame){
        setR(r);
        setG(g);
        setB(b);
        super.setStartFrame(startFrame);
    }
}
