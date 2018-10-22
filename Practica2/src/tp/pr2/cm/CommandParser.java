package tp.pr2.cm;


/**
 * Tiene como atributos el array de comandos
 * @author salva
 *
 */
public class CommandParser {
	private final static Command[] commands = {
		new Help(), new Quit(), new Reset(),
		new Replace(0), new Run(), new AddByteCodeProgram()};
	
	/**
	 * Muestra la que hacen todos los comandos
	 */
	public static void showHelp(){
		for (int i = 0; i < CommandParser.commands.length;i++){
			System.out.println(CommandParser.commands[i].textHelp());
		}
	}
	
	/**
	 * Parsea la parte comun de los comandos, los separa y comprueba que tenga de longitud 1 u 2
	 * y despues cada parsea asi mismo su parte, devuelve el comando
	 * @param line
	 * @return
	 */
	public static Command parse(String line) {
	line = line.trim();
	String[] arrayLine = line.split(" +");
	 if (arrayLine.length==0 || arrayLine.length>2){
		 return null;
	 }
	 Command cm = null;
	 for (Command c:commands) {
	cm	= c.parse(arrayLine);
	if (cm!=null) {
		return cm;
		} 	
	} 
	return cm;
	}
}