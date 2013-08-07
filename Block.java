public class Block{
    private int upLeftRow;
    private int upLeftColumn;
    private int botRightRow;
    private int botRightColumn;
    private final String[] directions = {"up", "down", "left", "right"};
    
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
    
    //merely changes instance variables. will need method in tray to actually move board on layout (such as creating a new board?)
    public void move(String direction){ //gotta check if possible to move, a "canMove" method?
        if (!directions.contains(direction))
            throw new Exception("not a direction");
        if (direction.equals("up"){ //must check if okay to move in this direction first. in tray
                this.upLeftRow += 1;
                this.botRightRow += 1;
        }
        else if (direction.equals("down"){
                this.upLeftRow -= 1;
                this.botRightRow -= 1;
        }
        else if (direction.equals("left"){
                this.upLeftColumn -= 1;
                this.botRightColumn -= 1;
        }
        else if (direction.equals("right")){
                this.upLeftColumn += 1;
                this.botRightColumn += 1;
        }
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
