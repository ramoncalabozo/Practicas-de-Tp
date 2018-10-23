package es.ucm.fdi.tp.launcher;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WolfAndSheepState;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		 GameState<?, ?> EstadoInicial = null;
		 List<GamePlayer> players = new ArrayList<GamePlayer>();
		 GamePlayer Jugador1 = null;
		 GamePlayer Jugador2 = null;
		 if (args.length == 3) {
			EstadoInicial = createInitialState(args[0]); // Creo el nuevo estado   
			 Jugador1 = createPlayer(args[0],args[1],"Ramón");// El jugador 1 ,Siempre se va a llamar Ramón
			if (Jugador1 != null){
			 players.add(Jugador1);
			} 
			Jugador2 = createPlayer(args[0],args[2],"Salvador"); // El jugador 2,Siempre se va a llamar Salvador
			if (Jugador1 != null){
				players.add(Jugador2);	
			}
			if (EstadoInicial != null && Jugador1 != null && Jugador2 != null){ 
			playGame(EstadoInicial,players);
				}
			} else {
			System.out.println(" ERROR EN EL NÚMERO DE ARGUMENTOS");
		}
	}
		/**
		 * Método que se encarga de crear el estado inicial del juego que deseamos
		 * @param gameName
		 * @return
		 */
	
	public static GameState<?, ?> createInitialState(String gameName) { 
		GameState<?, ?> state;
		if (gameName.equalsIgnoreCase("ttt") || gameName.equalsIgnoreCase("was")) {
			if (gameName.equalsIgnoreCase("ttt")) {
				 state = new TttState(3);
			} else {
				 state = new WolfAndSheepState(8);
			}
		} else {
			state = null;
			System.out.println("El juego " + gameName + " no está definido");
		} 		
		
		return state;
	}

	/**
	 * Método que se encarga de crear los jugadores 
	 * sabiendo que sólo pueden ser de tres tipos
	 * random,console o smart
	 * @param gameName
	 * @param playerType
	 * @param playerName
	 * @return
	 */
	public static GamePlayer createPlayer(String gameName, String playerType, String playerName) {
		Scanner sc =new Scanner(System.in);
		GamePlayer player = null;
		if (playerType.equalsIgnoreCase("console") || playerType.equalsIgnoreCase("rand") || playerType.equalsIgnoreCase("smart")) {
			if (playerType .equalsIgnoreCase("rand")){
				player = new RandomPlayer(playerName);
			} else if(playerType.equalsIgnoreCase("smart")){
				player = new SmartPlayer(playerName,5);
				} else if (playerType.equalsIgnoreCase("console")) {
					player = new ConsolePlayer(playerName,sc);
						} else {
							player = null;
						}
		
				} else {
					System.out.println("El jugador " + playerType + " no está definido");
				}
		return player;
	}
	/**
	 * Método que empieza a mover todo el juego
	 * @param initialState
	 * @param players
	 * @return
	 */
	public static <S extends GameState<S, A>, A extends GameAction<S, A>> int playGame(GameState<S, A> initialState,
			List<GamePlayer> players) {
		int playerCount = 0;
		for (GamePlayer p : players) {
			p.join(playerCount++); // welcome each player, and assign
									// playerNumber
		}
		@SuppressWarnings("unchecked")
		S currentState = (S) initialState;

		while (!currentState.isFinished()) {
			// request move
			A action = players.get(currentState.getTurn()).requestAction(currentState);
			// apply move
			currentState = action.applyTo(currentState);
			System.out.println("After action:\n" + currentState);

			if (currentState.isFinished()) {
				// game over
				String endText = "The game ended: ";
				int winner = currentState.getWinner();
				if (winner == -1) {
					endText += "draw!";
				} else {
					endText += "player " + (winner + 1) + " (" + players.get(winner).getName() + ") won!";
				}
				System.out.println(endText);
			}
		}
		return currentState.getWinner();
	}
}
