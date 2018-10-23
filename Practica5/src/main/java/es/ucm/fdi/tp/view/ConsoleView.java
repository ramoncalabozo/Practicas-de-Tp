package es.ucm.fdi.tp.view;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameEvent.EventType;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.mvc.GameTable;

public class ConsoleView<S extends GameState<S, A>, A extends GameAction<S, A>> implements GameObserver<S, A> {
	private GameTable<S, A> game; 

	public ConsoleView(GameTable<S, A> game) {
		this.game = game;
		game.addObserver(this);// la vista se añade a sí misma como observadora
	}

@Override
public void notifyEvent(GameEvent<S, A> e) {
		if (e.getType()== EventType.Start ||e.getType()== EventType.Stop||e.getType()== EventType.Info){
			System.out.println(e.toString());
		} else if (e.getType()== EventType.Change){
			System.out.println(e.getState().toString());
		} else {
			System.out.println(e.getError());
		}
	}
}