package eloProject;

import java.util.*;
import java.io.*;

public class Main 
{
	public static void main (String[] args) throws FileNotFoundException
	{
		
		// Read from file
		/*String playerName;
		double playerElo;
		
		File inputFile = new File("/Users/Sander/Desktop/input.txt");
		Scanner reader = new Scanner(inputFile);
		
		while(reader.hasNext())
		{
			//reader.
			
		}
		
		reader.close();
		*/
		
		
		// User Input
		int input;
		String playerName;
		double playerElo;
		
		// Player list
		// NOTE: How does HashMap deal with collision?
		HashMap<Integer, Player> playerList = new HashMap<Integer, Player>();
		
		// Default elo rating is 1500
		playerElo = 1500;
		
		// scanner class for user input reading
	    Scanner reader = new Scanner(System.in);  // Create a Scanner object
	    
	    
		
	    
		boolean lcv = true;
	    // try-catch since we are responsible
	    try
	    {
	    	// winner and loser holders
		    int winnerId = 0;
		    int loserId = 0;
		    
	    	while(lcv)
			{
				System.out.print("\nEnter an option (int only):\n"
						+ "1: Adding new player\n"
						+ "2: Report a match\n"
						+ "3: Print player list\n"
						+ "4: Save player list\n"
						+ "5: Load player list\n"
						+ "6: Change player elo\n"
						+ "Input: ");
				input = reader.nextInt();
				reader.nextLine();
				
				switch (input)
				{
				// add player
				case 1:
					System.out.print("\nEnter a name: ");
					playerName = reader.nextLine();
					System.out.print("Enter an elo: ");
					playerElo = reader.nextDouble();
					
					// Add player to player list
					Player newPlayer = new Player(playerName, playerElo);
					
					// check if hash code / key is mapped already, increment it by 1 until we find a key not taken
					while (playerList.containsKey(newPlayer.getId()) == true)
					{
						System.out.println("Key Collision occurred at " + newPlayer.getId());
						newPlayer.setId(newPlayer.getId() + 1);
					}
					playerList.put(newPlayer.getId(), newPlayer);
					
					System.out.println("Successfully added a player!");
					break;
					
					
				// report a match
				case 2:
					System.out.println("\nCase 2");
					
					System.out.print("Enter winner id: ");
					winnerId = reader.nextInt();
					
					System.out.print("\nEnter loser id: ");
					loserId = reader.nextInt();
					
					Player.play(playerList.get(winnerId), playerList.get(loserId), false);
					break;
				
					
				// print player list
				case 3:
					System.out.println("\nCase 3");
					for (int i : playerList.keySet())
					{
						System.out.println(playerList.get(i).toString());
					}
					break;
					
					
				// export player list as a serialized object
				case 4:
					System.out.println("\nCase 4");
					
					// stream class for write file
				    FileOutputStream fout = new FileOutputStream("/Users/Sander/Desktop/player_list.txt");
				    ObjectOutputStream out = new ObjectOutputStream(fout);
					
					out.writeObject(playerList);
					
					System.out.println("Successfully written to file.");
					
					// Close stream
					out.flush();
					out.close();
					fout.close();
					break;
					
					
				// import player list from serialized file
				case 5:
					System.out.println("\nCase 5");
					
					// stream class for read file
				    FileInputStream fin = new FileInputStream("/Users/Sander/Desktop/player_list.txt");
				    ObjectInputStream in = new ObjectInputStream(fin);
					
					playerList = null;
					playerList = (HashMap<Integer, Player>) in.readObject();
					
					System.out.println("WE READ A PLAYER LIST");
					
					// close stream
					fin.close();
					in.close();
					break;
					
				// edit player elo
				case 6:
					System.out.println("\nCase 6");
					
					System.out.print("\nEnter player id: ");
					input = reader.nextInt();
					
					System.out.print("\nCurrent elo: " + playerList.get(input).getElo() + ", Enter new elo: ");
					playerElo = reader.nextDouble();
					playerList.get(input).setElo(playerElo);
					break;
					
					
				// default case
				default: 
					System.out.println("\nWe have entered default.");
					lcv = false;
					break;
				}
			}
	    	
	    	
			
	    }
	    catch (Exception e)
	    {
	    	System.out.println(e.toString());
	    }
		
		
		// Close scanner
		reader.close();		
		
	}
	

}