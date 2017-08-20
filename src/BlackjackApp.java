import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackApp {

	Scanner kb = new Scanner(System.in);
	
	Player p1 = new Player();
	Player dealer = new Player();
	Deck cardDeck = new Deck();

	public static void main(String[] args) throws InterruptedException {
		BlackjackApp app = new BlackjackApp();
		app.gameIntro();
		do {
			app.run();
		} while (app.keepPlaying());

	}

	public void gameIntro() throws InterruptedException {
		System.out.println("           BLACKJACK SIMULATOR VERSION 1.0           ");
		for (int i = 0; i < 11; i++) {
			System.out.print('\u2660');
			Thread.sleep(50);
			System.out.print('\u2764');
			Thread.sleep(50);
			System.out.print('\u2663');
			Thread.sleep(50);
			System.out.print('\u2666');
			Thread.sleep(50);
		}
		Thread.sleep(1000);
		System.out.print("\n\nPlease enter your name: ");
		String name = kb.next();
		System.out.println("Welcome, " + name + ". Let's play some blackjack!\n");
		Thread.sleep(800);
		
	}

	public void run() {
		initializeDeck();
		cardDeck.shuffleDeck();
		cardDeck.dealCard();

		p1.getHand().addCard(cardDeck.dealCard());
		p1.getHand().addCard(cardDeck.dealCard());
		System.out.println();

		printPlayerHand();

		System.out.println("Player 1 current total: " + sumCalcPlayer() + "\n");
		checkBJ();
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cardDeck.dealCard();
		dealer.getHand().addCard(cardDeck.dealCard());
		dealer.getHand().addCard(cardDeck.dealCard());

		printDealerHand();
		System.out.println("Dealer current total: " + sumCalcDealer() + "\n");
		checkBJ();

		String hsChoice;
		do {
			System.out.print("(H)it or (S)tand?: ");
			hsChoice = kb.next();
			System.out.println();

			if (hsChoice.equalsIgnoreCase("H")) {
				cardDeck.dealCard();
				p1.getHand().addCard(cardDeck.dealCard());
				printPlayerHand();
				checkBJ();

				System.out.println("Your current total: " + sumCalcPlayer() + "\n");
				if (sumCalcPlayer() > 21) {
					break;
				}

			} else if (hsChoice.equalsIgnoreCase("S")) {
				System.out.println("Your total is: " + sumCalcPlayer() + "\n");

			}
			if (!hsChoice.equalsIgnoreCase("S") && (!hsChoice.equalsIgnoreCase("H"))) {
				System.out.println("INVALID ENTRY: Please try again. ");
			}
		} while (!hsChoice.equalsIgnoreCase("S"));

		while (sumCalcDealer() <= 16) {
			dealer.getHand().addCard(cardDeck.dealCard());
		}

		printDealerHand();
		System.out.println("Dealer current total: " + sumCalcDealer() + "\n");
		checkWin();

		p1.getHand().resetHand();
		dealer.getHand().resetHand();
		if (cardDeck.getDeck().size() < 10) {
			initializeDeck();
		}

	}

	public void checkBJ() {
		if (sumCalcDealer() == 21) {
			System.out.println("\nDealer has BLACKJACK. You lose!\n");
		} else if (sumCalcPlayer() == 21) {
			System.out.println("\nBLACKJACK! Winner, winner, chicken dinner!\n");
		}
	}

	public void checkWin() {
		if (sumCalcDealer() > 21) {
			System.out.println("\nThe dealer has BUSTED!");
			System.out.println("Congratulations, you've won the hand!\n");
		} else if (sumCalcPlayer() > 21) {
			System.out.println("\nYou have BUSTED! Better luck next hand!\n");
		} else if (sumCalcDealer() == sumCalcPlayer()) {
			System.out.println("The hand is a PUSH.");
		} else if (sumCalcDealer() < sumCalcPlayer()) {
			System.out.println("\nCongratulations! Player 1 wins the hand.\n");
		} else if (sumCalcPlayer() < sumCalcDealer()) {
			System.out.println("\nToo bad! The dealer has won the hand.\n");
		}

	}

	public void initializeDeck() {
		List<Card> cardsList = new ArrayList<>();
		for (Suit suits : Suit.values()) {
			for (Rank rank : Rank.values()) {

				Card card1 = new Card(suits, rank);
				cardsList.add(card1);

			}

		}
		cardDeck.setDeck(cardsList);

	}

	public int sumCalcPlayer() {
		int sumPlayer = 0;

		for (Card cardsList : p1.getHand().getHand()) {
			sumPlayer = sumPlayer + cardsList.getCardRank().getValue();
		}

		for (Card c : p1.getHand().getHand()) {
			if (c.getCardRank().equals(Rank.ACE) && sumPlayer > 21) {
				sumPlayer = sumPlayer - 10;
			}
		}
		return sumPlayer;
	}

	public int sumCalcDealer() {
		int sumDealer = 0;

		for (Card cardsList : dealer.getHand().getHand()) {
			sumDealer = sumDealer + cardsList.getCardRank().getValue();
		}

		for (Card c : dealer.getHand().getHand()) {
			if (c.getCardRank().equals(Rank.ACE) && sumDealer > 21) {
				sumDealer = sumDealer - 10;
			}
		}
		return sumDealer;
	}

	public void printPlayerHand() {
		System.out.println("Your hand: ");
		for (Card c : p1.getHand().getHand()) {
			System.out.println(c);
		}
	}

	public void printDealerHand() {

		System.out.println("Dealer Hand: ");
		for (Card c : dealer.getHand().getHand()) {
			System.out.println(c);
		}
	}

	public boolean keepPlaying() {
		System.out.println("Do you want to keep playing?");
		String input = kb.next();
		if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}

}
