package week7;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.ImageIO;


public class PixelGrid {
	// represents a grid of pixels
	private ArrayList<ArrayList<Color>> grid;
	// represents the actions done to a pixelgrid
	private Stack<Action> commands;
	// counts how many actions were made to a pixelgrid
	private int counter = 0;
	// seed number and Random object needed to get a seeded random column.
	private long seed = 2346789;
	private Random prg;
	
	//constructor that takes in a string (file name) and 
	//add a photo to pixelGrid 
	public PixelGrid(String fileName) {
		//allocate memory for grid
		grid = new ArrayList<ArrayList<Color>>();
		commands = new Stack<Action>();
		prg = new Random(seed);
		BufferedImage inputImg;
		File inputFile = null;
		//try to read file from string passed in
		try {
			inputFile = new File(fileName);
			inputImg = ImageIO.read(inputFile);
			
					for (int y = 0; y < inputImg.getHeight(); y++) {
						ArrayList<Color> row = new ArrayList<Color>();
						for (int x = 0; x < inputImg.getWidth(); x++) {
							//Retrieving contents of a pixel
							int pixel = inputImg.getRGB(x, y);
							//Creating a Color object from pixel value
				            Color originalColor = new Color(pixel);
				            //Retrieving the R G B values
				            int red = originalColor.getRed();
				            int green = originalColor.getGreen();
				            int blue = originalColor.getBlue();
				            //setting the new image to the original color pixels 
				            Color newColor = new Color(red, green, blue);

				            row.add(newColor);
						}
						grid.add(row);
					}
		            saveNewImg("temp_0" + counter + ".png");		
		}	
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		catch (IllegalArgumentException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//constructor that takes in a pixelGrid and creates a deep copy 
	public PixelGrid(ArrayList<ArrayList<Color>> givenGrid) {
		grid = new ArrayList<ArrayList<Color>>();
		commands = new Stack<Action>();
		prg = new Random(seed);
		int rows = givenGrid.size();
		int cols = givenGrid.get(0).size();
		//deep copy over every color
		for (int r = 0; r < rows; r++) {
			ArrayList<Color> row = new ArrayList<Color>();
		for (int c = 0; c < cols; c++) {
			row.add(givenGrid.get(r).get(c));
		}
		grid.add(row);
	}
		for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Color color = grid.get(r).get(c);
            }
        }
		
		saveNewImg("temp_0" + counter + ".png");
	}
	
	
	// undoes that action of deleting a column of pixels
	// from a pixelGrid.
	//takes in nothing, and returns nothing, but saves a new image
	//of the photo with the previously deleted column added back in 
	public void undoDelete() {
		if (!commands.isEmpty()) {
			Action prevCommand = commands.pop();
			int intToAdd = prevCommand.getIndex();
			ArrayList<Color> colToAdd = prevCommand.getColumn();
			
			for (int i = 0; i < grid.size(); i ++) {
				this.grid.get(i).add(intToAdd, colToAdd.get(i));
			}
			saveNewImg("temp_0" + counter + ".png");
		} else {
			System.out.println("No actions to undo.");
		}
	}
	
	//takes in an integer which represents the column number to delete
	// and returns nothing but saves a new image of the pixelGrid with the column deleted.
	public void deleteCol(int colDelete) {
		ArrayList<ArrayList<Color>> afterDelete = new ArrayList<ArrayList<Color>>();
		if (colDelete < 0) {
            throw new IllegalArgumentException("Invalid input: Negative number.");
        }
		 for (int row = 0; row < grid.size(); row++) {
		        ArrayList<Color> ogRow = grid.get(row);
		        if (colDelete > ogRow.size()) {
	                throw new IllegalArgumentException("Invalid input: Out of bounds.");
	            }
		        ArrayList<Color> newRow = new ArrayList<>();
		        for (int col = 0; col < ogRow.size(); col++) {
		            if (col != colDelete) {
		                newRow.add(ogRow.get(col));
		            }
		        }

		        afterDelete.add(newRow);
		    }
		
		grid = afterDelete;
		saveNewImg("temp_0" + counter + ".png");
	}
	
