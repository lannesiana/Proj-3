import java.util.ArrayList;
import java.util.List;

public class Tray{
	final private int rowsTotal;
	final private int colsTotal;
	final private ArrayList<Block> blocks;
	final private int hash;
    final private int[][] board;
    final private ArrayList<Move> moveHistory;


    public Tray(int rows, int columns, ArrayList<Block> blocks,
    		ArrayList<Move> routeHistory) {
    	this.rowsTotal = rows;
    	this.colsTotal = columns;
    	this.blocks = blocks;
        this.moveHistory = routeHistory;
        board = new int[rows][columns];
        fillBoard();   
    	hash = calculateDaHashCode(); 
    }

    //for a simpler layout. In the tray, a block will be represented by its index in the arraylist.
    //a filled board may look like:
    //1 1 0 2
    //1 1 0 2
    //3 3 3 0
    //groups of similar numbers represent one block, and 0s represent empty spaces
    private void fillBoard(){
        for (int i = 0; i < this.blocks.size(); i++){
            Block block = blocks.get(i);
            for (int r = block.getULR(); r <= block.getBRR(); r++){
                for (int c = block.getULC(); c <= block.getBRC(); c++){
                    if (r >= rowsTotal || c >= colsTotal){
                        throw new RuntimeException("rows/columns are out of bounds");
                    }
                    if (board[r][c] != 0){
                        throw new RuntimeException("2 blocks overlap");
                    }
                    board[r][c] = i+1;                    
                }
            }
        }
    }
    private int calculateDaHashCode(){
    	int prime = 47;
    	int result = 1;
    	for (int r = 0; r < rowsTotal; r++) {
			for (int c = 0; c < colsTotal; c++) {
				result = prime * result + board[r][c];
			}
    	}
    	return result; 
    }

    public int hashCode(){
    	return hash; 
        
    }
    public String boardToString(){
        String s = "";
        for (int r = 0; r < rowsTotal; r++){
            for (int c = 0; c < colsTotal; c++){
                s += board[r][c];
            }
        }
        return s;
    }
    
    public boolean goalReached(List<Block> goals){
    	return blocks.containsAll(goals);
    	
    }

    public void printMoveHistory(){
        for (int i = 0; i < moveHistory.size(); i++){
            System.out.println(moveHistory.get(i).toString());
        }

    }
        
        
    public boolean equals(Object obj) {
        if (!(obj.getClass() == getClass()))
            return false;
        Tray other = (Tray)obj;
        if (!(this.boardToString().equals(other.boardToString())))
            return false;
        return true;
    }

    public Tray createTrayAfterMove(Block b, Direction dir){
        //make copy of blocks and moveHistory
        //access the block in blocksCopy and mutate it by "moving". (may have to create new block in case pointers still connect to original blocks list.)
        //create Move and add it to moveHistory
        //use tray constructor with new block list and new history of moves
    	int newRow = this.rowsTotal;
    	int newCols = this.colsTotal;
    	ArrayList newBlocks = this.blocks;
    	ArrayList newMoveHistory = this.moveHistory;
    	Move change = new Move(b, dir);
    	
    	for (int i = 0; i < newBlocks.size(); i++){
    		if (newBlocks.get(i) == b){
    			newBlocks.set(i, change.getBlock());
    		}
    	}
    	newMoveHistory.add(change);
    	Tray blocksCopy = new Tray(newRow, newCols, newBlocks, newMoveHistory);
    	return blocksCopy;
    }

    public ArrayList<Move> findAllMoves(){
        ArrayList<Move> moves = new ArrayList<Move>();
        for (int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            if (canMoveUp(block))
                moves.add(new Move(block, Direction.up));
            if (canMoveDown(block))
                moves.add(new Move(block, Direction.down));
            if (canMoveLeft(block))
                moves.add(new Move(block, Direction.left));
            if (canMoveRight(block))
                moves.add(new Move(block, Direction.right));
        }
        return moves;
    }


    public boolean canMoveUp(Block block){
        if (block.getULR() > 0){
            for (int i = block.getULC(); i <= block.getBRC(); i++){
                if (!(board[block.getULR() - 1][i] == 0))
                    return false;
            }
            return true; 
        }
        return false;
    }



    public boolean canMoveDown(Block block){
        if (block.getBRR() < this.rowsTotal-1){
            for (int i = block.getULC(); i <= block.getBRC(); i++){
                if (!(board[block.getBRR() + 1][i] == 0))
                    return false;
            } 
            return true;
        }
        return false;
    }



    public boolean canMoveRight(Block block){
        if (block.getBRC() < this.colsTotal-1){
            for (int i = block.getULR(); i <= block.getBRR(); i++){
                if (!(board[i][block.getBRC() + 1] == 0))
                    return false;
            } 
            return true;
        }
        return false;
    }



    public boolean canMoveLeft(Block block){
        if (block.getULC() > 0){
            for (int i = block.getULR(); i <= block.getBRR(); i++){
                if (!(board[i][block.getULC() - 1] == 0))
                    return false;
            } 
            return true;
        }
        return false;
    }

   
    public boolean isOK(){		
	//overlapping Blocks
  	
    	for (int i = 0; i < this.blocks.size(); i++){
            Block block1 = blocks.get(i);
            for (int k = 0; k < this.blocks.size(); k++){
            	Block block2 = blocks.get(k);
            
            	if (!(block1 == blocks.get(k))){
            		if (block2.getULR() <= block1.getULR() && block2.getULR() >= block1.getBRR()){
            			if(block2.getULC() <= block1.getULC() && block2.getULC() >= block1.getBRC())
            				return false; 
            		
            	}
		}
		}
    	}
    
    	
	//non-negative Blocks
    	for (int i = 0; i < this.blocks.size(); i++) {  
    		Block block1 = blocks.get(i);
    		for(int k = 0; k <= 4; k++){    			
    			if (block1.getBlockCoords()[k] < 0)
   		 			return false; 
    	}
    	}
    	
        
    //blocks are in bounds
    	for (int i = 0; i < this.blocks.size(); i++) {
    		Block block1 = blocks.get(i); 
    		
    		if (block1.getBRC() > (this.rowsTotal - 1) || block1.getULC() < 0)
    			return false; 
    			
    		//brc > number rows false
    		//urc < 0 false
    	         	        


		}
		return true; 

}
}
