import java.util.ArrayList;

public class Tray{
	final private int rowsTotal;
	final private int colsTotal;
	final private ArrayList<Block> blocks;
	final private int hash;
    public int[][] board;
    final private int[][] layout;
    

    public Tray(int rows, int columns, ArrayList<Block> blocks,
			ArrayList<Move> routeHistory) {
    	this.rowsTotal = rows;
    	this.colsTotal = columns;
    	this.blocks = blocks;
    	hash = calculateDaHashCode(); 
  
        board = new int[rows][columns];
        
        
        
    }
    private int calculateDaHashCode(){
    	int prime = 47;
    	int result = 1;
    	for (int r = 0; r < rowsTotal; r++) {
			for (int c = 0; c < colsTotal; c++) {
				result = prime * result + layout[r][c];
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
}
