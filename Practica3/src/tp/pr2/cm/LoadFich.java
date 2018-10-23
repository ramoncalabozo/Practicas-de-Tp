package tp.pr2.cm;

import tp.pr2.exceptions.ArrayException;

//CREO QUE ESTA CLASE YA ESTA

import tp.pr2.mv.Engine;


public class LoadFich implements Command {
		private String fichero;
		public LoadFich (String fichero){
			this.fichero = fichero;
		}
	@Override
	/**
	 * llama al executeHelp de engine
	 */
	public void execute(Engine engine)throws java.io.FileNotFoundException,ArrayException {
		 engine.load(this.fichero);
	}

	@Override
	/**
	 * muestra lo que hace el comando
	 */
	public String textHelp() {
		return "Load: Carga el fichero como programa fuente " + System.lineSeparator();
	}

	@Override
	/**
	 * parsea el array de string, si su longitud es 1 y es Help, lo crea y lo devuelve
	 */
	public Command parse(String[] s) {
		if (s.length==2 && s[0].equalsIgnoreCase("LOAD"))
			return new LoadFich(s[1]);
			else return null;
	}

}
