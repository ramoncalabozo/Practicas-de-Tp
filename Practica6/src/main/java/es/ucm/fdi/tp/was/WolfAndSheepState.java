package es.ucm.fdi.tp.was;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.tp.base.model.GameState;

public class WolfAndSheepState extends GameState<WolfAndSheepState, WolfAndSheepAction> {

	private static final long serialVersionUID = 3215156352054119135L;// Lo he
	// puesto
	// yo
	// pero
	// no sé
	// si
	// está
	// bién
	private final int turn; // Turno del jugador al que le toca mover
	private final boolean finished; // Boleano que indica si la partida ha
	// acabado
	private final int[][] board; // Tablero
	private final int winner; // Indica el jugador que ha ganado la partida

	private final int dim; // Dimensiones del tablero

	final static int EMPTY = -1;
	final static int WOLF = 0;
	final static int SHEEP = 1;
	final static int DIM = 8;

	public WolfAndSheepState(int dim) {
		/**
		 * Constructora de la clase Inicializo los atributos de la clase y les
		 * doy valor
		 */
		super(2);//Numero de jugadores
		if (dim != DIM) {
			throw new IllegalArgumentException("Expected dim to be 8");
		}

		this.dim = dim;
		board = new int[dim][];
		for (int i = 0; i < dim; i++) {
			board[i] = new int[dim];
			for (int j = 0; j < dim; j++)
				board[i][j] = EMPTY;
		}
		this.board[7][0] = WOLF; // Lobo
		for (int i = 1; i < dim; i = i + 2) {
			this.board[0][i] = SHEEP; // Ovejas
		}
		this.turn = 0;// Empieza el lobo a jugar // aquí podemos hacer un Random para ver si 
					 // queremos que empiece un jugador aleatorio
		this.winner = -1;
		this.finished = false;
	}

	/**
	 * Constructora de la clase que nos sirve para crear el siguiente estado de
	 * un estado ya existente
	 */
	public WolfAndSheepState(WolfAndSheepState prev, int[][] board, boolean finished, int winner) {
		super(2);
		this.dim = prev.dim;
		this.board = board;
		this.turn = (prev.turn + 1) % 2;// Cambio de turno// division entre numero de jugadores
		this.finished = finished;
		this.winner = winner;
	}

	@Override
	/**
	 * Devuelbe el turno
	 */
	public int getTurn() {
		/**
		 * Devuelve el turno
		 */
		return this.turn;
	}

