import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;


public class TrayTest extends TestCase {
  
	public void testCanMoveDirection(){
		ArrayList<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(0, 0, 1, 1);
		blocks.add(b1);
		Block b2 = new Block(0,3,1,3);
		blocks.add(b2);
		Block b3 = new Block(3,1, 3, 1);
		blocks.add(b3)
		Block b4 = new Block(4,3,4,3);
		blocks.add(b4)
		Tray t1 = new Tray(5, 4, blocks, null);
		/* gives us tray of
		1 1 2 0
		1 1 2 0
		0 0 0 0
		0 3 0 0 
		0 0 0 4
		*/
		//blocks can move adjacent empty area
		assertTrue(t1.caMoveUp(b3));
		assertTrue(t1.canMovDown(b3));
		assertTrue(t1.canMoveRight(b3));
		assertTrue(t1.canMoveLeft(b3)));
		assertTrue(t1.canMoveDown(b2));
		assertTrue(t1.canMoveRight(b2));
		assertTrue(t1.canMoveDown(b1));

		//makes sure block cannot move out of tray
		assertFalse(t1.canMoveUp(b1));
		assertFalse(t1.canMoveLeft(b1));
		assertFalse(t1.canMoveRight(b4));
		assertFalse(t1.canMoveDown(b4));
		//blocks cannot move onto another block
		assertFalse(t1.canMoveRight(b1));
		assertFalse(t1.canMoveLeft(b2));
	}

}
