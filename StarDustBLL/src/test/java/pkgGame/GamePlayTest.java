package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import pkgCore.Card;
import pkgCore.GamePlay;
import pkgCore.HandPoker;
import pkgCore.Player;
import pkgCore.Rule;
import pkgCore.Table;
import pkgEnum.eGame;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;

class GamePlayTest {

	private HandPoker SetHand(ArrayList<Card> setCards, HandPoker handpoker) {
		Object t = null;
		try {
			// Load the Class into 'c'
			Class<?> c = Class.forName("pkgCore.HandPoker");
			// Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			// Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCards", new Class[] { ArrayList.class });
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			// invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);

		} catch (ClassNotFoundException x) {
			fail ("Class Not Found Exception Thrown");
		} catch (SecurityException e) {
			fail ("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail ("Illegal Arugment Exception Thrown");
		} catch (InstantiationException e) {
			fail ("Instantiation Excpetion Thrown");
		} catch (IllegalAccessException e) {
			fail ("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail ("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail ("Invocation Target Exception Thrown");
		}
		handpoker = (HandPoker) t;
		return handpoker;
	}
	
	private GamePlay PutGamePlay(GamePlay gp, UUID PlayerID, HandPoker hp) {

	 
		try {
			// Load the Class into 'c'
		//	Class<?> c = Class.forName("pkgCore.GamePlay");

			
			// Create a new instance 't' from the no-arg Deck constructor
				/*
			Class[] ArgsClass = new Class[] { Table.class, Rule.class };
			Table tbl = gp.getTable();
			Rule rle = gp.getRle();
			Object[] Args = new Object[] { tbl, rle };

			Constructor intArgsConstructor = c.getConstructor(ArgsClass);
			gamePlay = (GamePlay) intArgsConstructor.newInstance(Args);
			*/
			
			
			Method mPutGameHand = gp.getClass()
						.getDeclaredMethod("PutGameHand", 
								new Class[] { UUID.class, HandPoker.class });
			
			Object[] ArgsPutHand = new Object[] { PlayerID, hp };
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			mPutGameHand.setAccessible(true);
			
			
			
			mPutGameHand.invoke(gp, ArgsPutHand);

		
		} catch (SecurityException e) {
			fail("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail("Illegal Arugment Exception Thrown");
		} catch (IllegalAccessException e) {
			fail("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail("Invocation Target Exception Thrown");
		}
		return (GamePlay) gp;
		
	}

	@Test
	void GamePlay_Test1() {
		Table t = new Table("Table 1");
		t.AddPlayerToTable(new Player("Bert"));
		t.AddPlayerToTable(new Player("Joe"));
		t.AddPlayerToTable(new Player("Jim"));
		t.AddPlayerToTable(new Player("Jane"));

		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(eSuit.HEARTS, eRank.TWO));
		cards.add(new Card(eSuit.HEARTS, eRank.THREE));
		
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		
		ArrayList<Card> p2Cards = new ArrayList<Card>();
		cards.add(new Card(eSuit.HEARTS, eRank.ACE));
		cards.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		
		HandPoker hp1 = SetHand(cards, new HandPoker());
		HandPoker hp2 = SetHand(p2Cards, new HandPoker());
		
		gp = PutGamePlay(gp, p1.getPlayerID(), hp1);
		
		gp = PutGamePlay(gp, p2.getPlayerID(), hp2);
		
		

	}

}
