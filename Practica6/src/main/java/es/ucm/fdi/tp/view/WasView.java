package es.ucm.fdi.tp.view;

import java.awt.Color;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class WasView extends RectBoardGameView <WolfAndSheepState, WolfAndSheepAction>{
	/**
	 * 
	 */
	boolean primer_click = false;
	private int primera_fila;
	private int primera_columna;
	private static final long serialVersionUID = 1L;

	public WasView() {
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
		} else {
			return this.state.getBoard().length;
		}
	}

	@Override
	protected Integer getPosition(int row, int col) {
		return this.state.at(row, col);
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

		if ((row + col) % 2 == 0) {
			return Color.LIGHT_GRAY;
		} else {
			return Color.BLACK;
		}
	}

	public void actualizar(){
		this.state = (WolfAndSheepState) this.state;
	}
	@Override
	protected void cellClicked(int row, int col) {
		if(!primer_click){
			this.primera_fila = row;
			this.primera_columna = col;
			primer_click = true;
		}
		else{
			WolfAndSheepAction wasAction = new WolfAndSheepAction(state.getTurn(), primera_fila, primera_columna, row, col);
			if(this.state.isValid(wasAction)){
				this.gameCtrl.makeMov(wasAction);
				this.primer_click = false;
			}
			else{
				this.gameCtrl.error();
				this.primer_click = false;

			}
		}
	}

	@Override
	public void setController(GuiController<WolfAndSheepState, WolfAndSheepAction> gameCtrl) {
		this.gameCtrl = gameCtrl;

	}
}
