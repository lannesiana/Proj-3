import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Solver {
	private Tray initialTray;
	private List<Block> blocksGoal; //goal configuration
	private Queue<Tray> trayQueue = new LinkedList<Tray>(); //a first in, first out queue to examine all possible moves
	private Set<Integer> visitedTrays = new HashSet<Integer>(); //Stores every tray configuration seen, so repition does not occur.
	public boolean debug = false; //for debugging input


	public Solver(){

	}

	public static void main(String[] args) throws IOException, Exception{
		Solver sol = readCmd(args); //parses user input/files
		sol.solve(); //solves puzzle
	}



	//remove first item in queue, examine
	//get all possible moves of tray
	//make new tray for each move 
	//add all moves into queue, repeat loop
	//if empty queue, solution has not been found
	//check if current tray matches the goal config (new method)
	public void solve() throws Exception{
		//add initial tray in queue and hashset
		trayQueue.add(initialTray);
		visitedTrays.add(initialTray.hashCode());
		while (!trayQueue.isEmpty()){
			Tray t1 = trayQueue.remove();
			if (debug) { //allows us to see which tray was popped off the queue to be examined
				System.out.println("OFF THE QUEUE: " + t1.boardToString());
			}
			if (t1.goalReached(blocksGoal)){ //found solution, print move history
				t1.printMoveHistory();
				System.exit(0);
			}
				
			ArrayList<Move> allMoves = t1.findAllMoves(); 
			for (int k = 0; k < allMoves.size(); k++){		
				Move m1 = allMoves.get(k);
				
				Direction dire = m1.getDirection();
				Block blok = m1.getBlock();
				Tray newTray = t1.createTrayAfterMove(blok, dire);
				if (debug)
					System.out.println("NEW TRAY CREATED:: " + newTray.boardToString());
				if (!visitedTrays.contains(newTray.hashCode())){
					trayQueue.add(t1.createTrayAfterMove(blok, dire));
					visitedTrays.add(newTray.hashCode());
				}
			}
			}	
		System.exit(1); //trayQueue is empty, no more moves to be made.
		}
			
	

	private static Solver readCmd(String[] args) throws IOException{
		Solver sol = new Solver();		
		String option = null;
		String initFile = null;
		String goalFile = null;
		if(args.length < 2){
			if (args[0] == "[-ooptions]"){ //prints out all debugging options.
				System.out.println("List of all possible debugging options:");
				System.out.println("1.[-oinfo]: prints out all debugging info");
				System.out.println("2.[-oDebugTray]: prints out all debugging info for methods specifically in Tray.java"); //How do we implement this?
			} else
			throw new IOException("incorrect input"){};
		}
		//identifies debugging output, initial configuration, and goal configuration
		if (args.length ==2 && !args[0].startsWith("[-o")){
			initFile = args[0];
			goalFile = args[1];
		}
		else if (args.length >= 3){
			if (args[0].startsWith ("[-o")){
				option = args[0].substring(3);
				if (option == "info]") //insert debug options here. make sure options end with "]"
					sol.debug = true;
				if (option == "DebugTray]")
					//DO SUMFING HERE
				initFile = args[1];
				goalFile = args[2];
				}
			else
				throw new IOException("incorrect format");
		}

		String line;

		//read initial configuration file;
		BufferedReader fileConfig = null;
		try{
			fileConfig = new BufferedReader(new InputStreamReader(new FileInputStream(initFile)));
			int lineCount = 0;
			ArrayList<Block> blocksInit = new ArrayList<Block>();
			int rows = 1;
			int cols = -1;
			//create blocks according to the initial configuration
			while ((line = fileConfig.readLine()) != null){
				if (lineCount > 0) {
					String[] blockPos = line.split("\\s");
					if(blockPos.length != 4){
						throw new IllegalArgumentException(
							"Bad block definition in file");
					}
					int upLeftRow = Integer.parseInt(blockPos[0]);
					int upLeftColumn = Integer.parseInt(blockPos[1]);
					int botRightRow = Integer.parseInt(blockPos[2]);
					int botRightColumn = Integer.parseInt(blockPos[3]);
					Block b = new Block(upLeftRow, upLeftColumn, botRightRow, botRightColumn);
					blocksInit.add(b);
				} else{
					//first line is 2 digits, for totalRows and totalColumns
					String[] size = line.split("\\s");
					if (size.length != 2){
						throw new IllegalArgumentException("incorrect first line in file");
					}
					rows = Integer.parseInt(size[0]);
					cols = Integer.parseInt(size[1]);
				}
				lineCount++;
			}

			//the tray constructor sorts by row, then column. Instantiate intial tray to sort blocks for easier debug.
			sol.initialTray = new Tray(rows, cols, blocksInit, null);
			}catch (IOException e){
				e.printStackTrace();
			} finally{
				if (fileConfig != null){
					try{
						fileConfig.close();
					} catch(IOException e){ 
						e.printStackTrace();}
				}
			} //close finally

			//reads goal file;
			BufferedReader fileGoal = null;
			try{
				List<Block> blocksGoal = new ArrayList<Block>();
				fileGoal = new BufferedReader(new InputStreamReader(new FileInputStream(goalFile)));
				while ((line = fileGoal.readLine()) != null){
					String[] blockPos = line.split("\\s");
					if (blockPos.length != 4){
						throw new IllegalArgumentException(
							"Bad block definition");
					}
					int upLeftRow = Integer.parseInt(blockPos[0]);
					int upLeftColumn = Integer.parseInt(blockPos[1]);
					int botRightRow = Integer.parseInt(blockPos[2]);
					int botRightColumn = Integer.parseInt(blockPos[3]);
					blocksGoal.add(new Block(upLeftRow, upLeftColumn, botRightRow, botRightColumn));
				}
				sol.blocksGoal = blocksGoal;
			} catch(IOException e){
				e.printStackTrace();
				}
			return sol;
			}//close method


}


