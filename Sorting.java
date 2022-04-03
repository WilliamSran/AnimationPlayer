import java.io.IOException;
/*
@Author Harman Singh
Date: April 2022
Description: This program will sort all of the effects in chronological order according to frame number
*/
public class Sorting {

    Effects[] effects;
    int[] items;

    public void sort() throws IOException{ 

        Shape[] shapes = FileReader2.shapes;

        int numOfEffects = 0;   //Number of Effecsts

        for(int counter = 0; counter < shapes.length; counter++) {
            numOfEffects += shapes[counter].effects.size();     //Finding the number of effects
        }

        Effects[] sortedEffects = new Effects[numOfEffects];        //Creating arrays to store the effects sorted by their starting frame
        int[] sortedShapes = new int[numOfEffects];             //Creating an array to store the index of shape within the shapes array

        Effects[] unSortedEffects = new Effects[numOfEffects];  //Creating an unsorted array of effects
        int[] unSortedShapes = new int[numOfEffects];           //Creating an unsorted array of the indexes of shapes

        int index = 0;

        for (int counter = 0; counter < shapes.length; counter++) {       //Running through each shape
            for (int effectsCounter = 0; effectsCounter < shapes[counter].effects.size(); effectsCounter++) {     //Going through each effect in each shape
                unSortedEffects[index] = shapes[counter].effects.get(effectsCounter);       //Filling up the unsorted effects array with all of the effects
                unSortedShapes[index++] = counter;          //Filling up the unsorted shapes index with the index of the shape that is associated with the effecet in the unsorted effects class
            }
        }

        int indexOfLowest = 0;
        int lowestFrame = 999999;
        index = 0;

        while (unSortedEffects.length != 0) {
            for (int counter = 0; counter < unSortedEffects.length; counter++) {      //Going through each effect in the unsorted array

                if (unSortedEffects[counter].getStartFrame() < lowestFrame) {       //Finding the effect with the lowest start frame
                    indexOfLowest = counter;        //Saving the index of the shape and effect of the first effect (in order)
                    lowestFrame = unSortedEffects[counter].getStartFrame();
                }
            }

            sortedEffects[index] = unSortedEffects[indexOfLowest];      //Saving the effects in ascending order
            sortedShapes[index++] = unSortedShapes[indexOfLowest];      //Saving the associated shape in ascending order

            Effects[] temp = new Effects[unSortedEffects.length - 1];   //Creating the temporary arrays that 1 smaller than the unsorted array lengths
            int[] temp2 = new int[unSortedShapes.length - 1];

            int nextIndex = 0;
            for (int counter2 = 0; counter2 < unSortedShapes.length; counter2++) {  //Deleting the element of the lowest frame effect so the program doesn't read it again and put the same effect in the sorted array multiple times
                if (counter2 != indexOfLowest) {    //Checking
                    temp[nextIndex] = unSortedEffects[counter2];
                    temp2[nextIndex++] = unSortedShapes[counter2];
                }
            }

            unSortedEffects = temp;     //Saving the temp array into the unsorted array, which removes the lowest frame effect
            unSortedShapes = temp2;

            lowestFrame = 999999;
        }
        effects = sortedEffects;
        items = sortedShapes;
    }
}
