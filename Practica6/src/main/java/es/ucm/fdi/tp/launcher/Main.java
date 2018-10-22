package es.ucm.fdi.tp.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.base.player.RandomPlayer;
import es.ucm.fdi.tp.base.player.SmartPlayer;
import es.ucm.fdi.tp.chess.ChessState;
import es.ucm.fdi.tp.mvc.GameTable;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.view.ChessView;
import es.ucm.fdi.tp.view.ConsoleController;
import es.ucm.fdi.tp.view.ConsoleView;
import es.ucm.fdi.tp.view.GameView;
import es.ucm.fdi.tp.view.GameWindow;
import es.ucm.fdi.tp.view.GuiController;
import es.ucm.fdi.tp.view.TttView;
import es.ucm.fdi.tp.view.WasView;
import es.ucm.fdi.tp.was.WolfAndSheepState;
import java.util.Scanner;

import javax.swing.SwingUtilities;

/*
 * En el enunciado de la práctica biene bien explicado como se hace esto
 * */
public class Main {

	@SuppressWarnings("unchecked")
	private static GameTable<?, ?> createGame(String gType) {
		GameState<?, ?> state = createInitialState(gType);
		GameTable<?, ?> table;
		if (state != null){
			table =  new GameTable(state);
		}
		else{
			table = null;
		}
		return table;
	}

	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startConsoleMode(GameTable<S, A> game,String playerModes[]) {
		List<GamePlayer> players = new ArrayList<GamePlayer>();
		GamePlayer Jugador1 = null;
		GamePlayer Jugador2 = null;
		Jugador1 = createPlayer(playerModes[0], "Ramón");
		Jugador2 = createPlayer(playerModes[1], "Salvador");
		if (Jugador1 != null && Jugador2 != null) {
			players.add(Jugador1);
			players.add(Jugador2);
			new ConsoleView<S, A>(game);
			new ConsoleController<S, A>(players, game).run();
		}
	}

	private static <S extends GameState<S, A>, A extends GameAction<S, A>> void startGUIMode(String gType,GameTable<S, A> game, String playerModes[]) throws InvocationTargetException, InterruptedException {
		GuiController<S, A> controlador = new GuiController<S, A>( game);
		SwingUtilities.invokeAndWait(
				new Runnable(){
					public void run(){
						if(gType.equalsIgnoreCase("WAS")){
							GameState state = new WolfAndSheepState(8);
							for (int i = 0; i < state.getPlayerCount();i++){
								GameView<S, A> vistaTablero1 = (GameView<S, A>) new WasView();
								GameWindow<S, A> v1 = new GameWindow <S,A>(i,new RandomPlayer(null),new ConcurrentAiPlayer(null),vistaTablero1,controlador);
								v1.setVisible(true);
							}
						} else if (gType.equalsIgnoreCase("TTT")) {
							GameState state = new TttState(3);
							for (int i = 0; i < state.getPlayerCount();i++){
								GameView<S, A> vistaTablero1 = (GameView<S, A>) new TttView();
								GameWindow<S, A> v1 = new GameWindow <S,A>(i,new RandomPlayer(null),new ConcurrentAiPlayer(null),vistaTablero1,controlador);
								v1.setVisible(true);
							}
						} else {
							GameState state = new ChessState();
							for (int i = 0; i < state.getPlayerCount();i++){
								GameView<S, A> vistaTablero1 = (GameView<S, A>) new ChessView();
								GameWindow<S, A> v1 = new GameWindow <S,A>(i,new RandomPlayer(null),new ConcurrentAiPlayer(null),vistaTablero1,controlador);
								v1.setVisible(true);
							}
						}
					}
				});
		controlador.start();
	}
	private static void usage() {
		System.out.println("se ha producido un error el programa se cerrará autoáticamente");
	}
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		if (args.length < 2) {
			usage();
			System.exit(1);
		}
		GameTable<?, ?> game = createGame(args[0]);
		if (game == null) {
			System.err.println("Invalid game");
			usage();
			System.exit(1);
		}
		String[] otherArgs = Arrays.copyOfRange(args, 2, args.length);
		switch (args[1]) {
		case "console":
			startConsoleMode(game, otherArgs);
			break;
		case "gui":
			startGUIMode(args[0], game, otherArgs);
			break;
		default:
			System.err.println("Invalid view mode: " + args[1]);
			usage();
			System.exit(1);
		}
	}
	
	/*
	 * Estos métodos los usan los métodos programados arriba
	 */
	
	/**
	 * Método que se encarga de crear el estado inicial del juego que deseamos
	 * 
	 * @param gameName
	 * @return
	 */

	public static GameState<?, ?> createInitialState(String gameName) {
		GameState<?, ?> state;
		if (gameName.equalsIgnoreCase("ttt") || gameName.equalsIgnoreCase("was")|| gameName.equalsIgnoreCase("chess")) {
			if (gameName.equalsIgnoreCase("ttt")) {
				state = new TttState(3);
			} else if (gameName.equalsIgnoreCase("was")) {
				state = new WolfAndSheepState(8);
			}else{
				state = new ChessState();
			}
		} else {
			state = null;
			System.out.println("El juego " + gameName + " no está definido");
		}

		return state;
	}

	public static GamePlayer createPlayer(String playerType, String playerName) {
		Scanner sc = new Scanner(System.in);
		GamePlayer player = null;
		if (playerType.equalsIgnoreCase("manual") || playerType.equalsIgnoreCase("rand")
				|| playerType.equalsIgnoreCase("smart")) {
			if (playerType.equalsIgnoreCase("rand")) {
				player = new RandomPlayer(playerName);
			} else if (playerType.equalsIgnoreCase("smart")) {
				player = new SmartPlayer(playerName, 5);
			} else if (playerType.equalsIgnoreCase("manual")) {
				player = new ConsolePlayer(playerName, sc);
			} else {
				player = null;
			}

		} else {
			System.out.println("El jugador " + playerType + " no está definido");
		}
		return player;
	}
}