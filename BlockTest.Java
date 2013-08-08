import junit.framework.TestCase;


public class BlockTest extends TestCase {

  
	public void testInit(){
		Block b1 = new Block(0, 0, 0, 0);
		assertEquals(0,b1.getBRC());
		assertEquals(0, b1.getBRR());
		assertEquals(0, b1.getULC());
		assertEquals(0, b1.getULR());
		Block b2 = new Block(0, 1, 2, 3);
		assertEquals(3, b2.getBRC());
		assertEquals(2, b2.getBRR());
		assertEquals(1, b2.getULC());
		assertEquals(0, b2.getULR());
	}
	
	public void testisOK(){
		
	}
}