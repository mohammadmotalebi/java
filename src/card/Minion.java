package card;

public class Minion extends Card{
	
	private int attack;
	private int hp;
	public Minion(){}
	public Minion(String  name) {
		super(name);
	}
	
	final public int getattack() {
		return this.attack;
	}
	final public int getHp() {
		return this.hp;
	}
	
	final public void setAttack(int attack) {
		this.attack = attack;
	}
	final public void setHp(int hp) {
		this.hp = hp;
	}
	
}
