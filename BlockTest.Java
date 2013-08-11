import junit.framework.TestCase;


public class BlockTest extends TestCase {

  
	public void testInit(){
		Block b1 = new Block(0, 0, 0, 0);
		assertEquals(0,b1.getBRC());
		assertEquals(0, b1.getBRR());
		assertEquals(0, b1.getULC());
		assertEquals(0, b1.getULR());
		assertTrue(b1.isOK());
		Block b2 = new Block(0, 1, 2, 3);
		assertEquals(3, b2.getBRC());
		assertEquals(2, b2.getBRR());
		assertEquals(1, b2.getULC());
		assertEquals(0, b2.getULR());
		assertTrue(b2.isOK());
		Block b3 = new Block(3,3,0,0);
		assertFalse(b3.isOK());
	}
	
	public void testCreateBlockAfterMove() throws Exception{
		ArrayList<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(1, 1, 2, 2);
		blocks.add(b1);
		Tray t1 = new Tray(5, 4, blocks, null);
		Block upBlock = b1.createBlockAfterMove(Direction.up);
		System.out.print(upBlock.toString());
		assertEquals("Block[0 1 1 2]", upBlock.toString());
		Block downBlock = b1.createBlockAfterMove(Direction.down);
		System.out.print(downBlock.toString());
		assertEquals("Block[1 1 2 2]", downBlock.toString());
		Block leftBlock = b1.createBlockAfterMove(Direction.left);
		System.out.print(leftBlock.toString());
		assertEquals("Block[1 0 2 1]", leftBlock.toString());
		Block rightBlock = b1.createBlockAfterMove(Direction.right);
		System.out.print(rightBlock.toString());
		assertEquals("Block[1 1 2 2]", rightBlock.toString());
	}
}
