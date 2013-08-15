import java.util.List;

//represents a block on a tray
public class Block implements Comparable<Block>{
    private int upLeftRow; //upper-left row
    private int upLeftColumn; //upper-left column
    private int botRightRow; //bottom-right row
    private int botRightColumn; //bottom-right Column
    private int hash; //hashCode value
    
    public Block(int upLeftRow, int upLeftColumn, int botRightRow, int botRightColumn){
        this.upLeftRow = upLeftRow;
        this.upLeftColumn = upLeftColumn;
        this.botRightRow = botRightRow;
        this.botRightColumn = botRightColumn;
        hash = getHash(); //calculates the hash code
    }

    private int getHash(){
        final int prime = 31;
        int result = 1;
        result = prime * result + upLeftRow;
        result = prime * result + upLeftColumn;
        result = prime * result + botRightRow;
        result = prime * result + botRightRow;
        return result;
    }

    //overrides obj.hashCode()
    public int hashCode(){
        return hash;
    }

   //returns upperLeftRow
    public int getULR(){
        return this.upLeftRow;
    }

    //returns upperLeftcolumn of the block
    public int getULC(){
        return this.upLeftColumn;
    }

    //returns the BottomRightRow
    public int getBRR(){
        return this.botRightRow;
    }

    //returns the BottomRightColumn
    public int getBRC(){
        return this.botRightColumn;
    }

    //returns the block's coordinates (upperLeftRow, UpperLeftColumn, bottomRightRow, bottomRightcolumn) as an array.
    public int[] getBlockCoords(){
        int[] coords = new int[4];
        coords[0] = upLeftRow;
        coords[1] = upLeftColumn;
        coords[2] = botRightRow;
        coords[3] = botRightColumn;
        return coords;

    }
    
    //Creates a new block with updated coordinates after a "move". Original block is not changed.
    public Block createBlockAfterMove(Direction direction) throws Exception{
        Block block;
        if (direction.equals(Direction.up)){ 
                int ulr = upLeftRow - 1;
                int brr = botRightRow - 1;
                block = new Block(ulr, upLeftColumn, brr, botRightColumn);

        }
        else if (direction.equals(Direction.down)){
                int ulr = upLeftRow + 1;
                int brr = botRightRow + 1;
                block = new Block(ulr, upLeftColumn, brr, botRightColumn);
        }
        else if (direction.equals(Direction.left)){
                int ulc = upLeftColumn - 1;
                int brc = botRightColumn - 1;
                block = new Block(upLeftRow, ulc, botRightRow, brc);
        }
        else if (direction.equals(Direction.right)){
                int ulc = upLeftColumn + 1;
                int brc = botRightColumn + 1;
                block = new Block(upLeftRow, ulc, botRightRow, brc);
        }
        else
            throw new Exception("not a direction");
        return block;
    }

    //overrides Object.equals(); Takes into consideration 2 blocks of the same size are equal.
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != getClass())
            return false;
        Block other = (Block) obj;
        if (!(other.getULR() == upLeftRow && other.getULC() == upLeftColumn && other.getBRR() == botRightRow && other.getBRC() == botRightColumn))
            return false;
        return true;
    }

    //To sort the blocks first by rows, then columns.

    public int compareTo(Block b){
        if (this == b)
            return 0;
        if (this.getULR() != b.getULR())
            return this.getULR() - b.getULR();
        if (this.getULC() != b.getULC())
            return this.getULC() - b.getULC();
        if (this.getBRR() != b.getBRR())
            return this.getBRR() - b.getBRR();
        if (this.getBRC() != b.getBRC())
            return this.getBRC() - b.getBRC(); 
        return 0;
    }

    //sorts blocks by rows then columns

    
    public String toString(){
    	return "Block[" + upLeftRow + " " + upLeftColumn + " " + botRightRow + " " + botRightColumn + "]";
    }

    //the invariants regarding the particular location of the blocks is checked in Tray.
    //Checks to make sure the coordinates of the bottom-right corner is larger than those of the upperLeft corner.
    public boolean isOK(){
        return ((this.botRightRow >= this.upLeftRow) && (this.botRightColumn >= this.upLeftColumn));
    }

}
