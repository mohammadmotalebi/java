package hero;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Hero {
	private String name;
	private String _class;
	private String power;
	private String special_power;
	private int HP;

	public Hero(){}
	 public Hero(String Name) {
		Hero h=null;
		try{
			FileReader fileReader = new FileReader("src/objects/heros/"+Name+".json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			h = gson.fromJson( fileReader , Hero.class);
			this.name = h.name;
			this.power = h.power;
			this.HP = h.HP;
			this.special_power = h.special_power;
			this._class = h._class;
			this.special_power = h.special_power;
		}
		catch (FileNotFoundException e) {}
	}
	
	final public static Hero loadHero(String name) {return new Hero(name);}
	
	final public String getName() {
		return this.name;
	}
	final public String get_Class() {
		return this._class;
	}
	final public String getPower() {
		return this.power;
	}
	final public String getSpecialPower() {
		return this.special_power;
	}
	final public int getHP(){ return this.HP; }
	
}
