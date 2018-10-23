package es.ucm.fdi.tp.view;

import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameTable;

public class GuiController <S extends GameState<S,A>, A extends GameAction<S,A>>  {
	// Añadir los atributos de la clase;
	private List<GamePlayer> players;
	private GameTable<S,A>   game;

	public GuiController(List<GamePlayer> players, GameTable<S,A> game) {
		this.players = players;
		this.game = game;
	}
	
	public void stop(){
		this.game.stop();
	}
	
	public void start(){
		
		this.game.start();
	}
	
	public void makeMov(A action){
		this.game.execute(action);	
	}
	public void information (){
		this.game.informar();
	}
	public GameTable<S,A> getgame() {
		return game;
	}
}
