/*
@Author Harman Singh
The subclass of the jump effect
 */


public class Jump extends Effects {

    private int x = 1;
    private int y = 1;

    int getX() {         //Getting the X value
        return this.x;
    }

    void setX(int x) {   //Setting the X value
        this.x = x;
    }

    int getY() {         //Getting the Y value
        return this.y;
    }

    void setY(int y) {   //Setting the Y value
        this.y = y;
    }

    //Constructors
    public Jump() {
    }

    public Jump(int x, int y, int startFrame) {
        this.setX(x);
        this.setY(y);
        super.setStartFrame(startFrame);
    }
}
