package es.ucm.fdi.tp.view;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class ConsoleController<S extends GameState<S,A>, A extends GameAction<S,A>> implements Runnable {
// Añadir los atributos de la clase;
	private List<GamePlayer> players;
	private GameTable<S,A> game;
	
public ConsoleController(List<GamePlayer> players, GameTable<S,A> game) {
	this.players = players;
	this.game = game;
}
public void run() {
	int playerCount = 0;
	for (GamePlayer p : players) {
		p.join(playerCount++); // asigna los jugadores
	}
	
	S currentState = (S) this.game.getState();
	this.game.start();// Informa de que se empieza el juego
	while (!currentState.isFinished()) {
		A action = players.get(currentState.getTurn()).requestAction(currentState);// Bucle de Jugadores --> No sé sie sto funciona   
		this.game.execute(action);
		 currentState = (S) this.game.getState();	
		}
		this.game.stop();
	}
}
