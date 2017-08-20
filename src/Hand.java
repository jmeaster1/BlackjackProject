import java.util.ArrayList;
import java.util.List;

public class Hand {
	List<Card> cards = new ArrayList<>();

	public List<Card> getHand() {
		return cards;
	}

	public void setHand(List<Card> hand) {
		this.cards = hand;
	}

	public Hand(List<Card> hand) {
		super();
		this.cards = hand;
	}

	public Hand() {
		
	}
	@Override
	public String toString() {
		return "Hand [hand=" + cards + "]";
	}
	
	

	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void resetHand() {
		this.cards = new ArrayList<Card>();
	}

	public Hand getCardsInHand() {
		return null;

	}

	public int getValueOfHand() {
		return 0;

	}
}
