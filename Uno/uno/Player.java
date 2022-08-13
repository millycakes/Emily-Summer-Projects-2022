package uno;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	private ArrayList <Card> deck;
	private ArrayList <Card> compchoice;
	private ArrayList <Card> compwild;
	private int num;
	public static Color ccolor;
	public static Value cvalue;
	
	public Player(int n) {
		deck = new ArrayList <Card> ();
		compchoice = new ArrayList <Card> ();
		compwild = new ArrayList <Card> ();
		this.num = n;
	}
	
	

	public ArrayList<Card> getCompchoice() {
		return compchoice;
	}



	public void setCompchoice(ArrayList<Card> compchoice) {
		this.compchoice = compchoice;
	}



	public ArrayList<Card> getCompwild() {
		return compwild;
	}



	public void setCompwild(ArrayList<Card> compwild) {
		this.compwild = compwild;
	}



	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public Card draw (ArrayList <Card> main) {
			getDeck().add(main.get(main.size()-1));
			main.remove(main.get(main.size()-1));
		return getDeck().get(getDeck().size()-1);
	}
	
	public void drawTwo (ArrayList <Card> main) {
		if (getNum()==0) {
			for (int i = 1; i<=2; i++) {
				getDeck().add(main.get(main.size()-1));
				main.remove(main.get(main.size()-1));
			}
			System.out.print("You drew: ");
			for (int i = 1; i<=2; i++) {
				getDeck().get(getDeck().size()-i).printCard(getDeck().get(getDeck().size()-i));
			}
		}
		else {
			System.out.println("Player " + Integer.toString(getNum())+ " drew two cards.");
			for (int i = 1; i<=2; i++) {
				getDeck().add(main.get(main.size()-1));
				main.remove(main.get(main.size()-1));
			}
		}
	}
	
	public void drawFour (ArrayList <Card> main) {
		if (getNum()==0) {
			for (int i = 1; i<=4; i++) {
				getDeck().add(main.get(main.size()-1));
				main.remove(main.get(main.size()-1));
			}
			System.out.print("You drew: ");
			for (int i = 1; i<=4; i++) {
				getDeck().get(getDeck().size()-i).printCard(getDeck().get(getDeck().size()-i));
			}
		}
		else {
			System.out.println("Player " + Integer.toString(getNum()) + " drew four cards.");
			for (int i = 1; i<=4; i++) {
				getDeck().add(main.get(main.size()-1));
				main.remove(main.get(main.size()-1));
			}
		}
	}
	
	public void wild (Scanner ncolor) {
		if (getNum()==0) {
			System.out.println("What color would you like to change to (blue, red, yellow, green)?");
			String ncurrent = ncolor.nextLine().toUpperCase();
			while (!(ncurrent.equals("BLUE")||ncurrent.equals("RED")||ncurrent.equals("YELLOW")||ncurrent.equals("GREEN"))) {
				System.out.println("Please type a valid color: blue, red, yellow, or green.");
				ncurrent = ncolor.nextLine().toUpperCase();
			}
			ccolor = Color.valueOf(ncurrent);
		}
		else {
			ArrayList <Color> temp = new ArrayList <Color> ();
			for (Card c: getDeck()) {
				if (!(c.getColor().equals(ccolor)&&temp.contains(c.getColor()))) {
					temp.add(c.getColor());
				}
			}
			ccolor = temp.get((int) Math.floor(Math.random()*temp.size()));
			cvalue = Value.WILD;
			System.out.println("Player " + Integer.toString(getNum()) + " played a wild card! Player "+Integer.toString(getNum())+ " changes the color to "+ccolor);
		}
	}
	
	
	public Card choice (ArrayList <Card> main, ArrayList <Card> deck, Scanner obj, Scanner ncolor) {
		String tempcolor;
		String tempvalue;
		if (getNum()==0) {
			System.out.println("The current deck in your cards are: ");
			for (Card c: deck) {
				c.printCard(c);
			}
			System.out.println("Would you like to draw a card or play a card? Type 'draw' for draw and 'play' for play.");
			String choice = obj.nextLine();
			while (!(choice.equals("draw")||choice.equals("play"))) {
				System.out.println("Please type 'draw' or 'play'");
				choice = obj.nextLine();
			}
				if (choice.equals("draw")) {
					System.out.print("You drew: ");
					Card c = draw(main);
					c.printCard(c);
					return c;
				}
			else if (choice.equals("play")) {
					System.out.println("What card would you like to play? Please type the color in all caps (if you would like to play a wild card, type 'ALL')");
					tempcolor= obj.nextLine();
					System.out.println("Please type the value of the card in all caps (i.e. TWO, FOUR, WILD, WILD4, SKIP, REVERSE, PLUS TWO). Note: you can only play "
							+ " a card of the same color or number as the previous card.");
					tempvalue = obj.nextLine();
					while ((!((Color.valueOf(tempcolor).equals(Color.ALL))||(Color.valueOf(tempcolor).equals(ccolor))||(Value.valueOf(tempvalue).equals(cvalue)))&&(deck.contains(new Card(Color.valueOf(tempcolor),Value.valueOf(tempvalue)))))) {
						System.out.println("Please type a card of the same color or number as the previous card (unless you are playing a wild card). What card would you like to play? Please type the color in all caps (if you would like to play a wild card, type 'ALL')");
						tempcolor = obj.nextLine();
						System.out.println("Please type the value of the card in all caps (i.e. TWO, FOUR, WILD, WILD4, SKIP, REVERSE, PLUS TWO). Note: you can only play \"\r\n"
								+ "							+ \" a card of the same color or number as the previous card.");
						tempvalue = obj.nextLine();
					}
					ccolor = Color.valueOf(tempcolor.toUpperCase());
					cvalue = Value.valueOf(tempvalue.toUpperCase());
					Card fcard = new Card(ccolor,cvalue);
					if (ccolor.equals(Color.ALL)) {
						for (Card l: deck) {
							if (l.getColor().equals(ccolor) && l.getValue().equals(cvalue)) {
								deck.remove(l);
								break;
							}
						}
						wild(ncolor);
					}
					else {
						System.out.print("You played: ");
						fcard.printCard(fcard);
					}
					for (Card l: deck) {
						if (l.getColor().equals(ccolor) && l.getValue().equals(cvalue)) {
							deck.remove(l);
							break;
						}
					}
					return fcard;
				}
		}
		else {
			compchoice.clear();
			compwild.clear();
			for (Card c:deck) {
				if (c.getColor().equals(ccolor) || c.getValue().equals(cvalue)) {
					compchoice.add(c);
				}
				if (c.getColor().equals(Color.ALL)) {
					compwild.add(c);
				}
			}
			if (!(compchoice.isEmpty())) {
				Card fcard = compchoice.get((int)Math.floor(Math.random()*compchoice.size()));
				deck.remove(fcard);
				System.out.println("Player " + Integer.toString(getNum())+ " played ");
				fcard.printCard(fcard);
				ccolor = fcard.getColor();
				cvalue = fcard.getValue();
				return fcard;
			}
			else if (!(compwild.isEmpty())) {
				Card w = compwild.get((int)Math.floor(Math.random()*compwild.size()));
				deck.remove(w);
				wild(ncolor);
				return w;
				}
			else {
				System.out.println("Player " + Integer.toString(getNum())+ " drew a card.");
				Card t = draw(main);
				return t;
				}
		}
		return new Card();
	}
}
