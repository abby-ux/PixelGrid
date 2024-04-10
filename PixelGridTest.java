package week7;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
//test remove columns 
class PixelGridTest {
	@Test
	void testPixelGridArrayListOfArrayListOfColor() {
		//create a 3x3 PixelGrid
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
		
		assertEquals(
				"R0: 0, G0: 0, B0: 0 | R1: 1, G1: 1, B1: 1 | R2: 2, G2: 2, B2: 2\r\n"
				+ "R0: 0, G0: 0, B0: 0 | R1: 1, G1: 1, B1: 1 | R2: 2, G2: 2, B2: 2\r\n"
				+ "R0: 0, G0: 0, B0: 0 | R1: 1, G1: 1, B1: 1 | R2: 2, G2: 2, B2: 2\r\n",
				pixGrid1.toString()
				);
	}
	
	@Test
    public void testDeleteCol() {
		//create a 3x3 PixelGrid - test deleteCol(1):
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
			
			pixGrid1.deleteCol(1);
			
        assertEquals(
        		"R0: 0, G0: 0, B0: 0 | R1: 2, G1: 2, B1: 2\r\n"
        		+ "R0: 0, G0: 0, B0: 0 | R1: 2, G1: 2, B1: 2\r\n"
        		+ "R0: 0, G0: 0, B0: 0 | R1: 2, G1: 2, B1: 2\r\n", 
        		pixGrid1.toString());
        
        // Testing deleting a column with a negative number
        //- should throw exception
        IllegalArgumentException exceptionNegative = 
        		assertThrows(IllegalArgumentException.class, () -> {
            pixGrid1.deleteCol(-1);
        });
        assertEquals(
        		"Invalid input: Negative number.", 
        		exceptionNegative.getMessage());

        // Testing deleting a column with an out of bounds
        //- should throw exception
        IllegalArgumentException exceptionOutOfBounds = 
        		assertThrows(IllegalArgumentException.class, () -> {
            pixGrid1.deleteCol(3);
        });
        assertEquals("Invalid input: Out of bounds.", exceptionOutOfBounds.getMessage());
    }
	
	@Test
    public void testDeleterRandomCol() {
		//create a 3x3 PixelGrid - test deleteCol(1):
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
			int random = pixGrid1.getRandomCol(); //random = 1
			pixGrid1.deleteCol(random);
			
        assertEquals(
        		"R0: 0, G0: 0, B0: 0 | R1: 2, G1: 2, B1: 2\r\n"
        		+ "R0: 0, G0: 0, B0: 0 | R1: 2, G1: 2, B1: 2\r\n"
        		+ "R0: 0, G0: 0, B0: 0 | R1: 2, G1: 2, B1: 2\r\n", 
        		pixGrid1.toString());
        
        // Testing deleting a column with a negative number
        //- should throw exception
        IllegalArgumentException exceptionNegative = 
        		assertThrows(IllegalArgumentException.class, () -> {
            pixGrid1.deleteCol(-1);
        });
        assertEquals(
        		"Invalid input: Negative number.", 
        		exceptionNegative.getMessage());

        // Testing deleting a column with an out of bounds
        //- should throw exception
        IllegalArgumentException exceptionOutOfBounds = 
        		assertThrows(IllegalArgumentException.class, () -> {
            pixGrid1.deleteCol(3);
        });
        assertEquals("Invalid input: Out of bounds.", exceptionOutOfBounds.getMessage());
    }

	@Test
    public void testDeleterBlueCol() {
		//create a 3x3 PixelGrid - test deleteCol(1):
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
			int blue = pixGrid1.getBluestCol(); 
			pixGrid1.deleteCol(blue);
			
        assertEquals(
        		"R0: 0, G0: 0, B0: 0 | R1: 1, G1: 1, B1: 1\r\n"
        		+ "R0: 0, G0: 0, B0: 0 | R1: 1, G1: 1, B1: 1\r\n"
        		+ "R0: 0, G0: 0, B0: 0 | R1: 1, G1: 1, B1: 1\r\n", 
        		pixGrid1.toString());
        
        // Testing deleting a column with a negative number
        //- should throw exception
        IllegalArgumentException exceptionNegative = 
        		assertThrows(IllegalArgumentException.class, () -> {
            pixGrid1.deleteCol(-1);
        });
        assertEquals(
        		"Invalid input: Negative number.", 
        		exceptionNegative.getMessage());

        // Testing deleting a column with an out of bounds
        //- should throw exception
        IllegalArgumentException exceptionOutOfBounds = 
        		assertThrows(IllegalArgumentException.class, () -> {
            pixGrid1.deleteCol(3);
        });
        assertEquals("Invalid input: Out of bounds.", exceptionOutOfBounds.getMessage());
    }
}
