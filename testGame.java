import java.util.Scanner;

/**
 * a class which creates a test for the game
 * 
 * @author Marius Bonke 4839983 Gruppe 3b
 * @author Lucas Schröder 4809832 Gruppe 3b
 */
public class testGame {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Game game = new Game();
		SwingWindow window = new SwingWindow();
		int tempColumn;
		
		do {
			
			System.out.println("Player " + (char)(game.getActivePlayer() + 64) + "'s turn! Type in a row (from 0 to 6):");
			tempColumn = scanner.nextInt();
			if(tempColumn > 6) {
				
				System.out.println();
				System.out.println("Try again!");
				
			} else {
				
				System.out.println();
				System.out.println("new Field in Column: " + tempColumn + " from " + game.getName(game.getActivePlayer()));
				game.add(tempColumn);
				System.out.println();
				System.out.println(game.toString());
				
			}
			
		} while (!game.getGameFinished());
		
		scanner.close();
		
	}

}
