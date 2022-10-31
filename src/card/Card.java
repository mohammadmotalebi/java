package card;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Card {
	private String name;
	private String type;
	private String _class;
	private String rarity;
	private String description;
	private int manacost;
	private int value;

	public Card(){}
	public Card(String Name) {
		Card card = null;
		try{
			FileReader fileReader = new FileReader("src/objects/cards/"+Name+".json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			card = gson.fromJson( fileReader , Card.class);
			this.name = card.name;
			this.type = card.type;
			this._class = card._class;
			this.rarity = card.rarity;
			this.description = card.description;
			this.manacost = card.manacost;
			this.value = card.value;
		}
		catch (FileNotFoundException e) {}
	}

	final public static Card loadCard(String name) { return new Card(name); }

	final public static ArrayList<Card> allCards() {
		ArrayList<Card> cards = new ArrayList<>();
		File file = new File("src/objects/cards");
		File[] myfiles = file.listFiles();

		for(File myfile : myfiles){
			try {
				FileReader filereader = new FileReader(myfile);
				Gson gson = new Gson();
				cards.add(gson.fromJson(filereader, Card.class));
			}
			catch (FileNotFoundException e){}
		}
		return cards;
	}

	final public static boolean haveInAllCards(String name) {
		for(Card card : allCards())
			if(card.getName().equals(name))
				return true;
		return false;
	}

	final public String getName() {
		return this.name;
	}
	final public String getType() {
		return this.type;
	}
	final public String get_Class() {
		return this._class;
	}
	final public String getRarity() {
		return this.rarity;
	}
	final public String getDescription() {
		return this.description;
	}
	final public int getValue() {
		return this.value;
	}
	final public int getManaCost() {
		return this.manacost;
	}
}
