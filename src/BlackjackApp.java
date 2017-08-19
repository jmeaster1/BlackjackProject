import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackApp {

	Scanner kb = new Scanner(System.in);

	Player p1 = new Player();
	Player dealer = new Player();
	Deck cardDeck = new Deck();

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		app.run();

	}

	public void run() {
		initializeDeck();
		cardDeck.shuffleDeck();
		// for (Card cardsInDeck : cardDeck.getDeck()) {
		// System.out.println(cardsInDeck);
		// }
		cardDeck.dealCard();
		p1.getHand().addCard(cardDeck.dealCard());
		p1.getHand().addCard(cardDeck.dealCard());

		printPlayerHand();

		System.out.println("Player 1 current total: " + sumCalcPlayer() + "\n");

		cardDeck.dealCard();
		dealer.getHand().addCard(cardDeck.dealCard());
		dealer.getHand().addCard(cardDeck.dealCard());

		printDealerHand();
		System.out.println("Dealer current total: " + sumCalcDealer() + "\n");
		String hsChoice;
		do {
			System.out.print("(H)it or (S)tand?: ");
			hsChoice = kb.next();
			

			if (hsChoice.equalsIgnoreCase("H")) { // ??????????
				cardDeck.dealCard();
				p1.getHand().addCard(cardDeck.dealCard());
				printPlayerHand();
				checkWin();

				System.out.println("Player 1 current total: " + sumCalcPlayer() + "\n");
			} else if (hsChoice.equalsIgnoreCase("S")) {
				System.out.println("Your total is: " + sumCalcPlayer());
				
			}
			if (!hsChoice.equalsIgnoreCase("S") && (!hsChoice.equalsIgnoreCase("H"))) {
				System.out.println("INVALID ENTRY: Please try again. ");
			}
		} while (!hsChoice.equalsIgnoreCase("S"));

		while (sumCalcDealer() <= 16) {
			dealer.getHand().addCard(cardDeck.dealCard());
			checkWin();
			
		}

		printDealerHand();
		System.out.println("Dealer current total: " + sumCalcDealer() + "\n");

	}

	public void checkWin() {
		if (sumCalcDealer() == 21) {
			System.out.println("\nDealer has BLACKJACK. You lose!\n");
		} else if (sumCalcPlayer() == 21) {
			System.out.println("\nBLACKJACK! Winner, winner, chicken dinner!\n");
		} else if (sumCalcDealer() > 21) {
			System.out.println("\nThe dealer has BUSTED!");
			System.out.println("Congratulations, you've won the hand!\n");
		} else if (sumCalcPlayer() > 21) {
			System.out.println("\nYou have BUSTED! Better luck next hand!\n");
		} else if (sumCalcDealer() < sumCalcDealer()) {
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
		return sumPlayer;
	}

	public int sumCalcDealer() {
		int sumDealer = 0;

		for (Card cardsList : dealer.getHand().getHand()) {
			sumDealer = sumDealer + cardsList.getCardRank().getValue();
		}
		return sumDealer;
	}

	public void printPlayerHand() {
		System.out.println("Player Hand: ");
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

}
