	/**
	 * A class constructor that inherits Players private members for human players
	 */ 
	class HumanPlayer extends Player{
		public HumanPlayer(){
		}
	
		public HumanPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	/**
	 * A class constructor that inherits HumanPlayers private members for random players
	 */ 
	class RandomPlayer extends HumanPlayer{
		public RandomPlayer(){
		}
	
		public RandomPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	/**
	 * A class constructor that inherits RandomPlayers private members for blocking players
	 */ 
	class BlockingPlayer extends RandomPlayer{
		public BlockingPlayer(){
		}
	
		public BlockingPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}
	
	/**
	 * A class constructor that inherits BlockingPlayers private members for smart players
	 */ 
	class SmartPlayer extends BlockingPlayer{
		public SmartPlayer(){
		}
	
		public SmartPlayer(String name, char mark, Board board) {
			super(name, mark, board);
		}
	}