package uno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Uno {
	public static void main ( String [] args) {
		ArrayList <Card> maindeck = new ArrayList <Card> ();
		for (Color c: Color.values()) {
			for (Value v: Value.values()) {
				for (int i = 0; i<2; i++) {
					if (c.equals(Color.ALL)||v.equals(Value.WILD)||v.equals(Value.WILD4)) {
						continue;
					}
					maindeck.add(new Card(c,v));
				}
			}
		}
		for (int i = 0; i<4; i++) {
		maindeck.add(new Card(Color.ALL,Value.WILD));
		maindeck.add(new Card(Color.ALL,Value.WILD4));
		}
		Collections.shuffle(maindeck);
		//the human player will be Player (0)
		ArrayList <Player> players = new ArrayList <Player> (Arrays.asList(new Player(0),new Player(1),new Player(2),new Player(3)));
		System.out.println("Let the games begin!");
		int count = 0;
		for (int i = 0; i<32; i++) {
			if (i%4==0) {
				System.out.print("You drew ");
				players.get(i%4).draw(maindeck).printCard(players.get(i%4).getDeck().get(count));
				count ++;
			}
			else {
				players.get(i%4).draw(maindeck);
			}
		}
		for (Card c: maindeck) {
			if (c.getValue().equals(Value.PLUSTWO)||c.getValue().equals(Value.SKIP)||c.getValue().equals(Value.WILD)||c.getValue().equals(Value.WILD4)||c.getValue().equals(Value.REVERSE)) {
				continue;
			}
			else {
				maindeck.remove(c);
				Player.ccolor = c.getColor();
				Player.cvalue = c.getValue();
				System.out.print("The computer played ");
				c.printCard(c);
				break;
			}
		}
		Scanner play = new Scanner(System.in);
		Scanner ncolor = new Scanner(System.in);
		boolean check =  true;
		int tracker = 4;
		Direction dir = new Direction();
		boolean wild4 = false;
		boolean skip = false;
		boolean draw2 = false;
		
		while (check==true) {
			if (tracker==0) {
				tracker = 48;
			}
			for (Player p: players) {
				if (p.getDeck().isEmpty() && p.getNum()==0) {
					System.out.println("Congratulations! You win!");
					play.close();
					ncolor.close();
					break;
				}
				else if (p.getDeck().isEmpty() && p.getNum()!=0) {
					System.out.println("Player " + Integer.toString(p.getNum()) + " wins!");
					play.close();
					ncolor.close();
					break;
				}
			}
			if (maindeck.isEmpty()) {
				play.close();
				ncolor.close();
				System.out.println("The deck has no more cards! The game ends in a draw.");
				break;
			}
			if (players.get(tracker%4).getDeck().size()==1) {
				System.out.println("UNO!");
			}
			if (dir.getDir()==0) {
				if (skip==true) {
					tracker++;
					skip = false;
					continue;
				}
				if (wild4==true) {
					players.get(tracker%4).drawFour(maindeck);
					wild4 = false;
					tracker++;
					continue;
				}
				if (draw2==true) {
					players.get(tracker%4).drawTwo(maindeck);
					tracker++;
					draw2 = false;
					continue;
				}
				Card current = players.get(tracker%4).choice(maindeck,players.get(tracker%4).getDeck(),play,ncolor);
				if (current.getValue().equals(Value.WILD4)) {
					wild4 = true;
				}
				if (current.getValue().equals(Value.SKIP)) {
					skip = true;
				}
				if (current.getValue().equals(Value.PLUSTWO)) {
					draw2 = true;
				}
				if (current.getValue().equals(Value.REVERSE)) {
					dir.reverse(dir);
					tracker--;
					continue;
				}
				tracker++;
			}
			else {
				if (skip==true) {
					tracker--;
					skip = false;
					continue;
				}
				if (wild4==true) {
					players.get(tracker%4).drawFour(maindeck);
					tracker--;
					wild4 = false;
					continue;
				}
				if (draw2==true) {
					players.get(tracker%4).drawTwo(maindeck);
					tracker--;
					draw2 = false;
					continue;
				}
				Card current = players.get(tracker%4).choice(maindeck,players.get(tracker%4).getDeck(),play,ncolor);
				if (current.getValue().equals(Value.WILD4)) {
					wild4 = true;
				}
				if (current.getValue().equals(Value.SKIP)) {
					skip = true;
				}
				if (current.getValue().equals(Value.PLUSTWO)) {
					draw2 = true;
				}
				if (current.getValue().equals(Value.REVERSE)) {
					dir.reverse(dir);
					tracker++;
					continue;
				}
				tracker--;
			}
		}
	}
}