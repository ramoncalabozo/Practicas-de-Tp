package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObserver;
import es.ucm.fdi.tp.was.WolfAndSheepState;

public class GameWindow<S extends GameState <S,A>,A extends GameAction <S,A>> extends JFrame implements GameObserver <S,A> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int playerId;
	private S estado;
	private GamePlayer JugadorRandom;
	private GamePlayer JugadorSmart;
	private GamePlayer Jugador;
	GuiController<S, A> gameCtrl;

	private GameView<S,A> gameView;
	private ColorChooser colorChooser;
	private Map<Integer, Color> colors;
	private JTextArea StatusMessage;
	private JButton reiniciar;
	private JButton aleatorio;
	private JButton inteligente;
	boolean acabado = false;
	enum TiposJugadores{
		manual,
		smart,
		random;
	}
	public GameWindow(int playerId,GamePlayer randPlayer,GamePlayer smartPlayer,GameView<S, A> gameView,GuiController<S, A> gameCtrl){
		super("(view for player " + playerId + ")");
		this.playerId = playerId;
		this.JugadorRandom = randPlayer;
		this.JugadorRandom.join(playerId);
		this.JugadorSmart = smartPlayer;
		this.JugadorSmart.join(playerId);
		this.gameView = gameView;
		this.gameCtrl = gameCtrl;
		this.setLocation(25,25);
		this.setSize(700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		gameCtrl.getgame().addObserver(this);// Comprobar que esto se tiene que hacer
		GamePlayer consolePlayer = new ConsolePlayer("Jugador " + playerId, new Scanner(System.in));
		consolePlayer.join(playerId);
		JComboBox<TiposJugadores> comboJugadores;
		
		this. aleatorio = new JButton();
		aleatorio.setIcon(new ImageIcon(Utils.loadImage("dice.png")));
		aleatorio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						gameCtrl.makeMov(randPlayer.requestAction(estado));
					}
				});
			}
		});

		this. inteligente = new JButton();

		inteligente.setIcon(new ImageIcon(Utils.loadImage("nerd.png")));
		inteligente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						gameCtrl.makeMov(smartPlayer.requestAction(estado));
					}
				});
			}
		});
		this. reiniciar = new JButton();
		reiniciar.setIcon(new ImageIcon(Utils.loadImage("restart.png")));
		reiniciar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						gameCtrl.start();// No hace bien lo que debe hacer
					}
				});
			}
		});


		JButton salir = new JButton();
		salir.setIcon(new ImageIcon(Utils.loadImage("exit.png")));
		salir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						int Salir = JOptionPane.showConfirmDialog (null, "¿Quiere Salir de la aplicación ?",  "Salir",
								JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
						if (Salir == 0){
							gameCtrl.stop();// Comprobar que esto se tiene que hacer
						}
					}
				});
			}
		});

		comboJugadores = new JComboBox<TiposJugadores>(TiposJugadores.values());
		comboJugadores.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						// Esto puede servirme para hacer el cambio de tipo de jugador
						if (comboJugadores.getSelectedIndex() == 0){
							Jugador = consolePlayer;
						}
						else if (comboJugadores.getSelectedIndex() == 1){
							Jugador = smartPlayer;
						} else {
							Jugador = randPlayer;	
						}
						gameCtrl.information();
					}
				});
			}
		});

		JLabel espacio = new JLabel("                                                                               ");
		JLabel modo = new JLabel("  Player Mode:  ");
		JToolBar PanelNorte = new JToolBar();
		PanelNorte.add(aleatorio);
		PanelNorte.add(inteligente);
		PanelNorte.add(reiniciar);
		PanelNorte.add(salir);
		PanelNorte.add(modo);
		PanelNorte.add(comboJugadores);
		PanelNorte.add(espacio);
		this.Jugador = consolePlayer;


		// En estre trozo de código nos encargaremos de la parte derecha de la vista
		this. StatusMessage = new JTextArea(20,20);
		StatusMessage.setEditable(false);
		MyTableModel tablaModal = new MyTableModel();
		//tablaModal.getRowCount();
		JScrollPane scrollLista = new JScrollPane(StatusMessage);
		JPanel PanelEste = new JPanel();
		JPanel PanelEste1 = new JPanel();
		JPanel PanelEste2 = new JPanel();
		colors = new HashMap<Integer,Color>();
		colorChooser = new ColorChooser(new JFrame(), "Choose Line Color", Color.BLACK);
		PanelEste.setLayout(new BoxLayout(PanelEste, BoxLayout.PAGE_AXIS));
		//PanelEste1.add(StatusMessage);
		PanelEste1.add(scrollLista);
		PanelEste1.setBorder(BorderFactory.createTitledBorder("StatusMessage"));
		JTable table = new JTable(tablaModal){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);

				// the color of row 'row' is taken from the colors table, if
				// 'null' setBackground will use the parent component color.
				if (col == 1)
					comp.setBackground(colors.get(row));
				else
					comp.setBackground(Color.WHITE);
				comp.setForeground(Color.BLACK);
				return comp;
			}
		};
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					changeColor(row);
					gameView.update(GameWindow.this.estado);				}
			}

		});

		PanelEste2.add(table);
		PanelEste2.setBorder(BorderFactory.createTitledBorder("PlayerInformation"));
		PanelEste.add(PanelEste1);
		PanelEste.add(PanelEste2);


		// En este trozo de código nos encargaremos de la parte central de la vista
		JPanel PanelCentral = new JPanel();
		PanelCentral.add(this.gameView);

		/*this.gameView.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = gameView.rowAtPoint(evt.getPoint());
				int col = gameView.columnAtPoint(evt.getPoint());
				int clickCout = gameView.
				if (row >= 0 && col >= 0) {

				}

		});*/

		// añadimos los subpaneles al panel
		JPanel PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(new BorderLayout());
		this.add(PanelNorte,BorderLayout.NORTH);
		this.add(PanelEste,BorderLayout.EAST);
		this.add(PanelCentral,BorderLayout.CENTER);
		gameView.setVentanaPrincipal(this);
	}
	// Método que permite cambiar de color la tabla

	// NO SÉ DESDE DONDE TENGO QUE LLAMARLE
	private void changeColor(int row) {
		colorChooser.setSelectedColorDialog(colors.get(row));
		colorChooser.openDialog();
		if (colorChooser.getColor() != null) {
			colors.put(row, colorChooser.getColor());
			System.out.println(colors);

		}
	} 

	@Override
	public void notifyEvent(GameEvent<S, A> e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				handleEvent(e); // actualiza componentes visuales.
			}

			@SuppressWarnings("incomplete-switch")
			private void handleEvent(GameEvent<S, A> e) {
				estado = e.getState();
				switch(e.getType()){
				case Start:
					StatusMessage.setText(e.toString() );
					acabado = false;
					;break;
				case Change:
					StatusMessage.setText(StatusMessage.getText()+ "\n" +"* " + e.getAction());
					StatusMessage.setText(StatusMessage.getText() + "\n" + "Turno del jugador " + estado.getTurn() );
					if (estado.getTurn()==playerId){
						StatusMessage.setText(StatusMessage.getText() + "\n" + "Tu turno" );
					}
					;break;
				case Stop:
					System.exit(0);
					break;
				case Error:
					JOptionPane.showMessageDialog(null, " No puedes realizar esa opción" , 
							"Error" ,JOptionPane.ERROR_MESSAGE);
					;break;
				}
				gameView.update(estado);
				if (!estado.isFinished()){
					if (estado.getTurn()!= playerId){
						bloquear();
					} else {
						desbloquear();
					}
					if (playerId == estado.getTurn() && !estado.isFinished() &&(Jugador.equals(JugadorRandom) || Jugador.equals(JugadorSmart))){
						ejecuta(Jugador);
					}

				} else {
					desbloquear();	
					if (!acabado){
					finalizar();
					acabado = true;
					}
				}
			}
		});
	}
	public void ejecuta(GamePlayer Jugador){
		if (Jugador.equals(JugadorSmart)){
			this.gameCtrl.makeMov(JugadorSmart.requestAction(estado));
		} else {
			this.gameCtrl.makeMov(JugadorRandom.requestAction(estado));
		}
	}

	public Color getColor(int jugador){
		if( this.colors.get(jugador) == null){
			//this.colors.put(jugador,Utils.colorsGenerator().next()); // Siempre me genera el mismo Color
			if (jugador == 0){
				return Color.RED;
			} else {
				return Color.BLUE;
			}
		}
		return this.colors.get(jugador);
	}
	public void finalizar (){
		if (this.estado.getWinner()==this.playerId){
			JOptionPane.showMessageDialog(null, "HAS GANADO", 
					"Ganador" ,JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "HAS PERDIDO ", 
					"Perdedor" ,JOptionPane.INFORMATION_MESSAGE);
		}
		aleatorio.setEnabled(false);
		inteligente.setEnabled(false);
		gameView.enable();
	}

	public void bloquear (){
		reiniciar.setEnabled(false);
		aleatorio.setEnabled(false);
		inteligente.setEnabled(false);
		gameView.enable();
	}
	public void desbloquear(){
		reiniciar.setEnabled(true);
		aleatorio.setEnabled(true);
		inteligente.setEnabled(true);
		gameView.disable();
	}
}