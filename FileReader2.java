import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

/*
 * Author: Chinthana Sembakutti
 * Date: April 2022
 * Description: Program reads animation info from a file and puts it into a shape array
 */

public class FileReader2 {

    String filename;
    int frames;
    int speed;
    int number_of_elements = 1;
    static BufferedReader read2;
    static public Shape[] shapes;
    public static int counter = 0;

    public FileReader2(String filename) throws IOException {
        this.filename = filename;
        ReadStartingInfo(filename);
    }

    //this method reads the frames, speed and number of elements from the text file 
    private void ReadStartingInfo(String filename) throws FileNotFoundException, IOException {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        this.read2 = reader;
        //getting frames
        String tmp = CheckValid(read2.readLine());
        if (tmp.contains("frames")) {
            this.frames = Integer.parseInt(tmp.replaceAll("[^0-9]", ""));
        } else {
            this.frames = 1; //default value
        }

        //getting speed
        tmp = CheckValid(read2.readLine());
        if (tmp.contains("speed")) {
            this.speed = Integer.parseInt(tmp.replaceAll("[^0-9]", ""));
        } else {
            this.speed = 1; //default value
        }

        //getting number of elements
        tmp = CheckValid(read2.readLine());
        number_of_elements = Integer.parseInt(tmp.replaceAll("[^0-9]", ""));

        //creating shapes array with number of elements as size
        Shape[] shapesArray = new Shape[number_of_elements];
        //setting shapes array to shapes from filereader (so it can be accessed anyway as its static)
        this.shapes = shapesArray;
        
        //calling CommandReader methed which starts the rest of the reading
        CommandReader(); //rest of stuff will be read in another method
        reader.close();
    }

    private void CommandReader() throws IOException {
        String str;

        //continually checks if a line in the text file is circle, rect, line or effect.
        //if it is, it calls their respective method
        while (read2.readLine() != null) {
            str = CheckValid(read2.readLine());
            if (str == null) {
                break;
            }
            else if (str.equalsIgnoreCase("Circle")) {
                getCircleInfo();
            } else if (str.equalsIgnoreCase("Rect")) {
                getRectInfo();
            } else if (str.equalsIgnoreCase("Line")) {
                getLineInfo();
            } else if (str.startsWith("effect")) {
                //for additional effects for a shape. decrements counter so 
                //the arraylist reflects the intended shape, gets effect info,
                //then increments counter.
                counter--;
                getEffectInfo();
                counter++;
            }
        }
    }

    //getting information for a circle object from text file
    private void getCircleInfo() throws IOException {
        //default values in case user doesn't enter one
        String str;
        int r = 10;
        int x = 1;
        int y = 1;
        int red = 0;
        int green = 0;
        int blue = 0;
        int borderThickness = 5;
        int borderRed = 0;
        int borderGreen = 0;
        int borderBlue = 0; 

        //continually checks str against the required parameters to get info. When a str value
        //isn't recognized as a part of the circles parameters, loop will exit    
        while (true) {

            //if str is null, (end of file was reached) loop will break
            str = CheckValid(read2.readLine());
            if (str == null) {
                break;
            }
            //takes the entire line and selects only the numbers from it, then sets that to the required variable
            if (str.startsWith("r:")) {
                r = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("x:")) {
                x = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("y:")) {
                y = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("color")) {
                String[] color = str.split(",");
                red = Integer.parseInt(color[0].replaceAll("[^0-9]", ""));
                green = Integer.parseInt(color[1].replaceAll("[^0-9]", ""));
                blue = Integer.parseInt(color[2].replaceAll("[^0-9]", ""));
            } else if (str.startsWith("border:")) {
                borderThickness = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("borderColor")) {
                String[] borderColor = str.split(",");
                borderRed = Integer.parseInt(borderColor[0].replaceAll("[^0-9]", ""));
                borderGreen = Integer.parseInt(borderColor[1].replaceAll("[^0-9]", ""));
                borderBlue = Integer.parseInt(borderColor[2].replaceAll("[^0-9]", ""));
            } else {
                break;
            }
        }

        //creating new circle object in the shape array
        shapes[counter] = new Circle(r, red, green, blue, x, y, borderRed, borderGreen, borderBlue, borderThickness);
        //if an effect is read, the getEffectInfo method will be called
        if (str.startsWith("effect")) {
                getEffectInfo();
        }
        //counter is incrememnted to signify that the current shape information has been collected, and the 
        //next object is ready to be read
        counter++;
    }
    
