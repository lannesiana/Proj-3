
public class Move {
	private String moveString = "";
	private Block block;
	private Direction dir;
	
	public Move(Block block, Direction dir) {
		this.block = block;
		this.dir = dir;
		String destination = getNewCoords(dir);
		String source = block.getULR() + " " + block.getULC() + " ";
		moveString = source + destination;

	}
	
	//returns the destination of the upper corner of the block
	private String getNewCoords(Direction dir){
		String s = "";
		if (dir == Direction.up)
			s = (block.getULR() - 1) + " " + block.getULC();
		if (dir == Direction.down)
			s = (block.getULR() + 1) + " " + block.getULC();
		if (dir == Direction.left)
			s = (block.getULR() + " " + (block.getULC() - 1));
		if (dir == Direction.right)
			s = (block.getULR() + " " + (block.getULC() + 1));
		return s;
	}
	public Direction getDirection(){			
		return dir;
	}
	public String cleanToString(){ //returns a string version not according to specs
		return block + " moves " + dir;
	}

	//returns a move according to specs in the form
	// 1 1 0 1: first 2 nums are upLeft of a block, latter 2 are destination coordinates of the upLeft corner of the block.
	public String toString(){ 
		return moveString;
	}
	
	public Block getBlock() {
		return block;
	}
	public Move getMove() {
		
		//DO SOMETHING IN TRAY?

	}
	
	public boolean equals(){
		//is this even necessary?
	}
	
}
