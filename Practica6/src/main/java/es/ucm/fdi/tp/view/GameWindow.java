package es.ucm.fdi.tp.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;

import es.ucm.fdi.tp.base.Utils;
import es.ucm.fdi.tp.base.console.ConsolePlayer;
import es.ucm.fdi.tp.base.model.GameAction;
import es.ucm.fdi.tp.base.model.GamePlayer;
import es.ucm.fdi.tp.base.model.GameState;
import es.ucm.fdi.tp.base.player.ConcurrentAiPlayer;
import es.ucm.fdi.tp.mvc.GameEvent;
import es.ucm.fdi.tp.mvc.GameObserver;

public class GameWindow<S extends GameState <S,A>,A extends GameAction <S,A>> extends JFrame implements GameObserver <S,A> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int playerId;
	private S estado;
	private GamePlayer JugadorRandom;
	private ConcurrentAiPlayer JugadorSmart;
	private GamePlayer consolePlayer;
	private GamePlayer Jugador;
	private int maxThreads;
	private int millis;
	GuiController<S, A> gameCtrl;


	private GameView<S,A> gameView;
	private ColorChooser colorChooser;
	private Map<Integer, Color> colors;
	private JTextArea StatusMessage;
	private JButton reiniciar;
	private JButton aleatorio;
	private JButton inteligente;
	private JButton stopHebra;
	private JLabel Brain;
	private boolean acabado = false;
	private JSpinner SpinnerBrain;
	private JSpinner SpinnerTimer;

	private Thread hebra;

	enum TiposJugadores{
		manual,
		smart,
		random;
	}
	private JComboBox<TiposJugadores> comboJugadores;

	public GameWindow(int playerId,GamePlayer randPlayer,GamePlayer smartPlayer,GameView<S, A> gameView,GuiController<S, A> gameCtrl){
		super("(view for player " + playerId + ")");
		this.playerId = playerId;
		this.JugadorRandom = randPlayer;
		this.JugadorRandom.join(playerId);
		this.JugadorSmart = (ConcurrentAiPlayer) smartPlayer;
		this.JugadorSmart.join(playerId);
		this.gameView = gameView;
		this.gameCtrl = gameCtrl;
		this.setLocation(25,25);
		this.setSize(750, 639);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.maxThreads = Runtime.getRuntime().availableProcessors(); // Número de hebras máximo
		this.millis = 5000;// Número de tiempo máximo
		this.JugadorSmart.setMaxThreads(maxThreads);
		this.JugadorSmart.setTimeout(millis);

		this.consolePlayer = new ConsolePlayer("Jugador " + playerId, new Scanner(System.in));
		this.consolePlayer.join(playerId);
		//JComboBox<TiposJugadores> comboJugadores;
		//---
		gameView.setController(this.gameCtrl);
		this. aleatorio = new JButton();
		aleatorio.setIcon(new ImageIcon(Utils.loadImage("dice.png")));
		aleatorio.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() { 
						gameCtrl.makeMov(JugadorRandom.requestAction(estado));
					}
				});
			}
		});

		this. inteligente = new JButton();

		inteligente.setIcon(new ImageIcon(Utils.loadImage("nerd.png")));
		inteligente.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				jugadaConcurrente();
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
						interrumpirHebra();
						gameCtrl.start();
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

		this.comboJugadores = new JComboBox<TiposJugadores>(TiposJugadores.values());
		this.comboJugadores.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						CogerJugadorCombo();
						gameCtrl.information();
					}
				});
			}
		});


		JLabel espacio = new JLabel("                          ");
		JLabel modo = new JLabel("  Player Mode:  ");
		JToolBar PanelNorte = new JToolBar();
		PanelNorte.add(aleatorio);
		PanelNorte.add(inteligente);
		PanelNorte.add(reiniciar);
		PanelNorte.add(salir);
		PanelNorte.add(modo);
		PanelNorte.add(comboJugadores);
		PanelNorte.add(espacio);
		PanelNorte.setBorder(BorderFactory.createTitledBorder("Button Panel"));

		//----------------------------------------------------------


		JPanel PanelNorteSmart = new JPanel();

		JButton ContarSmart = new JButton();
		this.stopHebra = ContarSmart;
		ContarSmart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						interrumpirHebra();
					}
				});
			}
		});
		this.stopHebra.setIcon(new ImageIcon(Utils.loadImage("stop.png")));
		this.Brain = new JLabel("");
		this.Brain.setOpaque(true);
		this.Brain.setIcon(new ImageIcon(Utils.loadImage("brain.png")));
		JLabel Timer = new JLabel();
		Timer.setIcon(new ImageIcon(Utils.loadImage("timer.png")));

		SpinnerNumberModel modelBrain = new SpinnerNumberModel( 
				new Integer(Runtime.getRuntime().availableProcessors()), // Dato visualizado al inicio en el spinner 
				new Integer(1), // Límite inferior 
				new Integer(Runtime.getRuntime().availableProcessors()), // Límite superior 
				new Integer(1) // incremento-decremento 
				); 

		this.SpinnerBrain = new JSpinner(modelBrain); 
		/*
		spinnerBrain.addChangeListener(new ChangeListener() {      
			@Override
			public void stateChanged(ChangeEvent arg0) {
				maxThreads = (Integer)(spinnerBrain.getValue());
				JugadorSmart.setMaxThreads(maxThreads);
				gameCtrl.information();
			}
		});
		 */

		SpinnerNumberModel modelTimer = new SpinnerNumberModel( 
				new Integer(5000), // Dato visualizado al inicio en el spinner 
				new Integer(500), // Límite inferior 
				new Integer(5000), // Límite superior 
				new Integer(500) // incremento-decremento 
				); 
		this.SpinnerTimer = new JSpinner(modelTimer);

		/*spinnerTimer.addChangeListener(new ChangeListener() {      
			@Override
			public void stateChanged(ChangeEvent arg0) {
				millis = (Integer)(spinnerTimer.getValue());
				JugadorSmart.setTimeout(millis);
				gameCtrl.information();
			}
		});*/

		JLabel ns = new JLabel("ns");

		PanelNorteSmart.add(this.Brain);
		PanelNorteSmart.add(this.SpinnerBrain);
		PanelNorteSmart.add(Timer);
		PanelNorteSmart.add(this.SpinnerTimer);
		PanelNorteSmart.add(ns);
		PanelNorteSmart.add(this.stopHebra);
		PanelNorteSmart.setBorder(BorderFactory.createTitledBorder("Smart Moves"));

		//--------------------------------------------------------------------

		JPanel PanelNorteFinal = new JPanel();
		PanelNorteFinal.add(PanelNorte);
		PanelNorteFinal.add(PanelNorteSmart);


		this.Jugador = consolePlayer;


		// En estre trozo de código nos encargaremos de la parte derecha de la vista
		this.StatusMessage = new JTextArea(20,20);
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
					gameView.update(GameWindow.this.estado);
					if (GameWindow.this.estado.getTurn() != GameWindow.this.playerId){ // lo he añadido porque me he dado cuenta de que si no 
						// al cambiar los colores podría cometerse un fallo
						bloquear();
					}
				}

			}

		});
		PanelEste2.add(table);
		PanelEste2.setBorder(BorderFactory.createTitledBorder("PlayerInformation"));
		PanelEste.add(PanelEste1);
		PanelEste.add(PanelEste2);


		// En este trozo de código nos encargaremos de la parte central de la vista
		JPanel PanelCentral = new JPanel();
		PanelCentral.add(this.gameView);

		// añadimos los subpaneles al panel
		JPanel PanelPrincipal = new JPanel();
		PanelPrincipal.setLayout(new BorderLayout());
		this.add(PanelNorteFinal,BorderLayout.NORTH);
		//this.add(PanelNorteSmart,BorderLayout.NORTH);
		this.add(PanelEste,BorderLayout.EAST);
		this.add(PanelCentral,BorderLayout.CENTER);
		gameView.setVentanaPrincipal(this);
		gameCtrl.addObserver(this);
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
		//this.estado = e.getState();
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
					interrumpirHebra();
					comboJugadores.setSelectedItem(consolePlayer);
					comboJugadores.setSelectedIndex(0);
					Jugador = consolePlayer;
					StatusMessage.setText(e.toString() );
					acabado = false;
					gameView.update(estado);
					;break;
				case Change:
					StatusMessage.setText(StatusMessage.getText()+ "\n" +"* " + e.getAction());
					StatusMessage.setText(StatusMessage.getText() + "\n" + "Turno del jugador " + estado.getTurn() );
					if (estado.getTurn()==playerId){
						StatusMessage.setText(StatusMessage.getText() + "\n" + "Tu turno" );
					}
					gameView.update(estado);
					;break;
				case Stop:
					System.exit(0);
					break;
				case Error:
					if (estado.getTurn() == playerId){
						JOptionPane.showMessageDialog(null, " No puedes realizar esa opción" , 
								"Error" ,JOptionPane.ERROR_MESSAGE);
					}
				}
				gameView.update(estado);

				if (!estado.isFinished()){
					if (estado.getTurn()!= playerId){
						bloquear();
					} else {
						desbloquear();
					}
					 CogerJugadorCombo(); // esto es si queremos que siga en smart despues de interrumpir la hebra y ejecutar el movimiento manual
					if (playerId == estado.getTurn() && !estado.isFinished() &&(Jugador.equals(JugadorRandom) || Jugador.equals(JugadorSmart))){
						bloquear(); // utilizo esto porque se bloquean los mismos elementos que cuando se utiliza la hebra
						ejecuta(Jugador);
					}
					CogerJugadorCombo();
				} else {	
					if (!acabado){
						finalizar();
						acabado = true;
					} else {
						bloquear();
					}
				}
			}
		});
	}
	public void ejecuta(GamePlayer Jugador){
		if (Jugador.equals(JugadorSmart)){
			jugadaConcurrente();
		} else {
			if (this.hebra == null || !this.hebra.isAlive()){
				this.gameCtrl.makeMov(JugadorRandom.requestAction(estado));
			}
		}
	}

	public Color getColor(int jugador){
		if( this.colors.get(jugador) == null){
			this.colors.put(jugador,new Color((int)(Math.random() * 0x1000000)));
		}
		this.repaint();
		return this.colors.get(jugador);
	}
	public void finalizar (){
		if (this.estado.getWinner()==-1){
			JOptionPane.showMessageDialog(null, "HABEIS EMPATADO", 
					"Jugador "+this.playerId ,JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (this.estado.getWinner()==this.playerId){
				JOptionPane.showMessageDialog(null, "HAS GANADO", 
						"Jugador "+playerId ,JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "HAS PERDIDO ", 
						"Jugador "+ playerId ,JOptionPane.INFORMATION_MESSAGE);
			}
		}
		aleatorio.setEnabled(false);
		inteligente.setEnabled(false);
		gameView.enable();
	}

	public void bloquear (){
		//reiniciar.setEnabled(false);
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

	public void jugadaConcurrente(){
		// No sé porque cuando se me interrumpe la hebra sigue ejecutando la acción
		maxThreads = (Integer)(this.SpinnerBrain.getValue());
		JugadorSmart.setMaxThreads(maxThreads);
		millis = (Integer)(this.SpinnerTimer.getValue());
		JugadorSmart.setTimeout(millis);
		bloquear();
		this.Brain.setBackground(Color.YELLOW);
		if (this.hebra == null){
			this.hebra = new Thread(()->{
				long time0 = System.currentTimeMillis();
				A action = this.JugadorSmart.requestAction(estado);
				long time1 = System.currentTimeMillis();
				if (action != null){
					SwingUtilities.invokeLater(() -> {
						this.gameCtrl.makeMov(action);
						this.StatusMessage.setText(this.StatusMessage.getText()+ "\n" +
								" se ha evaluado " + this.JugadorSmart.getEvaluationCount() + " nodos  en " + "\n" + 
								(time1 - time0)+ " milisegundos" + " \n");
						this.Brain.setBackground(null);
					});
				} else {
					SwingUtilities.invokeLater(() -> {
						if (Jugador != this.JugadorRandom && this.Jugador != this.JugadorSmart && this.estado.getTurn() == this.playerId){
							desbloquear();
						}
					});
				}
				try {
					SwingUtilities.invokeAndWait(() -> {
						this.hebra = null;
					});
				} catch (InvocationTargetException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			this.hebra.start();// siempre que haga una hebra acabar así
		} 
	}
	public void interrumpirHebra(){
		if (this.hebra != null && this.hebra.isAlive()){
			this.hebra.interrupt();
			this.Jugador = this.consolePlayer;
			//this.comboJugadores.setSelectedIndex(0);// Para que se me ponga en jugador manual
			this.Brain.setBackground(null);
			if (this.Jugador != this.JugadorRandom && this.Jugador != this.JugadorSmart){
				desbloquear();
			}
		}
	}
	public void CogerJugadorCombo(){
		if (comboJugadores.getSelectedIndex() == 0){
			Jugador = consolePlayer;
		}
		else if (comboJugadores.getSelectedIndex() == 1){
			Jugador = this.JugadorSmart;
		} else {
			Jugador = this.JugadorRandom;	
		}
	}
}