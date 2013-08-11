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
	private List<Block> blocksGoal;
	private Queue<Tray> trayQueue = new LinkedList<Tray>();
	private Set<Integer> visitedTrays = new HashSet<Integer>();
	public boolean debug = true;


	public Solver(){

	}

	public static void main(String[] args) throws IOException, Exception{
		Solver sol = readCmd(args);
		sol.solve();
	}

	//remove first item in queue, examine
	//get all possible moves of tray
	//make new tray for each move 
	//add all moves into queue, repeat loop
	//if empty queue, solution has not been found
	//check if current tray matches the goal config (new method)

	
	//NOT ADDING TO QUEUEU CORRECTLY
	public void solve() throws Exception{
		boolean won = false; //debug
		trayQueue.add(initialTray);
		visitedTrays.add(initialTray.hashCode());
		while (!trayQueue.isEmpty()){
			Tray t1 = trayQueue.remove();
				if (t1.goalReached(blocksGoal)){
					won = true; //DELETE LATERRRZZ
					t1.printMoveHistory();
					break;
				}
				
				ArrayList<Move> allMoves = t1.findAllMoves(); 
				for (int k = 0; k < allMoves.size(); ){		
					Move m1 = allMoves.get(k);
					
					Direction dire = m1.getDirection();
					Block blok = m1.getBlock();
					if(debug)
						System.out.println("BEFORE: " + t1.boardToString());
					Tray normanSux = t1.createTrayAfterMove(blok, dire);
					if (debug)
						System.out.println("NEW: " + normanSux.boardToString());
					if (!visitedTrays.contains(normanSux.hashCode())){
						trayQueue.add(t1.createTrayAfterMove(blok, dire));
						visitedTrays.add(normanSux.hashCode());
				}}
			System.out.println(won);
			System.exit(1); //trayQue is empty, no more moves to be made.
		}
									
		}
			
			
			
		
		

	

	private static Solver readCmd(String[] args) throws IOException{
		//INTENSE MOMENTZZZ
		Solver sol = new Solver();		
		String option = null;
		String initFile = null;
		String goalFile = null;
		if(args.length < 2){
			throw new IOException("incorrect input"){};
		}
		if (args.length ==2){
			initFile = args[0];
			goalFile = args[1];
		}
		else if (args.length >= 3){
			if (args[0].startsWith ("-o)")){
				option = args[0].substring(2);
				sol.debug = true;
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
					String[] size = line.split("\\s");
					if (size.length != 2){
						throw new IllegalArgumentException("incorrect first line in file");
					}
					rows = Integer.parseInt(size[0]);
					cols = Integer.parseInt(size[1]);
				}
				lineCount++;
			}
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
				sol.blocksGoal = blocksGoal; //uhh did i have this file already?
			} catch(IOException e){
				e.printStackTrace();
				}
			return sol;
			}//close method


}


