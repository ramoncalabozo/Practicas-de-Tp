package tp.pr1.mv;

public enum ENUM_COMMANDO {
	HELP, QUIT, NEWINST(1), RUN, RESET, REPLACE(1);
	private int replace;// s�lo para el replace
	private ByteCode instruction;// s�lo para el Newinst

	ENUM_COMMANDO(int replace) {
		this.replace = replace;
	}

	ENUM_COMMANDO(ByteCode instruction) {
		this.instruction = instruction;
	}

	ENUM_COMMANDO() {

	}
}
	