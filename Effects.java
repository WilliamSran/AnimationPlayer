/*
@Author Harman Singh
The superclass that hold's the base information for the effects
*/



public class Effects {
    private int startFrame = 1;     //The starting frame for each effect. The default value is 1 if it not told to be anything else
    
    public int getStartFrame(){     //Getting the startFrame value
        return this.startFrame;
    }
    
    public void setStartFrame(int startFrame){      //Setting the startFrame value
        if(startFrame < 0){     //Checking if the user inputted a frame value less than 0. If so, the absolute value is used
            System.out.println("Enter a frame number greater than 0, the absolute value is used as default");
            this.startFrame = Math.abs(startFrame);
        }
        else{
            this.startFrame = startFrame;       //If the startFrame value is above 0
        }
    }
}
