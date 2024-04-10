package week7;

import java.awt.Color;
import java.util.*;

public class UserMenu {

	public static void printMenu() {
		System.out.println("Choose an option:\r\n"
				+ "b - remove the bluest column\r\n"
				+ "r - remove a random column\r\n"
				+ "u - undo the previous action\r\n"
				+ "q - quit:\r\n");
	}
	
	public void choice(PixelGrid pixelGrid, String input) {		
		switch(input) {
		case "b": //blue column
			int bluest = pixelGrid.getBluestCol();
			pixelGrid.colorCol(Color.BLUE, bluest);
			pixelGrid.deleteCol(bluest);
			break;
		case "r":
			int random = pixelGrid.getRandomCol();
			pixelGrid.colorCol(Color.RED, random);
			pixelGrid.deleteCol(random);
			break;
		case "u": 
			pixelGrid.undoDelete();
			break;
		case "q":
			pixelGrid.saveNewImg("NewIMG.png");
			System.out.println("Thanks for playing.");
			break;
		default:
			System.out.println("That is not a valid option.");
			break;
		}
	}

	public static void main(String[] args) {
		UserMenu test = new UserMenu();
		PixelGrid anteater = null;
		//anteater = new PixelGrid("images/anteater1.png");
		try {
			anteater = new PixelGrid("images/givenGrid.png");
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		boolean shouldQuit = false;
	
		Scanner scan = new Scanner(System.in);
		while(!shouldQuit) {
			printMenu();
			
			try {
				String input = scan.next();
				if(input.equals("q")) {
					shouldQuit = true;
				}
				test.choice(anteater, input);
			} 
			
			catch (InputMismatchException e) {
				System.out.println("Input should be a letter: b, r, u, or q");
				shouldQuit = true;
			}
		}
		scan.close();
	}
}
