
package es.ucm.fdi.tp.view;
import javax.swing.JComponent;
import javax.swing.JPanel;

import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class GameView <S extends GameState<S, A>, A extends GameAction<S, A>> extends JPanel {
	/**
	 * 
	 */
	
private static final long serialVersionUID = 1L;
	// añadir los parametros de la clase
	public GameView(){
		
	}

public abstract void setVentanaPrincipal(GameWindow<S,A>VentanaPrincipal);
public abstract void enable();
public abstract void disable();
public abstract void update(S state);
public abstract void setController(GuiController<S, A>gameCtrl);
}