    //getting information for a rectangle object
    private void getRectInfo() throws IOException {
        //default values
        String str;
        int length = 15;
        int width = 15;
        int x = 10;
        int y = 10;
        int red = 0;
        int green = 0;
        int blue = 0;
        int borderThickness = 5;
        int borderRed = 0;
        int borderGreen = 0;
        int borderBlue = 0;

        while (true) {

            //continually checks str against the parameters of rectangle to see if they match. If they dont,
            //loop will break
            str = CheckValid(read2.readLine());
            if (str == null) {
                break;
            }
            //turning string into integer after replacing all non int values in the current line
            if (str.startsWith("length")) {
                length = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("width")) {
                width = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("x")) {
                x = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("y")) {
                y = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("color")) {
                //splitting string at a comma to get the different colours induvidually (red, green, blue)
                String[] color = str.split(",");
                red = Integer.parseInt(color[0].replaceAll("[^0-9]", ""));
                green = Integer.parseInt(color[1].replaceAll("[^0-9]", ""));
                blue = Integer.parseInt(color[2].replaceAll("[^0-9]", ""));
            } else if (str.startsWith("border:")) {
                borderThickness = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("borderColor")) {
                String[] borderColor = str.split(",");
                borderRed = Integer.parseInt(borderColor[0].replaceAll("[^0-9]", ""));
                borderGreen = Integer.parseInt(borderColor[1].replaceAll("[^0-9]", ""));
                borderBlue = Integer.parseInt(borderColor[2].replaceAll("[^0-9]", ""));
            } else {
                break;
            }
        }
        //creating rectangle object in the shape array using information collected from text file
        shapes[counter] = new Rectangle(length, width, red, green, blue, x, y, borderRed, borderBlue, borderGreen, borderThickness);
        
        //checking to see if an effect is after the rectangle object. If true getEffectInfo method is called
        if (str.startsWith("effect")) {
                getEffectInfo();
        }
        //counter incrememnted so next shape can be added
        counter++;
    }
    
    //getting information to create line objects
    private void getLineInfo() throws IOException {
        //defaults
        String str;
        int startX = 1;
        int startY = 5;
        int endX = 20;
        int endY = 15;
        int red = 0;
        int green = 0;
        int blue = 0;
        int borderThickness = 5;

        while (true) {

            //continually checks str against the parameters of line to see if they match. If they dont,
            //loop will break
            str = CheckValid(read2.readLine());
            if (str == null) {
                break;
            }
            //replaces all non int values in the current line, then sets it to a variable
            if (str.startsWith("startX")) {
                startX = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("startY")) {
                startY = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("endX")) {
                endX = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("endY")) {
                endY = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else if (str.startsWith("color")) {
                //splits string at comma to get induvidual commas
                String[] color = str.split(",");
                red = Integer.parseInt(color[0].replaceAll("[^0-9]", ""));
                green = Integer.parseInt(color[1].replaceAll("[^0-9]", ""));
                blue = Integer.parseInt(color[2].replaceAll("[^0-9]", ""));
            } else if (str.startsWith("border")) {
                borderThickness = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            } else {
                break;
            }
        }
        //creating new line object in the shapes array
        shapes[counter] = new Line(endX, endY, startX, startY, red, green, blue, borderThickness);
        //checking if next line is effects. If it is, getEffectInfo method is called
        if (str.startsWith("effect")) {
                getEffectInfo();
        }
        //incrementing counter for next object in shapes array
        counter++;
        
    }

    //getEffectInfo method
    private void getEffectInfo() throws IOException {
        String str = CheckValid(read2.readLine());
        //if str is hide, then starting frame information is collected
        if (str.startsWith("Hide")) {
            str = CheckValid(read2.readLine());
            int startFrameHide = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            Hide hide = new Hide(startFrameHide);
            //adding hide object to the effects arraylist of the shapes object, under the index of the current object
            shapes[counter].effects.add(hide);
            return;
        
        //if str is equal to show, then starting frame information is collected
        } else if (str.startsWith("Show")) {
            str = CheckValid(read2.readLine());
            int startFrameShow = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            Show show = new Show(startFrameShow);
            //adding show object to the effects arraylist of the shapes object, under the index of the current object
            shapes[counter].effects.add(show);
            return;
            
        //if str is equal to jump, then starting frame information and jump position information is collected
        } else if (str.startsWith("Jump")) {
            str = CheckValid(read2.readLine());      
            int starFrameJump = Integer.parseInt(str.replaceAll("[^0-9]", ""));
            str = CheckValid(read2.readLine());
            int x = Integer.parseInt(str.replaceAll("[^0-9]",""));
            str = CheckValid(read2.readLine());
            int y = Integer.parseInt(str.replaceAll("[^0-9]",""));
            Jump jump = new Jump(x, y, starFrameJump);
            //adding jump object to effects arraylist in the same index as the object thats being referred to
            shapes[counter].effects.add(jump);
            return;

        } else if (str.startsWith("ChangeColor")) {
            //if str is equal to ChangeColor, then new colour information and starting frame information is collected
            str = CheckValid(read2.readLine());
            int startFrameColor = Integer.parseInt(str.replaceAll("[^0-9]", "")); 
            str = CheckValid(read2.readLine());
            String[] color = str.split(",");
            int red = Integer.parseInt(color[0].replaceAll("[^0-9]", ""));
            int green = Integer.parseInt(color[1].replaceAll("[^0-9]", ""));
            int blue = Integer.parseInt(color[2].replaceAll("[^0-9]", ""));
            ChangeColor changeColor = new ChangeColor(red, green, blue, startFrameColor);
            //adding ChangeColor to effects arraylist in the same index as the object thats being referred to
            shapes[counter].effects.add(changeColor);
            return;
        }
    }

    //CheckValid method to remove empty spaces in the text file and reduce errors
    private String CheckValid(String str) throws IOException {  
        while (str != null) {
            //if str is only whitespace, next line is read
            if (str.isBlank()) {
                str = read2.readLine();
            }
            else {
                break;
            }
        }
        //if the string is null (end of file) then str will be returned with the message
        if (str == null) {
                str = "the string is null";
            }
            //str is returned
        return str;
    }
}