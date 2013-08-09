import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;


public class TrayTest extends TestCase {

	public void testInitTray(){
		ArrayList<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(0, 0, 1, 1);
		blocks.add(b1);
		Block b2 = new Block(0,2,1,2);
		blocks.add(b2);
		Block b3 = new Block(3,1, 3, 1);
		blocks.add(b3);
		Block b4 = new Block(4,3,4,3);
		blocks.add(b4);
		Tray t1 = new Tray(5, 4, blocks, null);
		assertTrue(t1.isOK());
		assertEquals("11201120000003000004", t1.boardToString());
		Tray t2 = new Tray(3, 3, blocks, null);
		assertFalse(t2.isOK());
	}

	public void testEquals(){
		Block b1 = new Block(0, 0, 1, 1);
		blocks.add(b1);
		Block b2 = new Block(0,2,1,2);
		blocks.add(b2);
		Block b3 = new Block(3,1, 3, 1);
		blocks.add(b3);
		Block b4 = new Block(4,3,4,3);
		blocks.add(b4);
		Tray t1 = new Tray(5, 4, blocks, null);
		Tray t2 = new Tray(5, 4, blocks, null);
		Tray t3 = new Tray(4, 5, blocks, null);
		Tray t4 = new Tray(6, 4, blocks, null);
		assertTrue(t1.equals(t2));
		assertFalse(t1.equals(t3));
		assertFalse(t1.equals(t4));

	}
	
	public void testCanMoveDirection(){
		ArrayList<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(0, 0, 1, 1);
		blocks.add(b1);
		Block b2 = new Block(0,2,1,2);
		blocks.add(b2);
		Block b3 = new Block(3,1, 3, 1);
		blocks.add(b3);
		Block b4 = new Block(4,3,4,3);
		blocks.add(b4);
		Tray t1 = new Tray(5, 4, blocks, null);
		assertTrue(t1.isOK());
		/* gives us tray of
		1 1 2 0
		1 1 2 0
		0 0 0 0
		0 3 0 0 
		0 0 0 4
		*/
		//blocks can move adjacent empty area
		assertTrue(t1.canMoveUp(b3));
		assertTrue(t1.canMoveDown(b3));
		assertTrue(t1.canMoveRight(b3));
		assertTrue(t1.canMoveLeft(b3));
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

	public void testFindAllMoves(){
		ArrayList<Block> blocks = new ArrayList<Block>();
		Block b1 = new Block(0, 0, 1, 1);
		blocks.add(b1);
		Block b2 = new Block(0,2,1,2);
		blocks.add(b2);
		Block b3 = new Block(3,0, 3, 2);
		blocks.add(b3);
		Block b4 = new Block(4,3,4,3);
		blocks.add(b4);
		Block b5 = new Block(0,3,1,3);
		blocks.add(b5);
		Block b6 = new Block(2,0,2,3);
		blocks.add(b6);
		/* gives us tray of
		1 1 2 5
		1 1 2 5
		6 6 6 6
		3 3 3 0 
		0 0 0 4
		*/
		Tray t1 = new Tray(5, 4, blocks, null);
		ArrayList<Move> methodMoves = t1.findAllMoves();
		ArrayList<Move> allMoves = new ArrayList<Move>();
		Move m1 = new Move(b3, Direction.down);
		Move m2 = new Move(b3, Direction.right);
		Move m3 = new Move(b4, Direction.up);
		Move m4 = new Move(b4, Direction.left);
		allMoves.add(m1);
		allMoves.add(m2);
		allMoves.add(m3);
		allMoves.add(m4);
		System.out.println(methodMoves.size());
		System.out.println(allMoves.size());
		for (int i = 0; i < methodMoves.size(); i++){
			System.out.println(allMoves.get(i).toString());
			System.out.println(methodMoves.get(i).toString());
		}
		assertTrue(methodMoves.size() == allMoves.size());		
		for (int i = 0; i < allMoves.size(); i++){
			assertEquals("move " + i + " is incorrect", allMoves.get(i), methodMoves.get(i));
		}
	}


}
