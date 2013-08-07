import java.util.ArrayList;





public class Tray{
    final private int rowsTotal;
	final private int colsTotal;
	final private ArrayList<Block> blocks;
	final private int hash;
    public int[][] board;
    

    public Tray(int rows, int columns, ArrayList<Block> blocks,
			ArrayList<Move> routeHistory) {
    	this.rowsTotal = rows;
    	this.colsTotal = columns;
    	this.blocks = blocks;
    	hash = calculateDaHashCode(); 
  
        board = new int[rows][columns];
        
        
        
    }
    private int calculateDaHashCode(){
    	
    }

    public int hashCode(){
    	return hash; 
        
    }
    public String toString(){
        
        
    }
    public boolean equals(Object obj) {
    	//bunches of if statements
        
    }
}
