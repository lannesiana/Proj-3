import java.util.ArrayList;

public class Tray{
    final private int rowsTotal;
	final private int colsTotal;
	final private ArrayList<Block> blocks;
	final private int hash;
    final private int[][] board;
    

    public Tray(int rows, int columns, ArrayList<Block> blocks,
			ArrayList<Move> routeHistory) {
    	this.rowsTotal = rows;
    	this.colsTotal = columns;
    	this.blocks = blocks;
    	hash = calculateDaHashCode(); 
        board = new int[rows][columns];
        fillBoard();   
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
                    board[r][c] = i;
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
    public String toString(){
        
        
    }
    public boolean equals(Object obj) {
    	//bunches of if statements
        
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


}
