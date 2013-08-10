import java.util.List;

public class Block{
    private int upLeftRow;
    private int upLeftColumn;
    private int botRightRow;
    private int botRightColumn;
    
    public Block(int upLeftRow, int upLeftColumn, int botRightRow, int botRightColumn){
        this.upLeftRow = upLeftRow;
        this.upLeftColumn = upLeftColumn;
        this.botRightRow = botRightRow;
        this.botRightColumn = botRightColumn;
        
        // do we want to put blocks into a hash? need hash code?
    }

    public int getULR(){
        return this.upLeftRow;
    }

    public int getULC(){
        return this.upLeftColumn;
    }

    public int getBRR(){
        return this.botRightRow;
    }

    public int getBRC(){
        return this.botRightColumn;
    }

    public int[] getBlockCoords(){
        int[] coords = new int[4];
        coords[0] = upLeftRow;
        coords[1] = upLeftColumn;
        coords[2] = botRightRow;
        coords[3] = botRightColumn;
        return coords;

    }
    
    //merely changes instance variables. will need method in tray to actually move board on layout (such as creating a new board?)
    public void move(Direction direction) throws Exception{ //gotta check if possible to move, a "canMove" method?
        if (direction.equals(Direction.up)){ //must check if okay to move in this direction first. in tray
                this.upLeftRow += 1;
                this.botRightRow += 1;
        }
        else if (direction.equals(Direction.down)){
                this.upLeftRow -= 1;
                this.botRightRow -= 1;
        }
        else if (direction.equals(Direction.left)){
                this.upLeftColumn -= 1;
                this.botRightColumn -= 1;
        }
        else if (direction.equals(Direction.right)){
                this.upLeftColumn += 1;
                this.botRightColumn += 1;
        }
        else
            throw new Exception("not a direction");
    }

    public Block createBlockAfterMove(Direction direction) throws Exception{
        Block block;
        if (direction.equals(Direction.up)){ //must check if okay to move in this direction first. in tray
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

    //overrides Object.equals();
    public boolean equals(Object obj){
        if (obj == null)
            return false;
        if (obj.getClass() != getClass())
            return false;
        Block other = (Block) obj;
        if (!(other.getULR() == upLeftRow && other.getULC() == upLeftColumn && other.getBRR() == botRightRow && other.getBRC() == botRightColumn))
            return false;
        return true;
    }

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

    public String cleanToString(){
        return "Block[" + upLeftRow + " " + upLeftColumn + " " + botRightRow + " " + botRightColumn + "]";
    }
    
    public String toString(){
        return "Block[" + upLeftRow + " " + upLeftColumn + " " + botRightRow + " " + " " + botRightColumn + "]";
    }

    public String blockDimensions(){
        return (botRightRow - upLeftRow + 1) + "x" + (botRightColumn - upLeftColumn + 1);
    }

    public boolean isOK(){
        return ((this.botRightRow >= this.upLeftRow) && (this.botRightColumn >= this.upLeftColumn));
    }

}
