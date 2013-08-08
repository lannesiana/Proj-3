
public class Move {
  private Block block;
	public enum direction{
		up, down, left, right
	}
	private direction dire;
	
	public Move(Block block, direction dire) {
		this.block = block;
		this.dire = dire;
	}
	
	public direction getDirection(){			
		return dire;
	}
	public String toString(){
		return block + " moves " + dire;
	}
	
	public Block getBlock() {
		return block;
	}
	public getMove() {
		
		//DO SOMETHING IN TRAY?

	}
	
	}
}
