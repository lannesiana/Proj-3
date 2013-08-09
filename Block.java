import java.util.ArrayList;
import java.util.List;

public class Block{
    private int upLeftRow;
    private int upLeftColumn;
    private int botRightRow;
    private int botRightColumn;
    final private int[] allBlocks = new int[4];
    private final String[] directions = {"up", "down", "left", "right"};
    
    //Anni added allBlocks
    
    public Block(int upLeftRow, int upLeftColumn, int botRightRow, int botRightColumn, int[] allBlocks){
        this.upLeftRow = upLeftRow;
        this.upLeftColumn = upLeftColumn;
        this.botRightRow = botRightRow;
        this.botRightColumn = botRightColumn;
        this.allBlocks[0] = upLeftRow;
        this.allBlocks[1] = upLeftColumn;
        this.allBlocks[2] = botRightRow;
        this.allBlocks[3] = botRightColumn;
        
        
        
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
    
    public int[] getallBlocks(){
        return this.allBlocks; 
    }
    
    
    
    //merely changes instance variables. will need method in tray to actually move board on layout (such as creating a new board?)
    public void move(String direction) throws Exception{ //gotta check if possible to move, a "canMove" method?
        if (direction.equals("up")){ //must check if okay to move in this direction first. in tray
                this.upLeftRow += 1;
                this.botRightRow += 1;
        }
        else if (direction.equals("down")){
                this.upLeftRow -= 1;
                this.botRightRow -= 1;
        }
        else if (direction.equals("left")){
                this.upLeftColumn -= 1;
                this.botRightColumn -= 1;
        }
        else if (direction.equals("right")){
                this.upLeftColumn += 1;
                this.botRightColumn += 1;
        }
        else
            throw new Exception("not a direction");
    }

    //overrides Object.equals();
    public boolean equals(Object obj){

    }

    //sorts blocks by rows then columns
    public boolean compareTo(Block b){

    }

    public String toString(){
        return "Block[" + upLeftRow + " " + upLeftColumn + " " + botRightRow + " " + botRightColumn + "]";
    }

    public String blockDimensions(){
        return (botRightRow - upLeftRow + 1) + "x" + (botRightColumn - upLeftColumn + 1);
    }

}
