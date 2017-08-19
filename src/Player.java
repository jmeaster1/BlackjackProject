
public class Player {
	String name;
	Hand hand;
	double wallet;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public Player(String name, Hand hand, double wallet) {
		super();
		this.name = name;
		this.hand = hand;
		this.wallet = wallet;
	}
	public Player() {
		this.hand = new Hand();
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", hand=" + hand + ", wallet=" + wallet + "]";
	}
	public void placeWager(int wager) {
		
	}

}
