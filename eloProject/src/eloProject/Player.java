package eloProject;

import java.io.*;
import java.lang.*;

public class Player implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Data Members for the object Player
	private String name;
	private double elo;
	private int Id = this.hashCode();
	
	
	// Constructor
	public Player()
	{
		this.name = "Idiot";
		this.elo = 1500;
	}
	
	public Player(String playerName)
	{
		this.name = playerName;
		this.elo = 1500;
	}
	
	public Player(String playerName, double playerElo)
	{
		this.name = playerName;
		this.elo = playerElo;
	}
	
	
	// setters and getters
	public int getId()
	{
		return this.Id;
	}
	
	public void setId(int newId)
	{
		this.Id = newId;
	}
	public double getElo()
	{
		return this.elo;
	}
	
	public void setElo(double updatedElo)
	{
		this.elo = updatedElo;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String updatedName)
	{
		this.name = updatedName;
	}
	
	
	// toString method to output player info
	public String toString()
	{
		String result = "ID: "+ this.Id + ", Name: " + this.name + ", Elo: " + this.elo;
		return result;
	}
	
	
	// Player 1 v Player 2, if player1 wins, then the boolean will be true, otherwise player 2 wins.
	public static void play(Player winner, Player loser, boolean draw)
	{
		// Variables UwU
		final int K = 32; // use K = 16 for Masters
		
		double eA;		// expected score of player A
		double eB;		// expected score of player B
		
		double qA;		// adjusted expected score for player A
		double qB;		// adjusted expected score for player B
		
		double rA;		// updated elo for player A
		double rB;		// updated elo for player B
		
		qA = Math.pow(10, winner.getElo() / 400);
		qB = Math.pow(10,  loser.getElo() / 400);
		
		eA = qA / (qA + qB);
		eB = qB / (qB + qA);
		
		
		
		// If it is a draw, then each player score 0.5, otherwise winner scores 1 (sA = 1) and loser scores 0 (sB = 0)
		if (draw)
		{
			rA = winner.getElo() + K * (0.5 - eA);
			rB = loser.getElo() + K * (0.5 - eB);
		}
		else
		{
			rA = winner.getElo() + K * (1 - eA);
			rB = loser.getElo() + K * (0 - eB);
		}

		winner.setElo(rA);
		loser.setElo(rB);
		
	}

}
