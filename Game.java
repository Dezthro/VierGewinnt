public class Game {

	public class Field {
		
		// Variables
		private boolean isUsed;
		private boolean ownByFirst;
		private int row;
		private int column;
		
		// Constructor
		public Field(int pRow, int pColumn) {
			
			isUsed = false;
			ownByFirst = false;
			this.row = pRow;
			this.column = pColumn;
			
		}
		
		// Getter & Setter
		public boolean getIsUsed() {
			
			return isUsed;
			
		}
		
		public boolean getOwnByFirst() {
			
			return ownByFirst;
			
		}
		
		public void setIsUsed(boolean pIsUsed) {
			
			this.isUsed = pIsUsed;
			
		}
		
		public void setOwnByFirst(boolean pOwnByFirst) {
			
			this.ownByFirst = pOwnByFirst;
			
		}
		
		public int getRow() {
			
			return this.row;
			
		}
		
		public int getColumn() {
			
			return this.column;
			
		}
		
		// Methods
		public void reset() {
			
			this.isUsed = false;
			this.ownByFirst = false;
			
		}
		
	}
	
	// Variables
	private String nameA;
	private String nameB;
	private byte scoreA;
	private byte scoreB;
	private boolean firstActive;
	private boolean gameFinished;
	private boolean wonByFirst;
	private Field[][] gameField;
	private Field[] playableFields;
	private Field[] winnersField;
	
	// Constructor
	public Game() {
		
		nameA = "Player A";
		nameB = "Player B";
		firstActive = true;
		gameFinished = false;
		wonByFirst = false;
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
	
	private boolean isInGameField(int pRow, int pColumn) {
		
		return (pRow > -1 && pRow < 7 && pColumn > -1 && pColumn < 8);
		
	}
	
	public boolean getGameFinished() {
		
		return gameFinished;
		
	}
	
	public boolean getWonByFirst() {
		
		return wonByFirst;
		
	}
	
	public boolean getFirstActive() {
		
		return firstActive;
		
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
	
	private void nextPlayer() {
		
		if(this.getFirstActive()) {
			
			firstActive = false;
			
		} else {
			
			firstActive = true;
			
		}
		
	}
	
	private void changeGameFinished() {
		
		if(this.getGameFinished()) {
			
			gameFinished = false;
			
		} else {
			
			gameFinished = true;
			
		}
		
	}
	
	// Methods
	public void add(int pColumn) {
		
		if(!this.getGameFinished() && playableFields[pColumn] != null) {
			
			int tempRow = playableFields[pColumn].getRow();
			playableFields[pColumn].setIsUsed(true);
			playableFields[pColumn].setOwnByFirst(this.getFirstActive());
			
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
	
	public boolean checkGame(int pRow, int pColumn) {
		
		if(!this.getGameFinished()) {
			
			boolean gameFull = true;
			
			outerloop:
			for(int i = 0; i < gameField.length; i++) {
			
				for(int j = 0; j < gameField[0].length; j++) {
				
					if(!gameField[i][j].getIsUsed()) {
						
						gameFull = false;
						break outerloop;
						
					}
			
				}
				
			}
			
			if(!gameFull) {
			
				// possiblities for winning
				Field[] rightUp = new Field[4];
				Field[] horizontal = new Field[4];
				Field[] rightDown = new Field[4];
				Field[] vertical = new Field[4];
				
				// variables for a possibility winning row with counter
				boolean rightUpRow = false;
				boolean horizontalRow = false;
				boolean rightDownRow = false;
				boolean verticalRow = false;
				int counterRightUp = 0;
				int counterHorizontal = 0;
				int counterRightDown = 0;
				int counterVertical = 0;
				
				// testing for a win
				for(int i = 3; i < -4; i--) {
					
					// diagonal right up
					if(this.isInGameField((pRow - i), (pColumn + i))) {
						
						Field temp = this.getField((pRow - i), (pColumn + i));
						
						if(temp.getIsUsed()) {
							
							if(temp.getOwnByFirst() == this.getFirstActive()) {
								
								if(rightUpRow) {
									
									counterRightUp++;
									
								} else {
									
									counterRightUp = 0;
									
								}
								
								rightUp[counterRightUp] = temp;
								
								rightUpRow = true;
								
							} else {
								
								rightUpRow = false;
								
							}
							
						}
						
					}
					
					// horizontal
					if(this.isInGameField((pRow - i), (pColumn))) {
						
						Field temp = this.getField((pRow - i), (pColumn));
						
						if(temp.getIsUsed()) {
							
							if(temp.getOwnByFirst() == this.getFirstActive()) {
								
								if(horizontalRow) {
									
									counterHorizontal++;
									
								} else {
									
									counterHorizontal = 0;
									
								}
								
								horizontal[counterHorizontal] = temp;
								
								horizontalRow = true;
								
							} else {
								
								horizontalRow = false;
								
							}
							
						}
						
					}
					
					// diagonal right down
					if(this.isInGameField((pRow - i), (pColumn - i))) {
						
						Field temp = this.getField((pRow - i), (pColumn - i));
						
						if(temp.getIsUsed()) {
							
							if(temp.getOwnByFirst() == this.getFirstActive()) {
								
								if(rightDownRow) {
									
									counterRightDown++;
									
								} else {
									
									counterRightDown = 0;
									
								}
								
								rightDown[counterRightDown] = temp;
								
								rightDownRow = true;
								
							} else {
								
								rightDownRow = false;
								
							}
							
						}
						
					}
					
					// vertical
					if(this.isInGameField((pRow), (pColumn - i))) {
						
						Field temp = this.getField((pRow + i), (pColumn - i));
						
						if(temp.getIsUsed()) {
							
							if(temp.getOwnByFirst() == this.getFirstActive()) {
								
								if(verticalRow) {
									
									counterVertical++;
									
								} else {
									
									counterVertical = 0;
									
								}
								
								vertical[counterVertical] = temp;
								
								verticalRow = true;
								
							} else {
								
								verticalRow = false;
								
							}
							
						}
						
					}
					
					if(counterRightUp == 3) {
						
						this.changeGameFinished();
						winnersField = rightUp;
						wonByFirst = this.getFirstActive();
						
						if(this.getWonByFirst()) {
							
							this.increaseScoreA();
							
						} else {
							
							this.increaseScoreB();
							
						}
						
						break;
						
					}
					
					if(counterHorizontal == 3) {
						
						this.changeGameFinished();
						winnersField = horizontal;
						wonByFirst = this.getFirstActive();
						
						if(this.getWonByFirst()) {
							
							this.increaseScoreA();
							
						} else {
							
							this.increaseScoreB();
							
						}
						
						break;
						
					}
					
					if(counterRightDown == 3) {
						
						this.changeGameFinished();
						winnersField = rightDown;
						wonByFirst = this.getFirstActive();
						
						if(this.getWonByFirst()) {
							
							this.increaseScoreA();
							
						} else {
							
							this.increaseScoreB();
							
						}
						
						break;
						
					}
					
					if(counterVertical == 3) {
						
						this.changeGameFinished();
						winnersField = vertical;
						wonByFirst = this.getFirstActive();
						
						if(this.getWonByFirst()) {
							
							this.increaseScoreA();
							
						} else {
							
							this.increaseScoreB();
							
						}
						
						break;
						
					}
					
				}
				
			} else {
				
				this.changeGameFinished();
				
			}
				
				return this.getGameFinished();
			
		} else {
			
			return false;
			
		}
		
	}
	
	// reset-methods
	public void resetGameField() {
		
		firstActive = true;
		this.changeGameFinished();
		
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
		nameA = "PlayerA";
		nameB = "PlayerB";
		gameFinished = false;
		wonByFirst = false;
		
	}

	public String toString() {
		
		String retString = "Player A named '" + this.getNameA() + "': x\nPlayer B named '" + this.getNameB() + "': o \n\n";
		
		for(int i = 0; i < gameField.length; i++) {
			
			retString = retString + i + " ";
			
			for(int j = 0; j < gameField[0].length; j++) {
				
				if(gameField[i][j].getIsUsed()) {
					
					String tempString = "";
					
					if(gameField[i][j].getOwnByFirst()) {
						
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
			
			retString = retString + "\nThe Game was won by Player ";
			
			if(this.getWonByFirst()) {
				
				retString = retString + "A named: '" + this.getNameA() + "'!";
				
			} else {
				
				retString = retString + "B named: '" + this.getNameB() + "'!";
				
			}
			
		}
		
		return retString;
		
	}
	
}
