package tp.pr1.mv;

public class CommandParser {

	public static Command parse(String line) {
		String lines;
		Command comando = null;
		lines = line.trim();// Hago que el String line le quito los espacios en blanco
					// iniciales
		String[] arrayLine = lines.split(" +");//Lo divido en un array quitando los espacios de entre medias
		int replace = 0;
		ENUM_BYTECODE instruction = null;
		if (arrayLine.length < 4) {
			if (arrayLine.length == 1) {// si la longitud del array es 1 y no es ninguno de estos enumerados entonces el comando es null
				if (!arrayLine[0].equalsIgnoreCase("RUN")
						&& !arrayLine[0].equalsIgnoreCase("HELP")
						&& !arrayLine[0].equalsIgnoreCase("QUIT")
						&& !arrayLine[0].equalsIgnoreCase("RESET")
						&& !arrayLine[0].equalsIgnoreCase("NEWINST")) {
					comando = null;
				} else {
					if (arrayLine[0].equalsIgnoreCase("RUN")) {
						comando = new Command(ENUM_COMMANDO.RUN);
					} else if (arrayLine[0].equalsIgnoreCase("HELP")) {
						comando = new Command(ENUM_COMMANDO.HELP);
					} else if (arrayLine[0].equalsIgnoreCase("QUIT")) {
						comando = new Command(ENUM_COMMANDO.QUIT);
					} else if (arrayLine[0].equalsIgnoreCase("NEWINST")) {
						//ENUM_BYTECODE instruccion = null;
						comando = new Command(ENUM_COMMANDO.NEWINST,instruction,0);
					} else {
						comando = new Command(ENUM_COMMANDO.RESET);
					}
				}
			} else if (arrayLine.length == 2) {// si la longitud del array es 2 y no es ninguno de estos enumerados entonces el comando es null
				if (!arrayLine[0].equalsIgnoreCase("NEWINST")
						&& !arrayLine[0].equalsIgnoreCase("REPLACE")) {
					comando = null;
				} else {
					if (arrayLine[0].equalsIgnoreCase("REPLACE")) {
						replace = Integer.parseInt(arrayLine[1]);
						comando = new Command(ENUM_COMMANDO.REPLACE, replace);
					} else {
						String s = arrayLine[1];
						if (s.equalsIgnoreCase("Push")){
							comando = new Command(ENUM_COMMANDO.NEWINST,instruction,0);
						}
						else{
						if (ByteCodeParser.parse(s) == null) {
							comando = null;
						} else {
							instruction = ENUM_BYTECODE.valueOf(arrayLine[1].toUpperCase());
							comando = new Command(ENUM_COMMANDO.NEWINST,instruction, -1);
							}
						}
					}
				}
			} else if (arrayLine.length == 3) {
				if (!arrayLine[0].equalsIgnoreCase("NEWINST")) {
					comando = null;
				} else {
					String s = arrayLine[1] + " " + arrayLine[2];
					if (ByteCodeParser.parse(s) == null) {
						comando = null;
					} else {
						int param = Integer.valueOf(arrayLine[2]);
						instruction = ENUM_BYTECODE.valueOf(arrayLine[1].toUpperCase());
						comando = new Command(ENUM_COMMANDO.NEWINST,instruction, param);
					}
				}
			} else {
				comando = null;
			}
		}
		return comando;
	}
  /*
   Verifica si existe ese comando y si es así lo crea
   */
}
