package es.ucm.fdi.tp.was;

import static org.junit.Assert.*;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.ttt.TttState;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.Scanner;

public class wasStateTest {

	@Test
	public void test() throws IOException {
		WolfAndSheepState state = new WolfAndSheepState(8);

		// make a few moves, without winning
		int i = 0;
		while (i < 10 && !(state.getWinner()!= -1)) { 
			state = (WolfAndSheepState)takeConsoleAction(state); // Realiza 4 movimientos
			System.out.println(state.toString());		
			i++;
		}
		System.out.print(state.getWinner());
		// save it
		File temp = Files.createTempFile("game", ".state").toFile();
		System.out.println("Game saved as " + temp.getAbsolutePath() + " ...");
		state.save(temp);

		// try to load and compare to saved
		WolfAndSheepState loaded = (WolfAndSheepState) GameState.load(temp);
		System.out.println("Saved:\n" + state.toString());
		System.out.println("Loaded:\n" + loaded.toString());
		assertEquals("loaded == saved", loaded.toString(), state.toString());

		// see if we can continue playing
		loaded =  (WolfAndSheepState)takeConsoleAction(loaded);
		System.out.println("Loaded (after 1 move):\n" + loaded.toString());
	}

	/**
	 * Plays randomly, once.
	 * @param state
	 * @return the resulting state
	 */
	/*
	 * devuelve el estado al ejecutar un 
	 * movimiento por el jugador de consola
	 * */
	private static <S extends GameState<S,A>, A extends GameAction<S,A>> S takeConsoleAction(S state) {
		Scanner sc =new Scanner(System.in);
		GamePlayer player = new ConsolePlayer("Jugador",sc );
		player.join(state.getTurn());
		//GamePlayer player2 = new RandomPlayer("Jugador" );
		//player2.join(state.getTurn());
		return player/*2*/.requestAction(state).applyTo(state);
	}
}