	@Override
	/**
	 * Crea una lista con los movimientos posibles
	 */
	public List<WolfAndSheepAction> validActions(int playerNumber) {
		List<WolfAndSheepAction> valid = new ArrayList<>();
		if (finished) {
			return valid;
		}

		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				// Movimientos posibles para el lobo
				if (at(i, j) == -1 && playerNumber == 0) {
					if (i - 1 >= 0 && j - 1 >= 0 && this.board[i - 1][j - 1] == WOLF) {
						valid.add(new WolfAndSheepAction(playerNumber, i - 1, j - 1, i, j));
					} else if (i - 1 >= 0 && j + 1 < this.dim && this.board[i - 1][j + 1] == WOLF) {
						valid.add(new WolfAndSheepAction(playerNumber, i - 1, j + 1, i, j));
					} else if (i + 1 < this.dim && j - 1 >= 0 && this.board[i + 1][j - 1] == WOLF) {
						valid.add(new WolfAndSheepAction(playerNumber, i + 1, j - 1, i, j));
					} else if (i + 1 < this.dim && j + 1 < this.dim && this.board[i + 1][j + 1] == WOLF) {
						valid.add(new WolfAndSheepAction(playerNumber, i + 1, j + 1, i, j));
					}
					// Movimientos posibles para las ovejas
				} else if (at(i, j) == -1 && playerNumber == 1 && i > 0) {
					if (j - 1 >= 0 && this.board[i - 1][j - 1] == SHEEP) {
						valid.add(new WolfAndSheepAction(playerNumber, i - 1, j - 1, i, j));
					}
					/* else */ if (j + 1 < this.dim && this.board[i - 1][j + 1] == SHEEP) {
						valid.add(new WolfAndSheepAction(playerNumber, i - 1, j + 1, i, j));
					}
				}
			}
		}
		return valid;
	}
	public boolean isValid(WolfAndSheepAction action){
		boolean valid = false;
		List<WolfAndSheepAction> listaDeAcciones = validActions(action.getPlayerNumber());
		int i = 0;
		while (i<listaDeAcciones.size()&&!valid){
			if (action.getCol() == listaDeAcciones.get(i).getCol() && action.getRow()==listaDeAcciones.get(i).getRow()
					&& action.getPlayerNumber() == listaDeAcciones.get(i).getPlayerNumber()
					&& action.getNewCol() == listaDeAcciones.get(i).getNewCol()
					&& action.getNewRow() == listaDeAcciones.get(i).getNewRow()){
				valid = true;
			}
			i++;
		}
		return valid;
	}

	/**
	 * Devuelbe el jugador que está en una determinada zona del tablero
	 */
	public int at(int row, int col) {
		return board[row][col];
	}

	@Override
	/**
	 * Devuelbe un booleano para saber si ha acabado ya la partida
	 */
	public boolean isFinished() {
		return this.finished;
	}

	@Override
	public int getWinner() {
		return this.winner;
	}

	/*
	 * En esta práctica no existe el estado de empata
	 */

	/**
	 * Mira si hay algún ganador en la partida
	 */

	public boolean isWinner(int[][] board, int turn) { // Lo he cambiado con
		// respecto al del ttt
		// para que no sea
		// estático
		boolean isWinner = false;
		int i = 1;
		if (turn == 0) { // Mira si el lobo puede ganar
			if (movSheep() != 0 || !estandetras()) {
				while (i < board.length && !isWinner) {
					if (board[0][i] == WOLF) {
						isWinner = true;
					}
					i = i + 2;
				}
			} else {
				isWinner = true;
			}
		} else {
			// Comprobar que esto funciona
			if (movWolf() == 0) {
				isWinner = true;
			}
		}
		return isWinner;
	}

	/**
	 * Devuelbe el tablero
	 **/
	public int[][] getBoard() {
		int[][] copy = new int[board.length][];
		for (int i = 0; i < board.length; i++) {
			copy[i] = board[i].clone(); // Va clonando el tablero
		}
		return copy;
	}

	/**
	 * Devuelbe el número de movimientos posibles para el lobo
	 * 
	 * @param state
	 * @return
	 */
	public boolean estandetras() {
		boolean estan = false;
		int lobo = 0;
		int obeja = this.dim;
		for (int i = 0; i < this.dim; i++) {
			for (int j = 0; j < this.dim; j++) {
				if (this.board[i][j] == WOLF) {
					lobo = i;
				} else if (this.board[i][j] == SHEEP && i < obeja) {
					obeja = i;
				}
			}
		}
		if (lobo < obeja) {
			estan = true;
		}
		return estan;
	}

	public int movWolf() {
		int contador = 0;
		List<WolfAndSheepAction> actions = validActions(0);
		for (WolfAndSheepAction a : actions) {
			contador++;
		}
		return contador;
	}

	/**
	 * Devuelbe el número de movimientos posibles para las obejas
	 * 
	 * @param state
	 * @return
	 */
	public int movSheep() {// Cómo le meto la lista
		int contador = 0;
		List<WolfAndSheepAction> actions = validActions(1);
		for (WolfAndSheepAction a : actions) {
			contador++;
		}
		return contador;
	}

	/*public boolean isValid(int columnaorigen,int filaorignen,int columnadestino,int filadestino){
		if (this.turn == 0){
			if ((this.at(columnadestino,filadestino) == 1)&&){
				return true
			}

		} else{
			return false;
		}
	}*/
	public String toString() {
		/**
		 * Método que se encarga en pintar el tablero
		 */
		String r = ("  0");
		for (int i = 1; i < this.dim; i++) {
			r = r + (" " + i);
		}
		r = r + ("\n");
		for (int i = 0; i < this.dim; i++) {
			r = r + ((i));
			r = r + ("|");
			for (int j = 0; j < this.dim; j++) {
				if (this.board[i][j] == EMPTY) {
					r = r + (" |");
				} else {
					if (this.board[i][j] == WOLF) {
						r = r + ("W|");
					} else {
						r = r + ("S|");
					}
				}

			}
			r = r + ("\n");
		}
		return r;
	}
}
