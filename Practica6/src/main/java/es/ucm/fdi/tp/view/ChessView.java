package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.chess.ChessAction;
import es.ucm.fdi.tp.chess.ChessBoard.Piece;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.chess.ChessState;

public class ChessView extends RectBoardGameView <ChessState, ChessAction>{
	/**
	 *  
	 */
	boolean primer_click = false;
	private int primera_fila;
	private int primera_columna;

	final static int DIM = 8;
	private static final long serialVersionUID = 1L;

	public ChessView(){
		fillBoard();
	}
	@Override
	protected void actualizar() {
		this.state = (ChessState) this.state;
	}

	@Override
	protected int getNumCols() {
		if (state == null){
			return 0;
		}else {
			return this.state.getDimension();
		}
	}

	@Override
	protected int getNumRows() {
		if (state == null){
			return 0;
		}else {
			return this.state.getDimension();
		}
	}

	@Override
	protected Integer getPosition(int row, int col) {
		return this.state.at(row, col);
	}

	@Override
	protected void cellClicked(int row, int col) {
		boolean encontrar = false;
		int contador = 0;
		if(!primer_click){
			this.primera_fila = row;
			this.primera_columna = col;
			primer_click = true;
		}
		else{
			ChessAction chessAction = new ChessAction(state.getTurn(), primera_fila, primera_columna, row, col);
			if(this.state.isValid(chessAction)){
				this.gameCtrl.makeMov(chessAction);
				this.primer_click = false;
			}
			else {
				List <ChessAction> lista = this.state.validActions(this.state.getTurn());
				ChessAction accion = null;
				while (!encontrar && contador <lista.size()){
				 accion = lista.get(contador);
					if (accion.getDstCol() == col && accion.getDstRow() == row
							&& accion.getSrcCol()== primera_columna && accion.getSrcRow()== primera_fila){
						encontrar = true;
					}
					contador++;
				}
				if (encontrar){
					this.gameCtrl.makeMov(accion);
				} else {
					this.gameCtrl.error();
				}
				this.primer_click = false;

			}
		}

	}

	@Override
	protected Color getColor(int player) {
		int jugador;
		if (player < 9){
			jugador = 0;
		}
		else{
			jugador = 1;
		}
		if( this.VentanaPrincipal == null){

			if (jugador == 0){
				return Color.RED;
			} else {
				return Color.BLUE;
			}

		}else{
			return this.VentanaPrincipal.getColor(jugador);
		}
	}

	@Override
	protected ImageIcon getIcon(int player) {
		return  new ImageIcon(Utils.loadImage(Piece.iconName((byte) player)));
	}

	@Override
	protected Color getBackground(int row, int col) {
		if ((row + col) % 2 == 0) {
			return Color.LIGHT_GRAY;
		} else {
			return Color.BLACK;
		}
	}

	@Override
	public void setController(GuiController<ChessState, ChessAction> gameCtrl) {
		this.gameCtrl = gameCtrl;
	}

}
