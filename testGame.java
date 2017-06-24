import java.util.concurrent.ThreadLocalRandom;

public class testGame {

	public static void main(String[] args) {
		
		Game game = new Game();
		
		do {
			
			int tempColumn = ThreadLocalRandom.current().nextInt(7);
			System.out.println();
			System.out.println("new Field in Column: " + tempColumn + " from " + game.getName(game.getActivePlayer()));
			game.add(tempColumn);
			System.out.println();
			System.out.println(game.toString());
			
		} while (!game.getGameFinished());
		
	}

}
