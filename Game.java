public class Game {

	public class Field {
		
		// Variables
		private byte ownBy;
		private int row;
		private int column;
		
		// Constructor
		public Field(int pRow, int pColumn) {
			ownBy = 0;
			this.row = pRow;
			this.column = pColumn;
			
		}
		
		// Getter & Setter
		public byte getOwner() {
			
			return this.ownBy;
			
		}
		
		public void setOwner(byte pOwner) {
			
			if(pOwner >= 0 && pOwner < 3) {
			
				this.ownBy = pOwner;
				
			}
			
		}
		
		public int getRow() {
			
			return this.row;
			
		}
		
		public int getColumn() {
			
			return this.column;
			
		}
		
		// Methods
		public void reset() {
			
			this.ownBy = 0;
			
		}
		
	}
	
	// Variables
	private String nameA;
	private String nameB;
	private byte scoreA;
	private byte scoreB;
	private byte gameStage;
	private byte activePlayer;
	private Field[][] gameField;
	private Field[] playableFields;
	private Field[] winnersField;
	
	// Constructor
	public Game() {
		
		nameA = "Player A";
		nameB = "Player B";
		activePlayer = 1;
		gameStage = 0;
		winnersField = new Field[4];
		
		gameField = new Field[6][7];
		for(int i = 0; i < gameField.length; i++) {
			
			for(int j = 0; j < gameField[0].length; j++) {
				
				gameField[i][j] = new Field(i, j);
				
			}
			
		}
		
		playableFields = new Field[7];
		for(int i = 0; i < playableFields.length; i++) {
			
			playableFields[i] = gameField[5][i];
			
		}
		
	}
	
	// Getter & Setter
	private void increaseScoreA() {
		
		if(!this.getGameFinished()) {
			
			scoreA++;
			
		}
		
	}
	
	private void increaseScoreB() {
		
		if(!this.getGameFinished()) {
			
			scoreB++;
			
		}
		
	}
	
	public byte getScoreA() {
		
		return scoreA;
		
	}
	
	public byte getScoreB() {
		
		return scoreB;
		
	}
	
	public byte getScore(byte pActivePlayer) {
		
		if(pActivePlayer == 1) {
			
			return scoreA;
			
		} else {
			
			return scoreB;
			
		}
		
	}
	
	public byte getWinner() {
		
		return (gameStage > 0 && gameStage < 3) ? gameStage : 0;
		
	}
	
	public byte getActivePlayer() {
		
		return activePlayer;
		
	}
	
	public byte getGameStage() {
		
		return gameStage;
		
	}
	
	public void setNameA(String pNameA) {
		
		this.nameA = pNameA;
		
	}
	
	public void setNameB(String pNameB) {
		
		this.nameB = pNameB;
		
	}
	
	public String getNameA() {
		
		return nameA;
		
	}
	
	public String getNameB() {
		
		return nameB;
		
	}
	
	public String getName(byte pActivePlayer) {
		
		if(pActivePlayer == 1) {
			
			return nameA;
			
		} else {
			
			return nameB;
			
		}
		
	}
	
	private boolean isInGameField(int pRow, int pColumn) {
		
		return (pRow >= 0 && pRow < 6 && pColumn >= 0 && pColumn < 7);
		
	}
	
	public boolean getGameFinished() {
		
		return (this.gameStage > 0);
		
	}
	
	public Field getField(int pRow, int pColumn) {
		
		return gameField[pRow][pColumn];
		
	}
	
	public Field[] getWinnersField() {
		
		return (this.getGameFinished()) ? winnersField : null;
		
	}
	
	private boolean isInWinnersField(Field pField) {
		
		if(this.getGameFinished()) {
		
			Field[] tempFields= this.getWinnersField();
			
			return (pField == tempFields[0] || pField == tempFields[1] || pField == tempFields[2] || pField == tempFields[3]);
		
		} else {
			
			return false;
			
		}
		
	}
	
	private void setGameStage(int pStage) {
		
		if(pStage >= 0 && pStage < 4) {
			
			this.gameStage = (byte) pStage;
			
		}
		
	}
	
	// Methods
	private void nextPlayer() {
		
		if(this.getActivePlayer() == 1) {
			
			activePlayer = 2;
			
		} else {
			
			activePlayer = 1;
			
		}
		
	}
	
	public void add(int pColumn) {
		
		if(!this.getGameFinished() && playableFields[pColumn] != null) {
			
			int tempRow = playableFields[pColumn].getRow();
			playableFields[pColumn].setOwner(this.getActivePlayer());
			
			this.checkGame(tempRow, pColumn);
			
			if(!this.getGameFinished()) {
				
				this.nextPlayer();
			
				if(this.isInGameField((tempRow - 1), pColumn)) {
			
					playableFields[pColumn] = gameField[tempRow - 1][pColumn];
				
				} else {
					
					playableFields[pColumn] = null;
					
				}
			
			}
			
		}
		
	}
	
	public byte checkGame(int pRow, int pColumn) {
		
		if(!this.getGameFinished()) {
			
			outerloop:
			for(int i = 0; i < gameField.length; i++) {
			
				for(int j = 0; j < gameField[0].length; j++) {
				
					if(gameField[i][j].getOwner() == 0) {
						
						this.setGameStage(3);
						break outerloop;
						
					}
			
				}
				
			}
			
			// possiblities for winning
			Field[] aRow = new Field[4];
			Field[] bRow = new Field[4];
			
			// variables for a possibility winning row with counter
			boolean aHasRow = false;
			boolean bHasRow = false;
			int counterA = 0;
			int counterB = 0;
			
			// variables for calculating the direction of testing
			int rowMove = 0;
			int columnMove = 0;
			
			// base fields for testing in every direction
			Field[][] testFor = {{gameField[3][0], gameField[4][0], gameField[5][0], gameField[5][1], gameField[5][2], gameField[5][3]},
								 {gameField[0][0], gameField[1][0], gameField[2][0], gameField[3][0], gameField[4][0], gameField[5][0]},
								 {gameField[2][0], gameField[1][0], gameField[0][0], gameField[0][1], gameField[0][2], gameField[0][3]},
								 {gameField[0][0], gameField[0][1], gameField[0][2], gameField[0][3], gameField[0][4], gameField[0][5], gameField[0][6]}};
			
			outerloop:
			for(int i = 0; i < testFor.length; ) {
			
				switch(i) {
					
					// diagonal right up
					case 0:
						rowMove = -1;
						columnMove = +1;
						break;
						
					// horizontal
					case 1:
						rowMove = 0;
						columnMove = +1;
						break;
						
					// diagonal right down
					case 2:
						rowMove = +1;
						columnMove = +1;
						break;
						
					// vertical
					case 3:
						rowMove = +1;
						columnMove = 0;
						break;
					
				}
			
				for(int j = 0; j < testFor[i].length; j++) {
					
					int counter = 0;
					Field temp;
					
					while(this.isInGameField( (testFor[i][j].getRow() + (rowMove * counter)), (testFor[i][j].getColumn() + (columnMove * counter)) )) {
						
						System.out.print((testFor[i][j].getRow() + (rowMove * counter)) + " " + (testFor[i][j].getColumn() + (columnMove * counter)) + ", ");
						
						temp = this.getField( (testFor[i][j].getRow() + (rowMove * counter)), (testFor[i][j].getColumn() + (columnMove * counter)) );
						
						switch(temp.getOwner()) {
							
							case 1:
								if(aHasRow) {
									
									counterA++;
									
								} else {
									
									counterA = 0;
									
								}
								aRow[counterA] = temp;
								aHasRow = true;
								bHasRow = false;							
								break;
								
							case 2:
							if(bHasRow) {
									
									counterB++;
									
								} else {
									
									counterB = 0;
									
								}
								bRow[counterB] = temp;
								bHasRow = true;
								aHasRow = false;
								break;
							
							case 0: default:
								aHasRow = false;
								bHasRow = false;
								counterA = 0;
								counterB = 0;
								break;
							
						}
						
						counter++;
						
					}
				
					System.out.println("");
					
					if(counterA == 3) {
						
						this.setGameStage(1);
						break outerloop;
						
					} else if(counterB == 3) {
						
						this.setGameStage(2);
						break outerloop;
						
					}
					
				}
			
			}
			
			return this.getGameStage();
			
		} else {
			
			return this.getGameStage();
			
		}
		
	}
	
	// reset-methods
	public void resetGameField() {
		
		switch(this.getGameStage()) {
			
			case 1:
				activePlayer = 2;
				break;
			
			case 2: default:
				activePlayer = 1;
				break;
			
		}
		
		
		this.setGameStage(0);
		
		for(int i = 0; i < gameField.length; i++) {
			
			for(int j = 0; j < gameField[0].length; j++) {
				
				gameField[i][j].reset();
				
			}
			
		}
		
		
		for(int i = 0; i < playableFields.length; i++) {
			
			playableFields[i] = gameField[5][i];
			
		}
		
	}
	
	public void resetGame() {
		
		this.resetGameField();
		scoreA = 0;
		scoreB = 0;
		gameStage = 0;
		activePlayer = 0;
		nameA = "PlayerA";
		nameB = "PlayerB";
		
	}

	public String toString() {
		
		String retString = "Player A named '" + this.getNameA() + "': x\nPlayer B named '" + this.getNameB() + "': o \n\n";
		
		for(int i = 0; i < gameField.length; i++) {
			
			retString = retString + i + " ";
			
			for(int j = 0; j < gameField[0].length; j++) {
				
				if(gameField[i][j].getOwner() != 0) {
					
					String tempString = "";
					
					if(gameField[i][j].getOwner() == 1) {
						
						tempString = "x";
						
					} else {
						
						tempString = "o";
						
					}
					
					if(this.isInWinnersField(gameField[i][j])) {
						
						tempString = tempString.toUpperCase();
						
					}
					
					retString = retString + tempString + " ";
					
				} else {
					
					retString = retString + "  ";
					
				}
				
			}
			
			retString = retString + "\n";
			
		}
		
		retString = retString + "  0 1 2 3 4 5 6\n\n";
		
		if(this.getGameFinished()) {
			
			retString = retString + "The Game was won by ";
			
			switch(this.getGameStage()) {
				
				case 1:
					retString = retString + "Player A named: '" + this.getNameA() + "'!";
					break;
				
				case 2:
					retString = retString + "Player B named: '" + this.getNameB() + "'!";
					break;
					
				case 3:
					retString = retString + "no one! You played too long!";
					break;
					
			}
			
		}
		
		return retString;
		
	}
	
}
