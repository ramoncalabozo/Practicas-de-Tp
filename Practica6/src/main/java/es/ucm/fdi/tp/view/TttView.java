package es.ucm.fdi.tp.view;

import java.awt.Color;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;


// En está clase hay que llamar a isValid();

public class TttView extends RectBoardGameView <TttState, TttAction>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TttView (){
		fillBoard();
	}
	@Override
	protected int getNumCols() {
		if (state == null){
			return 0;
	}else{
		return this.state.getBoard().length;
	}
}

	@Override
	protected int getNumRows() {
		if (state == null){
			return 0;
	}else {
		return this.state.getBoard().length;
	}
}

	@Override
	protected Integer getPosition(int row, int col) {
		// Devuelbe el valor de una posición del tablero en el estado actual
		return  this.state.at(row, col);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setController(@SuppressWarnings("rawtypes") GuiController gameCtrl) {
		this.gameCtrl = gameCtrl;

	}
	@Override
	protected Color getColor(int player) {
		if( this.VentanaPrincipal==null){
			if (player == 0){
				return Color.RED;
			} else {
				return Color.BLUE;
			}

		}else{
			return this.VentanaPrincipal.getColor(player);
		}
	}
	@Override
	protected ImageIcon getIcon(int player) {
		if (player == 0) {
			return new ImageIcon(Utils.loadImage("cero.png"));
		} else {
			return new ImageIcon(Utils.loadImage("uno.png"));
		}
	}
	@Override
	protected Color getBackground(int row, int col) {
		if ((row + col)%2==0 ){
			return Color.LIGHT_GRAY;
		} else{
			return Color.BLACK;
		}
	}
	@Override
	protected void cellClicked(int row, int col) {
		TttAction action = new TttAction(this.state.getTurn(),row,col);
		if(state.isValid(action)){
			this.gameCtrl.makeMov(action);
		} else {
			this.gameCtrl.error();
		}	
	}

	@Override
	protected void actualizar() {
		this.state = (TttState) this.state;

	}

}
