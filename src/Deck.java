import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	List<Card> deck = new ArrayList<>();
	int numDealt;

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public int getNumDealt() {
		return numDealt;
	}

	public void setNumDealt(int numDealt) {
		this.numDealt = numDealt;
	}

	public Deck(List<Card> deck, int numDealt) {
		super();
		this.deck = deck;
		this.numDealt = numDealt;
	}
	public Deck() {
		
	}

	@Override
	public String toString() {
		return "Deck [deck=" + deck + ", numDealt=" + numDealt + "]";
	}

	public Card dealCard() {
		return deck.remove(0);
	}
	public void shuffleDeck() {
		Collections.shuffle(deck);
	} 
	public void cardsLeft() {
		
	}

}
