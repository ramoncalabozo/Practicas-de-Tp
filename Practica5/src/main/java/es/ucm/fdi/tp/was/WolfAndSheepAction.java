package es.ucm.fdi.tp.was;

import es.ucm.fdi.tp.base.model.GameAction;

/**
 * An action for Was_and_sheep.
 */

public class WolfAndSheepAction implements GameAction<WolfAndSheepState, WolfAndSheepAction> {

	private static final long serialVersionUID = -7061739732232203112L;// He
																		// puesto
																		// esto
																		// porque
																		// sino
																		// me dá
																		// un
																		// warning

	private int player; // Jugador que realiza la acción
	private int oldrow; // Antigua fila
	private int oldcol; // Antigua columna
	private int newrow; // Nueva fila
	private int newcol; // Nueva columna

	/**
	 * Constructora
	 * 
	 * @param playerNumber
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 */

	public WolfAndSheepAction(int playerNumber, int i, int j, int k, int l) {
		this.player = playerNumber;
		this.oldrow = i;
		this.oldcol = j;
		this.newrow = k;
		this.newcol = l;
	}

	@Override
	/**
	 * Devuelbe le jugador
	 */
	public int getPlayerNumber() {
		return this.player;
	}

	@Override
	/**
	 * Aplica la jugada que le marca el estado que le viene cómo parámetro
	 */
	public WolfAndSheepState applyTo(WolfAndSheepState state) {
		if (player != state.getTurn()) {
			throw new IllegalArgumentException("Not the turn of this player");
		}

		// make move if the wolf can do it

		int[][] board = state.getBoard();
		if (state.movWolf() != 0) {
			board[this.oldrow][this.oldcol] = -1;// Donde estaba antes la ficha
			board[this.newrow][this.newcol] = player;
			// update state
		}
		WolfAndSheepState next;
		if (state.isWinner(board, state.getTurn())) { // Si hay ganador
			next = new WolfAndSheepState(state, board, true, state.getTurn());
			/*
			 * } else if (TttState.isDraw(board)) { // Si hay empate next = new
			 * WolfAndSheepState(state, board, true, -1); }
			 */ } else {
			next = new WolfAndSheepState(state, board, false, -1);
		}
		if (next.getWinner() == -1) {
			if (next.movWolf() == 0) {
				next = new WolfAndSheepState(state, board, true, 1);// gana el
																	// lobo
			} else if (next.movSheep() == 0) {
				next = new WolfAndSheepState(state, board, true, 0);
			} else if (next.estandetras()) {
				next = new WolfAndSheepState(state, board, true, 0); // ganan
																		// las
																		// obejas
			}
		}
		return next;
	}

	/**
	 * Devuelbe la antigua columna
	 * 
	 * @return
	 */
	public int getRow() {
		return this.oldrow;
	}

	/**
	 * Devuelbe la antigua fila
	 * 
	 * @return
	 */
	public int getCol() {
		return this.oldcol;
	}

	/**
	 * Método toString que nos sirve para escribier las jugadas que puede usar
	 * el usuario
	 */
	public String toString() {
		return "place " + player + " at (" + this.newrow + ", " + this.newcol + ")";// He
																					// puesto
																					// lo
																					// mismo
																					// que
																					// en
																					// el
																					// juego
																					// ttt,Para
																					// decir
																					// al
																					// usuario
																					// las
																					// opciones
																					// que
																					// tiene
	}
}
