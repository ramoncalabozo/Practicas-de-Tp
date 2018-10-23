package es.ucm.fdi.tp.mvc;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameError;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.was.WolfAndSheepState;

/**
 * An event-driven game engine.
 * Keeps a list of players and a state, and notifies observers
 * of any changes to the game.
 */
public class GameTable<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObservable<S, A> {
	protected S state;
	private S initState;
	protected List <GameObserver<S,A>> obs;   
  
    public GameTable(S initState) {
       this.state = initState;
       this.initState = initState;
       this.obs = new ArrayList<GameObserver<S,A>>();
    }
	protected void notifyObservers(GameEvent<S,A> e){
		// No entra en el bucle porque no tiene ningún observador en la lista
		for(GameObserver<S,A> o: obs)
			o.notifyEvent(e);
	}
    public void start() {
    	this.state = initState;
    	notifyObservers(new GameEvent<S, A>(EventType.Start, null, state, null, "El juego ha comenzado"));
    }
    public void stop() {
    	notifyObservers(new GameEvent<S, A>(EventType.Stop, null, state, null, "El juego ha finalzidado"));
    }
    public void execute(A action) {
      if(!state.isFinished()&&action.getPlayerNumber()==state.getTurn()){
    	state = action.applyTo(state);
  		notifyObservers(new GameEvent<S,A>(EventType.Change,action,state,null,"El estado del juego ha cambiado"));
      	} else{
      		GameError err = new GameError ("¡No puede realizar esta opción!"); 
      		notifyObservers(new GameEvent<S, A>(EventType.Error, null , state, err, "Se ha producido un error")); 
      	}
      }
    public S getState() {
        return this.state;
    }
    public void informar(){
    	notifyObservers(new GameEvent<S, A>(EventType.Info, null, state, null, "Información"));
    }
    
    public void addObserver(GameObserver<S, A> o) {
       this.obs.add(o);
    }
    public void removeObserver(GameObserver<S, A> o) {
        this.obs.remove(o);
    }
}
