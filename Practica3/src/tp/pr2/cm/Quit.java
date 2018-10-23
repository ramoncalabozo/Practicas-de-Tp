package tp.pr2.cm;




import tp.pr2.mv.Engine;

public class Quit implements Command {

	@Override
	/**
	 * Llama al executeQuit de engine
	 */
	public void execute(Engine engine) {
		  engine.executeQuit();
	}

	@Override
	/**
	 * muestra lo que hace el comando
	 */
	public String textHelp() {
		return "QUIT: Cierra la aplicacion " +
				System.getProperty("line.separator");
}

	@Override
	/**
	 * parsea el comando, si la longitud del array de string es uno y es Quit, lo crea y lo devuelve
	 */
	public Command parse(String[] s) {
		if (s.length==1 && s[0].equalsIgnoreCase("QUIT"))
			return new Quit();
			else return null;
	}

}
