import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * Authors: William Sran, Harman Singh, Gurveer Saund, and Chinthana Sembakutti
 * Date: April 2022
 * Description: This program loads and plays an animation from a file
 */

public class AnimationPlayerUI extends JFrame implements ActionListener {

	// instance variable for components
	private JPanel controlPanel, textPanel;
	private JButton btnStart, btnExit;

	// instance variables for droid array, timer, and die
	private Timer timer;
	private Shape shape = new Shape();
	int frameCount = 0;

	private static Shape[] shapes; //shapes array

	private static Effects[] effects; // The array that will hold the sorted effects in chronological order
	private static int[] sortedCounter; // The array that will hold the shapes that the effects are refering to

	private static int index = 0;

	private static int frames;			//fps and number of frames
	private static int fps;

	/**
	 * Creates an instance of UI
	 */
	public AnimationPlayerUI() {

		super("AnimationPlayer"); // title for the frame

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // create variables for dimension, width and height
																		// of frame, and TextPixture for title
		int width, height;
		TextPicture text;

		// initialize the width of the frame
		width = 900;
		height = 650;

		setLayout(null); // layout for my frame

		controlPanel = new JPanel(); // panel for buttons
		textPanel = new JPanel(); // title panel

		// create and set text objects
		textPanel.setBounds(0, 0, width, height - 100);
		text = new TextPicture("Animation Player!", 270, 40);
		text.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 50));
		text.setBounds(0, 0, width, 50);
		textPanel.setBackground(Color.BLACK);
		textPanel.add(text);
		text.setC(Color.PINK);

		URL url = AnimationPlayerUI.class.getResource("spongebob-dance.gif"); // make gif
		ImageIcon imageIcon = new ImageIcon(url);
		JLabel label = new JLabel(imageIcon);

		btnStart = new JButton("Start"); // create buttons
		btnExit = new JButton("Exit");

		label.setBounds(200, 51, 498, 498); // set bounds for label and add it to the frame

		add(label);

		// change font and color of buttons
		btnStart.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
		btnStart.setForeground(Color.PINK);
		btnExit.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 20));
		btnExit.setForeground(Color.PINK);

		// set the size and position of the control panel
		controlPanel.setBounds(0, height - 100, width, 100);

		btnStart.setBackground(Color.BLACK); // change background color of buttons
		btnExit.setBackground(Color.BLACK);

		// add components to panels
		controlPanel.add(btnStart); // add button to control panel
		controlPanel.add(btnExit);

		controlPanel.setBackground(Color.BLACK);

		add(text);
		add(controlPanel); // add the button, drawing, and text panels to the frame
		add(textPanel);

		// add the buttons as listeners in this frame
		btnStart.addActionListener(this);
		btnExit.addActionListener(this);

		// set size and location of frame
		setSize(width, height);
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public AnimationPlayerUI(boolean state) { // frame for actual animation player

		super("AnimationPlayer"); // title for the frame

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // create variables for dimension, width and height
																		// of frame
		int width, height;

		// initialize the width of the frame
		width = 900;
		height = 650;

		setLayout(null); // layout for my frame

		JPanel drawingPanel = shape; // set the animation panel equal to the shape

		// set the boundary and layout of drawing panel
		drawingPanel.setBounds(0, 0, width, height);
		drawingPanel.setLayout(null);

		drawingPanel.setBackground(Color.WHITE);

		add(drawingPanel);

		timer = new Timer(1000/fps, this); // creates a timer and this class as the listener. set to fire every 250ms
		timer.setInitialDelay(5); // set the initial delay to 5 ms before it starts

		// set size and location of frame
		setSize(width, height);
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		timer.addActionListener(this);

		setVisible(true);

		timer.start();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed (ActionEvent e){

		frameCount++;		//add one to frame
		
		if (e.getSource() == btnStart){  // if button is clicked

			setVisible(false);
			new AnimationPlayerUI(true);

		}

		else if (e.getSource() == btnExit) {	// if button is clicked, end the program
			System.exit(0);
		}

		else if (e.getSource()==timer){	
			// if the timer fires, go through each of the frames

			int sameFrame = 0;
			int tempIndex = index;

				if(frameCount % 2 == 0){

				System.out.print(frameCount/2 + ": ");

				if(index != effects.length){

				System.out.println(effects[index].getStartFrame());

				if(frameCount/2 == effects[index].getStartFrame()){		//Checking if an effect will play at the current frame

					if(index != effects.length - 1){		//Checking if the program has reached the last effect
					while((tempIndex != effects.length - 1) && effects[index].getStartFrame() == effects[++tempIndex].getStartFrame()){		//Checking for multiple effects on the same frame
						sameFrame++;		//Counting the number of effects on the same frame
					}
					}
					
					for(int i = 0; i <= sameFrame; i++){		//Running the effect player the number of times there are effects on the same frame

					if(shapes[sortedCounter[index]].getVisible()){		//Checking if the object is currenly shown on the screen, if so, it will have access to all of the effects
						
						if(effects[index] instanceof Hide){				//If the current effect is of type hide
							shapes[sortedCounter[index]].setVisible(false);		//Setting the visibilty value to false
							draw();		//Calling the draw method
						
						}
						else if(effects[index] instanceof ChangeColor){		//If the current effect is of type ChangeColor
							shapes[sortedCounter[index]].setInColor(((ChangeColor)effects[index]).getR(),		//Setting the new rgb values
							((ChangeColor)effects[index]).getG(),((ChangeColor)effects[index]).getB());
							repaint();		//Repainting the scene with the updated values
						}
						else if(effects[index] instanceof Jump){		//If the current effect is of type Jump
							shapes[sortedCounter[index]].setX(((Jump)effects[index]).getX());		//Changing the x and y values of the shape
							shapes[sortedCounter[index]].setY(((Jump)effects[index]).getY());
							repaint();		//Updating the scene
						}
					}
					else if(effects[index] instanceof Show){		//If the current effect is of type show AND the shape isn't already drawn, then the code
						shapes[sortedCounter[index]].setVisible(true);		//will draw the shape
						draw();		//Drawing the new shape
				}
				index++;
			}
			}
		}
		}
		if(frameCount/2==frames){
		timer.stop();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Thanks for watching!");

		System.exit(0);
	}
	}



	}// actionPerformed

	public void draw(){													//method to draw shapes on panel

		if (shapes[sortedCounter[index]] instanceof Rectangle) {		//if current shape is rectangle
			
			if(effects[index] instanceof Hide){				
				shape.rect.remove((Rectangle)shapes[sortedCounter[index]]);		//if current effect is hide then remove current object from panel/array list of shapes
			}
			else{
			shape.rect.add((Rectangle)shapes[sortedCounter[index]]);		//else add current shape to panel/array of shapes
			}
		} 
		
		else if (shapes[sortedCounter[index]] instanceof Circle) {				//repeat for circle and line
			if(effects[index] instanceof Hide){
				shape.circ.remove((Circle)shapes[sortedCounter[index]]);
			}
			else{
			shape.circ.add((Circle)shapes[sortedCounter[index]]);
			}
		} 
		
		else if (shapes[sortedCounter[index]] instanceof Line) {
			if(effects[index] instanceof Hide){
				shape.line.remove((Line)shapes[sortedCounter[index]]);
			}
			else{
			shape.line.add((Line)shapes[sortedCounter[index]]);
			}
		}

		repaint();

	}

	public static void main(String[] args) throws IOException{

		String path = JOptionPane.showInputDialog(null, "Please enter the input file path");

		path = path.replace("\\", "\\\\");
		
		try{												//catch most errors 
		FileReader2 reader = new FileReader2(path);
		shapes = FileReader2.shapes;

		Sorting sorted = new Sorting();		//Making an object of type sorting
		sorted.sort();						//Sorting the shapes array

		effects = sorted.effects;			//Storing the values of the sorted effects in chronological order
		sortedCounter = sorted.items;		//Storing the values of the shapes that each effect is refering to

		frames = reader.frames;
		fps = reader.speed;
		new AnimationPlayerUI();   //make a animation player object
		}

		catch (FileNotFoundException exception){					//if filenotfound exception thrown then exit program						

			JOptionPane.showMessageDialog(null, "Incorrect file path, click ok to exit program.");
			System.exit(0);
		}
		catch(ArrayIndexOutOfBoundsException exception){			//if outofbounds exception thrown then exit program

			JOptionPane.showMessageDialog(null, "Incorrect number of elements listed in input file, please fix and try again.");
			System.exit(0);
		}
		catch(NumberFormatException exception){				//if numberformat exception thrown then exit program

			JOptionPane.showMessageDialog(null, "Crucial information missing in input file. Please check to make sure all data was formatted correctly.");
			System.exit(0);
		}
		catch(NullPointerException exception){				//if cancel is clicked

			System.exit(0);
		}
	}
}

