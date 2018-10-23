package es.ucm.fdi.tp.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GameState;

public abstract class RectBoardGameView<S extends GameState<S, A>, A extends GameAction<S, A>> extends GameView<S, A> {
	// Añadir los atributos de la clase
	private JButton btnArray[][];
	protected GameState<S, A> state;
	protected GameWindow VentanaPrincipal;
	
	@Override
	public void enable() {
		for (int i = 0 ; i < this.btnArray.length;i++){ 
			for (int j = 0; j < this.btnArray.length;j++){
				this.btnArray[i][j].setEnabled(false);
			}
		}
	}

	@Override
	public void disable() {
		for (int i = 0 ; i < this.btnArray.length;i++){ 
			for (int j = 0; j < this.btnArray.length;j++){
				this.btnArray[i][j].setEnabled(true);
			}
		}
	}

	@Override
	public void update(GameState state) {
		this.state = state;
		actualizar();
		this.revalidate();
		this.fillBoard(); 
	}

	public void fillBoard() {
		// Si el tablero tenía algún componente, lo eliminamos
		this.removeAll();

		int cols = getNumCols();
		int rows = getNumRows();
		if (cols <= 0 || rows <= 0) {
			JLabel lbl = new JLabel("Waiting for game to start!");
			lbl.setForeground(Color.red);
			this.add(lbl);
			return;
		}
		
		int sep = getSepPixels();
		setLayout(new GridLayout(rows, cols,sep,sep));
		btnArray = new JButton[rows][cols];
	for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				final int row = i;  
				final int col = j;   
				btnArray[row][col] = new JButton("");
				Integer pos = getPosition(row,col);
				if (pos != null) {
						if (pos == -1){
							btnArray[row][col].setBackground(getBackground(row,col));
						} else {
						btnArray[row][col].setBackground(getColor(pos));
						btnArray[row][col].setIcon(getIcon(pos));
						}
					}
					btnArray[row][col].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// Aquí  no ha sido necesario utilizar una clase que extienda JButton
						// porque utilizamos dos variables locales final para indicar fila y columna.
						// Pero puedes hacerlo si te parece más claro.
						cellClicked(row, col);
					}

				});
				this.add(btnArray[row][col]);
			}
		}

		this.setPreferredSize(new Dimension(450,510));// El lo tenía cómo 400,400
	}

	public void setVentanaPrincipal(GameWindow<S,A>VentanaPrincipal){
		this.VentanaPrincipal = VentanaPrincipal;
	}
	protected GameState<S, A> getState(){
		return this.state;
	}
	protected abstract void actualizar();
	protected abstract int getNumCols();

	protected abstract int getNumRows();

	protected abstract Integer getPosition(int row, int col);

	protected abstract void mouseClicked(int row, int col, int clickCout, int mouseButton);

	protected abstract void keyTyped(int keyCode);

	protected abstract void cellClicked(int row, int col);

	protected abstract Color getColor(int player);

	protected abstract ImageIcon getIcon(int player);

	protected abstract Color getBackground(int row, int col);

	protected int getSepPixels() {
		return 2;
	}

}
