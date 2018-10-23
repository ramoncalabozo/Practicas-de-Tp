package es.ucm.fdi.tp.view;

import java.awt.Color;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;


// En está clase hay que llamar a isValid();

public class TttView extends RectBoardGameView <TttState, TttAction>{
	// añadir los parametros de la clase
	private TttState estado;
	final static int DIM = 3;

	public TttView (){
		estado = new TttState(DIM);
		fillBoard();
	}
	@Override
	protected int getNumCols() {
		// Devuelve el número de filas del tablero
		return this.DIM;
	}

	@Override
	protected int getNumRows() {
		// Devuelve el número de columnas del tablero
		return this.DIM;
	}

	@Override
	protected Integer getPosition(int row, int col) {
		// Devuelbe el valor de una posición del tablero en el estado actual
		return  estado.at(row, col);
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCout, int mouseButton) {
		System.out.println(row + col + clickCout + mouseButton);
	}

	@Override
	protected void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setController(GuiController gameCtrl) {
		// TODO Auto-generated method stub

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
		//System.out.println("Selected (" + row + "," + col + ")." + " Click on destination cell.");
	}
	@Override
	protected void actualizar() {
		this.estado = (TttState) this.state;
	}

}
