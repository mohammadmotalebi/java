package card;

public class Weapons extends Card{
    private int durability;
    private int attack;

    public void setAttack(int Attack){
        this.attack = Attack;
    }
    public void setDurability(int d){
        this.durability = d;
    }
    public int getAttack(){
        return attack;
    }
    public int getDurability(){
        return durability;
    }
}
