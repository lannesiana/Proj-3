
public class Move {
	private Block block;
	public enum Direction{
		up, down, left, right
	}
	private Direction dir;
	
	public Move(Block block, Direction dir) {
		this.block = block;
		this.dir = dir;
	}
	
	public Direction getDirection(){			
		return dir;
	}
	public String cleanToString(){ //returns a string version not according to specs
		return block + " moves " + dir;
	}

	public String toString(){ //returns a move according to specs

	}
	
	public Block getBlock() {
		return block;
	}
	public Move getMove() {
		
		//DO SOMETHING IN TRAY?

	}
	
}
