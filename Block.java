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
    
    public void moveBlock(String direction) throws Exception{ //gotta check if possible to move, a "canMove" method?
        if (!directions.contains(direction))
          throw (new Exception e{});
        if direction.equals("up"){
          this.upLeftRow
        }
      
    }