	// takes in an integer representing the column of a pixelGrid and returns
	// an arrayList of color with all the colors of that column
	public ArrayList<Color> seeColor(int column) {
		if (column < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
		ArrayList<Color> colorList = new ArrayList<>();
		for (int row = 0; row < grid.size(); row++) {
	        ArrayList<Color> ogRow = grid.get(row);
	        if (column > ogRow.size()) {
                throw new IllegalArgumentException("Invalid input");
            }
	        for (int col = 0; col < ogRow.size(); col++) {
	        	if (col == column) {
	        		Color color = grid.get(row).get(col);
		            colorList.add(color);
	        	}
	        }
	    }
		return colorList;
	}
	
	// takes in nothing and returns a 'random' seeded integer
	// that represents a column number from the pixelGrid
	public int getRandomCol() {
	    return prg.nextInt(grid.get(0).size());    
	}
	
	//gets the width of a pixelGrid and returns it as the integer of pixels
	public int getWidth() {
		return grid.get(0).size();
	}

	// takes in nothing and returns an integer that represents the
	// column number in a PixelGrid that has the highest value 
	// of blue pixels out of all the columns.
	public int getBluestCol() {
		int maxBlue = -1;
		int maxBlueCol = -1;
		int rows = grid.size();
		int cols = grid.get(0).size();
		
		for (int c = 0; c < cols; c++) {
			int colSum = 0;
		for (int r = 0; r < rows; r++) {
			colSum += grid.get(r).get(c).getBlue();
		}
		if (colSum > maxBlue) {
			maxBlue = colSum;
			maxBlueCol = c;
		}
		}
		return maxBlueCol;
	}
	
	// takes in the color you want to color a column, and the column number that you want
	// to color, and saves a new image with the specified column changed to a solid 
	// colored column. 
	public void colorCol(Color inputColor, int index) {
		ArrayList<Color> colorOfCol = new ArrayList<Color>();
		for (int i = 0; i < grid.size(); i++) {
			colorOfCol.add(inputColor);
		}
		commands.push(new Action(index, seeColor(index)));
		int rows = grid.size();
        int cols = grid.get(0).size();
        ArrayList<ArrayList<Color>> newGrid = new ArrayList<ArrayList<Color>>();
        for (int r = 0; r < rows; r++) {
        	ArrayList<Color> row = new ArrayList<Color>();
            for (int c = 0; c < cols; c++) {
                Color color = grid.get(r).get(c);
                if (c == index) {
                	row.add(inputColor);
                } else {
                	row.add(color);	
                }           
            }
            newGrid.add(row);
        }
        grid = newGrid;
        saveNewImg("temp_0" + counter + ".png");
	}
	
	//takes in a string and then saves an image with the string as the file name
	public void saveNewImg(String fileName) {	
        int rows = this.grid.size();
        int cols = this.grid.get(0).size();

        BufferedImage newImg = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Color color = this.grid.get(r).get(c);
                newImg.setRGB(c, r, color.getRGB());
            }
        }
        
        try {
        	ImageIO.write(newImg, "png", new File(fileName));
            System.out.println("New image saved: " + fileName + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        counter++;
    }
	
	// toString method that represents a pixelGrid
	// toString with the RBG values of each 
	public String toString() {
		StringBuilder pixelString = new StringBuilder();
		for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid.get(0).size(); c++) {
            	pixelString.append("R" + c + ": " + grid.get(r).get(c).getRed());
            	if (c < grid.get(0).size()) {
            		pixelString.append(", ");
            	}
            	pixelString.append("G" + c + ": " + grid.get(r).get(c).getGreen());
            	if (c < grid.get(0).size()) {
            		pixelString.append(", ");
            	}
            	pixelString.append("B" + c + ": " + grid.get(r).get(c).getBlue());
            	if (c < grid.get(0).size()-1) {
            		pixelString.append(" | ");
            	}
                if (c == grid.get(0).size() - 1) {
    				pixelString.append(System.lineSeparator());
    			}
            }
        }
		return pixelString.toString();
	}

	// main method
	public static void main(String[] args) {
		ArrayList<Color> columns = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int red = i;
			int green = i;
			int blue = i;
			if (i > 255) {
				red = (int)(Math.random() * 256);
				green = (int)(Math.random() * 256);
				blue = (int)(Math.random() * 256);
			}
			Color color = new Color(red, blue, green);
			columns.add(color);
		}
		ArrayList<ArrayList<Color>> myGrid1 = new ArrayList<ArrayList<Color>>();
		for (int i = 0; i < 3; i++) {
			myGrid1.add(columns);
		}

		PixelGrid pixGrid1 = new PixelGrid(myGrid1);
		

		System.out.println("original grid:");
		System.out.println(pixGrid1.toString());
		pixGrid1.colorCol(Color.RED, 0);
		System.out.println("red colored grid:");
		System.out.println(pixGrid1.toString());

		
		System.out.println(pixGrid1.toString());
		System.out.println("deleted 2 cols (0 then 1):");
		pixGrid1.deleteCol(0);
		pixGrid1.deleteCol(1);
		System.out.println(pixGrid1.toString());
		System.out.println("undoDelete() once:");
		pixGrid1.undoDelete();
		System.out.println(pixGrid1.toString());
		
		System.out.println(pixGrid1.getRandomCol());
		System.out.println(pixGrid1.getRandomCol());
		System.out.println(pixGrid1.getRandomCol());
		System.out.println(pixGrid1.getRandomCol());
		System.out.println(pixGrid1.getRandomCol());

	}

}
