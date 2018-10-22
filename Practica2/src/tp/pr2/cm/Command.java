package tp.pr2.cm;

import tp.pr2.mv.Engine;
/**
 * Clase abstracta con 3 metodos abstractos
 * @author salva
 *
 */
abstract public  class Command {
	abstract public  boolean execute(Engine engine);
	abstract public String textHelp();
	abstract public Command parse(String[ ] s);
}
