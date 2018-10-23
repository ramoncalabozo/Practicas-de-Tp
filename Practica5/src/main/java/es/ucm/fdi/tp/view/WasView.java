package es.ucm.fdi.tp.view;

import java.awt.Color;

import javax.swing.ImageIcon;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.ttt.TttAction;
import es.ucm.fdi.tp.ttt.TttState;
import es.ucm.fdi.tp.was.WolfAndSheepAction;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class WasView extends RectBoardGameView <WolfAndSheepState, WolfAndSheepAction>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WolfAndSheepState estado;
	final static int DIM = 8;


	public WasView() {
		this.estado = new WolfAndSheepState(DIM);
		fillBoard();
	}

	@Override
	protected int getNumCols() {
		return this.DIM;
	}

	@Override
	protected int getNumRows() {
		return this.DIM;
	}

	@Override
	protected Integer getPosition(int row, int col) {
		return this.estado.at(row, col);
	}

	@Override
	protected void mouseClicked(int row, int col, int clickCout, int mouseButton) {
		// TODO Auto-generated method stub
		// dterminar si
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
		this.estado = (WolfAndSheepState) this.state;
	}
	@Override
	protected void cellClicked(int row, int col) {
		System.out.println("Selected (" + row + "," + col + ")." + " Click on destination cell.");
	}

	@Override
	protected void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setController(GuiController<WolfAndSheepState, WolfAndSheepAction> gameCtrl) {
		// TODO Auto-generated method stub
		
	}
}
