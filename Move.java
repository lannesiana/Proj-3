
public class Move {
  private Block block;
	public enum direction{
		up, down, left, right
	}
	private direction dir;
	
	public Move(Block block, direction dir) {
		this.block = block;
		this.dir = dir;
	}
	
	public direction getDirection(){			
		return dir;
	}
	public String toString(){
		return block + " moves " + dir;
	}
	
	public Block getBlock() {
		return block;
	}
	public getMove() {
		
		//DO SOMETHING IN TRAY?

	}
	
	}
}
