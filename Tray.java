import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class Tray{
	final private int rowsTotal; //total number of rows
	final private int colsTotal; //total number of columns
	final private ArrayList<Block> blocks; //list of blocks on the tray
	final private int hash; //hashCode value
    final private int[][] board; //2-d array to represent cells occupied by blocks
    private ArrayList<Move> moveHistory = new ArrayList<Move>(); //tracks the history of moves already made
    public boolean debug = false;


    public Tray(int rows, int columns, ArrayList<Block> blocks,
    		ArrayList<Move> routeHistory) {
    	this.rowsTotal = rows;
    	this.colsTotal = columns;
    	this.blocks = blocks;
        sortBlocks(); //allows tray states to be easily comparable
        this.moveHistory = routeHistory;
        board = new int[rows][columns];
        this.fillBoard();   
    	hash = calculateDaHashCode(); 
    }


    //implemented a compareTo() method for blocks, so blocks are sorted first by rows, then by columns
    private void sortBlocks(){
        Collections.sort(blocks);
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
    
    public ArrayList<Block> getBlocks(){
    	return this.blocks;
    }

    //hash value determined by every cell in board, multiplied by a prime
    private int calculateDaHashCode(){
    	int prime = 31;
    	int result = 1;
    	for (int r = 0; r < rowsTotal; r++) {
			for (int c = 0; c < colsTotal; c++) {
				result = prime * result + board[r][c];
			}
    	}
    	return result; 
    }

    //overrides hash function, hashcode from calculateDaHashCode();
    public int hashCode(){
    	return hash; 
        
    }

    //primarily for debugging purposes. Returns a visual representation of blocks on tray as demonstrated in fillBoard();
    public String boardToString(){
        String s = "";
        for (int r = 0; r < rowsTotal; r++){
            for (int c = 0; c < colsTotal; c++){
                s += board[r][c];
                if ((c + 1) % 4 == 0){
                	s += "\\";
                }
            }
        }
        return s;
    }
    
    //checks if goal is reached by comparing block lists.
    //overrode block.equals() method for this
    public boolean goalReached(List<Block> goals){
    	return blocks.containsAll(goals);
    	
    }

    public void printMoveHistory(){
        if (!(moveHistory == null)){
            for (int i = 0; i < moveHistory.size(); i++){
                System.out.println(moveHistory.get(i).toString());
            }
        } 
    }
        
    //equal if blocks laid out the same way on trays being compared.
    //Considers case where 2 identical blocks are swapped to be equal.
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj.getClass() == getClass()))
            return false;
        Tray other = (Tray)obj;
        if (this.boardToString().equals(other.boardToString()))
            return true;
        if (blocks == null) {
            if (other.blocks != null)
                return false;
        } else if (!blocks.equals(other.blocks))
            return false;
        return true;
    }

    public Tray createTrayAfterMove(Block b, Direction dir) throws Exception{
        //make copy of blocks and moveHistory
        //access the block in blocksCopy and mutate it by "moving". (may have to create new block in case pointers still connect to original blocks list.)
        //create Move and add it to moveHistory
        //use tray constructor with new block list and new history of moves
    	ArrayList<Block> newBlocks = new ArrayList<Block>();
    	ArrayList<Move> newMoveHistory = new ArrayList<Move>();
    	newBlocks = (ArrayList<Block>) blocks.clone();
        if (moveHistory != null){
            newMoveHistory = (ArrayList<Move>) moveHistory.clone();
        }
    	Move change = new Move(b, dir);
        Block newBlock = b.createBlockAfterMove(dir);
    	
    	for (int i = 0; i < blocks.size(); i++){
    		if (newBlocks.get(i) == b){
    			newBlocks.set(i, newBlock);
    		}
    	}
        newMoveHistory.add(new Move(b, dir));
        if (debug){
            System.out.println("MoveHistory length: " + newMoveHistory.size());
            System.out.println("MOVE: " + change.cleanToString());
            for (int i = 0; i<newMoveHistory.size(); i++)
                System.out.println("move: " + newMoveHistory.get(i).cleanToString());
            for (int i = 0; i < newBlocks.size(); i++) //MOAR DEBUGGING
            System.out.println(newBlocks.get(i).toString()); //AND MOARR
        }   
    	Tray blocksCopy = new Tray(rowsTotal, colsTotal, newBlocks, newMoveHistory);
    	return blocksCopy;
    }


    //returns a list of all possible moves of every block on the current tray.
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

    //returns True if a block can move up
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


    //returns true if a given block can move down
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


    //returns true if a given block can move right
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


    //returns true if a given block can move left
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

   
    //checks for overlapping blocks
    //checks to see block values are non-negative
    //makes sure blocks are properly in-bounds
    public boolean isOK(){		
	//no overlapping Blocks 	
    	for (int i = 0; i < this.blocks.size(); i++){
            Block block1 = blocks.get(i);
            for (int k = 0; k < this.blocks.size(); k++){
            	Block block2 = blocks.get(k);
            
            	if (!(block1 == blocks.get(k))){ //so block is not compared to itself
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
    		
    		if (block1.getBRC() > (this.colsTotal - 1) || block1.getULC() < 0 || block1.getULR()<0 || (block1.getBRR() > this.rowsTotal -1))
    			return false;     			        
		}
		return true; 

}
}
