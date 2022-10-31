package game;

import player.Player;
import hero.Hero;
import card.Card;
import file.*;
import constants.Constants;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class start {
	public static void main(String[] args) {

		System.out.println("Welcome to Sange Ojagh - beta version");

		boolean isrunning = true;
		while(isrunning) {
			System.out.print("Account dari ya na ? (Y,N) ");
			Scanner myInput = new Scanner(System.in);
			String command = myInput.nextLine();

			switch(command){

			case "Y":
				Player player = canLogin();
				if(player != null) {
					if(runMenu(player))
						System.out.println("Az Account paridi biroon ://");
					else {
						System.out.println("Montazeret mimoonam jigar :)");
						isrunning = false;
					}
				}
				else {
					System.out.println("Eshtep zadi jigar");
				}
				break;

			case "N":
				Player player1 = canMakeAccount();
				if(player1 != null) {
					if(runMenu(player1))
						System.out.println("Az Account paridi biroon ://");
					else {
						System.out.println("Montazeret mimoonam jigar :)");
						isrunning = false;
					}
				}
				else {
					System.out.println("Bakhti , yeki usero zodtar bardashteh");
				}
				break;

			case "exit":
				System.out.println("Montazeret mimoonam jigar :)");
				break;

			case "exit-a":
				System.out.println("Montazeret mimoonam jigar :)");
				break;

			case "SOS":
				runSOS("Start");
				break;

			default :
				System.out.println("Nemifahmam chi migi");
				System.out.println("Baraye komak bego SOS");
				break;
			}
		}
	}

	private static boolean runMenu(Player player) {
		FileHelper.logSingIn(player.getLog());
		System.out.println("Bah Bah bebin ki injas :)");

		boolean isrunning = true;
		while(isrunning) {
			Scanner myInput = new Scanner(System.in);
			String command = myInput.nextLine();

			switch(command) {

			case "delete-player":
				System.out.print("Password lotfan: ");
				String passworddelete = myInput.nextLine();
				if(passworddelete.equals(player.getpassword())) {
					player.deletePlayer();
					System.out.println("Delete History anjam shod");
					FileHelper.logDelete(player.getLog());
					return false;
				}
				else {
					FileHelper.logError(player.getLog() , "wrong_password_delete");
					System.out.println("Eshtep zadi golam hehehe");
				}
				break;
				
			case "collections":
				if(runCollections(player)) {
					FileHelper.logMoveTo(player.getLog() , "collections");
					System.out.println("Che daste khoobi chidi sheytoon :)");
				}
				else 
					return false;
				break;

			case "store":
				if(runStore(player)) {
					FileHelper.logMoveTo(player.getLog() , "store");
					System.out.println("Yekamam bara man kharj kon khasis :)");
				}
				else 
					return false;
				break;

			case "exit":
				FileHelper.logMoveTo(player.getLog(), "start");
				return true;

			case "exit-a":
				FileHelper.logSignOut(player.getLog());
				return false;

			case "SOS":
				FileHelper.logCommand(player.getLog(), "SOS");
				runSOS("Menu");
				break;

			default :
				FileHelper.logError(player.getLog(), "invalid_command:" + command);
				System.out.println("Nemifahmam chi migi");
				System.out.println("Baraye komak bego SOS");
				break;
			}
		}
		return false;
	}

	private static boolean runCollections(Player player) {
		System.out.println("Khob chikar mikhay bokoni");

		boolean isrunning = true;
		while(isrunning) {
			Scanner myInput = new Scanner(System.in);
			String command = myInput.nextLine();

			switch(command) {

			case "ls-a-heros":
				FileHelper.logCommand(player.getLog(), "ls-a-heros");
				int counter = 1;
				for(Hero hero : player.getHeros()) {
					System.out.println(counter + " " + hero.getName() + " " +hero.get_Class());
					counter++;
				}
				break;

			case "ls-m-heros":
				FileHelper.logCommand(player.getLog(), "ls-m-heros");
				System.out.println("1 " + player.getChoosenHero().getName() + " " + player.getChoosenHero().get_Class());
				break;

			case "select":
				System.out.print("Kio mikhay: ");
				String name = myInput.nextLine();

				if(player.haveHero(name)) {
					FileHelper.logSelect(player.getLog(), name);
					System.out.println("Che entekhabe khoobi , bah bah");
					Hero hero = Hero.loadHero(name);
					player.setHero(hero);
				}
				else {
					FileHelper.logError(player.getLog(), "invalid_hero:" + name);
					System.out.println("Motmaenni " + name +" ro dari???");
				}
				break;

			case "ls-a-cards":
				FileHelper.logCommand(player.getLog(), "ls-a-cards");
				int counter1 = 1;
				for(Card card : player.getCards()) {
					System.out.println(counter1 + " " + card.getName() + " " + card.get_Class() + " Value : " + card.getValue());
					counter1++;
				}
				break;

			case "ls-m-cards":
				FileHelper.logCommand(player.getLog(), "ls-m-cards");
				int counter2 = 1;
				for(Card card : player.getChoosenDeck()) {
					System.out.println(counter2 + " " + card.getName() + " " + card.get_Class() + " Value : " + card.getValue());
					counter2++;
				}
				break;

			case "ls-n-cards":
				FileHelper.logCommand(player.getLog(), "ls-n-cards");
				int counter3 = 1;
				for(Card card : player.getChoosenDeck()) {
					if(!player.checkCardClass(card)) {
						System.out.println(counter3 + " " + card.getName() + " " + card.get_Class() + " Value : " + card.getValue());
						counter3++;
					}
					else {
					    System.out.println("carti ndari ke jigar  :)  ");
                    }
				}
				break;

			case "add":
				System.out.print("Chio mikhy: ");
				String name1 = myInput.nextLine();
				if(player.haveCard(name1)) {
					int cnt=0;
					for(Card card : player.getChoosenDeck())
						if(card.getName().equals(name1))
							cnt++;

					if(player.getChoosenDeck().size() >= Constants.getMaxDeck()) {
						FileHelper.logError(player.getLog(), "max_deck:" + name1);
						System.out.println("Yekam ziad cart dari hehehe");
					}
					else if(cnt >= Constants.getMaxSame()) {
						FileHelper.logError(player.getLog(), "max_same:" + name1);
						System.out.println("Azin cart ziad dari hehehe");
					}
					else {
						FileHelper.logAdd(player.getLog(), name1);
						Card card = Card.loadCard(name1);
						player.addCardToDeck(card);
						System.out.println("Azafe shod ");
					}
				}
				else {
					FileHelper.logError(player.getLog(), "invalid_command:" + name1);
					System.out.println("Motmaenni " + name1 +" ro dari???");
				}
				break;

			case "remove":
				System.out.print("Chio nemikhy: ");
				String name2 = myInput.nextLine();

				if(player.haveCardInDeck(name2)) {
					FileHelper.logRemove(player.getLog(), name2);
					Card card = Card.loadCard(name2);
					player.removeCardFromDeck(card);
					System.out.println("Hazf shod ");
				}
				else {
					FileHelper.logError(player.getLog(), "invalid_command:" + name2);
					System.out.println("Aslan " + name2 +" to dastet nist !!!");
				}
				break;

			case "exit":
				FileHelper.logMoveTo(player.getLog(), "menu");
				return true;

			case "exit-a":
				FileHelper.logSignOut(player.getLog());
				return false;

			case "SOS":
				FileHelper.logCommand(player.getLog(), "SOS");
				runSOS("Collections");
				break;

			default :
				FileHelper.logError(player.getLog(), "invalid_command:" + command);
				System.out.println("Nemifahmam chi migi");
				System.out.println("Baraye komak bego SOS");
				break;
			}
		}
		return false;
	}

	private static boolean runStore(Player player) {
		System.out.println("Be maghazeh khosh oomadi");

		boolean isrunning = true;
		while(isrunning) {
			Scanner myInput = new Scanner(System.in);
			String command = myInput.nextLine();

			switch(command) {

			case "buy":
				System.out.print("Chio mikhy bekhari : ");
				String name = myInput.nextLine();

				if(player.haveCard(name)) {
					FileHelper.logError(player.getLog(), "invalid_command:" + name);
					System.out.println("Ino ke dari hehehe");
				}
				else {
					if(Card.haveInAllCards(name)) {
						Card card = Card.loadCard(name);
						if(player.getBag() - card.getValue() >= 0) {
							FileHelper.logAdd(player.getLog(), name);
							player.addCard(card);
							player.setBag(player.getBag() - card.getValue());
							System.out.println("Eyval kharidish");
						}
						else {
							FileHelper.logError(player.getLog(), "invalid_command:" + name);
							System.out.println("Az manam kamtar pool dari :)");
						}
					}
					else {
						FileHelper.logError(player.getLog(), "invalid_command:" + name);
						System.out.println("Aslan " + name +" vojood nadare !!!");
					}
				}
				break;

			case "sell":
				System.out.print("Chio mikhy befrooshi : ");
				String name1 = myInput.nextLine();

				if(player.haveCardInDeck(name1)) {
					FileHelper.logError(player.getLog(), "invalid_command:" + name1);
					System.out.println("Ino to dastet dari hehehe");
				}
				else {
					if(player.haveCard(name1)) {
						FileHelper.logSell(player.getLog(), name1);
						Card card = Card.loadCard(name1);
						player.removeCard(card);
						player.setBag(player.getBag() + card.getValue());
					}
					else {
						FileHelper.logError(player.getLog(), "invalid_command:" + name1);
						System.out.println("Aslan " + name1 +" ro nadari !!!");
					}
				}
				break;
			case "getbag":
					System.out.println("money: "+player.getBag());
					break;
			case "ls-s":
				FileHelper.logCommand(player.getLog(), "ls-s");
				System.out.println("Inaro mitoni befrooshi");
				int counter = 1;
				for(Card card : player.getCards()) {
					if(player.haveCardInDeck(card.getName()) == false) {
						System.out.println(counter + " " + card.getName() + " " + card.get_Class() + " Value : " + card.getValue());
						counter++;
					}
				}
				break;

			case "ls-b":
				FileHelper.logCommand(player.getLog(), "ls-b");
				System.out.println("Inaro mitoni bekhari");
				int counter1 = 1;
				for(Card card : Card.allCards()) {
					if(player.haveCard(card.getName()) == false) {
						System.out.println(counter1 + " " + card.getName() + " " + card.get_Class() + " Value : " + card.getValue());
						counter1++;
					}
				}
				break;

			case "exit":
				FileHelper.logMoveTo(player.getLog(), "menu");
				return true;

			case "exit-a":
				FileHelper.logSignOut(player.getLog());
				return false;

			case "SOS":
				FileHelper.logCommand(( player).getLog(), "SOS");
				runSOS("Store");
				break;

			default :
				FileHelper.logError(player.getLog(), "invalid_command:" + command);
				System.out.println("Nemifahmam chi migi");
				System.out.println("Baraye komak bego SOS");
				break;
			}
		}
		return false;
	}

	private static Player canLogin() {
		Scanner myInput = new Scanner(System.in);
		System.out.print("Username lotfan:");
		String username = myInput.nextLine();
		System.out.print("Password lotfan:");
		String password = myInput.nextLine();

		try (FileReader fileReader = new FileReader("src/objects/players/playerlist.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<String> listofplayer = gson.fromJson(fileReader, ArrayList.class);
			if(listofplayer.contains(username + " " + password)){
				Player player = new Player(username);
				return player;
			}
			else
				return null;
		}
		catch (IOException e){}
		return null;
	}

	private static Player canMakeAccount() {
		Scanner myInput = new Scanner(System.in);
		System.out.print("Bia account besazim ");
		System.out.print("Username lotfan:");
		String username = myInput.nextLine();
		System.out.print("Password lotfan:");
		String password = myInput.nextLine();

		try (FileReader fileReader = new FileReader("src/objects/players/playerlist.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<String> listofplayer = gson.fromJson(fileReader, ArrayList.class);
			if( !listofplayer.contains(username + " " + password)){
				Player player = new Player(username , password);
				return player;
			}
			else
				return null;
		}
		catch (IOException e){}
		return null;
	}

	private static void runSOS(String section) {
		
		switch(section) {
		case "Start":
			System.out.println("Age Avvale Kari:");
			System.out.println("Bego Y      ta berim too bazi");
			System.out.println("Bego N      ta berim barat account besazim");
			System.out.println("Bego exit   ta beri donbale zendegit");
			System.out.println("Bego exit-a ta beri donbale zendegit");
			break;
			
		case "Menu":
			System.out.println("Age To Menu Hasti:");
			System.out.println("Bego collections   ta berim dastato bechchinim");
			System.out.println("Bego store         ta ye sar berim maghazeh");
			System.out.println("Bego delete-player ta delete history konim");
			System.out.println("Bego exit          ta az accountet beri biroon");
			System.out.println("Bego exit-a        ta beri donbale zendegit");
			break;
			
		case "Collections":
			System.out.println("Age Too Collections Hasti:");
			System.out.println("Bego ls-a-cards ta cartato bebini");
			System.out.println("Bego ls-m-cards ta cartaye dasteto bebini");
			System.out.println("Bego ls-n-cards ta cartayi ke mitoni add koni ro bebini");
			System.out.println("Bego ls-a-heros ta heroro bebini");
			System.out.println("Bego ls-m-heros ta heroe aslito bebini");
			System.out.println("Bego select     ta ye hero jadid bardari");
			System.out.println("Bego add        ta ye cart be dastet ezafeh koni");
			System.out.println("Bego remove     ta ye carto az dastet hazf koni");
			System.out.println("Bego exit       ta az collections beri biroon");
			System.out.println("Bego exit-a     ta beri donbale zendegit");
			break;
			
		case "Store":
			System.out.println("Age Too Maghazeh Hasti:");
			System.out.println("Bego buy    ta ye cart bekhari");
			System.out.println("Bego sell   ta ye cart befrooshi");
			System.out.println("Bego ls-s   ta cartaye ghabel froosheto bebini");
			System.out.println("Bego ls-b   ta cartaye ghabel kharido bebini");
			System.out.println("Bego exit   ta az maghazeh beri biroon");
			System.out.println("Bego exit-a ta beri donbale zendegit");
			break;
		}
	}
}
