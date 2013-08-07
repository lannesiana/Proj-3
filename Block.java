Public class Block{
    private int upLeftRow;
    private int upLeftColumn;
    private int botRightRow;
    private int botRightColumn;
    private final String[] directions = new String["up", "down", "left", "right"];
    
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
        return this.upLeftCorner;
    }

    public int getBRR(){
        return this.bottomRightRow;
    }

    public int getBRC(){
        return this.bottomRightColumn;
    }
    
    //merely changes instance variables. will need method in tray to actually move board on layout (such as creating a new board?)
    public void move(String direction) throws Exception{ //gotta check if possible to move, a "canMove" method?
        if (!directions.contains(direction))
          throw (new Exception e{});
        if (direction.equals("up"){
            if (Tray.canMoveUP(this)){
                this.upLeftRow += 1;
                this.botRightRow += 1;
            }
        }
        else if (direction.equals("down"){
            if (Tray.canMoveDown(this)){
                this.upLeftRow -= 1;
                this.botRightRow -= 1;
            }
        }
        else if (direction.equals("left"){
            if (Tray.canMoveLeft(this)){
                this.upLeftColumn -= 1;
                this.botRightColumn -= 1;
            }
        }
        else if (direction.equals("right")){
            if (Tray.canMoveRight(this)){
                this.upLeftColumn += 1;
                this.botRightColumn += 1;
            }
        }
    }

}
