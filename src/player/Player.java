package player;

import file.*;
import card.Card;
import hero.Hero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import file.FileHelper;
import java.io.*;
import java.util.ArrayList;

public class Player {
	private String username;
	private String password;
	private int bag;
	private File file;
	private ArrayList<Card> choosen_deck = new ArrayList<>();
	private ArrayList<Card> cards = new ArrayList<>();
	private ArrayList<Hero> heros = new ArrayList<>();
	private Hero choosen_hero = new Hero();

	public  Player(String Name) {
		Player p = null;
		try{
			FileReader fileReader = new FileReader("src/objects/players/"+Name+".json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			p = gson.fromJson( fileReader , Player.class);
			this.username = p.getUsername();
			this.password = p.getpassword();
			this.file = p.getFile();
			this.bag = p.getBag();
			this.choosen_deck = p.getChoosenDeck();
			this.cards = p.getCards();
			this.heros = p.getHeros();
			this.choosen_hero = p.getChoosenHero();
		}
		catch (FileNotFoundException e) {}
	}

	public Player(String Username , String Password){
		File file = new File("src/objects/players/"+Username+".json");
		this.username = Username;
		this.password = Password;
		this.bag = 500;
		this.file = new File("src/objects/players/"+Username+".json");

		try (FileWriter fileWriter = new FileWriter(file)){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			fileWriter.write(gson.toJson(this));
		}
		catch (IOException e){}

		try (FileReader fileReader = new FileReader("src/objects/players/playerlist.json")){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<String> listofplayer = gson.fromJson(fileReader , ArrayList.class);
			listofplayer.add(Username + " " + Password);
			try ( FileWriter playerList = new FileWriter("src/objects/players/playerlist.json") ){
				BufferedWriter buff = new BufferedWriter(playerList);
				buff.write( gson.toJson(listofplayer));
				buff.close();
			}
			catch (IOException e) {}
		}
		catch (IOException e){}
	}

	final public  void deletePlayer() {
		System.gc();

		File file = this.getFile();

		file.delete();

		try (FileReader fileReader = new FileReader("src/objects/players/playerlist.json")){

			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			ArrayList<String> listofplayer = gson.fromJson(fileReader , ArrayList.class);

			listofplayer.remove(this.username + " " + this.password);

			try ( FileWriter playerList = new FileWriter("src/objects/players/playerlist.json") ){

				BufferedWriter buff = new BufferedWriter(playerList);

				buff.write( gson.toJson(listofplayer));

				buff.close();

			}

			catch (IOException e) {}

		}

		catch (IOException e){}
	}

	final public boolean haveHero(String name) {
		for(Hero hero : heros)
			if(hero.getName().equals(name) || hero.get_Class().equals(name))
				return true;
		return false;
	}

	final public boolean haveCardInDeck(String name) {
		for(Card card : choosen_deck)
			if(card.getName().equals(name))
				return true;
		return false;
	}

	final public boolean haveCard(String name) {
		for(Card card : cards)
			if(card.getName().equals(name))
				return true;
		return false;
	}

	final public boolean checkCardClass(Card card) {
		if(card.get_Class().equals("Neutral") || card.get_Class().equals(choosen_hero.get_Class()))
			return true;
		return false;
	}

	final public File getLog () {
		File log = new File("src/objects/logs/"+username+".log");
		if(log.exists())
			return log;
		else {
			try {
				log.createNewFile();
				FileHelper.logHeader(log , username , password);
				return log;
			}
			catch(Exception e) { return log;}
		}
	}

	final public String getUsername () {
		return this.username;
	}
	final public String getpassword () {
		return this.password;
	}
	final public int getBag () {
		return this.bag;
	}
	final public ArrayList<Card> getChoosenDeck () {
		return this.choosen_deck;
	}
	final public ArrayList<Card> getCards () {
		return this.cards;
	}
	final public  ArrayList<Hero> getHeros () {
		return this.heros;
	}
	final public Hero getChoosenHero () {
		return this.choosen_hero;
	}
	final public File getFile() {
		return this.file;
	}

	final public void setBag(int bag) {
		this.bag = bag;
		FileHelper.updater(this ,this.file.toString());
		return ;
	}

	final public void setHero (Hero hero) {
		choosen_hero = hero;
		choosen_deck.clear();
		FileHelper.updater(this ,this.file.toString());
		return ;
	}
	final public void addHero (Hero hero) {
		heros.add(hero);
		FileHelper.updater(this ,this.file.toString());
		return ;
	}
	final public void removeHero (Hero hero) {
		for(int i = 0; i < heros.size(); i++) {
			if(heros.get(i).getName().equals(hero.getName())) {
				heros.remove(i);
				break;
			}
		}
		FileHelper.updater(this ,this.file.toString());
		return ;
	}

	final public void addCard (Card card) {
		cards.add(card);
		FileHelper.updater(this ,this.file.toString());
		return ;
	}
	final public void removeCard (Card card) {
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getName().equals(card.getName())) {
				cards.remove(i);
				break;
			}
		}
		FileHelper.updater(this ,this.file.toString());
		return ;
	}

	final public void addCardToDeck (Card card) {
		choosen_deck.add(card);
		FileHelper.updater(this ,this.file.toString());
		return ;
	}
	final public void removeCardFromDeck (Card card) {
		for(int i = 0; i < choosen_deck.size(); i++) {
			if(choosen_deck.get(i).getName().equals(card.getName())) {
				choosen_deck.remove(i);
				break;
			}
		}
		FileHelper.updater(this ,this.file.toString());
		return ;
	}
}
