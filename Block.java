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
    //also consider, "|| if space next to block is empty"
    public boolean isMoveable(String direction){
        if (direction.equals("up"){
            if (this.upLeftRow > 0)
                return true;
        }
        if (direction.equals("down"){
            if (this.botRightRow < ) //< numRows in tray. maybe this should be in tray to actually access layout. but then would need get Methods for all these instance varaibles ewwwwww.
                return true;
        }
        
        if (direction.equals("left"){
            if (this.upLeftColumn > 0) 
                return true;
        }
        if (direction.equals("right"){
            if (this.botRightColumn < ) //< numColumns in tray
                return true;
        }
        return false;
    }
    
    public void moveBlock(String direction) throws Exception{ //gotta check if possible to move, a "canMove" method?
        if (!directions.contains(direction))
          throw (new Exception e{});
        if direction.equals("up"){

            this.upLeftRow
        }
      
    }
